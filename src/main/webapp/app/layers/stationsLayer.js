define([
    'leaflet',
    'markers/subwayMarker',
    'models/stationsCollection',
    'layers/StationsCollectionLayer',
    'layers/DoubleLayer'
], function(
    L,
    SubwayMarker,
    stationsCollection,
    StationsCollectionLayer,
    DoubleLayer
) {
    var topMarkerSet = {
        subway: L.Marker.extend({
            options: {
                icon: L.divIcon({
                    iconSize: [8, 8],
                    className: 'subwayTopIcon'
                })
            }
        })
    };
    var bottomMarkerSet = {
        subway: SubwayMarker
    };

    var stationLayers = {
        top: new StationsCollectionLayer({
            collection: stationsCollection,
            markerSet: topMarkerSet
        }),
        bottom: new StationsCollectionLayer({
            collection: stationsCollection,
            markerSet: bottomMarkerSet
        })
    }

    var doubleLayer = new DoubleLayer({
        collection: stationsCollection,
        zoomThreshold: 12,
        topLayer: stationLayers['top'],
        bottomLayer: stationLayers['bottom']
    });

    for (type in stationLayers) {
        stationLayers[type].on('stationclick', function(le) {
            doubleLayer.fire('stationclick', {
                stationId: le.stationId
            });
        });
    }
    return doubleLayer;
})
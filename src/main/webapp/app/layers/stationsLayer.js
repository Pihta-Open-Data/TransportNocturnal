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
        '0': L.Marker.extend({
            options: {
                icon: L.divIcon({
                    iconSize: [8, 8],
                    className: 'subwayTopIcon'
                })
            }
        }),
        '1': L.Marker.extend({
            options: {
                icon: L.divIcon({
                    iconSize: [8, 8],
                    className: 'busTopIcon'
                })
            }
        })
    };
    var bottomMarkerSet = {
        '0': SubwayMarker,
        '1': L.Marker.extend({
            options: {
                icon: L.divIcon({
                    iconSize: [25, 25],
                    className: 'busBottomIcon'
                })
            }
        })
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
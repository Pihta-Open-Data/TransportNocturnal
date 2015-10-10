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
        subway: SubwayMarker
    }

    var stationLayers = {
        top: new L.FeatureGroup(),
        bottom: new StationsCollectionLayer({
            collection: stationsCollection,
            markerSet: topMarkerSet
        })
    }

    var doubleLayer = new DoubleLayer({
        collection: stationsCollection,
        zoomThreshold: 13,
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
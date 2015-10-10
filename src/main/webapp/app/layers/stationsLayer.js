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

    var topLayer = new L.FeatureGroup();
    var bottomLayer = new StationsCollectionLayer({
        collection: stationsCollection,
        markerSet: topMarkerSet
    });

    return new DoubleLayer({
        collection: stationsCollection,
        zoomThreshold: 13,
        topLayer: topLayer,
        bottomLayer: bottomLayer
    });
})
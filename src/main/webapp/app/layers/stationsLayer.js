define(['lib/leaflet/dist/leaflet-src', 'markers/subwayMarker', 'layers/subwayData'], function(L, SubwayMarker, subwayData) {
    var layer = new L.FeatureGroup();
    for (var i = 0; i < subwayData.length; i++) {
        var marker = new SubwayMarker(subwayData[i].coords);
        marker.addTo(layer);
    }
    return layer;
})
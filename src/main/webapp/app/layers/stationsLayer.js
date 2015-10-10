define(['leaflet', 'markers/subwayMarker', 'models/stations'], function(L, SubwayMarker, stationsCollection) {
    var layer = new L.FeatureGroup();
    for (var i = 0; i < stationsCollection.length; i++)(function(i) {
        var station = stationsCollection.at(i);
        var marker = new SubwayMarker(station.get('latLng'));
        marker.on('click', function() {
            layer.fire('stationclick', {
                stationId: station.get('id')
            });
        });
        marker.addTo(layer);
    })(i);
    return layer;
})
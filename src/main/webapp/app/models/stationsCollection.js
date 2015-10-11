define(['backbone', 'leaflet', 'models/StationModel', 'config', 'models/StationTime'], function(Backbone, L, StationModel, config, StationTime) {
    var StationsCollection = Backbone.Collection.extend({
        initialize: function() {

        }
    });

    var stationsCollection = new StationsCollection();

    $.ajax(config.serverRoot + 'stations/').then(function(stations) {
        for (var i = 0; i < stations.length; i++) {
            stationsCollection.add(new StationModel({
                latLng: L.latLng(stations[i].latitude, stations[i].longitude),
                title: stations[i].name,
                id: stations[i].id,
                type: stations[i].stationType,
                openTime: new StationTime(stations[i].openTime[0], stations[i].openTime[1]),
                closeTime: new StationTime(stations[i].closeTime[0], stations[i].closeTime[1])
            }));
        }
        stationsCollection.trigger('upd');
    });

    return stationsCollection;
});
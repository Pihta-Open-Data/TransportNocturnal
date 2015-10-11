define(['backbone', 'leaflet', 'models/StationModel', 'config'], function(Backbone, L, StationModel, config) {
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
                id: stations[i].id
            }));
        }
        stationsCollection.trigger('upd');
    });

    return stationsCollection;
});
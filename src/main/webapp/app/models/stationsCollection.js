define(['backbone', 'leaflet', 'models/StationModel', 'configPromise'], function(Backbone, L, StationModel, configPromise) {
    var StationsCollection = Backbone.Collection.extend({
        initialize: function() {

        }
    });

    var stationsCollection = new StationsCollection();

    configPromise.then(function(config) {
        $.ajax(config.serverRoot + 'stations/').then(function(stations) {
            for (var i = 0; i < stations.length; i++) {
                stationsCollection.add(new StationModel({
                    latLng: L.latLng(stations[i].latitude, stations[i].longitude),
                    title: stations[i].name,
                    id: stations[i].id
                }));
            }
            stationsCollection.trigger('upd');
        })
    });

    return stationsCollection;
});
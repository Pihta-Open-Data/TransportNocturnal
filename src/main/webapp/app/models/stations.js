define(['backbone', 'models/Station', 'dummies/subwayData'], function(Backbone, StationModel, subwayData) {
    var StationsCollection = Backbone.Collection.extend({
        initialize: function() {
            for (var i = 0; i < subwayData.length; i++) {
                this.add(new StationModel({
                    id: 'subway-' + _.uniqueId(),
                    title: subwayData[i].title,
                    latLng: L.latLng(subwayData[i].coords)
                }));
            }
        }
    });

    return new StationsCollection();
});
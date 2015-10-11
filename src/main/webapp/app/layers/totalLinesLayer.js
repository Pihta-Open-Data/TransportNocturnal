define([
    'leaflet',
    'models/totalLinesCollection',
    'models/stationsCollection',
    'layers/FeatureCollectionLayer'
], function(
    L,
    totalLinesCollection,
    stationsCollection,
    FeatureCollectionLayer
) {
    var LinesLayer = FeatureCollectionLayer.extend({
        initialize: function(options) {
            FeatureCollectionLayer.prototype.initialize.apply(this, arguments);
            this.options.stationsCollection.on('upd', this.update.bind(this));
        },
        update: function() {
            this.clear();
            for (var i = 0; i < this.options.collection.length; i++)(function(i) {
                var lineModel = this.options.collection.at(i);
                var first = this.options.stationsCollection.findWhere({
                    id: lineModel.get('first')
                });
                var second = this.options.stationsCollection.findWhere({
                    id: lineModel.get('second')
                });
                if (first && second) {
                    var polyline = L.polyline([
                        first.get('latLng'),
                        second.get('latLng')
                    ]);
                    this._features.push(polyline);
                    polyline.addTo(this);
                }
            }.bind(this))(i);
        }
    });

    return new LinesLayer({
        collection: totalLinesCollection,
        stationsCollection: stationsCollection
    })
});
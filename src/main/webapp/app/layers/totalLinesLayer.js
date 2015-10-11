define([
    'leaflet',
    'models/totalLinesCollection',
    'models/stationsCollection',
    'layers/FeatureCollectionLayer',
    'config'
], function(
    L,
    totalLinesCollection,
    stationsCollection,
    FeatureCollectionLayer,
    config
) {
    var linesStyles = config.stationLinesStyles;

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
                    if (first.get('type') === second.get('type')) {
                        var style = first.get('type') + '';
                        if (lineModel.get('transfer')) {
                            style += '-transfer'
                        }
                    }
                    var polyline = L.polyline([
                        first.get('latLng'),
                        second.get('latLng')
                    ], linesStyles[style]);
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
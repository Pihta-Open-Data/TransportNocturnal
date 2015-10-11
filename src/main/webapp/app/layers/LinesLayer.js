define(['leaflet', 'layers/FeatureCollectionLayer', 'config'], function(L, FeatureCollectionLayer, config) {
    var linesStyles = config.stationLinesStyles;
    return FeatureCollectionLayer.extend({
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

                    var k = 0.75;
                    var debugPolyline = L.polyline([
                        L.latLng({
                            lat: second.get('latLng').lat + (first.get('latLng').lat - second.get('latLng').lat) * k,
                            lng: second.get('latLng').lng + (first.get('latLng').lng - second.get('latLng').lng) * k
                        }),
                        first.get('latLng')
                    ], {
                        color: '#ffff00'
                    });
                    var fg = L.featureGroup([polyline, debugPolyline]);
                    this._features.push(fg);
                    fg.addTo(this);
                }
            }.bind(this))(i);
        }
    });
})
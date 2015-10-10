define(['leaflet'], function(L) {
    // options.collection
    // options.markerSet
    return L.FeatureGroup.extend({
        initialize: function(options) {
            L.setOptions(this, options);
            L.FeatureGroup.prototype.initialize.apply(this, arguments)
            this.options.collection.on('upd', this.update.bind(this));
            this._features = [];
            this.update();
        },
        update: function() {
            this.clear();
            for (var i = 0; i < this.options.collection.length; i++)(function(i) {
                var station = this.options.collection.at(i);
                var marker = new this.options.markerSet['subway'](station.get('latLng'));
                marker.on('click', function() {
                    this.fire('stationclick', {
                        stationId: station.get('id')
                    });
                }.bind(this));
                this._features.push(marker);
                marker.addTo(this);
            }.bind(this))(i);
        },
        clear: function() {
            while (this._features.length) {
                var marker = this._features.pop();
                marker.off('click');
                this.removeLayer(marker);
            }
        }
    });
});
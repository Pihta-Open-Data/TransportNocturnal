define(['leaflet'], function(L) {
    return L.FeatureGroup.extend({
        initialize: function(options) {
            L.setOptions(this, options);
            L.FeatureGroup.prototype.initialize.apply(this, arguments)
            this.options.collection.on('upd', this.update.bind(this));
            this._features = [];
            this.update();
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
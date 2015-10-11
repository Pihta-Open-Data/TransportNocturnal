define(['leaflet', 'layers/FeatureCollectionLayer'], function(L, FeatureCollectionLayer) {
    // options.collection
    // options.markerSet
    return FeatureCollectionLayer.extend({
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
        }
    });
});
define(['leaflet', 'markers/subwayMarker', 'models/stationsCollection'], function(L, SubwayMarker, stationsCollection) {
    var StationsLayer = L.FeatureGroup.extend({
        initialize: function(options) {
            L.FeatureGroup.prototype.initialize.call(this);
            this.collection = options.collection;
            this._features = [];
            this.collection.on('upd', this.update.bind(this));
            this.update();
        },
        update: function() {
            this.clear();
            for (var i = 0; i < this.collection.length; i++)(function(i) {
                var station = this.collection.at(i);
                var marker = new SubwayMarker(station.get('latLng'));
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

    return new StationsLayer({
        collection: stationsCollection 
    });
})
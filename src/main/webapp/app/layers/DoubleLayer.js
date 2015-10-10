define(['leaflet'], function(L) {
    return L.Class.extend({
        includes: [L.Mixin.Events],
        initialize: function(options) {
            L.setOptions(this, options);
            this._activeLayer = null;
            this._onMapZoomEndBound = this._onMapZoomEnd.bind(this);
        },
        onAdd: function(map) {
            this._map = map;
            this._map.on('zoomend', this._onMapZoomEndBound);
            this._switchLayers();
        },
        onRemove: function() {
            this._activeLayer && this._map.removeLayer(this._activeLayer);
            this._map.off('zoomend', this._onMapZoomEndBound);
        },
        _onMapZoomEnd: function() {
            this._switchLayers();
        },
        _switchLayers: function() {
            this._activeLayer && this._map.removeLayer(this._activeLayer);
            this._activeLayer = this._map.getZoom() > this.options.zoomThreshold ? 
                this.options.bottomLayer : 
                this.options.topLayer;
            this._map.addLayer(this._activeLayer);
        }
    });
})
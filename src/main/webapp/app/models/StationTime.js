define(['leaflet'], function(Leaflet) {
    return L.Class.extend({
        initialize: function(h, m) {
            this.setHours(h);
            this.setMinutes(m);
        },
        setHours: function(h) {
            this._hours = h;
        },
        setMinutes: function(m) {
            this._minutes = m;
        },
        getHours: function() {
            return this._hours;
        },
        getMinutes: function() {
            return this._minutes;
        }
    });
});
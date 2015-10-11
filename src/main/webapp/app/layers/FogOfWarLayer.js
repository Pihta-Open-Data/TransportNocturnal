define(['leaflet', 'maskcanvas'], function(L, MaskCanvas) {
    // options.linesCollection
    // options.stationsCollection
    // options.fogOfWarOptions
    return MaskCanvas.extend({
        initialize: function(options) {
            MaskCanvas.prototype.initialize.call(this, options);
            console.log(this.options);

            this.options.linesCollection.on('upd', this.update.bind(this));
            this.options.stationsCollection.on('upd', this.update.bind(this));
            this.update();
        },
        update: function() {
            var data = [];
            var d = this.options.lineDelta;
            var linesCollection = this.options.linesCollection;
            var stationsCollection = this.options.stationsCollection;
            if (!stationsCollection.length || !linesCollection.length) {
                return;
            }
            // for (var i = 0; i < stationsCollection.length; i++) {
            //     data.push(stationsCollection.at(i).get('latLng'));
            // }
            for (var i = 0; i < linesCollection.length; i++) {
                var start = stationsCollection.findWhere({
                    id: linesCollection.at(i).get('first')
                }).get('latLng');
                var end = stationsCollection.findWhere({
                    id: linesCollection.at(i).get('second')
                }).get('latLng');
                var k = (end.lng - start.lng) / (end.lat - start.lat);
                var startLng, endLng, currLng;
                if (start.lng < end.lng) {
                    startLng = start.lng;
                    endLng = end.lng;
                } else {
                    startLng = end.lng;
                    endLng = start.lng;
                }
                currLng = d;
                while (currLng < Math.abs(endLng - startLng)) {
                    currLng += d;
                    data.push([
                        currLng / k + start.lat,
                        currLng + start.lng
                    ]);
                }
            }console.log(data.length);
            this.setData(data);
        }
    });
});
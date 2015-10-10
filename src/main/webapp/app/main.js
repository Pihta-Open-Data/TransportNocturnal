requirejs.config({
    shim: {
        'lib/leaflet.maskcanvas/src/QuadTree': {
            exports: 'QuadTree'
        },
        'lib/leaflet.maskcanvas/src/L.TileLayer.MaskCanvas': {
            deps: ['lib/leaflet/dist/leaflet-src', 'lib/leaflet.maskcanvas/src/QuadTree'],
            exports: 'L.TileLayer.MaskCanvas'
        }
    }
})

define(['map', 'lib/leaflet.maskcanvas/src/L.TileLayer.MaskCanvas'], function(map, MaskCanvas) {
    var coverageLayer = new MaskCanvas({
        opacity: 0.5,
        radius: 50,
        useAbsoluteRadius: false
    });

    coverageLayer.setData([
        [55.74860083639357, 37.61223077774048],
        [55.74860083639357, 37.61223077774048],
        [55.75425242402284, 37.60231733322143],
        [55.755616894047215, 37.61585712432861],
        [55.75189769534975, 37.62838840484619],
        [55.75599120933865, 37.64270067214966]
    ]);

    map.addLayer(coverageLayer);
    //map.fitBounds(coverageLayer.bounds);
});
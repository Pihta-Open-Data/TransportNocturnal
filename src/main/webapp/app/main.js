requirejs.config({
    paths: {
        'leaflet': 'lib/leaflet/dist/leaflet-src'
    },
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

define(['map', 'layers/stationsLayer'], function(map, stationsLayer) {
    stationsLayer.addTo(map);
});
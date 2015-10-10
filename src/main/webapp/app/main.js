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

define(['map', 'layers/subwayLayer'], function(map, subwayLayer) {
    subwayLayer.addTo(map)
});
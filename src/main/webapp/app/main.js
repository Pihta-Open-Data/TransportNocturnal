requirejs.config({
    paths: {
        'leaflet': 'lib/leaflet/dist/leaflet-src',
        'underscore': 'lib/underscore/underscore',
        'backbone': 'lib/backbone/backbone',
        'jquery': 'lib/jquery/dist/jquery'
    },
    shim: {
        'underscore': {
            exports: '_'
        },
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'lib/leaflet.maskcanvas/src/QuadTree': {
            exports: 'QuadTree'
        },
        'lib/leaflet.maskcanvas/src/L.TileLayer.MaskCanvas': {
            deps: ['lib/leaflet/dist/leaflet-src', 'lib/leaflet.maskcanvas/src/QuadTree'],
            exports: 'L.TileLayer.MaskCanvas'
        }
    }
})

define(['map', 'layers/stationsLayer', 'models/stations'], function(map, stationsLayer, stationsModel) {
    stationsLayer.addTo(map);
    stationsLayer.on('stationclick', function(le) {
        console.log(le.stationId);
    })
});
requirejs.config({
    paths: {
        'text': 'lib/requirejs-plugins/lib/text',
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

define([
    'map',
    'layers/stationsLayer',
    'models/stations',
    'modalsManager',
    'views/DepartureDialog',
    'models/stations'
], function(
    map,
    stationsLayer,
    stationsModel,
    modalsManager,
    DepartureDialog,
    stationsCollection
) {
    stationsLayer.addTo(map);
    stationsLayer.on('stationclick', function(le) {
        var departureDialog = new DepartureDialog({
            model: stationsCollection.findWhere({id:le.stationId})
        });
        modalsManager.setModal(departureDialog)
    });
    map.on('click', function() {
        modalsManager.destroyModal();
    });
});
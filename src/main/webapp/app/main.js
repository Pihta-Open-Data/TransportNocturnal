requirejs.config({
    paths: {
        'text': 'lib/requirejs-plugins/lib/text',
        'json': 'lib/requirejs-plugins/src/json',
        'leaflet': 'lib/leaflet/dist/leaflet-src',
        'underscore': 'lib/underscore/underscore',
        'backbone': 'lib/backbone/backbone',
        'jquery': 'lib/jquery/dist/jquery',
        'quadTree': 'lib/leaflet.maskcanvas/src/QuadTree',
        'maskcanvas': 'lib/leaflet.maskcanvas/src/L.TileLayer.MaskCanvas'
    },
    shim: {
        'underscore': {
            exports: '_'
        },
        'backbone': {
            deps: ['underscore', 'jquery'],
            exports: 'Backbone'
        },
        'quadTree': {
            exports: 'QuadTree'
        },
        'maskcanvas': {
            deps: ['leaflet', 'quadTree'],
            exports: 'L.TileLayer.MaskCanvas'
        }
    }
})

define([
    'map',
    'config',
    'models/stationsCollection',
    'layers/stationsLayer',
    'layers/totalLinesLayer',
    'models/totalLinesCollection',
    'modalsManager',
    'views/DepartureDialog',
    'models/stationsCollection',
    'layers/FogOfWarLayer',
    'layers/LinesLayer',
    'models/linesCollectionFactory'
], function(
    map,
    config,
    stationsCollection,
    stationsLayer,
    totalLinesLayer,
    totalLinesCollection,
    modalsManager,
    DepartureDialog,
    stationsCollection,
    FogOfWarLayer,
    LineLayer,
    linesCollectionFactory
) {
    map.addLayer(stationsLayer);
    map.addLayer(totalLinesLayer);

    var fogOfWarLayer = new FogOfWarLayer({
        radius: 700,
        zIndex: 100000,
        lineDelta: 0.001,
        linesCollection: totalLinesCollection,
        stationsCollection: stationsCollection
    });
    window.map = map;
    window.fwl = fogOfWarLayer;
    fogOfWarLayer.addTo(map);

    stationsLayer.on('stationclick', function(le) {
        var departureDialog = new DepartureDialog({
            model: stationsCollection.findWhere({
                id: le.stationId
            })
        });
        departureDialog.on('confirm', function(model, hours, minutes) {
            linesCollectionFactory($.ajax(config.serverRoot + 'lines?stationId=' + model.get('id') + '&hours=' + hours + '&minutes=' + minutes)).then(function(collection) {
                map.removeLayer(totalLinesCollection);
                map.addLayer(new LineLayer({
                    collection: collection,
                    stationsCollection: stationsCollection
                }))
                modalsManager.destroyModal();
            }).fail(function(err) {
                console.log('fail ' + err);
            });

        });
        departureDialog.on('cancel', function() {
            modalsManager.destroyModal();
        });
        modalsManager.setModal(departureDialog);
    });

    map.on('click', function() {
        modalsManager.destroyModal();
    });
});
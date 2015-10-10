define(['jquery'], function($) {
    var def = $.Deferred();
    $.ajax('/config.json').then(function(config) {
        $.ajax('/localConfig.json').then(function(localConfig) {
            def.resolve($.extend(config, localConfig));
        }).fail(function() {
            def.resolve(config);
        })
    }).fail(function() {
        def.reject('error loading config');
    })
    return def.promise();
});
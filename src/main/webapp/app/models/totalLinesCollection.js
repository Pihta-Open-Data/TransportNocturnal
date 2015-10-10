define(['backbone', 'configPromise'], function(Backbone, configPromise) {
    var LinesCollection = Backbone.Collection.extend({
        initialize: function() {

        }
    });

    configPromise.then(function(config) {
        $.ajax(config.serverRoot + 'lines').then(function(lines) {
            console.log(lines);
        }).fail(function() {
            console.log('error loading lines');
        })
    });

    return new LinesCollection();
});
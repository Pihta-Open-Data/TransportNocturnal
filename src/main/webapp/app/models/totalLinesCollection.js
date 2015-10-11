define(['backbone', 'configPromise'], function(Backbone, configPromise) {
    var LinesCollection = Backbone.Collection.extend({
        initialize: function() {

        }
    });

    var linesCollection = new LinesCollection();

    configPromise.then(function(config) {
        $.ajax(config.serverRoot + 'lines').then(function(lines) {
            for (var i = 0; i < lines.length; i++) {
                linesCollection.add(new Backbone.Model({
                    first: lines[i].first,
                    second: lines[i].second
                }));
            }
            linesCollection.trigger('upd');
        }).fail(function() {
            console.log('error loading lines');
        })
    });

    return linesCollection;
});
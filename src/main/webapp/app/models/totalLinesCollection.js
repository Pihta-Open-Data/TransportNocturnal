define(['backbone', 'config'], function(Backbone, config) {
    var LinesCollection = Backbone.Collection.extend({
        initialize: function() {

        }
    });

    var linesCollection = new LinesCollection();

    $.ajax(config.serverRoot + 'lines').then(function(lines) {
        for (var i = 0; i < lines.length; i++) {
            linesCollection.add(new Backbone.Model({
                first: lines[i].first,
                second: lines[i].second,
                transfer: lines[i].transfer
            }));
        }
        linesCollection.trigger('upd');
    }).fail(function() {
        console.log('error loading lines');
    });

    return linesCollection;
});
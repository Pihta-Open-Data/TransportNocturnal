define(['backbone', 'config', 'jquery'], function(Backbone, config, $) {
    return function(q) {
        var def = $.Deferred();

        var LinesCollection = Backbone.Collection.extend({
            initialize: function() {

            }
        });

        var linesCollection = new LinesCollection();

        q.then(function(lines) {
            for (var i = 0; i < lines.length; i++) {
                linesCollection.add(new Backbone.Model({
                    first: lines[i].first,
                    second: lines[i].second,
                    transfer: lines[i].transfer
                }));
            }
            def.resolve(linesCollection);
            linesCollection.trigger('upd');
        }).fail(function() {
            def.reject('err');
        });

        return def.promise();
    }
});
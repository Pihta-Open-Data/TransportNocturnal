define(['backbone'], function(Backbone) {
    return Backbone.View.extend({
        appendTo: function(el) {
            el.appendChild(this.el);
        }
    })
});
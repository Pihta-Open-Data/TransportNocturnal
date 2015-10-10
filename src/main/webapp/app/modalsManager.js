define(['leaflet', 'layoutManager'], function(L, layoutManager) {
    return new (L.Class.extend({
        initialize: function(options) {
            this.el = options.el;
        },
        destroyModal: function() {
            this.modal = null;
            this.el.innerHTML = '';
        },
        setModal: function(modal) {
            this.destroyModal();
            this.modal = modal;
            this.modal.appendTo(this.el);
            this.modal.on('destroy', function() {
                this.destroyModal();
            }.bind(this));
        }
    }))({
        el: layoutManager.getModalsContainer()
    })
});
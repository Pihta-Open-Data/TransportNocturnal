define(['views/BaseView', 'text!views/departureDialog.html'], function(BaseView, departureDialogTemplate) {
    return BaseView.extend({
        className: 'departureDialog',
        events: {
            'click .departureDialog-confirmButton': '_onConfirmButtonClick',
            'click .departureDialog-closeButton': '_onCloseButtonClick'
        },
        initialize: function(options) {
            this.render();
        },
        render: function() {
            this.$el.html(_.template(departureDialogTemplate)({
                stationName: this.model.get('title')
            }));
            this.model.get('closeTime') && this.setHours(this.model.get('closeTime').getHours());
            this.model.get('closeTime') && this.setMinutes(this.model.get('closeTime').getMinutes())
            return this;
        },
        setHours: function(h) {
            this.$el.find('.departureDialog-departureHours').val(this._pz(h));
        },
        setMinutes: function(m) {
            this.$el.find('.departureDialog-departureMinutes').val(this._pz(m));
        },
        getHours: function(h) {
            return this.$el.find('.departureDialog-departureHours').val();
        },
        getMinutes: function(m) {
            return this.$el.find('.departureDialog-departureMinutes').val();
        },
        _pz: function(s) {
            var s = s + '';
            if (s.length === 1) {
                return '0' + s;
            }
            return s;
        },
        _onConfirmButtonClick: function() {
            this.trigger('confirm', this.model, this.getHours(), this.getMinutes());
        },
        _onCloseButtonClick: function() {
            this.trigger('cancel');
        }
    })
});
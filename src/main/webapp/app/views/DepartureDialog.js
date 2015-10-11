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
            return this;
        },
        _onConfirmButtonClick: function() {
            this.trigger('confirm');
        },
        _onCloseButtonClick: function() {
            this.trigger('cancel');
        }
    })
});
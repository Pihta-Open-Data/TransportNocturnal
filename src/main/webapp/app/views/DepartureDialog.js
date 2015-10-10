define(['views/BaseView', 'text!views/departureDialog.html'], function(BaseView, departureDialogTemplate) {
    return BaseView.extend({
        className: 'departureDialog',
        initialize: function(options) {
            this.render();
        },
        render: function() {
            this.$el.html(_.template(departureDialogTemplate)({
                stationName: this.model.get('title')
            }));
            return this;
        }
    })
});
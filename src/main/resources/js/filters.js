KanbanApp.Filters = {

    jodaDate: function (date) {
        return new Date(date[0], date[1] - 1, date[2], date[3], date[4], date[5]);
    },

    friendlyDate: function (date) {
        var newDate = angular.copy(date);
        --newDate[1];
        return moment(newDate).fromNow();
    }
}
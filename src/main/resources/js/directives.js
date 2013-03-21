KanbanApp.Directives = {

    issuesColumn: {
        link: function postLink(scope, iElement, iAttrs) {
            setTimeout(function () {
                var offset = $('.states .issues').offset();
                //$('.states .issues').height();
                var $scroll = $('.states .state .issues').slimScroll({
                    height: ($('.states').height() - offset.top - 10) + 'px'
                });

                $('.issues').sortable({
                    connectWith: '.issues',
                    receive: function (event, ui) {
                        var stateId = ui.item.closest('ul').data('stateId');
                        var issueId = ui.item.data('issueId');
                        console.log('[RECEIVE]: ', 'issueId:' + issueId + ', stateId:', stateId);
                        scope.changeState(issueId, stateId);
                        /*console.log($(this).sortable('toArray', {key: 'issue'}));
                         console.log('----------------------');*/
                    },
                    update: function (event, ui) {
                        if (false && !ui.sender) {
                            console.log('[UPDATE]: ', ui.item, ui.sender);
                            console.log(event);
                            console.log($(this).sortable('toArray', {key: 'issue'}));
                            console.log('----------------------');
                        }
                    }
                }).disableSelection();
            }, 0);
        }
    }

}
;
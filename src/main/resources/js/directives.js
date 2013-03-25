KanbanApp.Directives = {

    notify: {
        link: function postLink(scope, iElement, iAttrs) {
            setTimeout(function () {
                KanbanApp.Notify = (function () {

                    $(function () {
                        console.log($("#notify-container"));
                        $("#notify-container").notify();
                    });

                    var showStringError = function (message, title) {
                        $("#notify-container")
                            .notify("create", "error-template", { title: title, text: message });
                    }

                    var showArrayError = function (messages, title) {
                        var message = '<ul><li>';
                        message += messages.join('</li><li>');
                        message += '</li></ul>';

                        showStringError(message, title);
                    }

                    return {
                        error: function (message, title) {
                            if (angular.isArray(message)) {
                                showArrayError(message, title);
                            } else {
                                showStringError(message, title);
                            }
                        }
                    }

                })();
            }, 0);
        }
    },

    issuesColumn: {
        link: function postLink(scope, iElement, iAttrs) {
            setTimeout(function () {
                $states = $('.states .state');
                var width = 0;
                $states.each(function () {
                    width += Math.ceil($(this).width()) + 1;
                });
                $('.states').width(width + 5);

                var offset = $('.states .issues').offset();
                var height = $('.states').height() - offset.top - 20;
                //console.log($('.states').height(), offset.top);
                var $scroll = $('.states .state .issues').slimScroll({
                    height: height + 'px'
                });

                /*$('.states-container').slimScrollHorizontal({
                 width: ($('.states-container').width()) + 'px',
                 height: ($('.states').height() + 10) + 'px',
                 touchScrollStep: 0,
                 wheelStep: 0
                 });*/

                $('.issues').sortable({
                    connectWith: '.issues',
                    receive: function (event, ui) {
                        var $state = ui.item.closest('ul');
                        var stateId = $state.data('stateId');
                        var issueId = ui.item.data('issueId');
                        console.log('[RECEIVE]: ', 'issueId:' + issueId + ', stateId:', stateId);
                        scope.changeState(issueId, stateId);

                        $state.trigger('mouseover'); // for slimscroll

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
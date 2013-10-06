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
                console.log('issuesColumn!!!');
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
                        console.debug('[RECEIVE]: ', 'issueId:' + issueId + ', stateId:', stateId);
                        scope.changeState(issueId, stateId);

                        $state.trigger('mouseover'); // for slimscroll

                        console.debug($(this).sortable('toArray', {key: 'issue'}));
                        console.debug('----------------------');
                    },
                    update: function (event, ui) {
                        scope.changeOrder($(this).sortable('toArray', {key: 'issue'}));
                        console.debug('[UPDATE]: ', ui.item, ui.sender);
                        console.debug(event);
                        console.debug($(this).sortable('toArray', {key: 'issue'}));
                        console.debug('----------------------');
                    }
                }).disableSelection();
            }, 0);
        }
    },

    modalFormResetOnHidden: {
        link: function postLink(scope, iElement, iAttrs) {
            setTimeout(function () {
                console.log(iElement);
                $(iElement).on('hidden', function () {
                    $(this).find('form').get(0).reset();
                });
            }, 0);
        }
    }

};
(function () {
    angular.module('app')
        .controller('AdminMessageController', AdminMessageController);

    AdminMessageController.$inject = ['AdminMessageService', 'NotificationService', 'AuthenticationService'];

    function AdminMessageController(AdminMessageService, NotificationService, AuthenticationService) {

        var vm = this;
        // vm.selectCommentToReport = selectCommentToReport;
        vm.saveAdminMessage = saveAdminMessage;
        vm.postReply = postReply;
        vm.markAsRead = markAsRead;
        vm.deleteAdminMessage = deleteAdminMessage;
        vm.trash = trash;
        vm.newMessage = {};
        vm.adminMessages = AdminMessageService.adminMessages;
        vm.username = AuthenticationService.currentUsername;
        vm.number = AdminMessageService.number;
        vm.reply  = {};
        vm.notification = {};
        vm.adminResponseMessage = '';
        vm.clearMessages = clearMessages;
        
        function clearMessages() {
        	vm.adminResponseMessage = '';
        }

        getAdminMessages();

        setInterval(function () {
            getAdminMessages();
        }, 10000);

        function getAdminMessages() {
            if (AuthenticationService.currentUsername !== null) {
                AdminMessageService.getAdminMessages().then(function (response) {
                    vm.number = AdminMessageService.number;
                    if (vm.adminMessages.length !== response.length) {
                        vm.adminMessages = response;
                        AdminMessageService.adminMessages = vm.adminMessages;
                        vm.number = 0;
                        for (var i = 0; i < vm.adminMessages.length; i++) {
                            if (vm.adminMessages[i].readState === false) {
                                vm.number++;
                            }
                        }
                        AdminMessageService.number = vm.number;
                    }
                });
            }
        }

        function markAsRead() {
            vm.number = 0;
            AdminMessageService.number = vm.number;
            for (var i = 0; i < AdminMessageService.adminMessages.length; i++) {
                if (AdminMessageService.adminMessages[i].readState === false) {
                    AdminMessageService.adminMessages[i].readState = true;
                    AdminMessageService.saveAdminMessage(AdminMessageService.adminMessages[i]).then(function (response) {
                    });
                }
            }
        }

        
        function saveAdminMessage(adminMessage) {
            AdminMessageService.saveAdminMessage(adminMessage).then(function (response) {
                getAdminMessages();
            })
        }
        
        function postReply(adminMessage) {
            vm.notification.type = "notification_admin";
            vm.notification.sender = vm.username;
            vm.notification.reciver = adminMessage.sentBy;
            vm.notification.message = adminMessage.reply;
            vm.notification.read = false;
            vm.notification.trashed = false;
            vm.adminResponseMessage = 'Your message is sent.';
            console.log(vm.adminResponseMessage);
            NotificationService.saveNotification(vm.notification).then(function (response) {
            })
        }
        
        function deleteAdminMessage(adminMessage) {
            AdminMessageService.deleteAdminMessage(adminMessage.id).then(function () {
                getAdminMessages();
            })
        }
        
        function trash(adminMessage) {
        	vm.adminResponseMessage = '';
            adminMessage.trashed = true;
            AdminMessageService.saveAdminMessage(adminMessage);
            getAdminMessages();
        }
    }

})();
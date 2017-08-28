(function () {
    angular.module('app')
        .controller('AdminMessageController', AdminMessageController);

    AdminMessageController.$inject = ['AdminMessageService'];

    function AdminMessageController(AdminMessageService) {

        var vm = this;
        // vm.selectCommentToReport = selectCommentToReport;
        vm.saveAdminMessage = saveAdminMessage;
        vm.adminMessages = [];
        vm.newMessage = {};

        getAdminMessages();

        function getAdminMessages() {
            AdminMessageService.getAdminMessages().then(function (response) {
                vm.adminMessages = response;
                console.log(response);
            })
        }

        
        function saveAdminMessage(adminMessage) {
            console.log(adminMessage);
            AdminMessageService.saveAdminMessage(adminMessage).then(function (response) {
                getAdminMessages();
            })
        }
    }

})();
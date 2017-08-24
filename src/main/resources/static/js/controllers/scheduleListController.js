(function () {
    angular.module('app')
        .controller('ScheduleListController', ScheduleListController);

    ScheduleListController.$inject = [ 'ScheduleListService', 'AuthenticationService'];

    function ScheduleListController(ScheduleListService, AuthenticationService) {

        var vm = this;
        vm.username = AuthenticationService.currentUsername;
        vm.scheduleList = [];

        getUserScheduleList();

        function getUserScheduleList(){
            ScheduleListService.getUserScheduleList(vm.username).then(function (response) {
                vm.scheduleList = response.data;
            })
        }

    }

})();


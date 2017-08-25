(function () {
    angular.module('app')
        .controller('ScheduleListController', ScheduleListController);

    ScheduleListController.$inject = [ 'ScheduleListService', 'AuthenticationService', 'WatchlistService'];

    function ScheduleListController(ScheduleListService, AuthenticationService, WatchlistService) {

        var vm = this;
        vm.saveSchedule = saveSchedule;
        vm.deleteSchedule = deleteSchedule;
        vm.selectSchedule = selectSchedule;
        vm.getUserWatchlist = getUserWatchlist;
        vm.username = AuthenticationService.currentUsername;
        vm.selectedSchedule = {}
        vm.newSchedule = {};
        vm.newSchedule.scheduledDateTime = new Date(vm.newSchedule.scheduledDateTime);
        vm.scheduleList = [];
        vm.userWatchlist = [];
        vm.hours;
        vm.minutes;

        getUserScheduleList();
        getUserWatchlist();

        function getUserScheduleList(){
            ScheduleListService.getUserScheduleList(vm.username).then(function (response) {
                vm.scheduleList = response.data;
            })
        }

        function saveSchedule(schedule){
            console.log("klik klak");
            console.log(schedule);
            schedule.scheduledDateTime.setHours(vm.hours);
            schedule.scheduledDateTime.setMinutes(vm.minutes);
            ScheduleListService.saveSchedule(schedule).then(function (response) {
                console.log(response);
                getUserScheduleList();
                vm.hours = null;
                vm.minutes = null;
            })
        }
        
        function deleteSchedule(schedule) {
            ScheduleListService.deleteSchedule(schedule.id).then(function () {
                getUserScheduleList();
            })
        }
        
        function selectSchedule(schedule) {
            vm.selectedSchedule = angular.copy(schedule);
            vm.selectedSchedule.scheduledDateTime = new Date(vm.selectedSchedule.scheduledDateTime);
            vm.hours = vm.selectedSchedule.scheduledDateTime.getHours();
            vm.minutes = vm.selectedSchedule.scheduledDateTime.getMinutes();
            console.log(vm.selectedSchedule);
        }

        function getUserWatchlist(){
            WatchlistService.getUserWatchlist(vm.username).then(function (response) {
                vm.userWatchlist = response;
                console.log(response);
            })
        }

        vm.openCalendar = openCalendar;
        vm.datePickerOptions = {
            formatYear: 'yy',
            // maxDate : new Date()
        };

        vm.popupCalendar = {
            opened: false
        };

        function openCalendar() {
            vm.popupCalendar.opened = true;
        }

    }

})();


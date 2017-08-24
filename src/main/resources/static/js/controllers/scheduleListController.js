(function () {
    angular.module('app')
        .controller('ScheduleListController', ScheduleListController);

    ScheduleListController.$inject = [ 'ScheduleListService', 'AuthenticationService'];

    function ScheduleListController(ScheduleListService, AuthenticationService) {

        var vm = this;
        vm.saveSchedule = saveSchedule;
        vm.deleteSchedule = deleteSchedule;
        vm.selectSchedule = selectSchedule;
        vm.username = AuthenticationService.currentUsername;
        vm.selectedSchedule = {}
        vm.newSchedule = {};
        vm.scheduleList = [];
        vm.hours = {};
        vm.minutes = {};

        getUserScheduleList();

        function getUserScheduleList(){
            ScheduleListService.getUserScheduleList(vm.username).then(function (response) {
                vm.scheduleList = response.data;
            })
        }

        function saveSchedule(schedule){
            console.log("klik klak")
            schedule.scheduledDateTime.setHours(vm.hours);
            schedule.scheduledDateTime.setMinutes(vm.minutes);
            ScheduleListService.saveSchedule(schedule).then(function (response) {
                console.log(response);
                getUserScheduleList();
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

        vm.openCalendar = openCalendar;
        vm.datePickerOptions = {
            formatYear: 'yy',
            maxDate : new Date()
        };

        vm.popupCalendar = {
            opened: false
        };

        function openCalendar() {
            vm.popupCalendar.opened = true;
        }

    }

})();


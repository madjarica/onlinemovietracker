(function () {
    angular.module('app')
        .controller('ScheduleListController', ScheduleListController);

    ScheduleListController.$inject = [ 'ScheduleListService', 'AuthenticationService', 'WatchlistService', '$location'];

    function ScheduleListController(ScheduleListService, AuthenticationService, WatchlistService, $location) {

        var vm = this;
        vm.saveSchedule = saveSchedule;
        vm.deleteSchedule = deleteSchedule;
        vm.selectSchedule = selectSchedule;
        vm.getUserWatchlist = getUserWatchlist;
        vm.createNewSchedule = createNewSchedule;
        vm.updateSchedule = updateSchedule;
        vm.username = AuthenticationService.currentUsername;
        vm.selectedSchedule = {};
        vm.newSchedule = {};
        vm.newSchedule.scheduledDateTime = new Date(vm.newSchedule.scheduledDateTime);
        vm.scheduleList = [];
        vm.userWatchlist = [];
        vm.hours;
        vm.minutes;
        vm.propertyName = 'watchlist.video.title';
        vm.reverse = false;
        vm.sortBy = sortBy;

        getUserScheduleList();
        getUserWatchlist();

        function sortBy(propertyName) {
            vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
            vm.propertyName = propertyName;
        }

        function createNewSchedule(watchlist) {
            vm.newSchedule.watchlist = watchlist;
        }

        function getUserScheduleList(){
            ScheduleListService.getUserScheduleList(vm.username).then(function (response) {
                vm.scheduleList = response.data;
                console.log(response);
            })
        }

        function wait(ms){
            var start = new Date().getTime();
            var end = start;
            while(end < start + ms) {
                end = new Date().getTime();
            }
        }

        function updateSchedule(schedule) {
            console.log(schedule);
            schedule.scheduledDateTime.setHours(vm.hours);
            schedule.scheduledDateTime.setMinutes(vm.minutes);
            ScheduleListService.saveSchedule(schedule).then(function (response) {
                getUserScheduleList();
                vm.hours = null;
                vm.minutes = null;
            }).then(function () {
                $location.url("schedule-list");
            });

        }

        function saveSchedule(schedule){
            console.log(schedule);
            schedule.scheduledDateTime.setHours(vm.hours);
            schedule.scheduledDateTime.setMinutes(vm.minutes);
            schedule.watchlistId = vm.newSchedule.watchlist.id;
            ScheduleListService.saveSchedule(schedule).then(function (response) {
                getUserScheduleList();
                vm.hours = null;
                vm.minutes = null;
            }).then(function () {
                $location.url("schedule-list");
            });
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
        }

        function getUserWatchlist(){
            WatchlistService.getUserWatchlist(vm.username).then(function (response) {
                vm.userWatchlist = response;
                console.log(response);
            })
        }

        vm.openCalendar = openCalendar;
        vm.datePickerOptions = {
            formatYear: 'yy'
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


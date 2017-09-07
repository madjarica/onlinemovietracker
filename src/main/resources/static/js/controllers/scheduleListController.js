(function () {
    angular.module('app')
        .controller('ScheduleListController', ScheduleListController);

    ScheduleListController.$inject = ['ScheduleListService', 'AuthenticationService', 'WatchlistService', '$location'];

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
        vm.newSchedule.scheduledDateTime = new Date();
        vm.scheduleList = [];
        vm.userWatchlist = [];
        vm.propertyName = 'watchlist.video.title';
        vm.reverse = false;
        vm.sortBy = sortBy;
        vm.mytime = new Date();

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
            ScheduleListService.saveSchedule(schedule).then(function (response) {
                getUserScheduleList();
            }).then(function () {
                $location.url("schedule-list");
            });

        }

        function saveSchedule(schedule){
            var hours = vm.mytime.getHours();
            var minutes = vm.mytime.getMinutes();
            schedule.scheduledDateTime.setHours(hours);
            schedule.scheduledDateTime.setMinutes(minutes);
            schedule.watchlistId = vm.newSchedule.watchlist.id;
            ScheduleListService.saveSchedule(schedule).then(function (response) {
                getUserScheduleList();
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


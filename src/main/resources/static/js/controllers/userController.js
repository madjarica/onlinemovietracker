(function () {
    angular.module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$filter', 'UserService', '$q'];

    function UserController($scope, $filter, UserService, $q) {

        var vm = this;
        vm.getNumber = getNumber;
        vm.editUser = editUser;
        vm.updateUser = updateUser;

        vm.active;
        vm.subscription;
        vm.status;
        vm.blockedUntil;
        vm.userOptions = {};

        vm.userOptions.active_options = [true, false];
        vm.userOptions.subscription_option = [true, false];
        vm.userOptions.status_option = [true, false];

        function updateUser(data) {
            UserService.updateUser(data.userId, data).then(function () {
                getUsers();
            })
        }

        function editUser(id) {
            UserService.getUserById(id).then(function (response) {
                vm.userOptions.userId = id;
                vm.userOptions.active = response.active;
                vm.userOptions.subscription = response.subscription;
                vm.userOptions.status = response.status;
                vm.userOptions.blockedUntil = response.blockedUntil;
            });
        }

        function getNumber(num) {
            return new Array(num);
        }

        vm.propertyName = 'id';
        vm.reverse = false;
        vm.getUsersData = getUsers();
        vm.usersData;
        vm.sortBy = sortBy;

        vm.openCalendar = openCalendar;
        vm.datePickerOptions = {
            formatYear: 'yy'
        };

        vm.popupCalendar = {
            opened: false
        };

        function openCalendar() {
            vm.popupCalendar.opened = true;
        }

        function sortBy(propertyName) {
            vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
            vm.propertyName = propertyName;
        }

        function getUserById(id) {
            UserService.getUserById().then(function (response) {
                console.log(response);
            })
        }

        function getUsers() {
            UserService.getUsers().then(function (response) {
                vm.usersData = response;
                // vm.filteredTables = makeTables(response);
                // console.log(vm.filteredTables);
            });
        }

        init();

        function init() {

        }
    }

})();
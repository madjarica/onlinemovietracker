(function () {
    angular.module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$filter', 'UserService', '$q'];

    function UserController($scope, $filter, UserService, $q) {

        var vm = this;
        vm.getNumber = getNumber;
        vm.editUser = editUser;

        vm.active;
        vm.subscription;
        vm.status;
        vm.blockedUntil;

        vm.active_options = [true, false];
        vm.subscription_option = [true, false];

        function editUser(id) {
            UserService.getUserById(id).then(function (response) {
                console.log(response);
                vm.active = response.active;
                vm.subscription = response.subscription;
                vm.status = response.status;
                vm.blockedUntil = response.blockedUntil;
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
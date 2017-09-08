(function () {
    angular.module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$filter', 'UserService', '$q', 'AuthenticationService'];

    function UserController($scope, $filter, UserService, $q, AuthenticationService) {

        var vm = this;
        vm.getNumber = getNumber;
        vm.editUser = editUser;
        vm.updateUser = updateUser;
        vm.selectUser = selectUser;
        vm.deleteUser = deleteUser;
        vm.username = AuthenticationService.currentUsername;

        vm.active;
        vm.subscription;
        vm.status;
        vm.blockedUntil;
        vm.userOptions = {};

        vm.userOptions.active_options = [true, false];
        vm.userOptions.subscription_option = [true, false];
        vm.userOptions.status_option = [true, false];
        vm.userOptions.role_option = [true, false];

        function updateUser(data) {
            UserService.updateUser(data.userId, data).then(function () {
                getUsers();
            })
        }

        function selectUser(id) {
            UserService.getUserById(id).then(function (response) {
                vm.userOptions.username = response.username;
                vm.userOptions.userId = id;
            });
        }

        function editUser(id) {
            UserService.getUserById(id).then(function (response) {
                vm.userOptions.userId = id;
                vm.userOptions.active = response.active;
                vm.userOptions.subscription = response.subscription;
                vm.userOptions.status = response.status;
                vm.userOptions.blockedUntil = response.blockedUntil;
                var role = response.roles;
                console.log(role);
                if(role.length == 2) { // user je admin
                    vm.userOptions.role = true;
                } else {
                    vm.userOptions.role = false;
                }
            });
        }
        
        function deleteUser(id) {
            UserService.deleteUserById(id).then(function (response) {
                getUsers();
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
                console.log(vm.usersData);
                // vm.filteredTables = makeTables(response);
                // console.log(vm.filteredTables);
            });
        }

        init();

        function init() {

        }
    }

})();
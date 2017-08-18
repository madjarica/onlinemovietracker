(function () {
    angular.module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$filter', 'UserService'];

    function UserController($scope, $filter, UserService) {

        var vm = this;

        vm.getUsersData = getUsers();

        function getUsers() {
            UserService.getUsers().then(function (response) {
                vm.usersList = response;
            });
        }

        init();

        function init() {

        }
    }

})();
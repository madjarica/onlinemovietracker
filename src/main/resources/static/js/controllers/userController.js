(function () {
    angular.module('app')
        .controller('UserController', UserController);

    UserController.$inject = ['$scope', '$filter', 'UserService', '$q'];

    function UserController($scope, $filter, UserService, $q) {

        var vm = this;
        vm.getNumber = getNumber;

        function getNumber(num) {
            return new Array(num);
        }

        vm.propertyName = 'id';
        vm.reverse = false;
        vm.getUsersData = getUsers();
        vm.usersData;
        vm.sortBy = sortBy;

        // vm.filteredTables = [];
        // vm.currentPage = 1;
        // vm.numOfPages = 0;
        // vm.numPerPage = 3;
        // vm.makeTables = makeTables;

        // function makeTables(table) {
        //     var def =  $q.defer();
        //     var number_of_elements = table.length;
        //     var number_of_tables = Math.ceil(number_of_elements / vm.numPerPage);
        //     var begin = 0;
        //     var end = vm.numPerPage;
        //     var pom_table = [];
        //     var pom_table_2 = [];
        //     vm.numOfPages = number_of_tables;
        //
        //     for(i = 0; i < number_of_tables; i++) {
        //         for(j = begin; j < end; j++) {
        //             pom_table.push(table[j]);
        //         }
        //         pom_table_2.push(pom_table);
        //
        //         begin = end;
        //         end = end + vm.numPerPage;
        //         pom_table = [];
        //     }
        //     return pom_table_2;
        // }

        function sortBy(propertyName) {
            vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
            vm.propertyName = propertyName;
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
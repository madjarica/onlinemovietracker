(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', '$http'];

    function HomeController($location, $http) {

        var vm = this;
        vm.isActive = isActive;

        init();

        function init() {

        }

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }
    }

})();
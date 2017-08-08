(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', '$http'];

    function HomeController($location, $http) {

        var vm = this;
        // rating
        vm.max = 10;
        vm.isReadonly = false;
        vm.hoveringOver = hoveringOver;
        vm.ratingStates = [{
            stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'
        }];

        // Navigation
        vm.isActive = isActive;

        init();

        function init() {

        }

        function hoveringOver(value) {
            vm.overStar = value;
            vm.percent = 100 * (value / vm.max);
        }

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }
    }

})();
(function () {
    angular.module('app')
        .controller('RatingController', RatingController);

    RatingController.$inject = ['$location', '$http', '$route'];

    function RatingController($location, $http, $route) {

        var vm = this;

        vm.max = 10;
        vm.isReadonly = false;
        vm.hoveringOver = hoveringOver;
        vm.ratingStates = [{
            stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'
        }];

        // Rating functions
        function hoveringOver(value) {
            vm.overStar = value;
            vm.percent = 100 * (value / vm.max);
        }
        // End of Rating functions

    }
})();
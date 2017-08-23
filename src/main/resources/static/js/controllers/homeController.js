(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'HomeService'];

    function HomeController($location, HomeService) {

        var vm = this;

        // Navigation
        vm.isActive = isActive;

        // TV Shows
        vm.getOnTv = getOnTv;
        vm.onTv = [];

        // In Theaters
        vm.getInTheaters = getInTheaters;
        vm.inTheaters = [];

        init();

        function init() {
            getOnTv();
            getInTheaters();
        }

        // On TV Functions
        function getOnTv(){
            HomeService.getOnTvFromService().then(function (response) {
                vm.onTv = response.results.slice(3,6);
            });
        }
        // End of On Tv Functions

        // In Theaters Functions
        function getInTheaters(){
            HomeService.getInTheatersService().then(function (response) {
                vm.inTheaters = response.results.slice(3,6);
            });
        }
        // End of In Theaters Functions

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }


    }
})();
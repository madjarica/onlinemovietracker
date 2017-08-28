(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'HomeService'];

    function HomeController($location, HomeService) {

        var vm = this;

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
                vm.onTv = response.results.slice(0,3);
            });
        }
        // End of On Tv Functions

        // In Theaters Functions
        function getInTheaters(){
            HomeService.getInTheatersService().then(function (response) {
                vm.inTheaters = response.results.slice(0,3);
            });
        }
        // End of In Theaters Functions

    }
})();
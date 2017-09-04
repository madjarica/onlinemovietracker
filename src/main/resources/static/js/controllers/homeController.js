(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'HomeService'];

    function HomeController($location, HomeService) {

        var vm = this;

        // TV Shows
        vm.getOnTv = getOnTv;
        vm.onTv = [];

        vm.getOnTvToday = getOnTvToday;
        vm.onTvToday = [];

        // In Theaters
        vm.getInTheaters = getInTheaters;
        vm.inTheaters = [];

        // Latest Three Movies
        vm.getLatestThreeTvShows = getLatestThreeTvShows;
        vm.latestThreeTvShows = [];

        // Latest Three Tv Shows
        vm.getLatestThreeMovies = getLatestThreeMovies;
        vm.latestThreeMovies = [];

        init();

        function init() {
            getOnTv();
            getInTheaters();
            getOnTvToday();
            getLatestThreeTvShows();
            getLatestThreeMovies();
        }

        // On TV Functions
        function getOnTv(){
            HomeService.getOnTvFromService().then(function (response) {
                vm.onTv = response.results.slice(0,10);
            });
        }
        // End of On Tv Functions

        // In Theaters Functions
        function getInTheaters(){
            HomeService.getInTheatersService().then(function (response) {
                vm.inTheaters = response.results.slice(0,10);
            });
        }
        // End of In Theaters Functions

        // On TV Today Functions
        function getOnTvToday(){
            HomeService.getOnTvTodayFromService().then(function (response) {
                vm.onTvToday = response.results.slice(0,10);
            });
        }
        // End of On TV Today Functions

        // On TV Today Functions
        function getLatestThreeTvShows(){
            HomeService.getLatestThreeTvShows().then(function (response) {
                vm.latestThreeTvShows = response;
            });
        }
        // End of On TV Today Functions

        // On TV Today Functions
        function getLatestThreeMovies(){
            HomeService.getLatestThreeMovies().then(function (response) {
                vm.latestThreeMovies = response;
            });
        }
        // End of On TV Today Functions

    }
})();
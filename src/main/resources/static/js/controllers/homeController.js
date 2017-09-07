(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'HomeService', 'WatchlistService'];

    function HomeController($location, HomeService, WatchlistService) {

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

        vm.getLatestThreeWatchlist = getLatestThreeWatchlist;
        vm.latestThreeWatchlists = [];

        vm.goToWatchlistCollection = goToWatchlistCollection;

        init();

        function init() {
            getOnTv();
            getInTheaters();
            getOnTvToday();
            getLatestThreeTvShows();
            getLatestThreeMovies();
            getLatestThreeWatchlist();
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
        function getLatestThreeWatchlist() {
            WatchlistService.getLatestThree().then(function (resposne) {
                vm.latestThreeWatchlists = resposne;
            })
        }
        // End of On TV Today Functions

        function goToWatchlistCollection(username) {
            console.log(username);
            WatchlistService.userOfCollection = username;
            $location.url("watchlist");
        }

    }
})();
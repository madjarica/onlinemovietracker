(function () {
    angular.module('app')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$location', '$http', '$route', 'SearchService'];

    function SearchController($location, $http, $route, SearchService) {

        var vm = this;

        vm.getMovies = getMovies;
        vm.movies = getMovies();

        vm.getTvShows = getTvShows;
        vm.tvShows = getTvShows();
        
        vm.getVideos = getVideos;
        vm.videos = getVideos();
        
        function getVideos() {
            SearchService.getVideos().then(handleSuccessVideos);
        }
        
        function handleSuccessVideos(data, status) {
            vm.videos = data;
        }

        function getMovies(){
            SearchService.getMovies().then(handleSuccessMovies);
        }

        function handleSuccessMovies(data, status){
            vm.movies = data;
        }

        function getTvShows(){
            SearchService.getTvShows().then(handleSuccessTvShows);
        }

        function handleSuccessTvShows(data, status){
            vm.tvShows = data;
        }

    }

})();


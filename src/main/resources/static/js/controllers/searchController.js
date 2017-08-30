(function () {
    angular.module('app')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$location', '$http', '$route', 'SearchService', 'MovieService', 'TvShowsService'];

    function SearchController($location, $http, $route, SearchService, MovieService, TvShowsService) {

        var vm = this;

        vm.propertyName = 'title';
        vm.reverse = false;
        vm.sortBy = sortBy;
        vm.getVideoByTitle = getVideoByTitle;
        function sortBy(propertyName) {
            vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
            vm.propertyName = propertyName;
        }

        vm.getMovieDetails = getMovieDetails;
        vm.getTvShowDetails = getTvShowDetails;

        // Show movie details
        function getMovieDetails(id) {
            console.log(id);
            MovieService.getMovieDetails(id).then(function (response) {
                MovieService.movieDetails = response;
                var runtime = MovieService.movieDetails.runtime;
                var hoursAndMinutes = Math.floor(runtime / 60) + 'h ' + Math.floor(runtime % 60) + 'min';
                MovieService.movieDetails.runtime = hoursAndMinutes;
            }).then(function () {
                $location.url("movie-details");
            })
        }

        // Show movie details
        function getTvShowDetails(id) {
            TvShowsService.getTvShowDetails(id).then(function (response) {
                TvShowsService.tvShowDetails = response;
            }).then(function () {
                $location.url("tv-show-details");
            })
        }
        // End of show movie details

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

        function getVideoByTitle(video) {
			SearchService.getVideoByTitle(video).then(function(response) {
				console.log(response);
				vm.videos = response;
			}, function(error) {
				vm.searchError = error;
			});
		}
    }

})();


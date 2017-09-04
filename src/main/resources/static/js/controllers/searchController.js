(function () {
    angular.module('app')
        .controller('SearchController', SearchController);

    SearchController.$inject = ['$location', '$http', '$route', 'SearchService', 'MovieService', 'TvShowsService', 'WatchlistService'];

    function SearchController($location, $http, $route, SearchService, MovieService, TvShowsService, WatchlistService) {

        var vm = this;

        vm.propertyName = 'title';
        vm.reverse = false;
        vm.sortBy = sortBy;
        vm.getVideoByTitle = getVideoByTitle;
        vm.goToDetailsPage = goToDetailsPage;
        vm.getAverageRatings = getAverageRatings;
        vm.selectVideo = {};
        vm.videos = {};
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
        
        function goToDetailsPage(video) {
            if (video.dtype === "Movie") {
                MovieService.movieDetails = video;
                SearchService.selectedVideo = video;
                $location.url('movie-details');
            } else if (video.dtype === "TvShow") {
                TvShowsService.tvShowDetails = video;
                SearchService.selectedVideo = video;
                $location.url('tv-show-details');
            }
        }
        
        function selectVideo(video) {
            vm.selectedVideo = video;
            console.log(vm.selectedVideo);
        }

        function getAverageRatings(video) {
            console.log("oi im heere")
            WatchlistService.getAverageRating(video.id).then(function (response) {
                video.averageRate = response;
            })
        }
    }

})();


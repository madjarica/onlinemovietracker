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
        vm.goToDetailsPage = goToDetailsPage;
        vm.selectVideo = {};
        vm.videos = {};
        function sortBy(propertyName) {
            vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
            vm.propertyName = propertyName;
        }

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
				console.log(click);
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
    }

})();


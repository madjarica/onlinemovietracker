(function () {
    angular.module('app')
        .controller('MovieController', MovieController)
        .config(function($sceProvider) {
            $sceProvider.enabled(false);
        });

    MovieController.$inject = ['$location', '$http', '$route', 'MovieService'];

    function MovieController($location, $http, $route, MovieService) {

        var vm = this;

        // Gallery
        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        vm.movie_images = MovieService.movieDetails.additionalBackdrops;
        var currIndex = 0;
        vm.addSlides = addSlides;

        if(vm.movie_images) {
            addSlides(vm.movie_images);
        }

        // Get Movies
        vm.getMovieByTitle = getMovieByTitle;
        vm.fillMovieData = fillMovieData;
        vm.getMovieDetails = getMovieDetails;
        vm.saveMovie = saveMovie;
        vm.movieObject = {};
        vm.movieDetails = MovieService.movieDetails;
        vm.movieObject.poster_path = "/img/default_poster.jpg";
        vm.movieObject.backdrop_path = "/img/default_backdrop.jpg";
        vm.movieList = [];

        // On List of Movies
        function getMovieByTitle(title) {
            MovieService.getMovieByTitle(title).then(function (response) {
                vm.movieList = response.results.slice(0,5);
            });
        }
        // End of List of Movies Functions

        // Show movie details
        function getMovieDetails(id) {
            MovieService.getMovieDetails(id).then(function (response) {
                MovieService.movieDetails = response;
                var runtime = MovieService.movieDetails.runtime;
                var hoursAndMinutes = Math.floor(runtime / 60) + 'h ' + Math.floor(runtime % 60) + 'min';
                MovieService.movieDetails.runtime = hoursAndMinutes;
            }).then(function () {
                $location.url("movie-details");
            })
        }
        // End of show movie details

        function fillMovieData(id) {
            $('#loading-spinner').removeClass('hidden');
            MovieService.getMovieByIdBackend(id).then(function (response) {
                console.log(response);
                $('#loading-spinner').addClass('hidden');
                vm.movieObject = response;
            }).then(MovieService.getMovieTrailer(id).then(function (videos) {
                    vm.movieObject.youtube = vm.movieObject.trailerLink;
            }));
        }

        function saveMovie(movie) {
            vm.movieObject = movie;
            console.log(vm.movieObject.genres);
            MovieService.saveMovie(movie).then(function (response) {
                vm.movieObject = response;
            })
        }

        // Gallery functions
        function addSlides(images) {
            for(var i = 0; i < images.length; i++) {
                vm.slides.push({
                    image: images[i],
                    id: currIndex++
                });
            }
        }
        // End of Gallery functions

    }

})();
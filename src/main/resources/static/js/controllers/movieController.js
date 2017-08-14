(function () {
    angular.module('app')
        .controller('MovieController', MovieController)
        .config(function($sceProvider) {
            $sceProvider.enabled(false);
        });

    MovieController.$inject = ['$location', '$http', '$route', 'MovieService'];

    function MovieController($location, $http, $route, MovieService) {

        var vm = this;

        // rating
        vm.max = 10;
        vm.isReadonly = false;
        vm.hoveringOver = hoveringOver;
        vm.ratingStates = [{
            stateOn: 'glyphicon-star', stateOff: 'glyphicon-star-empty'
        }];

        // gallery
        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        var currIndex = 0;
        vm.addSlides = addSlides;
        addSlides();

        // Get Movies
        vm.getMovieByTitle = getMovieByTitle;
        vm.fillMovieData = fillMovieData;
        vm.getMovieDetails = getMovieDetails;
        vm.saveMovie = saveMovie;
        vm.movieObject = {};
        vm.movieDetails = MovieService.movieDetails;
        vm.movieList = [];

        // On List of Movies
        function getMovieByTitle(title){
            MovieService.getMovieByTitle(title).then(function (response) {
                vm.movieList = response.results.slice(0,5);
            });
        }
        // End of List of Movies Functions

        // Show movie details
        function getMovieDetails(id) {
            MovieService.getMovieDetails(id).then(function (response) {
                MovieService.movieDetails = response;
            }).then(function () {
                $location.url("movie-details");
            })
        }
        // End of show movie details

        function fillMovieData(id) {
            MovieService.getMovieByIdBackend(id).then(function (response) {
                console.log(response);
                vm.movieObject = response;
            }).then(MovieService.getMovieTrailer(id).then(function (videos) {
                    vm.movieObject.youtube = vm.movieObject.trailerLink;
            }));
        }
        function saveMovie(movie) {
            console.log(vm.movieObject.genres);
            MovieService.saveMovie(movie).then(function (resposnse) {
                vm.movieObject = resposnse;
            })
            
        }

        // Rating functions
        function hoveringOver(value) {
            vm.overStar = value;
            vm.percent = 100 * (value / vm.max);
        }
        // End of Rating functions

        // Gallery functions
        function addSlides() {
            vm.slides.push(
                {
                    image: 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/fudEG1VUWuOqleXv6NwCExK0VLy.jpg',
                    id: currIndex++
                },
                {
                    image: 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/4yjJNAgXBmzxpS6sogj4ftwd270.jpg',
                    id: currIndex++
                },
                {
                    image: 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/9Xvb2PsWR7a1PM6b17bFlLYoTjR.jpg',
                    id: currIndex++
                },
                {
                    image: 'https://image.tmdb.org/t/p/w533_and_h300_bestv2/ywvdgEJyhA7kEwYxV32Hq3kmD9W.jpg',
                    id: currIndex++
                }
            );
        }
        // End of Gallery functions

    }

})();
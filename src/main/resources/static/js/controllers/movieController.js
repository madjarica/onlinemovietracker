(function () {
    angular.module('app')
        .controller('MovieController', MovieController)
        .config(function ($sceProvider) {
            $sceProvider.enabled(false);
        });

    MovieController.$inject = ['$location', '$http', '$scope', '$route', 'MovieService', 'WatchlistService', '$window', '$sce'];

    function MovieController($location, $http, $scope, $route, MovieService, WatchlistService, $window, $sce) {

        var vm = this;

        // Gallery
        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        vm.movie_images = MovieService.movieDetails.additionalBackdrops;
        var currIndex = 0;
        vm.addSlides = addSlides;

        if (vm.movie_images) {
            addSlides(vm.movie_images);
        }

        // Get Movies
        vm.getMovieByTitle = getMovieByTitle;
        vm.fillMovieData = fillMovieData;
        vm.getMovieDetails = getMovieDetails;
        vm.saveMovie = saveMovie;
        vm.fbshareCurrentPage = fbshareCurrentPage;
        vm.movieObject = {};
        vm.movieDetails = MovieService.movieDetails;
        vm.movieObject.poster_path = "/img/default_poster.jpg";
        vm.movieObject.backdrop_path = "/img/default_backdrop.jpg";
        vm.movieList = [];
        vm.shareFacebook = "";

        //Watchlist
        vm.checkFav = checkFav;
        vm.checkIfAdded = checkIfAdded;
        vm.favMovie = favMovie;
        vm.favourite = false;
        vm.userWatchlist = WatchlistService.userWatchlist;
        checkFav();

        // On List of Movies
        function getMovieByTitle(title) {
            MovieService.getMovieByTitle(title).then(function (response) {
                vm.movieList = response.results.slice(0, 5);
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

        function fbshareCurrentPage(imdb_link) {
            window.open("https://www.facebook.com/sharer/sharer.php?u="+escape(imdb_link)+"&t="+document.title, '',
            'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');
            return false;
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
            console.log(vm.movieObject.keywords);
            MovieService.saveMovie(movie).then(function (response) {
                MovieService.movieDetails = response;
            }).then(function () {
                vm.movieObject = {};
            }).then(function () {
                $location.url("movie-details");
            });
        }

        // Gallery functions
        function addSlides(images) {
            for (var i = 0; i < images.length; i++) {
                vm.slides.push({
                    image: images[i],
                    id: currIndex++
                });
            }
        }

        // End of Gallery functions


        //Watchlist favourite checking
        function checkFav() {
            if (vm.userWatchlist.length > 0) {
                for (var i = 0; i < vm.userWatchlist.length; i++) {
                    if (vm.userWatchlist[i].video.id === vm.movieDetails.id) {
                        vm.favourite = vm.userWatchlist[i].favourite;
                    }
                }
            }
        }

        function checkIfAdded() {
            if (vm.userWatchlist.length > 0) {
                for (var i = 0; i < vm.userWatchlist.length; i++) {
                    if (vm.userWatchlist[i].video.id === vm.movieDetails.id) {
                        return false;
                    }
                }
            }
            return true;
        }

        function favMovie(){
            if (vm.userWatchlist.length > 0) {
                for (var i = 0; i < vm.userWatchlist.length; i++) {
                    if (vm.userWatchlist[i].video.id === vm.movieDetails.id) {
                        if(vm.userWatchlist[i].favourite){
                            vm.favourite = false;
                            vm.userWatchlist[i].favourite = false;
                        }else {
                            vm.favourite = true;
                            vm.userWatchlist[i].favourite = true;
                        }
                        WatchlistService.saveWatchlist(vm.userWatchlist[i]).then(function (response) {
                            console.log(response);
                        })
                    }
                }
            }
        }

        //End of favourite checking


    }

})();
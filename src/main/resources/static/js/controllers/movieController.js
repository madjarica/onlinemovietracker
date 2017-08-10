(function () {
    angular.module('app')
        .controller('MovieController', MovieController);

    MovieController.$inject = ['$location', '$http', '$route', 'MovieService'];

    function MovieController($location, $http, $route, MovieService) {

        var vm = this;

        // Get Movies
        vm.getMovieByTitle = getMovieByTitle;
        vm.fillMovieData = fillMovieData;
        vm.movieObject = {};
        vm.movieList = [];

        // On List of Movies
        function getMovieByTitle(title){
            MovieService.getMovieByTitle(title).then(function (response) {
                vm.movieList = response.results.slice(0,5);
            });
        }
        // End of List of Movies Functions

        function fillMovieData(id) {
            MovieService.getMovieById(id).then(function (response) {
                vm.movieObject = response;
                vm.movieObject.imdb_id = 'http://www.imdb.com/title/' + vm.movieObject.imdb_id;
                vm.movieObject.poster = 'https://image.tmdb.org/t/p/w640' + vm.movieObject.poster_path;
                vm.movieObject.backdrop = 'https://image.tmdb.org/t/p/w1280' + vm.movieObject.backdrop_path;
            }).then(MovieService.getMovieTrailer(id).then(function (videos) {
                if(videos.results.length > 0) {
                    vm.movieObject.youtube = "http://www.youtube.com/embed/" + videos.results[0].key;
                }
            }));
        }

    }

})();
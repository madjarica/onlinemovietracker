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
            MovieService.getMovieByIdBackend(id).then(function (response) {
                console.log(response);
                vm.movieObject = response;
                vm.movieObject.imdb_id = vm.movieObject.imdb_id;
                vm.movieObject.poster =  vm.movieObject.poster_path;
                vm.movieObject.backdrop = vm.movieObject.backdrop_path;
            }).then(MovieService.getMovieTrailer(id).then(function (videos) {
                if(videos.results.length > 0) {
                    vm.movieObject.youtube = "http://www.youtube.com/embed/" + videos.results[0].key;
                }
            }));
        }

    }

})();
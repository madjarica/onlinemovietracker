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
            }).then(MovieService.getMovieTrailer(id).then(function (videos) {
                    vm.movieObject.youtube = vm.movieObject.trailerLink;
            }));
        }

    }

})();
(function () {
    angular.module('app')
        .controller('TvShowController', TvShowController);

    TvShowController.$inject = ['$location', '$http', '$route', 'TvShowsService'];

    function TvShowController($location, $http, $route, TvShowsService) {

        var vm = this;

        // Get Movies
        vm.getTvShowByTitle = getTvShowByTitle;
        vm.fillTvShowData = fillTvShowData;
        vm.saveTvShow = saveTvShow;
        vm.tvShowObject = {};
        vm.tvShowsList = [];

        // On List of Movies
        function getTvShowByTitle(title){
            TvShowsService.getTVShowByTitle(title).then(function (response) {
                vm.tvShowsList = response.results.slice(0,5);
            });
        }
        // End of List of Movies Functions

        function fillTvShowData(id) {
            TvShowsService.getTvShowByIdBackend(id).then(function (response) {
                console.log(response);
                vm.tvShowObject = response;
            })
        }
        function saveTvShow(tvShow) {
            console.log(vm.tvShowObject.genres);
            TvShowsService.saveTvShow(tvShow).then(function (resposnse) {
                vm.tvShowObject = resposnse;
            })

        }

    }

})();
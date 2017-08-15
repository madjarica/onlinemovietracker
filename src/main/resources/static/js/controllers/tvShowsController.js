(function () {
    angular.module('app')
        .controller('TvShowController', TvShowController)
        .config(function($sceProvider) {
            $sceProvider.enabled(false);
        });

    TvShowController.$inject = ['$location', '$http', '$route', 'TvShowsService'];

    function TvShowController($location, $http, $route, TvShowsService) {

        var vm = this;

        // Get Movies
        vm.getTvShowByTitle = getTvShowByTitle;
        vm.fillTvShowData = fillTvShowData;
        vm.saveTvShow = saveTvShow;
        vm.getTvShowDetails = getTvShowDetails;
        vm.tvShowObject = {};
        vm.tvShowDetails = TvShowsService.tvShowDetails;
        vm.tvShowsList = [];

        // On List of Movies
        function getTvShowByTitle(title){
            TvShowsService.getTVShowByTitle(title).then(function (response) {
                vm.tvShowsList = response.results.slice(0,5);
            });
        }
        // End of List of Movies Functions

        function fillTvShowData(id) {
            $('#loading-spinner').removeClass('hidden');
            TvShowsService.getTvShowByIdBackend(id).then(function (response) {
                $('#loading-spinner').addClass('hidden');
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

        // Show movie details
        function getTvShowDetails(id) {
            TvShowsService.getTvShowDetails(id).then(function (response) {
                TvShowsService.tvShowDetails = response;
            }).then(function () {
                $location.url("tv-show-details");
            })
        }
        // End of show movie details
    }

})();
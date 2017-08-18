(function () {
    angular.module('app')
        .controller('TvShowController', TvShowController)
        .config(function($sceProvider) {
            $sceProvider.enabled(false);
        });

    TvShowController.$inject = ['$location', '$http', '$route', 'TvShowsService'];

    function TvShowController($location, $http, $route, TvShowsService) {

        var vm = this;

        // Gallery
        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        vm.tv_show_images = TvShowsService.tvShowDetails.additionalBackdrops;
        var currIndex = 0;
        vm.addSlides = addSlides;

        if(vm.tv_show_images) {
            addSlides(vm.tv_show_images);
        }

        // Get Movies
        vm.getTvShowByTitle = getTvShowByTitle;
        vm.fillTvShowData = fillTvShowData;
        vm.saveTvShow = saveTvShow;
        vm.getTvShowDetails = getTvShowDetails;
        vm.tvShowObject = {};
        vm.tvShowDetails = TvShowsService.tvShowDetails;
        vm.tvShowEdit = {};
        vm.tvShowEdit = angular.copy(vm.tvShowDetails);
        vm.tvShowObject.poster_path = "img/default_poster.jpg";
        vm.tvShowObject.backdrop_path = "img/default_backdrop.jpg";
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
            tvShow.addedBy = "default";
            TvShowsService.saveTvShow(tvShow).then(function (response) {
                vm.tvShowDetails = response;
                TvShowsService.tvShowDetails = response;
            }).then(function(){
                vm.tvShowObject = {};
            }).then(function () {
                $location.url("tv-show-details");
            });
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

        // Gallery functions
        function addSlides(images) {
            for(var i = 0; i<images.length; i++){
                vm.slides.push({
                    image: images[i],
                    id: currIndex++
                });
            }
        }
        // End of Gallery functions

    }

})();
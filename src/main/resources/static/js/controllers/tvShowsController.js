(function () {
    angular.module('app')
        .controller('TvShowController', TvShowController)
        .config(function($sceProvider) {
            $sceProvider.enabled(false);
        });

    TvShowController.$inject = ['$location', '$http', '$route', 'TvShowsService'];

    function TvShowController($location, $http, $route, TvShowsService) {

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

        // Show movie details
        function getTvShowDetails(id) {
            TvShowsService.getTvShowDetails(id).then(function (response) {
                TvShowsService.tvShowDetails = response;
            }).then(function () {
                $location.url("tv-show-details");
            })
        }
        // End of show movie details

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
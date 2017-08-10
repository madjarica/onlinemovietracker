(function () {
    angular.module('app')
        .controller('HomeController', HomeController);

    HomeController.$inject = ['$location', 'HomeService'];

    function HomeController($location, HomeService) {

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

        // Navigation
        vm.isActive = isActive;

        // TV Shows
        vm.getOnTv = getOnTv;
        vm.onTv = [];

        // In Theaters
        vm.getInTheaters = getInTheaters;
        vm.inTheaters = [];

        init();

        function init() {
            getOnTv();
            getInTheaters();
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

        // On TV Functions
        function getOnTv(){
            HomeService.getOnTvFromService().then(function (response) {
                vm.onTv = response.results.slice(0,3);
            });
        }
        // End of On Tv Functions

        // In Theaters Functions
        function getInTheaters(){
            HomeService.getInTheatersService().then(function (response) {
                vm.inTheaters = response.results.slice(0,3);
            });
        }
        // End of In Theaters Functions

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }


    }
})();
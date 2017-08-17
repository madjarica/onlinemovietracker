(function () {
    angular.module('app')
        .controller('GalleryController', GalleryController);

    GalleryController.$inject = ['$location', '$http', '$route', 'MovieService', 'TvShowsService'];

    function GalleryController($location, $http, $route, MovieService, TvShowsService) {

        var vm = this;

        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        vm.movie_images = MovieService.movieDetails.additionalBackdrops;
        vm.tv_show_images = TvShowsService.tvShowDetails.additionalBackdrops;
        var currIndex = 0;
        vm.addSlides = addSlides;


        // Gallery functions
        function addSlides(images) {
            for(var i = 0; i<images.length; i++){
                vm.slides.push({
                    image: "http://image.tmdb.org/t/p/w185" + images[i],
                    id: currIndex++
                });
            }
        }
        // End of Gallery functions

    }
})();
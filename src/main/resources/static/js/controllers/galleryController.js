(function () {
    angular.module('app')
        .controller('GalleryController', GalleryController);

    GalleryController.$inject = ['$location', '$http', '$route', 'MovieService'];

    function GalleryController($location, $http, $route, MovieService) {

        var vm = this;

        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        vm.images = MovieService.movieDetails.additionalBackdrops;
        var currIndex = 0;
        vm.addSlides = addSlides;
        addSlides();

        // Gallery functions
        function addSlides() {
            // for(var i = 0; i<images.length; i++){
            //     vm.slides.push({
            //         image: images[i],
            //         id: currIndex++
            //     });
            //
            // }
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
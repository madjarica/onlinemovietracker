(function () {
    angular.module('app')
        .controller('GalleryController', GalleryController);

    GalleryController.$inject = ['$location', '$http', '$route'];

    function GalleryController($location, $http, $route) {

        var vm = this;

        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        var currIndex = 0;
        vm.addSlides = addSlides;
        addSlides();

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
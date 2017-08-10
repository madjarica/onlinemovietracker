(function () {
    angular.module('app')
        .controller('TvShowController', TvShowController);

    TvShowController.$inject = ['$location', '$http', '$route', 'TvShowsService'];

    function TvShowController($location, $http, $route, TvShowsService) {

        var vm = this;

        // Get Movies
        vm.getTvShowByTitle = getTvShowByTitle;
        vm.fillTvShowData = fillTvShowData;
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
            TvShowsService.getTvShowyId(id).then(function (response) {
                vm.tvShowObject = response;
                vm.tvShowObject.imdb_id = 'http://www.imdb.com/title/' + vm.tvShowObject.imdb_id;
                vm.tvShowObject.poster = 'https://image.tmdb.org/t/p/w640' + vm.tvShowObject.poster_path;
                vm.tvShowObject.backdrop = 'https://image.tmdb.org/t/p/w1280' + vm.tvShowObject.backdrop_path;
            }).then(TvShowsService.getTvShowTrailer(id).then(function (videos) {
                if(videos.results.length > 0) {
                    vm.tvShowObject.youtube = "http://www.youtube.com/embed/" + videos.results[0].key;
                }
            }).then(TvShowsService.getTvShowImdbId(id).then(function (imdb_link) {
                vm.tvShowObject.imdb_id = 'http://www.imdb.com/title/' + imdb_link.imdb_id;
            })));
        }

    }

})();
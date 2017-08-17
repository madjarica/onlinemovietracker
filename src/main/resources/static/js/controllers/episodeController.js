(function () {
    angular.module('app')
        .controller('EpisodeController', EpisodeController);

    EpisodeController.$inject = ['$location', '$http', '$route', 'EpisodeService', 'TvShowsService'];

    function EpisodeController($location, $http, $route, EpisodeService, TvShowsService) {

        var vm = this;

        vm.episodeObject = {};
        vm.tvShows = [];
        vm.tvShowForEpisode = {};
        vm.saveEpisode = saveEpisode;
        vm.getTvShows = getTvShows;
        vm.select = select;

        function saveEpisode(episode) {
            vm.episodeObject = episode;
            EpisodeService.saveEpisode(episode).then(function (response) {
                console.log(response);
                vm.episodeObject = response;
            }).then(function () {
                vm.tvShowForEpisode.tvShowEpisodes.push(vm.episodeObject);
            }).then(function () {
                TvShowsService.saveTvShow(vm.tvShowForEpisode)
                    .then(function (response) {
                        console.log(response);
                    })
            })
        }

        function getTvShows(search){
            TvShowsService.searchTvShowInLocalDatabase(search).then(function (response) {
                vm.tvShows = response;
            })
        }
        function select(tvShow) {
            vm.tvShowForEpisode = tvShow;
            vm.episodeObject.search = tvShow.name;
        }

    }
})();
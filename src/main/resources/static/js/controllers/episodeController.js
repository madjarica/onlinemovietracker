(function () {
    angular.module('app')
        .controller('EpisodeController', EpisodeController);

    EpisodeController.$inject = ['$location', '$http', '$route', 'EpisodeService'];

    function EpisodeController($location, $http, $route, EpisodeService) {

        var vm = this;

        vm.episodeObject = {};
        vm.saveEpisode = saveEpisode;

        function saveEpisode(episode) {
            vm.episodeObject = episode;
            EpisodeService.saveEpisode(episode).then(function (response){
                console.log(response);
            });
        }

    }
})();
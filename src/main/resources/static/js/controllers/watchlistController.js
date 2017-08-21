(function () {
    angular.module('app')
        .controller('WatchlistController', WatchlistController);

    WatchlistController.$inject = ['WatchlistService', 'AuthenticationService', '$location'];

    function WatchlistController(WatchlistService, AuthenticationService, $location) {

        var vm = this;
        vm.username = AuthenticationService.currentUsername;
        vm.addToWatchlist = addToWatchlist;
        vm.userWatchlist = {};

        init();
        function getWatchlists() {
            WatchlistService.getWatchlists().then(function (response) {
                vm.watchlistsList = response;
            });
        }
        
        function getUserWatchlist(username) {
            console.log(username);
            WatchlistService.getUserWatchlist(username).then(function (response) {
                vm.userWatchlist = response;
                console.log(vm.userWatchlist)
            });
        }

        function addToWatchlist(video){
            vm.userWatchlist.videos.push(video);
            WatchlistService.saveWatchlist(vm.userWatchlist).then(function (response) {
                vm.userWatchlist = response;
            }).then(function () {
                $location.url('watchlist')
            })
        }

        function init() {
            getUserWatchlist(vm.username)
        }
    }

})();
(function () {
    angular.module('app')
        .controller('WatchlistController', WatchlistController);

    WatchlistController.$inject = ['WatchlistService', 'AuthenticationService', '$location', 'MovieService', 'TvShowsService'];

    function WatchlistController(WatchlistService, AuthenticationService, $location, MovieService, TvShowsService) {

        var vm = this;
        vm.username = AuthenticationService.currentUsername;
        vm.addToWatchlist = addToWatchlist;
        vm.deleteWatchlist = deleteWatchlist;
        vm.editWatchlist = editWatchlist;
        vm.selectWatchlist = selectWatchlist;
        vm.favWatchlist = favWatchlist;
        vm.goToDetailsPage = goToDetailsPage;
        vm.newWatchlist = {};
        vm.selectedWatchlist = {};
        vm.userWatchlist = {};

        init();
        
        function getUserWatchlist(username) {
            console.log(username);
            WatchlistService.getUserWatchlist(username).then(function (response) {
                vm.userWatchlist = response;
                WatchlistService.userWatchlist = response;
                console.log(vm.userWatchlist)
            });
        }

        function addToWatchlist(video){
            vm.newWatchlist.video = video;
            vm.newWatchlist.watchlistUser = vm.username;
            vm.newWatchlist.visibleToOthers = true;
            WatchlistService.saveWatchlist(vm.newWatchlist).then(function (response) {
                vm.userWatchlist.push(response);
                getUserWatchlist(vm.username);
            }).then(function () {
                $location.url('watchlist')
            })
        }
        
        function deleteWatchlist(id) {
            WatchlistService.deleteWatchlist(id).then(function () {
                getUserWatchlist(vm.username);
            })
        }

        function editWatchlist(watchlist){
            WatchlistService.saveWatchlist(watchlist).then(function () {
                getUserWatchlist(vm.username);
            })
        }

        function selectWatchlist(watchlist){
            vm.selectedWatchlist = watchlist;
            console.log(vm.selectedWatchlist);
        }

        function favWatchlist(watchlist){
            if(watchlist.favourite){
                watchlist.favourite = false;
            }else {
                watchlist.favourite = true;
            }
            WatchlistService.saveWatchlist(watchlist).then(function(response){
                getUserWatchlist(vm.username);
            })
        }
        
        function goToDetailsPage(watchlist) {
            if(watchlist.video.dtype === "Movie"){
                MovieService.movieDetails = watchlist.video;
                WatchlistService.selectedWatchlist = watchlist;
                $location.url('movie-details');
            } else if(watchlist.video.dtype === "TvShow"){
                TvShowsService.tvShowDetails = watchlist.video;
                WatchlistService.selectedWatchlist = watchlist;
                $location.url('tv-show-details');
            }
        }

        function init() {
            getUserWatchlist(vm.username)
        }
    }

})();
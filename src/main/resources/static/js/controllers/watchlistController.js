(function () {
    angular.module('app')
        .controller('WatchlistController', WatchlistController);

    WatchlistController.$inject = ['WatchlistService', 'AuthenticationService', '$location'];

    function WatchlistController(WatchlistService, AuthenticationService, $location) {

        var vm = this;
        vm.username = AuthenticationService.currentUsername;
        vm.addToWatchlist = addToWatchlist;
        vm.deleteWatchlist = deleteWatchlist;
        vm.editWatchlist = editWatchlist;
        vm.selectWatchlist = selectWatchlist;
        vm.favWatchlist = favWatchlist;
        vm.newWatchlist = {};
        vm.editedWatchlist = {};
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
            vm.editedWatchlist = watchlist;
            console.log(vm.editedWatchlist);
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

        function init() {
            getUserWatchlist(vm.username)
        }
    }

})();
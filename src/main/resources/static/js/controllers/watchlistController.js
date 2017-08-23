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
        vm.saveWatchDate = saveWatchDate;
        vm.newWatchlist = {};
        vm.selectedWatchlist = {};
        vm.userWatchlist = {};
        vm.watchDate = {};
        
        function saveWatchDate(id, date) {
            WatchlistService.changeWatchDate(id, date).then(function (response) {

            })
        }

        vm.openCalendar = openCalendar;
        vm.datePickerOptions = {
            formatYear: 'yy',
            maxDate : new Date()
        };

        vm.popupCalendar = {
            opened: false
        };

        function openCalendar() {
            vm.popupCalendar.opened = true;
        }

        init();
        
        function getUserWatchlist(username) {
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
            vm.newWatchlist.watchDate = new Date();
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
            vm.watchDate = vm.selectedWatchlist.watchDate;
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
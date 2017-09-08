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
        vm.getWatchlists = getWatchlists;
        vm.getWatchlistDetails = getWatchlistDetails;
        vm.getUserWatchlist = getUserWatchlist;
        vm.watchlistCollection = WatchlistService.selectedCollection;
        vm.userOfCollection = WatchlistService.userOfCollection;
        vm.newWatchlist = {};
        vm.selectedWatchlist = {};
        vm.userWatchlist = {};
        vm.watchlists = {};
        vm.watchDate = {};
        vm.searchData = {};

        vm.propertyName = 'video.name';
        vm.reverse = false;
        vm.sortBy = sortBy;

        function sortBy(propertyName) {
            vm.reverse = (vm.propertyName === propertyName) ? !vm.reverse : false;
            vm.propertyName = propertyName;
        }

        function saveWatchDate(id, date) {
            WatchlistService.changeWatchDate(id, date).then(function (response) {
                init();
            })
        }

        vm.openCalendar = openCalendar;
        vm.datePickerOptions = {
            formatYear: 'yy',
            maxDate: new Date()
        };

        vm.popupCalendar = {
            opened: false
        };

        function openCalendar() {
            vm.popupCalendar.opened = true;
        }

        init();
        getUserWatchlist(vm.userOfCollection);


        function init() {
            if ($location.path() !== '/search-watchlists') {
                getUserWatchlist(vm.userOfCollection);
            }
            if ($location.path() !== '/movie-details') {
                getUserWatchlist(vm.userOfCollection);
            }
        }

        function getWatchlists() {
            WatchlistService.getWatchlists().then(function (response) {
                vm.watchlists = response;
            })
        }


        // function getUserWatchlist(searchByUser) {
        //     console.log('click');
        //     var username = searchByUser.username;
        //     WatchlistService.getUserWatchlist(username).then(function (response) {
        //         vm.userWatchlist = response;
        //         WatchlistService.userWatchlist = response;
        //         console.log(vm.userWatchlist)
        //     });
        // }

        function addToWatchlist(video) {
            vm.newWatchlist.video = video;
            vm.newWatchlist.watchlistUser = vm.username;
            vm.newWatchlist.visibleToOthers = true;
            vm.newWatchlist.watchDate = new Date();
            vm.newWatchlist.favourite = false;
            console.log(vm.username);
            vm.userOfCollection = angular.copy(vm.username);
            WatchlistService.userOfCollection = vm.userOfCollection;
            getUserWatchlist(vm.userOfCollection);
            WatchlistService.saveWatchlist(vm.newWatchlist).then(function (response) {
                vm.watchlistCollection.watchlistElements.push(response);
            }).then(function () {
                WatchlistService.saveWatchlistCollection(vm.watchlistCollection).then(function (response) {
                    vm.watchlistCollection = response;
                    getUserWatchlist(vm.userOfCollection);
                    WatchlistService.currentUserWatchlist = angular.copy(vm.watchlistCollection.watchlistElements);
                })
            }).then(function () {
                $location.url('watchlist');
            })

        }

        function deleteWatchlist(id) {
            WatchlistService.deleteWatchlist(id).then(function () {
            	WatchlistService.getUserWatchlistCollection(vm.username).then(function (response) {            	
                    console.log(vm.username);
                    vm.userWatchlist = response.watchlistElements;
                    WatchlistService.userWatchlist = vm.userWatchlist;
                    console.log(vm.userWatchlist);
                    vm.watchlistCollection = response;
                    WatchlistService.selectedCollection = vm.watchlistCollection;
                    WatchlistService.currentUserWatchlist = angular.copy(vm.watchlistCollection.watchlistElements);
                    console.log(vm.watchlistCollection.watchlistElements);
            })
            })
        }

        function editWatchlist(watchlist) {
            WatchlistService.saveWatchlist(watchlist).then(function () {
                getUserWatchlist(vm.username);
            })
        }

        function selectWatchlist(watchlist) {
            vm.selectedWatchlist = watchlist;
            vm.watchDate = vm.selectedWatchlist.watchDate;
        }

        function favWatchlist(watchlist) {
            if (watchlist.favourite) {
                watchlist.favourite = false;
            } else {
                watchlist.favourite = true;
            }
            WatchlistService.saveWatchlist(watchlist).then(function (response) {
                getUserWatchlist(vm.username);
            })
        }

        function goToDetailsPage(watchlist) {
            if (watchlist.video.dtype === "Movie") {
                MovieService.movieDetails = watchlist.video;
                WatchlistService.selectedWatchlist = watchlist;
                $location.url('movie-details');
            } else if (watchlist.video.dtype === "TvShow") {
                TvShowsService.tvShowDetails = watchlist.video;
                WatchlistService.selectedWatchlist = watchlist;
                $location.url('tv-show-details');
            }
        }

        function getWatchlistDetails(id) {
            WatchlistService.getWatchlistDetails(id).then(function (response) {
                WatchlistService.watchlistDetails = response;
                var runtime = WatchlistService.watchlistDetails.runtime;
                var hoursAndMinutes = Math.floor(runtime / 60) + 'h ' + Math.floor(runtime % 60) + 'min';
                WatchlistService.watchlistDetails.runtime = hoursAndMinutes;
            }).then(function () {
                $location.url("watchlists-preview");
            })
        }

        function getUserWatchlist(username) {        	
            WatchlistService.getUserWatchlistCollection(username).then(function (response) {            	
                console.log(vm.userOfCollection);
                vm.userWatchlist = response.watchlistElements;
                WatchlistService.userWatchlist = vm.userWatchlist;
                console.log(vm.userWatchlist);
                vm.watchlistCollection = response;
                WatchlistService.selectedCollection = vm.watchlistCollection;
            });
        }

    }

})();
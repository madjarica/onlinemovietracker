(function () {
    angular.module('app')
        .controller('TvShowController', TvShowController)
        .config(function($sceProvider) {
            $sceProvider.enabled(false);
        });

    TvShowController.$inject = ['$location', '$http', '$route', 'TvShowsService', 'WatchlistService', 'AuthenticationService'];

    function TvShowController($location, $http, $route, TvShowsService, WatchlistService, AuthenticationService) {

        var vm = this;

        // Gallery
        vm.myInterval = 3000;
        vm.noWrapSlides = false;
        vm.active = 0;
        vm.slides = [];
        vm.tv_show_images = TvShowsService.tvShowDetails.additionalBackdrops;
        var currIndex = 0;
        vm.addSlides = addSlides;

        if(vm.tv_show_images) {
            addSlides(vm.tv_show_images);
        }

        // Get Movies
        vm.getTvShowByTitle = getTvShowByTitle;
        vm.fillTvShowData = fillTvShowData;
        vm.saveTvShow = saveTvShow;
        vm.getTvShowDetails = getTvShowDetails;
        vm.fbshareCurrentPage = fbshareCurrentPage;
        vm.username = AuthenticationService.currentUsername;
        vm.tvShowObject = {};
        vm.tvShowDetails = TvShowsService.tvShowDetails;
        vm.tvShowEdit = {};
        vm.tvShowEdit = angular.copy(vm.tvShowDetails);
        vm.tvShowObject.poster_path = '';
        vm.tvShowObject.backdrop_path = '';
        vm.tvShowsList = [];

        //Watchlist
        vm.checkFav = checkFav;
        vm.checkIfAdded = checkIfAdded;
        vm.favTvShow = favTvShow;
        vm.favourite = false;
        vm.userWatchlist = WatchlistService.currentUserWatchlist;
        if($location.path() !== '/'){
            checkFav();
        }

        function fbshareCurrentPage(imdb_link) {
            window.open("https://www.facebook.com/sharer/sharer.php?u="+escape(imdb_link)+"&t="+document.title, '',
                'menubar=no,toolbar=no,resizable=yes,scrollbars=yes,height=300,width=600');
            return false;
        }


        // On List of Movies
        function getTvShowByTitle(title){
            TvShowsService.getTVShowByTitle(title).then(function (response) {
                vm.tvShowsList = response.results.slice(0,8);
            });
        }
        // End of List of Movies Functions

        function fillTvShowData(id) {
            $('#loading-spinner').removeClass('hidden');
            TvShowsService.getTvShowByIdBackend(id).then(function (response) {
                $('#loading-spinner').addClass('hidden');
                console.log(response);
                vm.tvShowObject = response;
            })
        }

        function youtube_parser(url){
            var regExp = /^.*((youtu.be\/)|(v\/)|(\/u\/\w\/)|(embed\/)|(watch\?))\??v?=?([^#\&\?]*).*/;
            var match = url.match(regExp);
            res = (match&&match[7].length==11)? match[7] : false;
            return res;
        }
        
        function saveTvShow(tvShow) {
            console.log(tvShow);
            if(tvShow.trailerLink != null) {
                var youtube_link = tvShow.trailerLink;
                var video_id = youtube_parser(youtube_link);
                var parsed_youtube_link = null;
                if(video_id) {
                    parsed_youtube_link = "https://www.youtube.com/embed/" + video_id;
                }
                tvShow.trailerLink = parsed_youtube_link;
            } else {
                tvShow.trailerLink = null;
            }
            TvShowsService.saveTvShow(tvShow).then(function (response) {
                vm.tvShowDetails = response;
                TvShowsService.tvShowDetails = response;
            }).then(function(){
                vm.tvShowObject = {};
            }).then(function () {
                $location.url("tv-show-details");
            });
        }


        // Show movie details
        function getTvShowDetails(id) {
            TvShowsService.getTvShowDetails(id).then(function (response) {
                TvShowsService.tvShowDetails = response;
            }).then(function () {
                $location.url("tv-show-details");
            })
        }
        // End of show movie details

        // Gallery functions
        function addSlides(images) {
            for(var i = 0; i<images.length; i++){
                vm.slides.push({
                    image: images[i],
                    id: currIndex++
                });
            }
        }
        // End of Gallery functions

        //Watchlist favourite checking
        function checkFav() {
            if (vm.userWatchlist.length > 0) {
                for (var i = 0; i < vm.userWatchlist.length; i++) {
                    if (vm.userWatchlist[i].video.id === vm.tvShowDetails.id) {
                        vm.favourite = vm.userWatchlist[i].favourite;
                    }
                }
            }
        }

        function checkIfAdded() {
            if (vm.userWatchlist.length > 0) {
                for (var i = 0; i < vm.userWatchlist.length; i++) {
                    if (vm.userWatchlist[i].video.id === vm.tvShowDetails.id) {
                        return false;
                    }
                }
            }
            return true;
        }

        function favTvShow(){
            if (vm.userWatchlist.length > 0) {
                for (var i = 0; i < vm.userWatchlist.length; i++) {
                    if (vm.userWatchlist[i].video.id === vm.tvShowDetails.id) {
                        if(vm.userWatchlist[i].favourite){
                            vm.favourite = false;
                            vm.userWatchlist[i].favourite = false;
                        } else {
                            vm.favourite = true;
                            vm.userWatchlist[i].favourite = true;
                        }
                        WatchlistService.saveWatchlist(vm.userWatchlist[i]).then(function (response) {
                            console.log(response);
                        })
                    }
                }
            }
        }

        //End of favourite checking

    }

})();
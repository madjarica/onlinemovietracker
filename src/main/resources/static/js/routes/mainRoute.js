(function () {
    angular.module('app')
        .config(config);

    config.$inject = ['$routeProvider'];

    function config($routeProvider) {
        $routeProvider
            .when('/', {
                templateUrl: '/views/home.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/movie-details', {
                templateUrl: '/views/movie-details.html',
                controller: 'MovieController',
                controllerAs: 'vm'
            })
            .when('/tv-show-details', {
                templateUrl: '/views/tv-show-details.html',
                controller: 'TvShowController',
                controllerAs: 'vm'
            })
            .when('/search', {
                templateUrl: '/views/search-results.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/add-movie', {
                templateUrl: '/views/add-movie.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/add-tv-show', {
                templateUrl: '/views/add-tv-show.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/add-episode', {
                templateUrl: '/views/add-episode.html',
                controller: 'EpisodeController',
                controllerAs: 'vm'
            })
            .when('/watchlist', {
                templateUrl: '/views/watchlist.html',
                controller: 'WatchlistController',
                controllerAs: 'vm'
            })
            .when('/schedule-list', {
                templateUrl: '/views/schedule-list.html',
                controller: 'ScheduleListController',
                controllerAs: 'schListCtrl'
            })
            .when('/messages/success-password-activation', {
                templateUrl: '/messages/success-password-activation.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/messages/failed-password-activation', {
                templateUrl: '/messages/failed-password-activation.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/messages/success-account-activation', {
                templateUrl: '/messages/success-account-activation.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/messages/failed-account-activation', {
                templateUrl: '/messages/failed-account-activation.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/admin/view-users', {
                templateUrl: '/views/admin/view-users.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/admin/view-messages', {
                templateUrl: '/views/admin/view-messages.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/user/view-notifications', {
                templateUrl: '/views/user/view-notifications.html',
                controller: 'NotificationController',
                controllerAs: 'notifyController'
            })
            .when('/user/change-password', {
                templateUrl: '/views/user/change-password.html',
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .otherwise('/');
    }
})();
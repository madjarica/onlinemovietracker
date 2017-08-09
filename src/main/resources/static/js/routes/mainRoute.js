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
                controller: 'HomeController',
                controllerAs: 'vm'
            })
            .when('/tv-show-details', {
                templateUrl: '/views/tv-show-details.html',
                controller: 'HomeController',
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
            .otherwise('/');
    }
})();
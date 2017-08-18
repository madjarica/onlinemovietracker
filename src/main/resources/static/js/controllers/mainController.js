(function () {
    angular.module('app')
        .controller('MainController', MainController);

    MainController.$inject = ['$rootScope', '$location', '$http', '$route', '$window'];

    function MainController($rootScope, $location, $http, $route, $window) {

        var self = this;

        init();

        function init() {

        }
    }

})();
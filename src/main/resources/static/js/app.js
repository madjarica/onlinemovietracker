(function () {
    var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ngTagsInput']);
    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }]);
})();

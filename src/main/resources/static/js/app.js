(function () {
    var app = angular.module('app', ['ngRoute', 'ngResource', 'ui.bootstrap', 'ngTagsInput', 'vcRecaptcha', 'ngSanitize', '720kb.socialshare']);
    app.config(['$httpProvider', function ($httpProvider) {
        $httpProvider.defaults.headers.common['X-Requested-With'] = 'XMLHttpRequest';
    }]);
})();

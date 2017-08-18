(function () {
    angular.module('app')
        .controller('AuthenticationController', AuthenticationController);

    AuthenticationController.$inject = ['$location', '$http', '$route', '$routeParams', 'AuthenticationService'];

    function AuthenticationController($location, $http, $route, $routeParams, AuthenticationService) {

        var self = this;

        self.fireLogin = fireLogin;
        self.logout = logout;
        self.login = login;
        self.register = register;
        self.requestNewPassword = requestNewPassword;
        // self.activateNewPassword = activateNewPassword;

        self.registerCredentials = {};
        self.loginCredentials = {};
        self.forgotCredentials = {};

        self.errors = {};
        self.errors.register = '';

        self.registerForm = {};
        self.loginForm = {};
        self.forgotForm = {};

        self.user;
        
        function init() {
            $('#auth-modal').modal('hide');
            if (self.user) {
                $route.reload();
            }
        }

        function fireLogin() {
            $('#auth-modal').modal('show');
        }

        function login() {
            // creating base64 encoded String from username and password
            var base64Credential = btoa(self.loginCredentials.username + ':' + self.loginCredentials.password);

            // calling GET request for getting the user details
            $http.get('users/user', {
                headers: {
                    // setting the Authorization Header
                    'Authorization': 'Basic ' + base64Credential
                }
            }).success(function (res) {
                self.loginCredentials.password = null;
                self.message = '';
                // setting the same header value for all request calling from this app
                $http.defaults.headers.common['Authorization'] = 'Basic ' + base64Credential;
                self.user = res;
                init();
            }).error(function (error) {
                self.error = 'Bad credentials!';
            });
        }

        function logout() {
            $http.defaults.headers.common['Authorization'] = null;
            delete self.user;
            $location.url("/");
        }

        function register(user) {
            AuthenticationService.saveUser(user).then(function(response) {
                init();
                console.log("registered");
            }, function(error) {
                console.log(error)

            });
        }

        function requestNewPassword(email) {
            AuthenticationService.requestNewPassword(email).then(function (response) {
                console.log("sent");
            }, function (error) {
                console.log("failed");
            });
        }

    }
})();
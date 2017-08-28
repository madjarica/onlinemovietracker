(function () {
    angular.module('app')
        .controller('AuthenticationController', AuthenticationController);

    AuthenticationController.$inject = ['$location', '$http', '$route', '$routeParams', 'AuthenticationService', 'vcRecaptchaService'];

    function AuthenticationController($location, $http, $route, $routeParams, AuthenticationService, vcRecaptchaService) {

        var self = this;

        self.fireLogin = fireLogin;
        self.logout = logout;
        self.login = login;
        self.register = register;
        self.requestNewPassword = requestNewPassword;
        self.getHashedEmail = getHashedEmail;
        self.changePassword = changePassword;

        self.registerCredentials = {};
        self.loginCredentials = {};
        self.forgotCredentials = {};

        self.loginMessages = {};
        self.loginMessages.error = '';

        self.errors = {};
        self.errors.register = '';

        self.registerForm = {};
        self.loginForm = {};
        self.forgotForm = {};

        // Navigation
        self.isActive = isActive;

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }

        self.publicKey = "6Ld4Ii4UAAAAAPUDKnd4I2PrzhEZ4no-C8S62rB9";

        self.user;
        self.username = AuthenticationService.currentUsername;
        self.hashedEmail = "https://www.gravatar.com/avatar/?d=identicon";
        
        function init() {
            $('#auth-modal').modal('hide');
            // self.hashedEmail = "https://www.gravatar.com/avatar/" + AuthenticationService.requestHashedEmail(self.user.username);
            if (self.user) {
                $route.reload();
            }
        }
        
        function getHashedEmail(username) {
            AuthenticationService.requestHashedEmail(username).then(function (response) {
                self.hashedEmail = "https://www.gravatar.com/avatar/" + response;
            })
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
                AuthenticationService.currentUsername = self.user.username;

                AuthenticationService.requestHashedEmail(self.user.username).then(function (response) {
                    self.hashedEmail = "https://www.gravatar.com/avatar/" + response.hashedEmail;
                }).then(function () {
                    init();
                });

            }).error(function (error) {
                console.log(error.message);
                self.loginMessages.error = error.message;
            });
        }

        function logout() {
            $http.defaults.headers.common['Authorization'] = null;
            delete self.user;
            AuthenticationService.currentUsername = null;
            $location.url("/");
        }

        function register(user) {
            if(vcRecaptchaService.getResponse() === "") {
                self.errors.register = "You need to solve captcha first.";
            } else {
                var data = {
                    'g-recaptcha-response': vcRecaptchaService.getResponse()  //send g-captcah-reponse to our server
                };
                self.errors.register = "";
                AuthenticationService.sendCaptcha(data).then(function (response) {
                   if(response.data.success) {
                       AuthenticationService.saveUser(user).then(function() {
                           init();
                           console.log("registered");
                       }, function(error) {
                           console.log(error)
                       });
                   }
                }, function (error) {
                    console.log(error);
                });

            }
        }

        function changePassword(username, password) {
            AuthenticationService.changePassword(username, password).then(function (response) {
                console.log("changed");
            }, function (error) {
                console.log("error");
            })
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
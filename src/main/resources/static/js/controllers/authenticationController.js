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

        self.requestPasswordMessages = {};
        self.requestPasswordMessages.success = '';
        self.requestPasswordMessages.error = '';

        self.loginMessages = {};
        self.loginMessages.success = '';
        self.loginMessages.error = '';

        self.registerMessages = {};
        self.registerMessages.success = '';
        self.registerMessages.error = '';

        self.registerForm = {};
        self.loginForm = {};
        self.forgotForm = {};

        // Navigation
        self.isActive = isActive;

        self.clickOnLogin = clickOnLogin;
        self.clickOnRegister = clickOnRegister;
        self.clickOnRequire = clickOnRequire;

        function clickOnLogin() {
            self.requestPasswordMessages.success = '';
            self.requestPasswordMessages.error = '';
            self.loginMessages.success = '';
            self.loginMessages.error = '';
            self.registerMessages.success = '';
            self.registerMessages.error = '';
        }

        function clickOnRegister() {
            self.requestPasswordMessages.success = '';
            self.requestPasswordMessages.error = '';
            self.loginMessages.success = '';
            self.loginMessages.error = '';
            self.registerMessages.success = '';
            self.registerMessages.error = '';
        }

        function clickOnRequire() {
            self.requestPasswordMessages.success = '';
            self.requestPasswordMessages.error = '';
            self.loginMessages.success = '';
            self.loginMessages.error = '';
            self.registerMessages.success = '';
            self.registerMessages.error = '';
        }

        function isActive(viewLocation) {
            return viewLocation === $location.path();
        }

        self.publicKey = "6Ld4Ii4UAAAAAPUDKnd4I2PrzhEZ4no-C8S62rB9";

        self.user;
        self.username = AuthenticationService.currentUsername;
        self.hashedEmail = "https://www.gravatar.com/avatar/?d=identicon";
        
        function init() {
            $('#auth-modal').modal('hide');
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
                self.requestPasswordMessages.success = '';
                self.requestPasswordMessages.error = '';
                self.loginMessages.success = '';
                self.loginMessages.error = '';
                self.registerMessages.success = '';
                self.registerMessages.error = '';
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
                if(!error.message) {
                    self.loginMessages.error = "Bad Credentials";
                } else {
                    self.loginMessages.error = error.message;
                }
                self.requestPasswordMessages.error = '';
                self.requestPasswordMessages.success = '';
                self.loginMessages.success = '';
                self.registerMessages.success = '';
                self.registerMessages.error = '';
            });
        }

        function logout() {
            $http.defaults.headers.common['Authorization'] = null;
            delete self.user;
            AuthenticationService.currentUsername = null;
            self.requestPasswordMessages.success = '';
            self.requestPasswordMessages.error = '';
            self.loginMessages.success = '';
            self.loginMessages.error = '';
            self.registerMessages.success = '';
            self.registerMessages.error = '';
            $location.url("/");
        }

        function register(user) {
            if(vcRecaptchaService.getResponse() === "") {
                self.registerMessages.error = "You need to solve captcha first.";
            } else {
                var data = {
                    'g-recaptcha-response': vcRecaptchaService.getResponse()  //send g-captcah-reponse to our server
                };
                self.registerMessages.error = "";
                AuthenticationService.sendCaptcha(data).then(function (response) {
                   if(response.data.success) {
                       AuthenticationService.saveUser(user).then(function() {
                           init();
                           self.requestPasswordMessages.success = '';
                           self.requestPasswordMessages.error = '';
                           self.loginMessages.success = '';
                           self.loginMessages.error = '';
                           self.registerMessages.success = 'You have been successfully registered';
                           self.registerMessages.error = '';
                       }, function(error) {
                           self.requestPasswordMessages.success = '';
                           self.requestPasswordMessages.error = '';
                           self.loginMessages.success = '';
                           self.loginMessages.error = '';
                           self.registerMessages.success = '';
                           self.registerMessages.error = 'There was an error with your registration. Please try again.';
                       });
                   }
                }, function (error) {
                    self.requestPasswordMessages.success = '';
                    self.requestPasswordMessages.error = '';
                    self.loginMessages.success = '';
                    self.loginMessages.error = '';
                    self.registerMessages.success = '';
                    self.registerMessages.error = 'There was an error with your registration. Please try again.';
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
                self.requestPasswordMessages.success = 'We have sent you new password on your email.';
                self.requestPasswordMessages.error = '';
                self.loginMessages.success = '';
                self.loginMessages.error = '';
                self.registerMessages.success = '';
                self.registerMessages.error = '';
            }, function (error) {
                self.requestPasswordMessages.success = '';
                self.requestPasswordMessages.error = 'Email you entered is not registered.';
                self.loginMessages.success = '';
                self.loginMessages.error = '';
                self.registerMessages.success = '';
                self.registerMessages.error = '';
            });
        }
    }
})();
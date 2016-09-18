(function(angular){
    'use strict';

    var component = {
        bindings: {
            accessdenied: '='
        },
        controller: loginController,
        templateUrl: 'app/account/login/login.html'
    };
    
    angular
        .module('app')
        .component('login', component);

    loginController.$inject = ['$state', 'Auth'];
    
    function loginController ($state, Auth) {
        var self = this;

        self.credentials = {
            password: null,
            username: null,
            rememberMe: true
        };
        self.login = login;
        self.authenticationError = false;

        function login(event) {
             Auth.login(self.credentials)
                 .then(loginSuccess)
                 .catch(loginFailed);
        }

        function loginSuccess() {
            self.authenticationError = false;
            $state.go('home');
        }

        function loginFailed() {
            self.authenticationError = true;
        }
    }
})(angular);

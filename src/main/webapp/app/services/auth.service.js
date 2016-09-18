(function() {
    'use strict';

    angular
        .module('app')
        .factory('Auth', Auth);

    Auth.$inject = ['$rootScope', '$state', '$q', 'Principal', 'AuthServerProvider', '$sessionStorage'];

    function Auth ($rootScope, $state, $q, Principal, AuthServerProvider, $sessionStorage) {
        var service = {
            login: login,
            logout: logout,
            loginWithToken: loginWithToken,
            authorize : authorize
        };

        return service;

        function login (credentials, callback) {
            var cb = callback || angular.noop;
            var deferred = $q.defer();

            AuthServerProvider.login(credentials)
                .then(loginThen)
                .catch(function (err) {
                    this.logout();
                    deferred.reject(err);
                    return cb(err);
                }.bind(this));

            function loginThen (data) {
                Principal.identity(true).then(function(account) {
                    deferred.resolve(data);
                });
                return cb();
            }

            return deferred.promise;
        }

        function loginWithToken(jwt, rememberMe) {
            return AuthServerProvider.loginWithToken(jwt, rememberMe);
        }

        function logout () {
            AuthServerProvider.logout();
            Principal.authenticate(null);
        }
        
        function authorize(force) {
            var authReturn = Principal.identity(force).then(authThen);
            
            return authReturn;
            
            function authThen() {
        	var isAuthenticated = Principal.isAuthenticated();
        	
        	if (isAuthenticated && $rootScope.toState.name === 'login') {
        	    // Un usuario autenticado no accede nunca a login
        	    $state.go('home');
        	}
        	
        	var authorities = $rootScope.toState.data && $rootScope.toState.data.authorities;
        	if (authorities && authorities.length > 0 && !Principal.hasAnyAuthority(authorities)) {
        	    if (isAuthenticated) {
        		$state.go('accessdenied');
        	    } else {
        		// El usuario no está autenticado. Se guarda el state
			// para poder redirigirlo una vez autenticado.
        		storePreviousState($rootScope.toState.name, $rootScope.toStateParams);
        		
        		$state.go('login');
        	    }
        	}
            }
        }
        
        function storePreviousState(previousStateName, previousStateParams) {
            var previousState = {"name" : previousStateName, "params" : previousStateParams};
            $sessionStorage.previousState = previousState;
        }
    }
})();

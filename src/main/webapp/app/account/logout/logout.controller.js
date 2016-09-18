(function(angular) {
    'use strict';

    angular.module('app').controller('LogoutController', LogoutController);

    LogoutController.$inject = [ '$state', 'Auth' ];

    function LogoutController($state, Auth) {
	Auth.logout();
	$state.go('home');
    }
})(angular);

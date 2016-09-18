(function(angular) {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider.state('logout', {
	    views : {
		'content@' : {
		    controller : 'LogoutController'
		}
	    }
	});
    }
})(angular);

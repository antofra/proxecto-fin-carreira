(function(angular) {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider.state('login', {
	    parent : 'app',
	    views : {
		'content@' : {
		    template : '<login></login>'
		}
	    }
	});
    }
})(angular);

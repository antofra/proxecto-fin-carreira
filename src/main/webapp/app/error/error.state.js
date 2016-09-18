(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider.state('accessdenied', {
	    parent : 'app',
	    url : '/accessdenied',
	    data : {
		authorities : []
	    },
	    views : {
		'content@' : {
		    templateUrl : 'app/error/accessdenied.html'
		}
	    },
	    resolve : {
		translatePartialLoader : [ '$translate',
			'$translatePartialLoader',
			function($translate, $translatePartialLoader) {
			    $translatePartialLoader.addPart('global');
			    // Si no hago esto parece que las traducciones no
			    // funcionan
			    return $translate.refresh();
			} ]
	    }
	});
    }
})();

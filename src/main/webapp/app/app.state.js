(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider.state('app', {
	    abstract : true,
	    views : {
		'navbar@' : {
		    template : '<menu></menu>'
		},
		'footer@' : {
		    template : '<footer></footer>'
		}
	    },
	    resolve : {
		authorize : [ 'Auth', function(Auth) {
		    Auth.authorize();
		} ],
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

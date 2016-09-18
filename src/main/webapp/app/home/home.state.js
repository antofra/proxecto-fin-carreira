(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state(
			'home',
			{
			    parent : 'app',
			    url : '/',
			    data : {
				authorities : [ 'ROLE_USER', 'ROLE_ADMIN' ]
			    },
			    views : {
				'content@' : {
				    template : '<home ediciones="$resolve.data.ediciones" otras="$resolve.data.otras"></home>'
				}
			    },
			    resolve : {
				data : function($q, Edicion, Concurso) {
				    return $q
					    .all({
						ediciones : Edicion
							.query({
							    ultimaEdicionPublicadaByConcurso : true
							}).$promise,
						otras : Edicion
							.query({
							    ultimaEdicionFinalizadaByConcurso : true
							}).$promise
					    });
				}
			    }
			});
    }

})();

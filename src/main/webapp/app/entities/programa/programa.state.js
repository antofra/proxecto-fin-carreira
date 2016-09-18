(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state('programa', {
		    parent : 'app',
		    data : {
			authorities : [ 'ROLE_ADMIN' ]
		    },
		    abstract : true
		})
		.state(
			'programa.list',
			{
			    url : '/programas',
			    views : {
				'content@' : {
				    template : '<programa-list data="$ctrl.data"></programa-list>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(Programa) {
				    return Programa.query().$promise;
				}
			    }
			})
		.state(
			'programa.get',
			{
			    url : '/programas/:id',
			    bindings : {
				programa : '='
			    },
			    views : {
				'content@' : {
				    template : '<programa programa="$resolve.data.programa" asociadas="$resolve.data.asociadas" roles="$resolve.data.roles"></programa>'
				}
			    },
			    resolve : {
				data : function($q, $stateParams, Programa,
					Asociada, Rol) {
				    return $q.all({
					programa : Programa.get({
					    id : $stateParams.id
					}).$promise,
					asociadas : Asociada.query().$promise,
					roles : Rol.query().$promise
				    });
				}
			    }
			});
    }
})();

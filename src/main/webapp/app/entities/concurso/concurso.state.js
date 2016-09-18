(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state('concurso', {
		    parent : 'app',
		    abstract : true,
		    data : {
			authorities : [ 'ROLE_ADMIN' ]
		    }
		})
		.state(
			'concurso.list',
			{
			    url : '/concursos',
			    data : {
				authorities : [ 'ROLE_ADMIN' ]
			    },
			    views : {
				'content@' : {
				    template : '<concurso-list data="$ctrl.data"></concurso-list>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(Concurso) {
				    return Concurso.query().$promise;
				}
			    }
			})
		.state(
			'concurso.get',
			{
			    url : '/concursos/:id',
			    views : {
				'content@' : {
				    template : '<concurso data="$ctrl.data"></concurso>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(Concurso, $stateParams) {
				    return Concurso.get({
					id : $stateParams.id
				    }).$promise;
				}
			    }
			});
    }
})();

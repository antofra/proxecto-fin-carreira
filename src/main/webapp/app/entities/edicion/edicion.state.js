(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state('edicion', {
		    parent : 'app',
		    abstract : true
		})
		.state(
			'edicion.list',
			{
			    url : '/ediciones',
			    views : {
				'content@' : {
				    template : '<edicion-list data="$ctrl.data"></edicion-list>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(Edicion) {
				    return Edicion.query().$promise;
				}
			    }
			})
		.state(
			'edicion.nueva',
			{
			    params : {
				concurso : null
			    },
			    data : {
				authorities : [ 'ROLE_ADMIN' ]
			    },
			    views : {
				'content@' : {
				    template : '<edicion edicion="$resolve.data.edicion" programas="$resolve.data.programas" roles="$resolve.data.roles" categorias="$resolve.data.categorias" no-asociadas="$resolve.data.noAsociadas"></edicion>'
				}
			    },
			    resolve : {
				data : function($q, $stateParams, Programa,
					Rol, Categoria, NoAsociada, Edicion) {
				    return $q
					    .all({
						edicion : new Edicion(
							{
							    concurso : $stateParams.concurso,
							    programas : [],
							    categoriasEdicion : [],
							    noAsociadas : self.noAsociadas
							}),
						programas : Programa.query().$promise,
						roles : Rol.query().$promise,
						categorias : Categoria.query().$promise,
						noAsociadas : NoAsociada
							.query().$promise
					    });
				}
			    }
			})
		.state(
			'edicion.get',
			{
			    url : '/ediciones/:id',
			    data : {
				authorities : [ 'ROLE_USER', 'ROLE_ADMIN' ]
			    },
			    views : {
				'content@' : {
				    template : '<edicion edicion="$resolve.data.edicion" programas="$resolve.data.programas" roles="$resolve.data.roles" categorias="$resolve.data.categorias" no-asociadas="$resolve.data.noAsociadas"></edicion>'
				}
			    },
			    resolve : {
				data : function($q, $stateParams, Programa,
					Rol, Categoria, NoAsociada, Edicion) {
				    return $q
					    .all({
						edicion : Edicion.get({
						    id : $stateParams.id
						}).$promise,
						programas : Programa.query().$promise,
						roles : Rol.query().$promise,
						categorias : Categoria.query().$promise,
						noAsociadas : NoAsociada
							.query().$promise
					    });
				}
			    }
			});
    }
})();

(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state('categoria', {
		    parent : 'app',
		    abstract : true
		})
		.state(
			'categoria.list',
			{
			    url : '/categorias',
			    views : {
				'content@' : {
				    template : '<categoria-list data="$ctrl.data"></categoria-list>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(Categoria) {
				    return Categoria.query().$promise;
				}
			    }
			});
    }
})();

(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state('no asociada', {
		    parent : 'app',
		    abstract : true
		})
		.state(
			'no asociada.list',
			{
			    url : '/noAsociadas',
			    views : {
				'content@' : {
				    template : '<no-asociada><no-asociada><no-asociada-list data="$ctrl.data"></no-asociada-list>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(NoAsociada) {
				    return NoAsociada.query().$promise;
				}
			    }
			});
    }
})();

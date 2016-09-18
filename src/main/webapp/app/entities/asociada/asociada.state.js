(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider
		.state('asociada', {
		    parent : 'app',
		    abstract : true
		})
		.state(
			'asociada.list',
			{
			    url : '/asociadas',
			    views : {
				'content@' : {
				    template : '<asociada-list data="$ctrl.data"></asociada-list>',
				    controller : function(data) {
					this.data = data;
				    },
				    controllerAs : '$ctrl'
				}
			    },
			    resolve : {
				data : function(Asociada) {
				    return Asociada.query().$promise;
				}
			    }
			});
    }
})();

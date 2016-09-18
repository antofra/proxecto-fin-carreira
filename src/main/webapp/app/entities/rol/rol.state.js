(function() {
    'use strict';

    angular.module('app').config(stateConfig);

    stateConfig.$inject = [ '$stateProvider' ];

    function stateConfig($stateProvider) {
	$stateProvider.state('rol', {
	    parent : 'app',
	    data : {
		authorities : [ 'ROLE_ADMIN' ]
	    },
	    abstract : true
	}).state('rol.list', {
	    url : '/roles',
	    views : {
		'content@' : {
		    template : '<rol-list data="$ctrl.data"></rol-list>',
		    controller : function(data) {
			this.data = data;
		    },
		    controllerAs : '$ctrl'
		}
	    },
	    resolve : {
		data : function(Rol) {
		    return Rol.query().$promise;
		}
	    }
	});
    }
})();

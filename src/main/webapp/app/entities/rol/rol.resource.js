(function(angular) {
    'use strict';

    angular.module('app').factory('Rol', Rol);

    // Rol.$inject = [];
    function Rol($resource) {
	return $resource('api/roles/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }

})(angular);

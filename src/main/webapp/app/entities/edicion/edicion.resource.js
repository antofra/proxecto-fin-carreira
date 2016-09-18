(function(angular) {
    'use strict';

    angular.module('app').factory('Edicion', Edicion);

    Edicion.$inject = [ '$resource' ];

    function Edicion($resource) {
	return $resource('api/ediciones/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }
})(angular);

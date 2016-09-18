(function(angular) {
    'use strict';

    angular.module('app').factory('Categoria', Categoria);

    // Categoria.$inject = [];
    function Categoria($resource) {
	return $resource('api/categorias/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }
})(angular);

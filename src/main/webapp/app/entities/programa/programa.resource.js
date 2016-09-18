(function(angular) {
    'use strict';

    angular.module('app').factory('Programa', Programa);

    // Programa.$inject = [];
    function Programa($resource) {
	return $resource('api/programas/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }
})(angular);

(function(angular) {
    'use strict';

    angular.module('app').factory('NoAsociada', NoAsociada);

    // NoAsociada.$inject = [];
    function NoAsociada($resource) {
	return $resource('api/noasociadas/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }
})(angular);

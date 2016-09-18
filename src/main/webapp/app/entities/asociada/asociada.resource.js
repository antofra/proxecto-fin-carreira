(function(angular) {
    'use strict';

    angular.module('app').factory('Asociada', Asociada);

    // Asociada.$inject = [];
    function Asociada($resource) {
	return $resource('api/asociadas/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }
})(angular);

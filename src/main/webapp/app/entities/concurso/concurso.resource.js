(function(angular) {
    'use strict';

    angular.module('app').factory('Concurso', Concurso);

    // Concurso.$inject = [];
    function Concurso($resource) {
	return $resource('api/concursos/:id', {}, {
	    'update' : {
		method : 'PUT'
	    }
	});
    }
})(angular);

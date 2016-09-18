(function(angular) {
    'use strict';

    angular.module('app').factory('Voto', Voto);

    // Voto.$inject = [];
    function Voto($resource) {
	return $resource('api/votos', {}, {});
    }

})(angular);
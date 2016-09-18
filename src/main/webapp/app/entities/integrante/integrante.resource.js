(function(angular) {
    'use strict';

    angular.module('app').factory('Integrante', Integrante);

    function Integrante($resource) {
	return $resource('api/programas/:idPrograma/integrantes/:id', {}, {});
    }

})(angular);

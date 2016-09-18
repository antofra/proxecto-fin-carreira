(function(angular) {
    'use strict';

    angular.module('app').directive(
	    'entidades',
	    function() {
		return {
		    templateUrl : 'app/components/entidades/entidades.html',
		    replace : true,
		    controller : entidadesController,
		    controllerAs : 'etCtrl'
		};

		function entidadesController() {
		    this.globales = [ 'concurso', 'categoria', 'programa',
			    'asociada', 'rol', 'no asociada' ];
		}
	    });
})(angular);
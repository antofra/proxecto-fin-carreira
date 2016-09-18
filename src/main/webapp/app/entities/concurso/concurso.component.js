(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : concursoController,
	templateUrl : 'app/entities/concurso/concurso.html'
    };

    angular.module('app').component('concurso', component);

    concursoController.$inject = [ '$state' ];

    function concursoController($state) {
    }

})(angular);

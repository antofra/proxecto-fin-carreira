(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    otras : '='
	},
	controller : otrosConcursosController,
	controllerAs : '$ctrl',
	templateUrl : 'app/home/otros-concursos.html'
    };

    angular.module('app').component('otrosConcursos', component);

    otrosConcursosController.$inject = [];

    function otrosConcursosController() {
    }

})(angular);
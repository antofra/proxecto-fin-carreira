(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : resultadoEdicionController,
	controllerAs : '$ctrl',
	templateUrl : 'app/entities/edicion/resultado/resultado.edicion.html'
    };

    angular.module('app').component('resultadoEdicion', component);

    // resultadoEdicionController.$inject = [];
    function resultadoEdicionController() {

    }

})(angular);
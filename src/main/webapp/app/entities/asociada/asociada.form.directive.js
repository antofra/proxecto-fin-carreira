(function(angular) {
    'use strict';

    angular.module('app').component('asociadaForm', {
	bindings : {
	    onCreate : '&',
	    onClose : '&'
	},
	templateUrl : 'app/entities/asociada/asociada-form.html',
	controller : asociadaFormController
    });

    function asociadaFormController(Asociada) {
	var self = this;
	self.guardar = guardar;
	self.asociada = new Asociada();
	self.cancelar = cancelar;

	function guardar() {
	    self.asociada.$save().then(function(data) {
		self.asociada = new Asociada();
		if (self.onCreate)
		    self.onCreate({
			data : data
		    });
	    });
	}

	function cancelar() {
	    self.asociada = new Asociada();
	    self.onClose();
	}

    }
})(angular);

(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    programas : '=',
	    roles : '=',
	    categorias : "=",
	    noAsociadas : "=",
	    edicion : "="
	},
	controller : EdicionController,
	controllerAs : '$ctrl',
	templateUrl : 'app/entities/edicion/edicion.html'
    }

    angular.module('app').component('edicion', component);

    EdicionController.$inject = [ '$state' ];

    function EdicionController($state) {
	var self = this;

	// Acciones
	self.save = save;
	self.cancel = cancel;

	function save() {
	    if (self.edicion.id == null) {
		// Creacion
		self.edicion.$save().then(function() {
		});
	    } else {
		// Actualizacion
		self.edicion.$update({
		    id : self.edicion.id
		}).then(function() {
		});
	    }
	}

	function cancel() {
	    if (self.edicion.concurso) {
		$state.go('concurso.get', {
		    id : self.edicion.concurso.id
		});
	    } else {
		$state.go('concurso.list');
	    }
	}

    }
})(angular);

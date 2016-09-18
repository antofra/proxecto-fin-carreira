(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    edicion : '='
	},
	controller : EdicionFormController,
	controllerAs : '$ctrl',
	templateUrl : 'app/entities/edicion/edicion.form.html'
    }

    angular.module('app').component('edicionForm', component);

    EdicionFormController.$inject = [];

    function EdicionFormController() {
	var self = this;

	self.calendarios = {
	    fechaHoraCelebracion : {
		open : false
	    },
	    fechaHoraPublicacion : {
		open : false
	    },
	    fechaHoraInicioVotaciones : {
		open : false
	    },
	    fechaHoraFinVotaciones : {
		open : false
	    }

	};

    }
})(angular);

(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    participantes : '=',
	    voto : '='
	},
	controller : participanteCategoriaEdicionListController,
	templateUrl : 'app/entities/edicion/categorias/participantes/participante-categoria-edicion-list.html'
    };

    angular.module('app').component('participanteCategoriaEdicionList',
	    component);

    participanteCategoriaEdicionListController.$inject = [ 'NgTableParams' ];

    function participanteCategoriaEdicionListController(NgTableParams) {
	var self = this;
	self.tableParams = new NgTableParams({}, {
	    dataset : self.participantes,
	    counts : []
	});

	self.remove = remove;
	function remove(participante) {
	    self.participantes.splice(self.participantes.indexOf(participante),
		    1);
	}
    }
})(angular);
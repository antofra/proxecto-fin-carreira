(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    categoriaEdicion : '=',
	    candidatos : '='
	},
	controller : categoriaEdicionController,
	controllerAs : '$ctrl',
	templateUrl : 'app/entities/edicion/categorias/categoria-edicion.html'

    };

    angular.module('app').component('categoriaEdicion', component);

    categoriaEdicionController.$inject = [];

    function categoriaEdicionController() {

	var self = this;
	self.tiposParticipante = Object.keys(self.candidatos);
	self.tipoParticipante = [];

	self.categoriaEdicion.participantes = [];

	self.candidatos.settings = {
	    displayProp : 'nombre',
	    scrollableHeight : '300px',
	    scrollable : true,
	    buttonDefaultText : 'Hola',
	    smartButtonTextConverter : function(itemText, originalItem) {
		return "Hola"
	    },
	    externalIdProp : ''
	};
	self.candidatos.events = {
	    onDeselectAll : function() {

	    },
	    onItemDeselect : function(item) {
		console.log(item);
	    }
	}
    }

})(angular);

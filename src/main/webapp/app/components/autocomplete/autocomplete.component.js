(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    model : '=',
	    localData : '='
	},
	controller : autocompleteController,
	controllerAs : '$ctrl',
	templateUrl : 'app/components/autocomplete/autocomplete.html'
    };

    angular.module('app').component('autocomplete', component);

    autocompleteController.$inject = [ '$filter' ];

    function autocompleteController($filter) {
	var self = this;
	self.opts = {
	    id : null,
	    placeholder : $filter('translate')(
		    'acciones.introducir_tres_caracteres'),
	    pause : 100,
	    selectElement : selectElement,
	    searchFields : [ 'nombre', 'apellidos' ],
	    localSearch : localSearch,
	    titleField : 'nombre,apellidos',
	    minLength : 3,
	    overrideSuggestions : false
	};

	// Extendemos las opciones por defecto
	angular.extend(self.opts, {
	    model : self.model,
	    localData : self.localData
	});

	function selectElement(selected) {
	    angular.extend(self.model, self.model, selected.originalObject);
	}

	function localSearch(str) {
	    var strNormalized = str.toString().toLowerCase();
	    var matches = [];

	    self.localData
		    .forEach(function(element) {
			var fullName = element.nombre;

			if (element.apellidos) {
			    fullName = element.nombre + ' ' + element.apellidos;

			    if (element.apellidos.toLowerCase().indexOf(
				    strNormalized) >= 0) {
				matches.push(element);
			    }
			} else if ((element.nombre.toLowerCase().indexOf(
				strNormalized) >= 0)
				|| (fullName.toLowerCase().indexOf(
					strNormalized) >= 0)) {
			    matches.push(element);
			}

		    });

	    return matches;
	}
    }

})(angular);

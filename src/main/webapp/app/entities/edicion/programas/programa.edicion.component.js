(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    edicion : '=',
	    programas : '=',
	    roles : '='
	},
	controller : ProgramaEdicionController,
	controllerAs : '$ctrl',
	templateUrl : 'app/entities/edicion/programas/programa.edicion.html'
    }

    angular.module('app').component('programaEdicion', component);

    ProgramaEdicionController.$inject = [ 'NgTableParams', '$filter' ];

    function ProgramaEdicionController(NgTableParams, $filter) {
	var self = this;

	self.cols = initCols();
	initProgramas();

	// Se encarga de establecer a selected los elementos que forman parte de
	// la edicion (programas e integrantes)
	function initProgramas() {
	    for (var i = 0; i < self.programas.length; i++) {
		var programa = self.programas[i];
		var programaEdicion = $filter('filter')(self.edicion.programas,
			{
			    programa : {
				nombre : programa.nombre
			    }
			})[0];

		if (programaEdicion) {
		    programa.selected = true;
		    for (var j = 0; j < programa.integrantes.length; j++) {
			var integrante = programa.integrantes[j];
			var integranteEdicion = $filter('filter')
				(
					programaEdicion.integrantes,
					{
					    asociada : {
						nombre : integrante.asociada.nombre,
						apellidos : integrante.asociada.apellidos
					    }
					})[0];

			if (integranteEdicion) {
			    integrante.selected = true;
			}
		    }
		}
	    }
	}

	function initCols() {
	    var cols = new Array();
	    // Check
	    cols.push({
		// headerTemplate : '<a>' +
		// traducir('acciones.seleccionar_todos') + '</a> / <a>' +
		// traducir('acciones.seleccionar_ninguno') + '</a>',
		show : true
	    });
	    // Programa
	    cols.push({
		field : "nombre",
		title : traducir('entidad.programa.nombre'),
		show : true
	    });
	    // Integrante
	    cols.push({
		field : "integrante.asociada.nombre",
		title : traducir('entidad.nombre'),
		show : true
	    });
	    // Roles
	    for (var i = 0; i < self.roles.length; i++) {
		cols.push({
		    title : self.roles[i].rol,
		    show : true
		});
	    }

	    return cols;
	}

	self.tableParams = new NgTableParams({
	    group : "nombre" // Agrupa por nombre de programa
	}, {
	    dataset : self.programas,
	    groupOptions : {
		isExpanded : false
	    }
	});

	function traducir(key) {
	    return $filter('translate')(key);
	}

	self.checkAllIntegrantes = function(programa) {
	    for (var i = 0; i < programa.integrantes.length; i++) {
		programa.integrantes[i].selected = true;
		self.selectIntegrante(programa, programa.integantes[i]);
	    }
	}

	self.uncheckAllIntegrantes = function(programa) {
	    for (var i = 0; i < programa.integrantes.length; i++) {
		programa.integrantes[i].selected = false;
		self.selectIntegrante(programa, programa.integantes[i]);
	    }
	}

	self.selectPrograma = function(programa) {
	    var programaEdicion = $filter('filter')(self.edicion.programas, {
		programa : {
		    id : programa.id
		}
	    })[0];
	    if (programa.selected === true && !programaEdicion) {
		self.edicion.programas.push({
		    tipo : 'ProgramaEdicion',
		    programa : programa,
		    integrantes : []
		});
	    } else if (programaEdicion) {
		self.edicion.programas.splice(self.edicion.programas
			.indexOf(programaEdicion), 1);
	    }
	}

	self.selectIntegrante = function(programa, integrante) {
	    var programaEdicion = $filter('filter')(self.edicion.programas, {
		programa : {
		    id : programa.id
		}
	    })[0];
	    if (programaEdicion) {
		var integranteEdicion = $filter('filter')(
			programaEdicion.integrantes, {
			    asociada : {
				id : integrante.asociada.id
			    }
			})[0];
		if (integrante.selected === true && !integranteEdicion) {
		    programaEdicion.integrantes.push({
			tipo : 'IntegranteEdicion',
			integrante : integrante,
			roles : integrante.roles,
			edicion : {
			    id : self.edicion.id
			}
		    });
		} else if (programaEdicion) {
		    programaEdicion.integrantes.splice(
			    programaEdicion.integrantes.indexOf(integrante), 1);
		}
	    }
	}

    }

})(angular);
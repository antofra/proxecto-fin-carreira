(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    programa : '=',
	    asociadas : '=',
	    roles : '='
	},
	controller : programaController,
	templateUrl : 'app/entities/programa/programa.html'
    };

    angular.module('app').component('programa', component);

    programaController.$inject = [ 'Integrante', 'Rol', 'NgTableParams',
	    '$filter' ];

    function programaController(Integrante, Rol, NgTableParams, $filter) {
	var self = this;
	self.table = {
	    cols : getTableColumns(),
	    tableParams : new NgTableParams({}, {
		dataset : self.programa.integrantes,
		counts : []
	    })
	};

	function getTableColumns() {
	    var cols = new Array();
	    // Nombre
	    cols.push({
		title : $filter('translate')('entidad.nombre'),
		field : "asociada.nombre",
		show : true
	    });

	    // Roles
	    for (var i = 0; i < self.roles.length; i++) {
		cols.push({
		    title : self.roles[i].rol,
		    show : true
		});
	    }

	    // Acciones
	    cols.push({});
	    return cols;
	}

	self.eliminarIntegrante = eliminarIntegrante;
	self.addIntegrante = addIntegrante;
	self.editIntegrante = editIntegrante;
	self.saveIntegrante = saveIntegrante;

	function addIntegrante() {
	    self.programa.integrantes.unshift(new Integrante({
		edit : true,
		roles : [],
		asociada : {},
		programa : {
		    id : self.programa.id
		}
	    }));
	    self.table.tableParams.reload();
	}

	function saveIntegrante(integrante) {
	    Integrante.save({
		idPrograma : self.programa.id
	    }, integrante).$promise.then(function(data) {
		integrante.id = data.id;
		integrante.edit = false;
	    });
	}

	function editIntegrante(integrante) {
	    integrante.edit = true;
	}

	function eliminarIntegrante(integrante) {
	    if (integrante.id) {
		Integrante.remove({
		    id : integrante.id,
		    idPrograma : self.programa.id
		}).$promise.then(function() {
		    eliminarIntegranteTabla(integrante);
		});
	    } else {
		eliminarIntegranteTabla(integrante);
	    }

	    function eliminarIntegranteTabla(integrante) {
		self.programa.integrantes.splice(self.programa.integrantes
			.indexOf(integrante), 1);
		self.table.tableParams.reload();
	    }
	}

    }
})(angular);

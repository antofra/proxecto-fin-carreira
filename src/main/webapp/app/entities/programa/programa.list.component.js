(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : programaListController,
	templateUrl : 'app/entities/programa/programa.list.html'
    };

    angular.module('app').component('programaList', component);

    // programaListController.$inject = [];
    function programaListController(NgTableParams, Programa) {
	var self = this;
	self.tableParams = new NgTableParams({
	    sorting : {
		nombre : "asc"
	    }
	}, {
	    dataset : self.data,
	    counts : []
	});

	self.crear = crear;
	self.cancelar = cancelar;
	self.editar = editar;
	self.cancelarEditar = cancelarEditar;
	self.guardar = guardar;
	self.eliminar = eliminar;

	function crear() {
	    self.programa = new Programa();
	    self.showForm = true;
	}

	function cancelar() {
	    self.programa = null;
	    self.showForm = false;
	}

	function editar(programa) {
	    programa.backup = angular.copy(programa);
	    programa.edit = true;
	}

	function cancelarEditar(programa) {
	    programa.edit = false;
	    programa.nombre = programa.backup.nombre;
	    programa.direccionCorreo = programa.backup.direccionCorreo;

	    delete programa.backup;
	}

	function guardar(programa) {
	    if (programa.id) {
		programa.$update({
		    id : programa.id
		}).then(function() {
		    programa.edit = false;

		    delete programa.backup;
		});
	    } else {
		programa.$save().then(function() {
		    self.data.push(programa);
		    self.tableParams.reload();
		    self.showForm = false;

		    delete programa.backup;
		});
	    }
	}

	function eliminar(programa) {
	    programa.$remove({
		id : programa.id
	    }).then(function() {
		self.data.splice(self.data.indexOf(programa), 1);
		self.tableParams.reload();
	    });
	}

    }
})(angular);

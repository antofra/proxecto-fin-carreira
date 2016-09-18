(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : rolListController,
	templateUrl : 'app/entities/rol/rol.list.html'
    };

    angular.module('app').component('rolList', component);

    rolListController.$inject = [ 'NgTableParams', 'Rol' ];

    function rolListController(NgTableParams, Rol) {
	var self = this;

	self.tableParams = new NgTableParams({}, {
	    dataset : self.data,
	    counts : []
	});

	self.crear = crear;
	self.cancelar = cancelar;
	self.eliminar = eliminar;
	self.editar = editar;
	self.cancelarEditar = cancelarEditar;
	self.guardar = guardar;

	function crear() {
	    self.rol = new Rol();
	    self.showForm = true;
	}

	function cancelar() {
	    self.showForm = false;
	    self.rol = null;
	}

	function editar(rol) {
	    rol.backup = angular.copy(rol);
	    rol.edit = true;
	}

	function cancelarEditar(rol) {
	    rol.edit = false;
	    rol.rol = rol.backup.rol;

	    delete rol.backup;
	}

	function guardar(rol) {
	    if (rol.id) {
		rol.$update({
		    id : rol.id
		}).then(function() {
		    rol.edit = false;

		    delete rol.backup;
		})
	    } else {
		rol.$save().then(function() {
		    self.data.push(rol);
		    self.tableParams.reload();
		    self.showForm = false;

		    delete rol.backup;
		});
	    }
	}

	function eliminar(rol) {
	    rol.$remove({
		id : rol.id
	    }).then(function() {
		self.data.splice(self.data.indexOf(rol), 1);
		self.tableParams.reload();
	    });
	}
    }
})(angular);

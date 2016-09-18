(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : noAsociadaListController,
	templateUrl : 'app/entities/no-asociada/no-asociada.list.html'
    };

    angular.module('app').component('noAsociadaList', component);

    noAsociadaListController.$inject = [ 'NgTableParams', 'NoAsociada' ];

    function noAsociadaListController(NgTableParams, NoAsociada) {
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
	    self.noAsociada = new NoAsociada();
	    self.showForm = true;
	}

	function cancelar() {
	    self.showForm = false;
	    self.noAsociada = null;
	}

	function editar(noAsociada) {
	    noAsociada.backup = angular.copy(noAsociada);
	    noAsociada.edit = true;
	}

	function cancelarEditar(noAsociada) {
	    noAsociada.edit = false;
	    noAsociada.nombre = noAsociada.backup.nombre;

	    delete noAsociada.backup;
	}

	function guardar(noAsociada) {
	    if (noAsociada.id) {
		noAsociada.$update({
		    id : noAsociada.id
		}).then(function() {
		    noAsociada.edit = false;

		    delete noAsociada.backup;
		})
	    } else {
		noAsociada.$save().then(function() {
		    self.data.push(noAsociada);
		    self.tableParams.reload();
		    self.showForm = false;

		    delete noAsociada.backup;
		});
	    }
	}

	function eliminar(noAsociada) {
	    noAsociada.$remove({
		id : noAsociada.id
	    }).then(function() {
		self.data.splice(self.data.indexOf(noAsociada), 1);
		self.tableParams.reload();
	    });
	}
    }
})(angular);

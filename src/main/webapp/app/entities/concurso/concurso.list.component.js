(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : concursoListController,
	templateUrl : 'app/entities/concurso/concurso.list.html'
    };

    angular.module('app').component('concursoList', component);

    concursoListController.$inject = [ 'NgTableParams', 'Concurso' ];

    function concursoListController(NgTableParams, Concurso) {
	var self = this;

	self.tableParams = new NgTableParams({}, {
	    dataset : self.data,
	    counts : []
	});

	self.crear = crear;
	self.cancelar = cancelar;
	self.editar = editar;
	self.cancelarEdicion = cancelarEdicion;
	self.guardar = guardar;
	self.eliminar = eliminar;

	function crear() {
	    self.concurso = new Concurso();
	    self.showForm = true;
	}

	function cancelar() {
	    self.showForm = false;
	    self.concurso = null;
	}

	function editar(concurso) {
	    concurso.backup = angular.copy(concurso);
	    concurso.edit = true;
	}

	function cancelarEdicion(concurso) {
	    concurso.edit = false;
	    concurso.nombre = concurso.backup.nombre;
	    delete concurso.backup;
	}

	function guardar(concurso) {
	    if (concurso.id) {
		concurso.$update({
		    id : concurso.id
		}).then(function() {
		    concurso.edit = false;
		    delete concurso.backup;
		});
	    } else {
		concurso.$save().then(function() {
		    self.data.push(concurso);
		    self.tableParams.reload();
		    self.showForm = false;
		    delete concurso.backup;
		});
	    }
	}

	function eliminar(concurso) {
	    concurso.$remove({
		id : concurso.id
	    }).then(function() {
		self.data.splice(self.data.indexOf(concurso), 1);
		self.tableParams.reload();
	    });
	}

    }

})(angular);

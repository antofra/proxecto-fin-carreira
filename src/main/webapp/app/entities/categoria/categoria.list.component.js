(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    data : '='
	},
	controller : categoriaListController,
	templateUrl : 'app/entities/categoria/categoria.list.html'
    };

    angular.module('app').component('categoriaList', component);

    categoriaListController.$inject = [ 'NgTableParams', 'Categoria', '$filter' ];

    function categoriaListController(NgTableParams, Categoria, $filter) {
	var self = this;

	self.tableParams = new NgTableParams({}, {
	    dataset : self.data,
	    total : self.data.length
	});

	self.add = add;
	self.cancel = cancel;
	self.save = save;
	self.edit = edit;
	self.cancelEdit = cancelEdit;
	self.remove = remove;

	function add() {
	    self.categoria = new Categoria();
	    self.showForm = true;
	}

	function cancel() {
	    self.categoria = null;
	    self.showForm = false;
	}

	function save(categoria) {
	    if (categoria.id) {
		categoria.$update({
		    id : categoria.id
		}).then(function() {
		    categoria.edit = false;
		    delete categoria.backup;
		});
	    } else {
		categoria.$save().then(function() {
		    self.data.push(categoria);
		    self.tableParams.reload();
		    self.showForm = false;
		    delete categoria.backup;
		});
	    }
	}

	function edit(categoria) {
	    categoria.backup = angular.copy(categoria);
	    categoria.edit = true;
	}

	function cancelEdit(categoria) {
	    categoria.edit = false;
	    categoria.nombre = categoria.backup.nombre;
	    delete categoria.backup;
	}

	function remove(categoria) {
	    categoria.$remove({
		id : categoria.id
	    }).then(function() {
		self.data.splice(self.data.indexOf(categoria), 1);
		self.tableParams.reload().then(function(data) {
		    if (data.length === 0 && self.tableParams.total() > 0) {
			self.tableParams.page(self.tableParams.page() - 1);
			self.tableParams.reload();
		    }
		});
	    });
	}

    }
})(angular);

(function(angular){
    'use strict';

    var component = {
        bindings: {
            data: '='
        },
        controller: asociadaListController,
        templateUrl: 'app/entities/asociada/asociada.list.html'
    };

    angular
        .module('app')
        .component('asociadaList', component);

    asociadaListController.$inject = ['NgTableParams', 'AlertService'];

    function asociadaListController(NgTableParams, AlertService) {
        var self = this;
        self.tableParams = new NgTableParams({}, { dataset: self.data, counts : [] });

        self.showForm = false;

        self.showHideForm = showHideForm;
        self.onCreateElement = onCreateElement;
        self.save = save;
        self.remove = remove;
        self.edit = edit;
        self.cancelEdit = cancelEdit;

        function showHideForm() {
            self.showForm = !self.showForm;
        }

        function onCreateElement(data) {
            delete data.backup;
            self.data.push(data);
            showHideForm();
            self.tableParams.reload();
        }

        function save(asociada) {
            if (asociada.id) {
        	asociada.$update({id : asociada.id}).then(function(){
        	    asociada.edit = false;
        	});
            } else {
        	asociada.$save().then(function(){
        	    asociada.edit = false;
        	    
        	});
            }
        }
        
        function edit(asociada) {
            asociada.backup = angular.copy(asociada);
            asociada.edit = true;
        }
        
        function cancelEdit(asociada) {
            asociada.edit = false;
            asociada.nombre = asociada.backup.nombre;
            asociada.apellidos = asociada.backup.apellidos;
            asociada.dni = asociada.backup.dni;
            
            delete asociada.backup;
        }

        function remove(asociada) {
            asociada.$remove({id : asociada.id})
                .then(function(){
                    self.data.splice(self.data.indexOf(asociada), 1);
                    self.tableParams.reload();
                }).catch(function(err) {
                    AlertService.error(err.data.message);
                });
        }
    }
})(angular);

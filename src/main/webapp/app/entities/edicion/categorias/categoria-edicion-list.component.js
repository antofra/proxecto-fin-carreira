(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    edicion : '=',
	    categorias : '=',
	    noAsociadas : '='
	},
	controller : categoriaEdicionListController,
	controllerAs : '$ctrl',
	templateUrl : 'app/entities/edicion/categorias/categoria-edicion-list.html'
    };

    angular.module('app').component('categoriaEdicionList', component);

    categoriaEdicionListController.$inject = [ 'Voto', '$filter', 'Principal',
	    '$state' ];

    function categoriaEdicionListController(Voto, $filter, Principal, $state) {
	var self = this;

	if (Principal.hasAnyAuthority([ "ROLE_ADMIN" ])) {
	    self.admin = true;
	    init();

	    self.addCategoria = addCategoria;
	    function addCategoria() {
		self.edicion.categoriasEdicion.push({
		    categoria : {},
		    candidatos : angular.copy(self.candidatos),
		    participantes : []
		});
	    }

	    self.removeCategoriaEdicion = removeCategoriaEdicion;
	    function removeCategoriaEdicion(categoriaEdicion) {
		self.edicion.categoriasEdicion.splice(
			self.edicion.categoriasEdicion
				.indexOf(categoriaEdicion), 1);
	    }

	    function init() {

		self.candidatos = initCandidatos();
		for (var i = 0; i < self.edicion.categoriasEdicion.length; i++) {
		    self.edicion.categoriasEdicion[i].candidatos = angular
			    .copy(self.candidatos);
		}

		function initCandidatos() {
		    var _candidatos = {};
		    _candidatos["programa"] = self.edicion.programas;
		    _candidatos["noAsociada"] = self.noAsociadas;

		    // Se recorren los programas de la edicion
		    var programas = _candidatos.programa;
		    for (var i = 0; i < programas.length; i++) {
			var programa = programas[i];
			var integrantes = programa.integrantes;

			// Se recorren los integrantes de cada uno de los
			// programas
			for (var j = 0; j < integrantes.length; j++) {
			    var integrante = integrantes[j];
			    integrante.nombre = integrante.asociada.nombre;
			    integrante.apellidos = integrante.asociada.apellidos;

			    var roles = integrante.roles;

			    // Se recorren los roles de cada uno de los
			    // integrantes de cada programa
			    for (var k = 0; k < roles.length; k++) {
				var rol = roles[k].rol;

				if (_candidatos[rol]) {
				    _candidatos[rol].push(integrante);
				} else {
				    _candidatos[rol] = new Array(integrante);
				}
			    }
			}
		    }

		    self.edicion.candidatos = _candidatos;
		    return _candidatos;
		}
	    }
	} else {
	    // Votaciones
	    self.voto = iniciarVoto();
	    self.votar = votar;

	    function iniciarVoto() {

		var _voto = new Voto({
		    idEdicion : self.edicion.id,
		    votos : new Array(),
		    isValid : isValid
		});

		for (var i = 0; i < self.edicion.categoriasEdicion.length; i++) {
		    var _votoCategoria = {
			idCategoriaEdicion : self.edicion.categoriasEdicion[i].id,
			isValid : function() {
			    return _votoCategoria.uno != null
				    && _votoCategoria.dos != null
				    && _votoCategoria.tres != null
				    && _votoCategoria.uno != _votoCategoria.dos
				    && _votoCategoria.uno != _votoCategoria.tres
				    && _votoCategoria.dos != _votoCategoria.tres;
			}
		    };

		    self.edicion.categoriasEdicion[i].voto = _votoCategoria;
		    _voto.votos.push(_votoCategoria);
		}

		function isValid() {
		    var _isValid = true;
		    for (var i = 0; i < _voto.votos.length; i++) {
			if (!_voto.votos[i].isValid()) {
			    _isValid = false;
			}
		    }

		    return _isValid;
		}

		return _voto;
	    }

	    function votar() {
		if (self.voto.isValid()) {
		    self.voto.$save().then(function() {
			alert('voto emitido');
		    });
		}
	    }
	}
    }

})(angular);
(function(angular) {
    'use strict';

    var component = {
	bindings : {
	    ediciones : '=',
	    otras : '='
	},
	controller : homeController,
	controllerAs : '$ctrl',
	templateUrl : 'app/home/home.html'
    };

    angular.module('app').component('home', component);

    homeController.$inject = [];

    function homeController() {
    }

})(angular);
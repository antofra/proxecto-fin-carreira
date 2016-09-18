(function(angular) {
    'use strict';

    angular.module('app').directive('idiomas', function() {
	return {
	    templateUrl : 'app/components/idiomas/idiomas.html',
	    replace : true,
	    controller : 'LanguageController',
	    controllerAs : 'languageVm'
	};
    });

})(angular);

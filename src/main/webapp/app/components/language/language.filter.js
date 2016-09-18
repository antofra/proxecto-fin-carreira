(function() {
    'use strict';

    angular.module('app').filter('findLanguageFromKey', findLanguageFromKey);

    function findLanguageFromKey() {
	return findLanguageFromKeyFilter;

	function findLanguageFromKeyFilter(lang) {
	    return {
		'es' : 'Castellano',
		'gl' : 'Galego'
	    }[lang];
	}
    }

})();
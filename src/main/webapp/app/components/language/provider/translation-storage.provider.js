(function() {
    'use strict';

    angular.module('app').factory('translationStorageProvider',
	    translationStorageProvider);

    translationStorageProvider.$inject = [ '$cookies', 'LANGUAGES' ];

    function translationStorageProvider($cookies, LANGUAGES) {
	return {
	    get : get,
	    put : put
	};

	function get(name) {
	    if (LANGUAGES.indexOf($cookies.getObject(name)) === -1) {
		$cookies.putObject(name, 'es');
	    }

	    return $cookies.getObject(name);
	}

	function put(name, value) {
	    $cookies.putObject(name, value);
	}
    }
})();
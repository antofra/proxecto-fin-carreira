(function() {
    'use strict';

    angular.module('app').factory('stateHandler', stateHandler);

    stateHandler.$inject = [ '$rootScope', '$state', '$translate',
	    'LanguageService', 'Auth', 'Principal' ];

    function stateHandler($rootScope, $state, $translate, LanguageService,
	    Auth, Principal) {
	return {
	    initialize : initialize
	};

	function initialize() {

	    var stateChangeStart = $rootScope.$on('$stateChangeStart',
		    function(event, toState, toStateParams, fromState) {
			$rootScope.toState = toState;
			$rootScope.toStateParams = toStateParams;
			$rootScope.fromState = fromState;

			if (Principal.isIdentityResolved()) {
			    Auth.authorize();
			}

			LanguageService.getCurrent().then(function(language) {
			    $translate.use(language);
			});
		    });

	    $rootScope.$on('destroy', function() {
		if (angular.isDefined(stateChangeStart)
			&& stateChangeStart != null) {
		    stateChageStart();
		}
	    });

	}
    }
})();

(function(angular) {
    angular.module(
	    'app',
	    [ 'ngResource', 'ui.router', 'ngTable', 'ngStorage',
		    'tmh.dynamicLocale', 'pascalprecht.translate', 'ngCookies',
		    'angucomplete-alt', 'checklist-model',
		    'isteven-multi-select', 'ui.bootstrap',
		    'angularjs-dropdown-multiselect',
		    'ui.bootstrap.datetimepicker', 'ngCacheBuster' ]).run(run)
	    .config(config);

    run.$inject = [ 'stateHandler' ];
    function run(stateHandler) {
	stateHandler.initialize();
    }

    // config.$inject = [];
    function config($urlRouterProvider, $httpProvider, $translateProvider,
	    tmhDynamicLocaleProvider, httpRequestInterceptorCacheBusterProvider) {
	$urlRouterProvider.otherwise('/');
	$httpProvider.interceptors.push('authInterceptor');
	$httpProvider.interceptors.push('authExpiredInterceptor');

	$translateProvider.useLoader('$translatePartialLoader', {
	    urlTemplate : 'i18n/{lang}/{part}.json'
	});

	$translateProvider.preferredLanguage('es');
	$translateProvider.useStorage('translationStorageProvider');
	$translateProvider.useSanitizeValueStrategy('escaped');
	$translateProvider
		.addInterpolation('$translateMessageFormatInterpolation');

	tmhDynamicLocaleProvider
		.localeLocationPattern('i18n/angular-locale_{{locale}}.js');
	tmhDynamicLocaleProvider.useCookieStorage();
	tmhDynamicLocaleProvider.storageKey('NG_TRANSLATE_LANG_KEY');

	// Cache everything except rest api requests
	httpRequestInterceptorCacheBusterProvider.setMatchlist([ /.*api.*/ ],
		true);
    }

})(angular);

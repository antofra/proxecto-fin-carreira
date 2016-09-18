(function(angular) {
    'use strict';

    angular.module('app').directive('hasAnyAuthority', directive);

    directive.$inject = [ 'Principal' ];

    function directive(Principal) {
	return {
	    restrict : 'A',
	    link : link
	};

	function link(scope, element, attrs) {
	    var authorities = attrs.hasAnyAuthority.replace(/\s+/g, '').split(
		    ',');

	    function defineVisibility() {
		element.addClass('hidden');
		if (Principal.hasAnyAuthority(authorities)) {
		    element.removeClass('hidden');
		}
	    }

	    if (authorities.length > 0) {
		defineVisibility();

		scope.$watch(function() {
		    return Principal.isAuthenticated();
		}, function() {
		    defineVisibility();
		});
	    }
	}
    }
})(angular);

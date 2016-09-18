(function(angular) {
    'use strict';

    var Alert = {
	template : '<div class="alerts" ng-cloak="">'
		+ '<div ng-repeat="alert in $ctrl.alerts" ng-class="[alert.position, {\'toast\': alert.toast}]">'
		+ '<uib-alert ng-cloak="" type="{{alert.type}}" close="alert.close($ctrl.alerts)">'
		+ '<pre>' + '{{ alert.msg }}' + '</pre>' + '</uib-alert>'
		+ '</div>' + '</div>',
	controller : AlertController
    };

    angular.module('app').component('alert', Alert);

    AlertController.$inject = [ '$scope', 'AlertService' ];

    function AlertController($scope, AlertService) {
	var vm = this;

	vm.alerts = AlertService.alerts;
	$scope.$on('$destroy', function() {
	    vm.alerts = [];
	});
    }
})(angular);

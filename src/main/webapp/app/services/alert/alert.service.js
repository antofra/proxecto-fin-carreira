(function(angular) {
    'use strict';

    angular.module('app').factory('AlertService', AlertService);

    AlertService.$inject = [ '$translate', '$timeout', '$sce' ];

    function AlertService($translate, $timeout, $sce) {
	var alertId = 0;
	var alerts = [];
	var timeout = 5000;

	return {
	    alerts : alerts,
	    add : add,
	    error : error,
	    factory : factory,
	    closeAlert : closeAlert
	};

	function add(options) {
	    options.alertId = alertId++;
	    options.msg = $translate.instant(options.msg, options.params);

	    var that = this;
	    var alert = this.factory(options);

	    if (options.timeout && options.timeout > 0) {
		$timeout(function() {
		    that.closeAlert(options.alertId);
		}, options.timeout);
	    }

	    return alert;
	}

	function error(msg, params, position) {
	    return this.add({
		type : 'danger',
		msg : msg,
		params : params,
		timeout : timeout,
		position : position
	    });
	}

	function factory(options) {
	    var alert = {
		type : options.type,
		msg : $sce.trustAsHtml(options.msg),
		id : options.alertId,
		timeout : options.timeout,
		position : options.position ? options.position : 'top right',
		close : function(alerts) {
		    return closeAlert(this.id, alerts);
		}
	    };
	    alerts.push(alert);
	    return alert;
	}

	function closeAlert(id) {
	    return alerts.splice(alerts.find(function(a) {
		return a.id === id;
	    }), 1);
	}
    }
})(angular);

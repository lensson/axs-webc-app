'use strict';

/* Services */

var webcServices = angular.module('webcServices', ['ngResource']);

webcServices.factory('User', ['$resource',
  function($resource){
	var User = $resource('/api/users/:userId', {userId: '@id'});
	return User;
  }]);

'use strict';

/* App Module */

var webcApp = angular.module('webcApp', [
  'ngRoute',
  'webcControllers',
  'webcFilters',
  'webcServices'
]);

webcApp.config(['$routeProvider',
  function($routeProvider) {
    $routeProvider.
      when('/users', {
        templateUrl: 'partials/user-list.html',
        controller: 'UserListCtrl'
      }).
      when('/users/:userId', {
        templateUrl: 'partials/user-detail.html',
        controller: 'UserDetailCtrl'
      }).
      otherwise({
        redirectTo: '/users'
      });
  }]);
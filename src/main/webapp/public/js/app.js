'use strict';

/* App Module */

var webcApp = angular.module('webcApp', [
  'ngRoute',
  'ui.bootstrap', 
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
      when('/users/:id/', {
        templateUrl: 'partials/user-detail.html',
        controller: 'UserDetailCtrl'
      }).
      otherwise({
        redirectTo: '/users'
      });
  }]);

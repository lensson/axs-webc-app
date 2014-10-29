'use strict';

/* Controllers */

var webcControllers = angular.module('webcControllers', []);

/*
webcControllers.controller('UserListCtrl', ['$scope', 'User',
  function($scope, User) {
    $scope.users = User.query();
    $scope.orderProp = 'id';
  }]);
*/

webcControllers.controller('UserListCtrl', function($scope, $http) {
	  $http.get('/api/users').success(function(data) {
	    $scope.users = data;
	  });

	  $scope.orderProp = 'id';
	});


webcControllers.controller('UserDetailCtrl', ['$scope', '$routeParams', 'User',
  function($scope, $routeParams, User) {
    $scope.user = Phone.get({userId: $routeParams.userId}, function(user) {
//      $scope.mainImageUrl = phone.images[0];
    });

//    $scope.setImage = function(imageUrl) {
//      $scope.mainImageUrl = imageUrl;
//    }
  }]);

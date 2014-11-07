'use strict';

/* Controllers */

var webcControllers = angular.module('webcControllers', []);

/*
 * webcControllers.controller('UserListCtrl', ['$scope', 'User',
 * function($scope, User) { $scope.users = User.query(); $scope.orderProp =
 * 'id'; }]);
 */

webcControllers.controller('UserListCtrl', function($scope, $http, $modal) {
	$http.get('/api/user').success(function(data) {
		$scope.users = data;
	});

	$scope.orderProp = 'id';

	$scope.openNewUserDlg = function() {
		var modalInstance = $modal.open({
			templateUrl : '/partials/new_user.html',
			controller : 'AddNewUserCtrl',
			scope : $scope
		});

		modalInstance.result.then(function(newUser) {
			$scope.users.push(newUser);
		}, function() {
		}
		);
	};
});

webcControllers.controller('AddNewUserCtrl', function($scope, $modalInstance,
		$http) {
	$scope.user = {
		name : null,
		description : null
	};

	$scope.cancel = function() {
		$modalInstance.dismiss('cancel');
	};

	$scope.submit = function() {
		$scope.submitting = true;
		$http({
			method : 'POST',
			url : '/api/user',
			data : $scope.user
		}).success(function(data) {
			$scope.submitting = false;
			$modalInstance.close(data);
		}).error(function(data, status) {
			$scope.submitting = false;
			if (status === 400)
				$scope.badRequest = data;
			else if (status === 409)
				$scope.badRequest = 'The name is already used.';
		});
	};
});

webcControllers.controller('UserDetailCtrl', function($scope, $routeParams,
		$http) {
	$http.get('/api/user/' + $routeParams.id + "/users").success(
			function(data) {
				$scope.user = data;
			});
});
/*
 * + $routeParams.id webcControllers.controller('UserDetailCtrl', ['$scope',
 * '$routeParams', 'User', function($scope, $routeParams, User) {
 * 
 * $scope.user = Phone.get({userId: $routeParams.userId}, function(user) { //
 * $scope.mainImageUrl = phone.images[0]; }); // $scope.setImage =
 * function(imageUrl) { // $scope.mainImageUrl = imageUrl; // } }]);
 */

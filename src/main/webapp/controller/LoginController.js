myApp.controller("loginController", function($scope, $location, $http,
		$rootScope,$window) {
	$rootScope.mainHeader = true;
	$rootScope.mainHeader1 = false;

	/* Verifying whether already a session exists */
	//                       $http.post("getuser", {}).then(function(response) {
	//                           var username = response.data.message;
	//                           if (username !== null) {
	//                        	   $location.path( "/products" );
	//                           } 
	//                       });
	/* Register Function */
	$scope.authenticate = function() {
		var data = {
			'username' : $scope.username,
			'password' : $scope.password
		};
		/* Sending the data to the server*/
		$http.post("authenticateUser", data).then(function(response) {
			if (response.data.message == "success") {
				$location.path("/products");
			} else {
				$scope.errorMessage = response.data.message;
			}
		});
	};

});

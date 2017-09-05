
		myApp.controller(
				"registerController",
				function($scope, $rootScope, $location, $http) {
					/* Verifying whether already a session exists */
					// $http.post("getuser", {}).then(function(response) {
					// var username = response.data.message;
					// if (username == null) {
					// console.log("No session data found");
					// } else {
					// $location.path( "/products" );
					// }
					// });
					/* Register Function */
					$rootScope.mainHeader = true;
					$rootScope.mainHeader1 = false;

					$scope.register = function() {
						if ($scope.password != $scope.confirmPassword) {
							$scope.message = "Password Mismatch !";
							return;
						}
						var data = {
							'firstName' : $scope.firstName,
							'lastName' : $scope.lastName,
							'username' : $scope.username,
							'email' : $scope.email,
							'password' : $scope.password
						};
						/* Sending the data to the server */
						$http
								.post("registerUser", data)
								.then(
										function(response) {
											if (response.data.message == "success") {
												$rootScope.activationMessage = "Signup Successful.Welcome to NKart!!!. Email Verification Needed.Before you can login,please check mail and activate your account";
												$location.path("/login");
											} else {
												$scope.message = response.data.message;
											}
										});
					};

				});
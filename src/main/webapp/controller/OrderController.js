myApp.controller("orderController", function($scope, $rootScope, $location,
		$http, $window) {

	//                	   /* Verifying whether already a session exists */
	//                       $http.post("getuser", {}).then(function(response) {
	//                           var username = response.data.message;
	//                           if (username == null) {
	//                               window.location = '/';
	//                           } else {
	//                               $scope.username = response.data.message;
	//                               //document.getElementById("username").innerHTML = response.data.message;	
	//                           }
	//                       });

	$http.get("getOrders").then(function(response) {
		$scope.orders = response.data;
		$rootScope.cartCount = response.data.length;
		if (response.data.length == 0) {
			$scope.message = "No Orders found";
		}
	});

	$scope.navigateToOrderPayment = function(orders) {

		var popupWindow = $window.open("#/payments", "_blank")
		popupWindow.paymentOrders = orders;
	};

	$scope.removeOrder = function(orderId) {

		$http.post("removeOrder?orderId=" + orderId).then(function(response) {
			if (response.data.message == "success") {
				$http.get("getOrders").then(function(response) {
					$scope.orders = response.data;
					$rootScope.cartCount = response.data.length;
					if (response.data.length == 0) {
						$scope.message = "No Orders found";
					}
				});
			} else {
				$scope.message = response.data.message;
			}
		});

	}

});
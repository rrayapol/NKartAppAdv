myApp.controller("productController", function($scope, $rootScope, $location,
		$http, $window) {
	/* Verifying whether already a session exists */

	$scope.productslist = [ "Fossile", "Raybon" ];
	$scope.showCartButton = false;
	//              	     $http.post("getuser", {}).then(function(response) {
	//                      var username = response.data.message;
	//                      if (username == null) {
	//                          window.location = '/';
	//                      } else {
	//                          $scope.username = response.data.message;
	//                          //document.getElementById("username").innerHTML = response.data.message;	
	//                      }
	//                  });
	//
	/* Get all the products */
	$http.get("getProducts").then(function(response) {
		$scope.products = response.data;
		$scope.globalProducts = $scope.products;
	});

	$scope.navigationToOrders = function() {
		$location.path("/orders");
	};

	$scope.navigateToPayment = function(product) {
		$scope.paymentOrders = [ {
			"productName" : product.name,
			"quantity" : 1,
			"amount" : product.price
		} ];
		var popupWindow = $window.open("#/payments", "_blank")
		popupWindow.paymentOrders = $scope.paymentOrders;
	};

	/* Search Function */
	$scope.searchfunc = function() {
		var prods = [];
		var keyword = document.getElementById("search").value;
		if (keyword == null || keyword == "") {
			alert("Enter a search keyword");
			return;
		}
		keyword = keyword.toLowerCase();
		$.each($scope.globalProducts, function() {
			if (this.name.toLowerCase().indexOf(keyword) > -1) {
				prods.push(this);
			} else if (this.description.toLowerCase().indexOf(keyword) > -1) {
				prods.push(this);
			}
		});
		$scope.products = prods;
	};
	/* Clear Function */
	$scope.clear = function() {
		$scope.products = $scope.globalProducts;
		document.getElementById("search").value = "";
	};

	/* Placing Order Function */
	$scope.placeOrder = function(product) {
		$http.post("createOrder", product).then(function(response) {
			$scope.showCartButton = false;
			$location.path("/orders");
		});
	}

});

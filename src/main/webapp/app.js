var myApp = angular.module("NKartApp", ["ngRoute"]).config(
		function($routeProvider, $locationProvider) {
			$locationProvider.hashPrefix('');
			$routeProvider.when("/login", {
				templateUrl : "templates/login.html",
				controller : "loginController"
			}).when("/register", {
				templateUrl : "templates/register.html",
				controller : "registerController"
			})

			.when("/products", {
				templateUrl : "templates/products.html",
				controller : "productController"
			}).when("/orders", {
				templateUrl : "templates/orders.html",
				controller : "orderController"
			}).when("/payments", {
				templateUrl : "templates/payments.html",
				controller : "paymentController"
			}).otherwise({
				redirectTo : '/login'
			});

		});

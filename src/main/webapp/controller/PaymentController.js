myApp.controller("paymentController", function($scope, $rootScope, $window) {
	$rootScope.mainHeader = false;
	$rootScope.mainHeader1 = true;
	$scope.paymentOrders = $window.paymentOrders;
	$scope.paymentNo = 1;
	$scope.invoiceNo = Math.floor((Math.random() * 100000) + 1);
	$scope.orderDate = new Date();
	var totalAmount = 0;

	if ($scope.paymentOrders) {

		for (var i = 0; i < $scope.paymentOrders.length; i++) {

			totalAmount = totalAmount + $scope.paymentOrders[i].amount;

		}
	}
	$scope.totalAmount = totalAmount;

});
let app = angular.module("nguyen", [])
app.controller("dovan", function ($scope, $http) {
    let endPoint = "http://localhost:6969/demov1/";

    $scope.signIn = function () {
        $http.post(endPoint + "api/loginJWT", $scope.user).then(
            function (item) {

            }
        )
    }
})
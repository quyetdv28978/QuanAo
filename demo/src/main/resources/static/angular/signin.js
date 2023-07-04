let app = angular.module("nguyen",[])
// var app = angular.module("signIp", [])


    app.controller("dovan", function ($scope, $http){
        console.log("quyetclick")
    $scope.signIn = function(){
        console.log("quyet click")
        console.log($scope.user)
        $http.get("http://localhost:6969/api/users1/" + $scope.user.tk).then(
            window.alert("Dang nhap thanh cong")
        )
}
})
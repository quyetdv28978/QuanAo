let app = angular.module("nguyen",[])
// var app = angular.module("signIp", [])


    app.controller("dovan", function ($scope, $http){
        console.log("quyetclick")
    $scope.signIn = function(){
        console.log("quyet click")
        console.log($scope.user)
        $http.post("http://localhost:6969/api/loginJWT", $scope.user).then(
            function (item){
                window.alert("Dang nhap thanh cong")
                console.log(item)
            }
        )
}
})
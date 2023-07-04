let app = angular.module("quyet",[]);

app.controller("dovan", function ($scope,$http, $locale){
    console.log("quyet click 2")

    $scope.signUp = function (){
        console.log("hoi met nha")
    $http.post("http://localhost:6969/api/users2", $scope.user).then(function (item){
        console.log($scope.user)
        swal("Thông báo","Dang ky thanh cong", "success");
        
    }).catch(
    function (item){
window.alert("Dang ky that bai")    }
    )
}

})

app.controller("getData", function ($scope, $http){
  console.log('quyet')
})


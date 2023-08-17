var app = angular.module("detail", []);
var end_point = "http://localhost:6969/demov1/";

app.controller("detailDV", function ($scope, $http){
    var id = document.getElementById("idsp").value;
    var idlsp;
    $scope.slid2 = 1;
        $http.get(end_point+"api/sanpham/"+id).then(function (item){
            $scope.sanpham=item.data;
            idlsp = item.data.loaisanphamviewmodel.id;
            $scope.anh = item.data.images[0].substring(item.data.images[0].indexOf("h"));
            $scope.anh2 = item.data.images[1].substring(item.data.images[0].indexOf("h"));
            $scope.anh3 = item.data.images[2].substring(item.data.images[0].indexOf("h"));

            $http.get(end_point+"api/sanphamBySL/" + Number(idlsp)).then(function (item){
                $scope.spCungLoai = item.data;
                console.log(item.data);
            })
    })

    sessionStorage.length != 0 ?$scope.sldh = sessionStorage.length-1:1;

    $scope.addCart = function (item) {
        console.log(document.getElementById("userid").value);
        if (document.getElementById("userid").value.length!=0) {
            console.log($scope.slid2)
            if ($scope.slid2 > 0) {
                var check = 0;
                for (let i = 0; i < sessionStorage.length; i++) {
                    let key = sessionStorage.key(i);
                    if (key == item) {
                        check = 1;
                        sessionStorage.setItem(key, Number(sessionStorage.getItem(key)) + Number($scope.slid2))
                        break
                    }
                }
                if (check == 0) {
                    sessionStorage.setItem(item, $scope.slid2);
                }
                swal("Thông báo", "Thêm thành công", "success");
            } else {
                swal("Thông báo", "Số lượng phải lớn hớn 0", "warning")
            }
        }
        else{
            swal("Thông báo", "Vui lòng đăng nhập", "warning")

        }
    }

//     San pham tuong tu

})
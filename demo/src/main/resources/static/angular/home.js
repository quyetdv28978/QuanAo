var app = angular.module("hom", []);
var numberSP = 0;
var end_point = "http://localhost:6969/demov1/";


app.controller("ie", function ($scope, $http) {
    sessionStorage.length != 0 ? $scope.sldh = sessionStorage.length-1:1;
    console.log(sessionStorage.length)
    $http.get(end_point+"api/sanphambysl").then(function (item) {
        $scope.quickview = function (item) {
            if (document.getElementById("userid").value.length != 0) {
                document.getElementById("ip").setAttribute("value", 1);
                // diablog.showModal();
                // diablog2.style.display = "block";
                $scope.sanpham = $scope.products.find(function (i) {
                    if (i.id == item) {
                        return i;
                    }
                });
            } else {
                swal("Thông báo","Vui lòng đăng nhập để mua hàng", "warning")
            }

        }


            // $scope.addCart = function (item) {
            // if (document.getElementById("ip").value > 0) {
            //     sessionStorage.setItem(item, document.getElementById("ip").value);
            //     $scope.slP = sessionStorage.length-1;
            //     document.getElementById("ip").value = 0;
            //     alert("Them thanh cong");
            // }
            // else{
            //     alert("Số lượng phải lớn hơn 0")
            // }
            // }
            $scope.products = item.data
// $scope.products = $scope.products.filter(function (i){
//      console.log(i.images  + '?timestamp=' + Date.now())
//     return i.images  + '?timestamp=' + Date.now();
// })
        $scope.products.forEach(i =>
            i.images[0] = i.images[0]  + '?timestamp=' + Date.now()
        )
        })

    $scope.logout = function (){
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            console.log(key)
            sessionStorage.removeItem(key)
        }
        // location.reload();
        window.location.reload();
    }
})
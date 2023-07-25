var app = angular.module("hom", []);
var numberSP = 0;


app.controller("ie", function ($scope, $http) {
    var diablog = document.querySelector('#myDialog');
    var diablog2 = document.querySelector('#dialog2');
    diablog2.style.width = 50%
    $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
        $scope.quickview = function (item) {
            if (document.getElementById("userid").value.length != 0) {
                // $scope.sanpham.sl = 1;
                document.getElementById("ip").setAttribute("value", 1);
                diablog.showModal();
                diablog2.style.display = "block";
                $scope.sanpham = $scope.products.find(function (i) {
                    if (i.id == item) {
                        return i;
                    }
                });
            } else {
                swal("Thông báo","Vui lòng đăng nhập để mua hàng", "warning")
            }

        }

            $scope.close = function () {
                document.querySelector('#myDialog').close();
                diablog2.style.display = "none"
            }

            $scope.addCart = function (item) {
            if (document.getElementById("ip").value > 0) {
                console.log(item);
                console.log(document.getElementById("ip").value)
                sessionStorage.setItem(item, document.getElementById("ip").value);
                $scope.slP = sessionStorage.length;
                alert("Them thanh cong");
            }
            else{
                alert("Số lượng phải lớn hơn 0")
            }
            }
            $scope.products = item.data
// $scope.products = $scope.products.filter(function (i){
//      console.log(i.images  + '?timestamp=' + Date.now())
//     return i.images  + '?timestamp=' + Date.now();
// })
        $scope.products.forEach(i =>
            i.images[0] = i.images[0]  + '?timestamp=' + Date.now()
        )


        console.log($scope.products)

        })

    $scope.man = function (){
        console.log("man")
        $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
            $scope.products = item.data.filter(function (item){

                return item.tensanpham == "ao dui"
            });
        })
    }

    $scope.women = function (){
        console.log("women")
        $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
            $scope.products = item.data.filter(function (item){

                return item.tensanpham == "hehe"
            });
        })
    }

    $scope.all = function (){
        console.log("all")
        $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
            $scope.products = item.data;
        })
    }

    $scope.logout = function (){
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            sessionStorage.removeItem(key)
        }
    }

})
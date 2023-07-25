var app = angular.module("product", []);

app.controller("sp", function ($scope, $http){
    var diablog = document.querySelector('#myDialog');
    var diablog2 = document.querySelector('#dialog2');
    console.log("product")
    $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
        $scope.products = item.data;
    })

    $scope.quickview = function (item) {
        if (document.getElementById("userid").value.length != 0) {

            document.getElementById("ip").setAttribute("value", 1);
            console.log(document.querySelector('#myDialog'));
            diablog.showModal();
            diablog2.style.display = "block";
            $scope.sanpham = $scope.products.find(function (i) {
                if (i.id == item) {
                    return i;
                }
            });
        }else{
            swal("Vui lòng đăng nhập để mua hàng")
        }
    };

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
        window.alert("Them thanh cong");
        }
        else{
            alert("Số lượng phải lớn hơn 0")
        }
    }

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

    $scope.search = function (item) {
        console.log(item.keyCode)
        console.log($scope.tenSP)
        if ($scope.tenSP.length == 0) {
            $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
                $scope.products = item.data
            })
        } else {
            if (item.keyCode == 13) {
                $http.get("http://localhost:6969/demov1/api/sanpham").then(function (item) {
                    $scope.products = item.data.filter(function (i) {
                        console.log(i.tensanpham)
                        console.log(item)
                        return i.tensanpham == $scope.tenSP
                    });
                })
            }
        }
    }

    $scope.logout = function (){
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            sessionStorage.removeItem(key)
        }
    }

})

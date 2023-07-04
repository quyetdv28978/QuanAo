var app = angular.module("shoping",[]);
var productCart = [];

app.controller("cart", function ($scope, $http) {
    console.log(sessionStorage)
    var tong = 0;
    $http.get("http://localhost:6969/api/sanpham").then(function (item) {
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            let value = sessionStorage.getItem(key);

            item.data.find(function (p){
                if (p.id == key){
                    console.log(p.ma)
                    p.slsp = Number(value)
                    tong +=(Number(value)*p.giaban)
                    console.log(tong)
                    productCart.push(p);
                }
            })
        }
        $scope.tongtien = tong;
        console.log(productCart)
    })
    $scope.products = productCart;
    console.log(document.querySelectorAll(".ma2").length)


    $scope.thanhtoan = function () {
        console.log(document.getElementById("userid").value
        )
        if (document.getElementById("userid").value.length != 0) {
            var check = true;
            let slsp2 = document.querySelectorAll(".ma2");
            for (let i = 0; i < slsp2.length; i++) {
                if (slsp2[i].value <= 0) {
                    check = false;
                    break;
                }
                productCart.slsp = slsp2[i];
            }
            if (check == false) {
                alert("So Luong san pham lon hon 0")
            } else {
                let donhang = [];
                let sanpham;
                let idsp = [];
                let sl = 0;
                console.log($scope.products)
                for (let i = 0; i < sessionStorage.length; i++) {
                    let key = sessionStorage.key(i);
                    let value = sessionStorage.getItem(key);
                    idsp.push(Number(key));
                    sl += Number(value);
                }
                sanpham = {
                    idUser : document.getElementById("userid").value,
                    soluong: sl,
                    idsanphams: idsp,
                    tongTien: $scope.tongtien
                }
                donhang.push(sanpham)
                console.log(donhang)
                // $http.post("http://localhost:6969/api/donhang", donhang[0]).then(function (item) {
                //     alert("them thanh cong")
                // })
            }
        }
        else{
            swal('Thông báo', "Vui lòng đăng nhập để thanh toán", 'warning')

        }
    }

    $scope.logout = function (){
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            sessionStorage.removeItem(key)
        }
    }

})



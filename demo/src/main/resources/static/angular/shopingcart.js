var app = angular.module("shoping", []);
var productCart = [];
var end_point = "http://localhost:6969/demov1/";

app.controller("cart", function ($scope, $http) {
    var tong = 0;
    $http.get(end_point+"api/sanpham").then(function (item) {
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            let value = sessionStorage.getItem(key);

            item.data.find(function (p) {
                if (p.id == key) {
                    p.slsp = Number(value)
                    tong += (Number(value) * p.giaban)
                    productCart.push(p);
                }
            })
        }
        $scope.tongtien = tong;
    })
    $scope.products = productCart;


    $scope.thanhtoan = function () {
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
                for (let i = 0; i < sessionStorage.length; i++) {
                    let key = sessionStorage.key(i);
                    if (key !="jwttk") {
                        let value = sessionStorage.getItem(key);
                        idsp.push(Number(key));
                        sl += Number(value);
                    }
                }
                sanpham = {
                    idUser: document.getElementById("userid").value,
                    soluong: sl,
                    idsanphams: idsp,
                    tongTien: $scope.tongtien
                }
                donhang.push(sanpham)
                $http.post(end_point+"api/donhang", donhang[0], {
                    headers: {
                        "Authorization": "Bearer " + sessionStorage.getItem("jwttk")
                    }
                }).then(function (item) {
                    console.log(sessionStorage.length)
                    for (let i = 0; i < sessionStorage.length; i++) {
                        let key = sessionStorage.key(i);
                        console.log(key == "jwttk")
                        if (key != "jwttk") {
                            sessionStorage.removeItem(key)
                            i--;
                        }
                    }
                    swal('Thông báo', "Thanh toán thành công", 'success')
                    setTimeout(function () {
                        location.reload();
                    }, 2000);
                })
            }
        } else {
            swal('Thông báo', "Vui lòng đăng nhập để thanh toán", 'warning')
        }
    }

    $scope.logout = function () {
        for (let i = 0; i < sessionStorage.length; i++) {
            let key = sessionStorage.key(i);
            sessionStorage.removeItem(key)
        }
        location.reload();
    }

})



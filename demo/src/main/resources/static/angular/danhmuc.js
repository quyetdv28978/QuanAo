var b = angular.module("quyet", []);
document.getElementById("a").style.height = window.innerHeight + "px";
var tokenJWT = sessionStorage.getItem("jwttk")
var end_point = "http://localhost:6969/demov1/";

// Trang danh muc
b.controller("danhmuc", function ($http, $scope) {
    $http.get(end_point+"api/danhmuc", {
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (item) {
        $scope.danhmucs = item.data;
        $scope.danhmucs.forEach(i => {
            if (i.trangthai != 1) {
                i.trangthai = 'khong hoat dong'
            } else i.trangthai = "Hoat Dong"
        })
    })

    $scope.xoa = function (item) {
        $http.delete(end_point+"api/danhmuc/" + item,{
            headers: {
                "Authorization": "Bearer " + tokenJWT
            }
        }).then(
            location.reload()
        )
    }
})

// Trang them danh muc
b.controller("dovan", function ($scope, $http, $rootScope) {
    // Loai san pham
    $http.get(end_point+"api/loaisanpham",{
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (item) {
        $scope.danhmucs = item.data;
    })

//     chon san pham
    var check = true;
    var sanphamChon = [];
    $scope.selectSP = function (id) {
        var checkSP = false;
        if (sanphamChon.length == 0) {
            document.getElementsByClassName(id)[0].style.background = "gray";
            sanphamChon.push(id)
        } else {
            for (let i = 0; i < sanphamChon.length; i++) {
                if (sanphamChon[i] == id) {
                    sanphamChon.splice(i, 1);
                    document.getElementsByClassName(id)[0].style.background = "none";
                    checkSP = true;
                    break;
                }
            }
            if (checkSP == false) {
                sanphamChon.push(id)
                document.getElementsByClassName(id)[0].style.background = "gray";
            }
        }
    }

//     San pham
    $http.get(end_point+"api/sanpham",{
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (item) {
        $scope.products = item.data;
    })

//     Danh muc
    $scope.themDM = function () {
        if (document.getElementById("name").value.length != 0) {

            $scope.danhmuc.idSP = sanphamChon;
            $scope.danhmuc.trangthai = $scope.danhmuc.trangthai == 'Ẩn' ? 0 : 1
            $http.post(end_point+"api/danhmuc", $scope.danhmuc,{
                headers: {
                    "Authorization": "Bearer " + tokenJWT
                }
            }).then(function (item) {
                swal("Thông báo", "Thêm danh mục thành công", "success");
                location.reload();
            })
        } else {
            swal("Thông báo", "Thông tin không được để trống", "warning");
        }
    }
})

// Trang sua danh muc

b.controller("suadm", function ($scope, $http, $rootScope, $location) {
    var iddm = document.getElementById("iddm");
    var IDSD = [];
    $http.get(end_point+"api/danhmuc/" + iddm.value,{
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (item) {
        IDSP = item.data.listSanPham;
        sanphamChon.splice(0, sanphamChon.length)
        $scope.products = item.data.listSanPham;
    })
    // Hien thi san pham

    $http.get(end_point+"api/danhmuc/" + iddm.value,{
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (item) {
        $scope.danhmuc = item.data;
        console.log(item)
    })


    // Chon sp theo loai san pham
    $http.get(end_point+"api/loaisanpham",{
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (data) {
        $scope.danhmucs = data.data;
    })

    //     chon san pham
    var check = true;
    var sanphamChon = [];
    $scope.selectSP = function (id) {
        var checkSP = false;
        if (sanphamChon.length == 0) {
            document.getElementsByClassName(id)[0].style.background = "gray";
            sanphamChon.push(id)
        } else {
            for (let i = 0; i < sanphamChon.length; i++) {
                if (sanphamChon[i] == id) {
                    sanphamChon.splice(i, 1);
                    document.getElementsByClassName(id)[0].style.background = "none";
                    checkSP = true;
                    break;
                }
            }
            if (checkSP == false) {
                sanphamChon.push(id)
                document.getElementsByClassName(id)[0].style.background = "gray";
            }
        }
    }

    // Su kien lọc theo loai san pham
    $scope.selectChange = function () {
        let select = $scope.select;
        if (select == "spdm") {
            $http.get(end_point+"api/danhmuc/" + iddm.value,{
                headers: {
                    "Authorization": "Bearer " + tokenJWT
                }
            }).then(function (item) {
                IDSP = item.data.listSanPham;
                sanphamChon.splice(0, sanphamChon.length)
                $scope.products = item.data.listSanPham;
            })
        } else if (select == "allsp") {
            $http.get(end_point+"api/sanpham", {
                headers: {
                    "Authorization": "Bearer " + tokenJWT
                }
            }).then(function (item) {
                var sps = [];
                sanphamChon.splice(0, sanphamChon.length)
                item.data.filter(function (item) {
                    var check = false;
                    IDSP.forEach((i) => {
                        if (i.id == item.id) {
                            check = true;
                        }
                    })
                    if (check == false) {
                        sps.push(item)
                    }
                    return sps
                });
                $scope.products = sps
            })
        }

    }

    //     Su kien xoa san pham danh muc
    $scope.XoaSanPham = function () {
        $scope.products = $scope.products.filter(function (item) {
            var check = true;
            sanphamChon.forEach((i) => {
                if (i == item.ma)
                    check = false;
            })
            if (check == true) {
                return item;
            }
        })

        $http.put(end_point+"api/danhmuc/" + iddm.value, sanphamChon, {
            headers: {
                "Authorization": "Bearer " + tokenJWT
            }
        })
    }

    //     THem danh muc
    $scope.themDM = function () {
        $scope.danhmuc.idSP = sanphamChon;
        $scope.danhmuc.trangthai = $scope.danhmuc.trangthai == 'Ẩn' ? 0 : 1
        $http.put(end_point+"api/danhmuc2/" + iddm.value, $scope.danhmuc, {
            headers: {
                "Authorization": "Bearer " + tokenJWT
            }
        }).then(function (item) {
            swal("Thông báo", "Sua thanh cong", "success");
            location.reload()
        })

    }
})


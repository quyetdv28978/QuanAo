// CRUD San Pham
var a = angular.module("themsp", []);
var checkA = [];
document.getElementById("a").style.height = window.innerHeight + "px";
var tokenJWT = sessionStorage.getItem("jwttk");
var end_point = "http://localhost:6969/demov1/";

// Hien thi san pham
a.controller("sanpham", function ($scope, $http) {
    $http.get(end_point+"api/sanpham").then(function (item) {
        $scope.products = item.data;
    })

    $scope.xoa = function (id) {
        $http.delete(end_point+"api/sanpham/" + id, {
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("jwttk")
            }
        }).then(
            location.reload()
        )
    }
    //     Get DS loai san pham

    $http.get(end_point+"api/loaisanpham", {
        headers: {
            "Authorization": "Bearer " + sessionStorage.getItem("jwttk")
        }
    }).then(function (item) {
            $scope.loais2 = item.data
        }
    )

    $scope.loaiSPChange = function (item){
        console.log(item)
        $http.get(end_point+"api/loaibysanpham/" + item, {
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("jwttk")
            }
        }).then(function (item) {
            $scope.products = item.data;
            }
        )
    }
})

// Them san pham
a.controller("them", function ($scope, $http) {
    // Them san pham
    $scope.themspclick = function () {
        var checkNull = false;
        document.querySelectorAll(".validate").forEach(i => {
            if (i.value.length == 0) {
                checkNull = true;
            }
        })
        if (checkNull == true

        ) {
            swal("Thông báo", "Vui lòng nhập đủ thông tin", "warning")
        } else {
            if ($scope.product.soluong > 0 && $scope.product.giaban > 0 && $scope.product.giagoc > 0) {
                if (nameIMG.length < 3) {
                    swal("Thông báo", "Vui lòng chọn 3 ảnh", "warning");
                } else {
                    if ($scope.loaiSP != null){
                    if ($scope.product.trangthai == "Ẩn") {
                        $scope.product.trangthai = 1;
                    } else $scope.product.trangthai = 0;

                    $scope.product.idlsp = $scope.loaiSP;
                    let formdata = new FormData();

                    nameIMG.forEach(item => formdata.append("image", item));
                    formdata.append("sanpham", new Blob([JSON.stringify($scope.product)], {type: 'application/json'}))

                    $http.post(end_point+"api/sanpham", formdata,
                        {headers: {'Content-Type': undefined}}
                    ).then(function (item) {
                        swal("Thông báo", "Them thanh cong", "success");
                        setTimeout(function(){
                            location.reload();
                        }, 2000);
                    })
                }
                    else {
                        swal("Thông báo", "Vui lòng chọn loại sản phẩm", "warning");
                    }
            }
            } else {
                swal("Thông báo", "Số lượng phải lớn 0", "warning");
            }
        }
    }

    // Chon anh xoa
    $scope.clickA = function (vt) {
        var confirm = window.confirm("Ban co muon xoa anh khong?");
        if (confirm == true) {
            if (checkA.indexOf(vt) != -1) {
                checkA.splice(checkA.indexOf(vt), 1)
                checkA.push(vt);
                check2 = checkA.indexOf(vt);
            } else {
                checkA.push(vt);
            }
            document.querySelectorAll(".image2")[vt].setAttribute("src", "");
        }
    }

//     Them loai san pham
    $scope.themloai = function () {
        console.log($scope.product)
        $scope.loai.trangthai = $scope.loai.trangthai == true ? 1 : 0;
        $http.post(end_point+"api/loaisanpham", $scope.loai).then(
            function () {
                alert("Them thanh cong")
                dialog.close();
                $http.get(end_point+"api/loaisanpham", {
                        headers: {
                            "Authorization": "Bearer " + tokenJWT
                        }
                    }
                ).then(
                    function (item) {
                        $scope.loais = item.data
                    }
                )
            }
        )


    }

//     Get DS loai san pham
    $http.get(end_point+"api/loaisanpham", {
        headers: {
            "Authorization": "Bearer " + tokenJWT
        }
    }).then(function (item) {
        $scope.loais = item.data
    })

    $http.get(end_point+"api/danhmuc",
        {
            headers: {
                "Authorization": "Bearer " + sessionStorage.getItem("jwttk")
            }
        }
        ).then(function (item) {

        $scope.danhmucss = item.data
    })
})

// Sua san pham
a.controller("suasp", function ($scope, $http) {
    var idSP = document.getElementById("idSP");
//     Hien thi san pham cho de sua

    var idAnh = [];
    $http.get('http://localhost:6969/demov1/api/sanpham/' + idSP.value).then(function (a) {
        $scope.selectD = a.data.loaisanphamviewmodel.id;
        a.data.images.forEach(item => idAnh.push(item.substring(0, item.indexOf("h"))))
        console.log(a.data)
        $scope.product = a.data;
        $scope.anh = $scope.product.images[0].substring($scope.product.images[0].indexOf("h"));
        $scope.anh2 = $scope.product.images[1].substring($scope.product.images[0].indexOf("h"));
        $scope.anh3 = $scope.product.images[2].substring($scope.product.images[0].indexOf("h"));
        // $scope.lsp = $scope.product.loaisanphamviewmodel.id
        if ($scope.product.trangthai == 0) {
            $scope.checked = function (item) {
                return false;
            }
        } else $scope.checked2 = function (item) {
            return true;
        }
    })


    // Chon anh xoa
    $scope.clickA = function (vt) {
        var confirm = window.confirm("Ban co muon xoa anh khong?");
        if (confirm == true) {
            if (checkA.indexOf(vt) != -1) {
                checkA.splice(checkA.indexOf(vt), 1)
                checkA.push(vt);
                check2 = checkA.indexOf(vt);
            } else {
                checkA.push(vt);
            }
            document.querySelectorAll(".image2")[vt].setAttribute("src", "");
        }
    }

    $scope.themspclick = function () {
        var checkNull = false;
        document.querySelectorAll(".validate").forEach(i => {
            if (i.value.length == 0) {
                console.log("Day la if value == 0")
                checkNull = true;
            }
        })
        if (checkNull == true

        ) {
            swal("Thông báo", "Vui lòng nhập đủ thông tin", "warning")
        } else {
            console.log("Day la else trong if lan 2")
            var checkImgNull = false;
            document.querySelectorAll(".image2").forEach(i => {
                if (i.getAttribute("src").length == 0) {
                    checkImgNull = true;
                }
            })
            if ($scope.product.soluong > 0 && $scope.product.giaban > 0 && $scope.product.giagoc > 0) {
                console.log("Day la so trong if")
                if (checkImgNull == true) {
                    swal("Thông báo", "Vui lòng chọn 3 ảnh", "warning");
                } else {
                    console.log("Day la else say chon 3 anh")
                    if ($scope.lsp != null) {
                        console.log("Day la check loaiSP")
                        if ($scope.product.trangthai == "Ẩn") {
                            $scope.product.trangthai = 1;
                        } else $scope.product.trangthai = 0;

                        let formdata = new FormData();
                        var imagesID = [];
                        nameIMG.forEach(item => formdata.append("image", item));
                        $scope.product.images = idAnh[checkA];
                        checkA.forEach(i => imagesID.push(idAnh[i]));
                        $scope.product.images = imagesID;
                        $scope.product.idlsp = $scope.lsp;
                        formdata.append("sanpham", new Blob([JSON.stringify($scope.product)], {type: 'application/json'}))
                        $http.put(end_point+"api/sanpham/" + idSP.value, formdata,
                            {headers: {'Content-Type': undefined}}
                        ).then(function (item) {
                            swal("Thông báo", "Sua thanh cong", "success");
                            // location.reload();
                        })
                    }
                }
            } else {
                swal("Thông báo", "Số lượng phải lớn 0", "warning");
            }
        }
    }

//     Them loai san pham
    $scope.themloai = function () {
        $scope.loai.trangthai = $scope.loai.trangthai == true ? 1 : 0;
        $http.post(end_point+"api/loaisanpham", $scope.loai).then(
            function () {
                alert("Them thanh cong")
                // $scope.product.loaiSP = $scope.loai.tenlsp
                dialog.close();
            }
        )
    }

//     Get DS loai san pham

    $http.get(end_point+"api/loaisanpham", {
        headers: {
            "Authorization": "Bearer " + sessionStorage.getItem("jwttk")
        }
    }).then(function (item) {
        $scope.loais = item.data
    }
    )

})





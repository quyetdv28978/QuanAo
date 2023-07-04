// CRUD San Pham
var a = angular.module("themsp", []);
var checkA = [];
document.getElementById("a").style.height = window.innerHeight + "px";


// Hien thi san pham
a.controller("sanpham", function ($scope, $http){
    $http.get("http://localhost:6969/api/sanpham").then(function (item){
        console.log(item.data)
        $scope.products = item.data;
    })

    $scope.xoa = function (id){
        console.log(id)
$http.delete("http://localhost:6969/api/sanpham/"+id).then(
    location.reload()
)
    }
})

// Them san pham
a.controller("them", function ($scope, $http){
    // Them san pham
    $scope.themspclick=function () {
        var checkNull = false;
        console.log($scope.product)
        console.log(document.querySelectorAll(".validate"))
        document.querySelectorAll(".validate").forEach(i => {
            console.log(i.value.length == 0)
            if (i.value.length == 0){
                checkNull = true;
            }
        })
        if (checkNull == true

        ){
            swal("Thông báo", "Vui lòng nhập đủ thông tin", "warning")
        }
        else {
            if ($scope.product.soluong > 0 && $scope.product.giaban > 0 && $scope.product.giagoc > 0) {
                if (nameIMG.length < 3){
                    swal("Thông báo", "Vui lòng chọn 3 ảnh", "warning");
                }
                else {
                    if ($scope.product.trangthai == "Ẩn") {
                        $scope.product.trangthai = 1;
                    } else $scope.product.trangthai = 0;

                    $scope.product.idlsp = $scope.loaiSP;
                    let formdata = new FormData();

                    nameIMG.forEach(item => formdata.append("image", item));
                    formdata.append("sanpham", new Blob([JSON.stringify($scope.product)], {type: 'application/json'}))

                    $http.post("http://localhost:6969/api/sanpham", formdata,
                        {headers: {'Content-Type': undefined}}
                    ).then(function (item) {
                        swal("Thông báo", "Them thanh cong", "success");
                        location.reload();

                    })
                }
            } else {
                swal("Thông báo", "Số lượng phải lớn 0", "warning");
            }
        }
    }

//     Them loai san pham
    $scope.themloai = function (){
        console.log($scope.product)
        $scope.loai.trangthai =  $scope.loai.trangthai == true ? 1 : 0;
        $http.post("http://localhost:6969/api/loaisanpham", $scope.loai).then(
            function (){
                alert("Them thanh cong")
                dialog.close();
                $http.get("http://localhost:6969/api/loaisanpham").then(
                    function (item){
                        $scope.loais = item.data
                    }
                )
            }
        )


    }

//     Get DS loai san pham
$http.get("http://localhost:6969/api/loaisanpham").then(function (item){
    $scope.loais = item.data
})

    $http.get("http://localhost:6969/api/danhmuc").then(function (item){
        console.log(item.data)
        $scope.danhmucss = item.data
    })
})

// Sua san pham
a.controller("suasp", function ($scope, $http){
    var idSP = document.getElementById("idSP");
//     Hien thi san pham cho de sua

    var idAnh = [];
$http.get('http://localhost:6969/api/sanpham/' + idSP.value).then(function (a){
    $scope.selectD = a.data.loaisanphamviewmodel.id;
    console.log($scope.selectD)
    a.data.images.forEach(item => idAnh.push(item.substring(0,item.indexOf("h"))))
    idAnh.forEach(i => console.log(i));
    $scope.product = a.data;
    $scope.anh = $scope.product.images[0].substring($scope.product.images[0].indexOf("h"));
    $scope.anh2 = $scope.product.images[1].substring($scope.product.images[0].indexOf("h"));
    $scope.anh3 = $scope.product.images[2].substring($scope.product.images[0].indexOf("h"));
    // $scope.lsp = $scope.product.loaisanphamviewmodel.id
    if ($scope.product.trangthai == 0){
        $scope.checked = function (item){
            return false;
        }
    }
    else $scope.checked2 = function (item){
        return true;
    }
})


    // Chon anh xoa
    $scope.clickA = function (vt){
        var confirm = window.confirm("Ban co muon xoa anh khong?");
        if (confirm == true){
            if (checkA.indexOf(vt) != -1){
                checkA.splice(checkA.indexOf(vt),1)
                checkA.push(vt);
                check2 = checkA.indexOf(vt);
            }else {
                checkA.push(vt);
            }
            document.querySelectorAll(".image2")[vt].setAttribute("src", "");
        }
    }

    $scope.themspclick=function (){
    var checkNull = false;
        document.querySelectorAll(".validate").forEach(i => {
            console.log(i.value.length == 0)
            if (i.value.length == 0){
                checkNull = true;
            }
        })
        if (checkNull == true

        ){
            swal("Thông báo", "Vui lòng nhập đủ thông tin", "warning")
        }
        else {
            var checkImgNull = false;
           document.querySelectorAll(".image2").forEach(i => {
               if(i.getAttribute("src").length == 0){
                   checkImgNull = true;
               }
           })
            if ($scope.product.soluong > 0 && $scope.product.giaban > 0 && $scope.product.giagoc > 0) {
                if (checkImgNull == true) {
                    swal("Thông báo", "Vui lòng chọn 3 ảnh", "warning");
                } else {

                    if ($scope.product.trangthai == "Ẩn") {
                        $scope.product.trangthai = 1;
                    } else $scope.product.trangthai = 0;

                    let formdata = new FormData();
                    let img = document.querySelector("input[type=file]").files[0];
                    var imagesID = [];
                    nameIMG.forEach(item => formdata.append("image", item));
                    $scope.product.images = idAnh[checkA];
                    checkA.forEach(i => imagesID.push(idAnh[i]));
                    $scope.product.images = imagesID;
                    $scope.product.idlsp = $scope.lsp;
                    formdata.append("sanpham", new Blob([JSON.stringify($scope.product)], {type: 'application/json'}))
                    $http.put("http://localhost:6969/api/sanpham/" + idSP.value, formdata,
                        {headers: {'Content-Type': undefined}}
                    ).then(function (item) {
                        swal("Thông báo", "Sua thanh cong", "success");
                        location.reload();
                    })
                }
            } else {
                swal("Thông báo", "Số lượng phải lớn 0", "warning");
            }
                }
            }

//     Them loai san pham
    $scope.themloai = function (){
        console.log($scope.product)
        $scope.loai.trangthai =  $scope.loai.trangthai == true ? 1 : 0;
        $http.post("http://localhost:6969/api/loaisanpham", $scope.loai).then(
            function (){
                alert("Them thanh cong")
                // $scope.product.loaiSP = $scope.loai.tenlsp
                dialog.close();
            }
        )
    }

//     Get DS loai san pham
    $http.get("http://localhost:6969/api/loaisanpham").then(function (item){
        $scope.loais = item.data
    })

})





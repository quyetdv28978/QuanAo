var b = angular.module("quyet", []);
document.getElementById("a").style.height = window.innerHeight + "px";


// Trang danh muc
b.controller("danhmuc", function ($http, $scope){
    console.log('hahaha')
    $http.get("http://localhost:6969/api/danhmuc").then(function (item){
       $scope.danhmucs = item.data;
        console.log(item.data)
       $scope.danhmucs.forEach(i => {
           if (i.trangthai == 1){
               i.trangthai = 'khong hoat dong'
           }
           else i.trangthai = "Het han"
       })
    })

    $scope.xoa = function (item){
        console.log(item)
        $http.delete("http://localhost:6969/api/danhmuc/"+item).then(
            location.reload()
        )
    }
})

// Trang them danh muc
b.controller("dovan", function ($scope, $http, $rootScope){
    console.log("danh muc")
    // Loai san pham
    $http.get("http://localhost:6969/api/loaisanpham").then(function (item){
        $scope.danhmucs = item.data;
    })

//     chon san pham
var check = true;
    var sanphamChon = [];
$scope.selectSP = function (id){
    console.log(id)
    var checkSP = false;
    console.log(document.getElementsByClassName(id)[0])
    if (sanphamChon.length == 0){
        document.getElementsByClassName(id)[0].style.background="gray";
        sanphamChon.push(id)
    }
    else {
        for (let i = 0; i < sanphamChon.length; i++) {
            console.log('la sao ' + sanphamChon[i])
            if (sanphamChon[i] == id) {
                console.log(sanphamChon[i] +" haha " + id)
                sanphamChon.splice(i, 1);
                console.log(sanphamChon)
                document.getElementsByClassName(id)[0].style.background = "none";
                checkSP = true;
                break;
            }
        }
        if (checkSP == false){
            sanphamChon.push(id)
            document.getElementsByClassName(id)[0].style.background="gray";
        }
        }
    console.log(sanphamChon)
}

//     San pham
    $http.get("http://localhost:6969/api/sanpham").then(function (item){
        console.log(item.data)
        $scope.products = item.data;
    })

//     Danh muc
    $scope.themDM = function (){
    if (document.getElementById("name").value.length != 0)
    {

           console.log(sanphamChon.length)
        $scope.danhmuc.idSP = sanphamChon;
        $scope.danhmuc.trangthai = $scope.danhmuc.trangthai == 'Ẩn' ? 0 : 1
        console.log($scope.danhmuc)
        $http.post("http://localhost:6969/api/danhmuc", $scope.danhmuc).then(function (item){
            swal("Thông báo", "Thêm danh mục thành công", "success");
            location.reload();
        })
    }
    else{
        swal("Thông báo", "Thông tin không được để trống", "warning");
    }
}
})

// Trang sua danh muc

b.controller("suadm", function ($scope, $http, $rootScope, $location){
    var iddm = document.getElementById("iddm");
    console.log(iddm.value)
    var IDSD = [];
    $http.get("http://localhost:6969/api/danhmuc/" + iddm.value).then(function (item) {
        IDSP = item.data.listSanPham;
        sanphamChon.splice(0,sanphamChon.length)
        $scope.products = item.data.listSanPham;
    })
    // Hien thi san pham

    $http.get("http://localhost:6969/api/danhmuc/" + iddm.value).then(function (item){
        $scope.danhmuc = item.data;
        console.log(item)
    })


    // Chon sp theo loai san pham
    $http.get("http://localhost:6969/api/loaisanpham").then(function (data){
        $scope.danhmucs = data.data;
        console.log(data.data)
    })

    //     chon san pham
    var check = true;
    var sanphamChon = [];
    $scope.selectSP = function (id){
        console.log(id)
        var checkSP = false;
        console.log(document.getElementsByClassName(id)[0])
        if (sanphamChon.length == 0){
            document.getElementsByClassName(id)[0].style.background="gray";
            sanphamChon.push(id)
        }
        else {
            for (let i = 0; i < sanphamChon.length; i++) {
                console.log('la sao ' + sanphamChon[i])
                if (sanphamChon[i] == id) {
                    console.log(sanphamChon[i] +" haha " + id)
                    sanphamChon.splice(i, 1);
                    console.log(sanphamChon)
                    document.getElementsByClassName(id)[0].style.background = "none";
                    checkSP = true;
                    break;
                }
            }
            if (checkSP == false){
                sanphamChon.push(id)
                document.getElementsByClassName(id)[0].style.background="gray";
            }
        }
        console.log(sanphamChon)
    }

    // Su kien lọc theo loai san pham
    $scope.selectChange = function (){
        let select = $scope.select;
        if (select == "spdm") {
            $http.get("http://localhost:6969/api/danhmuc/" + iddm.value).then(function (item) {
                IDSP = item.data.listSanPham;
                sanphamChon.splice(0,sanphamChon.length)
                $scope.products = item.data.listSanPham;
            })
        }
            else if (select == "allsp"){
            $http.get("http://localhost:6969/api/sanpham").then(function (item){
                console.log(IDSP)
                var sps = [];
                sanphamChon.splice(0,sanphamChon.length)
                item.data.filter(function (item){
                    var check = false;
                    IDSP.forEach((i) => {
                        console.log("day la i: " + i.id)
                        console.log("day la item: " + item.id)
                        if (i.id == item.id){
                            check = true;
                        }
                    })
                    if (check == false){
                        sps.push(item)
                    }
                    console.log(sps)
                    return sps
                });
                console.log(sps)
                $scope.products = sps
                console.log($scope.products)

            })
            }

        }

    //     Su kien xoa san pham danh muc
    $scope.XoaSanPham = function (){
        console.log(sanphamChon)
        console.log($scope.products)
        $scope.products = $scope.products.filter(function (item){
            var check = true;
            sanphamChon.forEach((i) => {
                if (i == item.ma)
                    check = false;
            })
            if (check == true){
                return item;
            }
        })

        $http.put("http://localhost:6969/api/danhmuc/"+iddm.value, sanphamChon)
    }

    //     THem danh muc
    $scope.themDM = function (){
        console.log(sanphamChon)
        $scope.danhmuc.idSP = sanphamChon;
        $scope.danhmuc.trangthai = $scope.danhmuc.trangthai == 'Ẩn' ? 0 : 1
        $http.put("http://localhost:6969/api/danhmuc2/" + iddm.value, $scope.danhmuc).then(function (item){
            swal("Thông báo", "Sua thanh cong", "success");
            location.reload()
        })

    }

})


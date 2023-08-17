var app = angular.module("product", []);
var trang;
var countPage;
var end_point = "http://localhost:6969/demov1/";

app.controller("sp", function ($scope, $http) {
    sessionStorage.length != 0 ?$scope.sldh = sessionStorage.length-1:1;

    var diablog = document.querySelector('#myDialog');
    var diablog2 = document.querySelector('#dialog2');
    $http.get(end_point+"api/pageableSanPham/0").then(function (item) {
        $scope.products = item.data[0].list_sanpham
        trang = item.data[0].trang;
        countPage = item.data[0].count_page;
        $scope.pageEnd = item.data[0].count_page
        $scope.currenPage = item.data[0].trang
    })

    $scope.loadMore = function () {
        if (Number(trang) == Number(countPage)) {
            trang = 0;
        } else trang = trang + 1;
        $http.get(end_point+"api/pageableSanPham/" + Number(trang)).then(function (item) {
            $scope.products = item.data[0].list_sanpham
            $scope.pageEnd = item.data[0].count_page
            $scope.currenPage = item.data[0].trang
        })
    }

    // $scope.quickview = function (item) {
    //     if (document.getElementById("userid").value.length != 0) {
    //         document.getElementById("ip").setAttribute("value", 1);
    //         diablog.showModal();
    //         diablog2.style.display = "block";
    //         $scope.sanpham = $scope.products.find(function (i) {
    //             if (i.id == item) {
    //                 return i;
    //             }
    //         });
    //     } else {
    //         swal("Vui lòng đăng nhập để mua hàng")
    //     }
    // };

    $scope.close = function () {
        document.querySelector('#myDialog').close();
        diablog2.style.display = "none"
    }

    $scope.addCart = function (item) {
        if (document.getElementById("ip").value > 0) {
            sessionStorage.setItem(item, document.getElementById("ip").value);
            $scope.slP = sessionStorage.length;
            window.alert("Them thanh cong");
        } else {
            alert("Số lượng phải lớn hơn 0")
        }
    }

    var trangMan;
    var countPageMan;
    $scope.man = function (item) {
        $http.get(end_point+"api/findByLsp/" + item + "/0").then(function (item) {
            $scope.products = item.data[0].list_sanpham
            trangMan = item.data[0].trang;
            countPageMan = item.data[0].count_page;
            $scope.pageEnd = item.data[0].count_page
            $scope.currenPage = item.data[0].trang
        })

        $scope.loadMore = function () {
            if (Number(trangMan) == Number(countPageMan)) {
                trangMan = 0;
            } else trangMan = trangMan + 1;
            $http.get(end_point+"api/findByLsp/" + item + "/" + Number(trangMan)).then(function (item) {
                $scope.products = item.data[0].list_sanpham
                $scope.pageEnd = item.data[0].count_page
                $scope.currenPage = item.data[0].trang
            })
        }
    }


    var trangWo;
    var countPageWo;
    $scope.women = function (item) {
        $http.get(end_point+"api/findByLsp/" + item + "/0").then(function (item) {
            $scope.products = item.data[0].list_sanpham
            trangWo = item.data[0].trang;
            countPageWo = item.data[0].count_page;
            $scope.pageEnd = item.data[0].count_page
            $scope.currenPage = item.data[0].trang
        })

        $scope.loadMore = function () {
            if (Number(trangWo) == Number(countPageWo)) {
                trangWo = 0;
            } else trangWo = trangWo + 1;
            $http.get(end_point+"api/findByLsp/" + item + "/" + Number(trangWo)).then(function (item) {
                $scope.products = item.data[0].list_sanpham
                $scope.pageEnd = item.data[0].count_page
                $scope.currenPage = item.data[0].trang
            })
        }
    }


    var trangALL;
    var countPageALL;
    $scope.all = function () {
        $http.get(end_point+"api/pageableSanPham/0").then(function (item) {
            $scope.products = item.data[0].list_sanpham
            trangALL = item.data[0].trang;
            countPageALL = item.data[0].count_page;
            $scope.pageEnd = item.data[0].count_page
            $scope.currenPage = item.data[0].trang
        })

        $scope.loadMore = function () {
            if (Number(trangALL) == Number(countPageALL)) {
                trangALL = 0;
            } else trangALL = trangALL + 1;
            $http.get(end_point+"api/pageableSanPham/" + Number(trangALL)).then(function (item) {
                $scope.products = item.data[0].list_sanpham
                $scope.pageEnd = item.data[0].count_page
                $scope.currenPage = item.data[0].trang
            })
        }
    }

    $scope.search = function (item) {
        if ($scope.tenSP.length == 0) {
            $http.get(end_point+"api/sanpham").then(function (item) {
                $scope.products = item.data
            })
        } else {
            if (item.keyCode == 13) {
                $http.get(end_point+"api/sanpham").then(function (item) {
                    $scope.products = item.data.filter(function (i) {
                        return i.tensanpham == $scope.tenSP
                    });
                })
            }
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

let app = angular.module("quyet", []);

app.controller("dovan", function ($scope, $http, $locale) {
    let endPoint = "http://localhost:6969/demov1/";

    $scope.signUp = function () {
        if ($scope.user.email == null) {
            $scope.check = 4;
            swal("Thông báo", "Sai định dạng email", "warning")
        } else {
            $http.post(endPoint + "api/users2", $scope.user).then(function (item) {
                    if (item.data == 0) {
                        $scope.check = 0;
                        swal("Thông báo", "Vui lòng nhập đủ thông tin", "warning")
                    } else if (item.data == 1) {
                        $scope.check = 1;
                        swal("Thông báo", "Đăng ký thất bại", "warning")
                    } else if (item.data == 2) {
                        $scope.check = 2;
                        swal("Thông báo", "Đăng ký thất bại", "warning")
                    } else if (item.data == 3) {
                        swal("Thông báo", "Đăng ký thất bại", "warning")
                        $scope.check = 3;
                    } else {
                        swal("Thông báo", "Đăng ký thành công", "success");
                        $scope.check = 100;
                    }
                }
            )
                .catch(
                    function (item) {
                        console.log(item)
                        swal("Thông báo", "Đăng ký thất bại", "warning")
                    }
                )
        }
    }
})

app.controller("getData", function ($scope, $http) {
})


angular.module('loginApp', [])
    .controller('loginController', function($scope, $http, $window) {
        $scope.loginmodel = {
            id : '',
            pass : '',
            login : function() {
                var sendmodel = {
                    id : this.id,
                    password : this.pass
                };
                $http.post('/login', sendmodel)
                    .success(function(data, status){
                        $window.location.href = '/tagchat';
                    })
                    .error(function(data){
                        console.log(data);
                        alert("network error")
                    })
            }
        };
        $scope.joinFlag = false;
        $scope.joinmodel = {
            id : '',
            pass : '',
            repass : '',
            join : function() {
                if(this.pass != this.repass) {
                    alert("password not match!!");
                    return;
                }
                var sendmodel = {
                    id : this.id,
                    password : this.pass
                };
                $http.post('/join', sendmodel)
                    .success(function(data, status){
                        $window.location.href = '/tagchat';
                    })
                    .error(function(data){
                        console.log(data);
                        alert("network error")
                    })
            }
        };

    });
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
                        $window.location.href = '/chat/main';
                    })
                    .error(function(data){
                        console.log(data);
                        alert("network error")
                    })
            }
        }
    });
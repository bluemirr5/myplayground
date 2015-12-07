angular.module('main.controllers', [])
    .controller('selectTagController', function($scope) {
        $scope.connected = false;
        var stompClient;
        function connect(tag) {
            var socket = new SockJS('/chatByTag');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                $scope.connected = true;
                $scope.$apply();
                stompClient.subscribe('/chat/'+tag, function(chatData){
                    console.log("subscribe");
                    console.log(chatData);
                });
            });
        }

        $scope.send = function() {
            //stompClient.send
            console.log($scope.tag.id + ":" + $scope.curmessage);
            stompClient.send('/chat/channel/'+$scope.tag.id, {}, $scope.curmessage);

        };

        $scope.tag = {
            id : '',
            onClickConnect : function() {
                if(this.id.length < 3) {
                    alert("3 more")
                } else {
                    connect(this.id);
                }
                console.log('selectTagController');
            },
            onClickDisconnect : function() {
                stompClient.disconnect();
                $scope.connected = false;
            }
        };
    });


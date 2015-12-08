angular.module('main.controllers', [])
    .controller('selectTagController', function($scope) {
        $scope.connected = false;
        $scope.messages = [];
        var stompClient;
        function connect(tag) {
            var socket = new SockJS('/chatByTag');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                $scope.connected = true;
                stompClient.subscribe('/chat/'+tag, function(chatData){
                    $scope.messages.push(JSON.parse(chatData.body));
                    $scope.$apply();
                });
                $scope.$apply();
            });
        }

        $scope.send = function() {
            //stompClient.send
            var sendObject = {message : $scope.curmessage};
            console.log($scope.tag.id + ":" + sendObject);
            stompClient.send('/channel/'+$scope.tag.id, {},  JSON.stringify(sendObject));
            $scope.curmessage = '';
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


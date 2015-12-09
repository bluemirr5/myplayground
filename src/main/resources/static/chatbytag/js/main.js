angular.module('main.controllers', [])
    .controller('selectTagController', function($scope) {
        $scope.connected = false;
        $scope.messages = [];
        $scope.me = '';
        var stompClient;
        function connect(tag) {
            var socket = new SockJS('/chatByTag');
            stompClient = Stomp.over(socket);
            stompClient.connect({}, function(frame) {
                console.log(frame);
                $scope.me = frame.headers['user-name'];
                $scope.connected = true;
                stompClient.subscribe('/chat/'+tag, function(chatData){
                    var parsedData = JSON.parse(chatData.body);
                    $scope.messages.push(parsedData);
                    parsedData.convDate = new Date(parsedData.pubDate);
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
            connect : function() {
                if(this.id.length < 3) {
                    alert("3 more length string")
                } else {
                    connect(this.id);
                }
            },
            disconnect : function() {
                stompClient.disconnect();
                $scope.connected = false;
                $scope.messages = [];
            }
        };
    });


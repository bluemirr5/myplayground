package kr.rang2ne.playground.websocketchat;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.messaging.SubProtocolWebSocketHandler;

/**
 * Created by gswon on 15. 12. 9.
 */
@Slf4j
public class CustomSubProtocolWebSocketHandler extends SubProtocolWebSocketHandler {
    @Autowired
    private WebSocketSessionManager webSocketSessionManager;

    public CustomSubProtocolWebSocketHandler(MessageChannel clientInboundChannel, SubscribableChannel clientOutboundChannel) {
        super(clientInboundChannel, clientOutboundChannel);
    }

    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        log.info("New websocket connection was established");
        webSocketSessionManager.register(session);
        super.afterConnectionEstablished(session);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus closeStatus) throws Exception {
        webSocketSessionManager.remove(session);
        super.afterConnectionClosed(session, closeStatus);
    }
}

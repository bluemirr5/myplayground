package kr.rang2ne.playground.purewebsocket;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by gswon on 15. 12. 4.
 *
 */
@Component
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler {

    private Set<WebSocketSession> sessions = new HashSet<>();

    private synchronized void addSession(WebSocketSession session) {
        sessions.add(session);
    }

    private synchronized void removeSession(WebSocketSession session) {
        sessions.remove(session);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        addSession(session);
        log.info(sessions.size() + "");
        log.info("after connect : " + session  + ":" + this);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        log.info("after disconnect : " + session  + ":" + this);
        removeSession(session);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        sessions.forEach(eachSession -> {
            try {
                if(!eachSession.isOpen()){
                    removeSession(eachSession);
                } else {
                    eachSession.sendMessage(message);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}

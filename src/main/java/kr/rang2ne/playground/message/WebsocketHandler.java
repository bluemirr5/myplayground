package kr.rang2ne.playground.message;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.io.IOException;
import java.util.Date;

/**
 * Created by gswon on 15. 12. 4.
 *
 */
@Component
@Slf4j
public class WebsocketHandler extends TextWebSocketHandler {



    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        Thread th = new Thread(() -> {
            int times = 0;
            while(times < 5) {
                try {
                    Thread.sleep(1000);
                    session.sendMessage(new TextMessage("now is " + (new Date()).toString()));
                    log.debug("send");
                } catch (IOException | InterruptedException e) {
                    e.printStackTrace();
                }
                times++;
            }
        });
        th.start();
        log.info("after connect : " + session  + ":" + this);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        log.info(session.getRemoteAddress().toString());
        log.info(message.getPayload());
    }
}

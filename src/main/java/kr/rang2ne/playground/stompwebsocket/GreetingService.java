package kr.rang2ne.playground.stompwebsocket;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

/**
 * Created by gswon on 15. 12. 5.
 */
@Service
public class GreetingService {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    public String sendMessage(String channel, Object message){
        simpMessagingTemplate.convertAndSend(channel, message);
        return System.currentTimeMillis() + "";
    }
}

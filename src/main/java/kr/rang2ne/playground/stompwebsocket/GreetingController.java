package kr.rang2ne.playground.stompwebsocket;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by gswon on 15. 12. 5.
 */
@Controller
public class GreetingController {

    @MessageMapping("/hello")
    @SendTo("/topic/greeting")
    public PMessage greeting(OMessage oMessage) throws Exception {
        Thread.sleep(1000);
        return new PMessage("Hello " + oMessage.getName());
    }
}

package kr.rang2ne.playground.websocketchat;

import kr.rang2ne.playground.stompwebsocket.GreetingService;
import kr.rang2ne.playground.stompwebsocket.OMessage;
import kr.rang2ne.playground.stompwebsocket.PMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

/**
 * Created by gswon on 15. 12. 5.
 */
@Controller
public class ChatController {
    @Autowired
    GreetingService greetingService;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public PMessage greeting(OMessage message) throws Exception {
        Thread.sleep(3000); // simulated delay
        return new PMessage("Hello, " + message.getName() + "!");
    }

    @MessageMapping("/toServer")
    public void toServer(){
        System.out.println("toServer");
        for (int i = 0; i < 5 ; i++) {
            OMessage oMessage = new OMessage();
            oMessage.setName(System.currentTimeMillis() + "");
            greetingService.sendMessage("/topic/greetings", oMessage);
//            simpMessagingTemplate.convertAndSend("/topic/greetings", oMessage);
        }
    }

}

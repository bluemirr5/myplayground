package kr.rang2ne.playground.websocketchat;

import kr.rang2ne.playground.member.model.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by gswon on 15. 12. 5.
 */
@Controller
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/chat/main")
    public String chatMain(HttpSession session){
        Object member = session.getAttribute("auth");
        if(member == null || !(member instanceof Member) || ((Member)member).getId() == null) {
            return "hello";
        }
        return "chatbytag/main";
    }

    @MessageMapping(value = "/chat/channel/{channel}")
    public void sendChannel(@PathVariable String channel, String message) {
        System.out.println(channel + ":" + message);
        simpMessagingTemplate.convertAndSend("/chat/"+channel, message);
    }
}

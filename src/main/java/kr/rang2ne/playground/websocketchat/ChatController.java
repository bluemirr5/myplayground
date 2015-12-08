package kr.rang2ne.playground.websocketchat;

import kr.rang2ne.playground.member.model.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by gswon on 15. 12. 5.
 */
@Controller
@Slf4j
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @RequestMapping(value = "/tagchat")
    public String chatMain(HttpSession session){
        Object member = session.getAttribute("auth");
        if(member == null ||
                !(member instanceof MemberDTO.SessionModel) ||
                ((MemberDTO.SessionModel)member).getId() == null) {
            return "tagchat/login";
        }
        return "tagchat/main";
    }

    @MessageMapping("/channel/{channel}")
    public void sendChannel(@DestinationVariable String channel, SimpMessageHeaderAccessor headerAccessor, Message message) {
        String userName = headerAccessor.getUser().getName();
        message.setFromUser(userName);
        simpMessagingTemplate.convertAndSend("/chat/"+channel, message);
    }
}

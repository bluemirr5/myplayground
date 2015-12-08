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

    @RequestMapping(value = "/chat2/main")
    public String chatMain(HttpSession session){
        Object member = session.getAttribute("auth");
        log.info(session.getId());
        if(member == null ||
                !(member instanceof MemberDTO.SessionModel) ||
                ((MemberDTO.SessionModel)member).getId() == null) {
            return "hello";
        }
        return "chatbytag/main";
    }

    @MessageMapping("/channel/{channel}")
    public void sendChannel(@DestinationVariable String channel, SimpMessageHeaderAccessor headerAccessor, Message message) {
//        Member member = (Member) headerAccessor.getSessionAttributes().get("auth");
//        log.info(member.getId());
        String sessionId = headerAccessor.getSessionId();
        String userName = headerAccessor.getUser().getName();
        message.setFromUser(userName);
        simpMessagingTemplate.convertAndSend("/chat/"+channel, message);
    }
}

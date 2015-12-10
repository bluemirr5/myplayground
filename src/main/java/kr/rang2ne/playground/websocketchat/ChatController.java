package kr.rang2ne.playground.websocketchat;

import kr.rang2ne.playground.member.model.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.Map;

/**
 * Created by gswon on 15. 12. 5.
 */
@Controller
@Slf4j
public class ChatController {
    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @Autowired
    private WebSocketSessionManager webSocketSessionManager;

    @RequestMapping(value = "/tagchat/logout")
    public String chatLogout(HttpSession session) {
        session.removeAttribute("auth");
        return "redirect:/tagchat";
    }

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
    public void sendMessage(@DestinationVariable String channel, SimpMessageHeaderAccessor headerAccessor, Message message) {
        Map<String, Object> sessionAttributes = headerAccessor.getSessionAttributes();
        if(sessionAttributes != null && sessionAttributes.get("auth") != null) {
            MemberDTO.SessionModel sessionModel = (MemberDTO.SessionModel)sessionAttributes.get("auth");
            message.setFromUser(sessionModel.getId());
        }
        message.setPubDate(new Date());
        simpMessagingTemplate.convertAndSend("/chat/"+channel, message);
    }

    @MessageMapping("/reqUserInfo/{channel}")
    public void getUserInfo(@DestinationVariable String channel){
        webSocketSessionManager.sendUsersInfo(channel);
    }

    @SubscribeMapping("/chat/{channel}")
    public void onInChannelSubscribe(@DestinationVariable String channel, SimpMessageHeaderAccessor headerAccessor){
        webSocketSessionManager.registerByChannel(channel, headerAccessor.getSessionId());
    }

}

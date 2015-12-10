package kr.rang2ne.playground.websocketchat;

import kr.rang2ne.playground.member.model.MemberDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketSession;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by gswon on 15. 12. 9.
 */
@Slf4j
@Component
public class WebSocketSessionManager {
    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;

    private final Map<String, WebSocketSession> sessionTotalMap = new ConcurrentHashMap<>();
    private final Map<String, Map<String, MemberDTO.SessionModel>> sessionChannelMap = new ConcurrentHashMap<>();

    public synchronized void register(WebSocketSession session) {
        sessionTotalMap.put(session.getId(), session);
    }

    public synchronized void remove(WebSocketSession session) {
        sessionChannelMap.forEach((s, webSocketSessions) -> {
            if(webSocketSessions.remove(session.getId()) != null) {
                simpMessagingTemplate.convertAndSend("/userInfo/"+s, findByChannel(s));
            }
        });
    }

    public synchronized void registerByChannel(String channel, String sessionId) {
        Map<String, MemberDTO.SessionModel> sessions = getSessionChannelItem(channel);
        MemberDTO.SessionModel sessionModel = (MemberDTO.SessionModel)sessionTotalMap.get(sessionId).getAttributes().get("auth");
        sessions.put(sessionId, sessionModel);
    }

    public List<MemberDTO.SessionModel> findByChannel(String channel) {
        List<MemberDTO.SessionModel> result = new ArrayList<>();



        getSessionChannelItem(channel).forEach((s, sessionModel) -> result.add(sessionModel));
        return result;
    }

    public void sendUsersInfo(String channel) {
        simpMessagingTemplate.convertAndSend("/userInfo/"+channel, findByChannel(channel));
    }

    private Map<String, MemberDTO.SessionModel> getSessionChannelItem(String channel) {
        Map<String, MemberDTO.SessionModel> sessions = sessionChannelMap.get(channel);
        if(sessions == null) {
            sessions = new ConcurrentHashMap<>();
            sessionChannelMap.put(channel, sessions);
        }
        return sessions;
    }
}

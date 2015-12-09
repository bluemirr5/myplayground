package kr.rang2ne.playground.websocketchat;

import lombok.extern.slf4j.Slf4j;
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
    private final Map<String, WebSocketSession> sessionTotalMap = new ConcurrentHashMap<>();
    private final Map<String, List<WebSocketSession>> sessionChannelMap = new ConcurrentHashMap<>();

    public synchronized void register(WebSocketSession session) {
        sessionTotalMap.put(session.getId(), session);
    }

    public synchronized void remove(WebSocketSession session) {
        sessionTotalMap.remove(session.getId());
    }

    public synchronized void registerByChannel(String channel, String sessionId) {
        List<WebSocketSession> sessions = sessionChannelMap.get(channel);
        if(sessions == null) {
            sessions = new ArrayList<>();
            sessionChannelMap.put(channel, sessions);
        }
        sessions.add(sessionTotalMap.get(sessionId));
    }

    public synchronized void removeByChannel(String channel, String sessionId) {
        List<WebSocketSession> sessions = sessionChannelMap.get(channel);
        WebSocketSession webSocketSession = sessionTotalMap.get(sessionId);
        sessions.remove(webSocketSession);
    }
}

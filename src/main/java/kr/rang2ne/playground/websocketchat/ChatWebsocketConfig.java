package kr.rang2ne.playground.websocketchat;

import kr.rang2ne.playground.common.RuntimeBaseException;
import kr.rang2ne.playground.member.model.MemberDTO;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.config.annotation.AbstractWebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.server.support.AbstractHandshakeHandler;

import java.security.Principal;
import java.util.Map;

/**
 * Created by gswon on 15. 12. 5.
 */
@Configuration
@EnableWebSocketMessageBroker//
public class ChatWebsocketConfig extends AbstractWebSocketMessageBrokerConfigurer {

    @Override
    public void configureMessageBroker(MessageBrokerRegistry config) {
        config.enableSimpleBroker("/chat");
//        config.setApplicationDestinationPrefixes("/app");
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/chatByTag").setHandshakeHandler(new AbstractHandshakeHandler() {
            @Override
            protected Principal determineUser(ServerHttpRequest request, WebSocketHandler wsHandler, Map<String, Object> attributes) {
                if(request instanceof ServletServerHttpRequest) {
                    Object memberObj = ((ServletServerHttpRequest)request).getServletRequest().getSession().getAttribute("auth");
                    if(memberObj instanceof MemberDTO.SessionModel) {
                        MemberDTO.SessionModel member = (MemberDTO.SessionModel) memberObj;
                        return () -> member.getId();
                    }
                }
                throw new RuntimeException("not user");
            }
        }).withSockJS();
    }
}

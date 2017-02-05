package jp.pinkikki.config;

import jp.pinkikki.handler.WebSocketConnectHandler;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.session.ExpiringSession;

@Configuration
public class HandlerConfig<S extends ExpiringSession> {

    @Bean
    public WebSocketConnectHandler<S> webSocketConnectHandler(
            SimpMessageSendingOperations messagingTemplate) {
        return new WebSocketConnectHandler<S>(messagingTemplate);
    }
}

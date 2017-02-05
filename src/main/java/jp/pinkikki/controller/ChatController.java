package jp.pinkikki.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    @Autowired
    private SimpMessagingTemplate simpMessagingTemplate;

    @SubscribeMapping("env/{id}")
    public void chat(@DestinationVariable String id, String message, Principal principal) {
        String user = principal.getName();
        simpMessagingTemplate.convertAndSend("/res/env/" + id, String.join(":", user, message));
    }
}

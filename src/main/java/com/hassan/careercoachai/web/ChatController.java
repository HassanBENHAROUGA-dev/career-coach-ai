package com.hassan.careercoachai.web;

import com.hassan.careercoachai.dto.ChatRequest;
import com.hassan.careercoachai.dto.ChatResponse;
import com.hassan.careercoachai.service.ChatService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chat")
public class ChatController {

    private final ChatService chatService;

    public ChatController(ChatService chatService) {
        this.chatService = chatService;
    }

    @PostMapping("/askChat")
    public ChatResponse chat(@Valid @RequestBody ChatRequest request) {
        String answer = chatService.reply(
                request.conversationId(),
                request.message()
        );

        return new ChatResponse(request.conversationId(), answer);
    }
}
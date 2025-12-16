package com.hyltest.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@Slf4j
@RequiredArgsConstructor
@RestController
public class AIChatController {

    private final ChatClient serviceChatClient;

    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<String> service(
            @RequestParam String prompt,
            @RequestParam String chatId) {
        log.info("用户输入：{}",prompt);
        return serviceChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content();
    }

/*    @RequestMapping(value = "/chat", produces = "text/html;charset=utf-8")
    public Flux<ServerSentEvent<String>> service(
            @RequestParam String prompt,
            @RequestParam String chatId) {
        log.info("用户输入：{}", prompt);
        return serviceChatClient.prompt()
                .user(prompt)
                .advisors(a -> a.param(ChatMemory.CONVERSATION_ID, chatId))
                .stream()
                .content()
                .map(content -> ServerSentEvent.<String>builder()
                        .data(content)
                        .build());
    }*/



}

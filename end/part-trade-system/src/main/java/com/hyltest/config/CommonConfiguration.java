package com.hyltest.config;

import com.hyltest.tools.PurchasePartsTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.client.advisor.SimpleLoggerAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.ai.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import static com.hyltest.constant.SystemConstants.CUSTOMER_SERVICE_SYSTEM;

@Component
public class CommonConfiguration {
    @Bean
    public ChatMemory chatMemory(){ //ChatMemory保存了会话记录（与ai的对话）的详情
        return MessageWindowChatMemory.builder().build();   //构建一个基于消息窗口的记忆体对象（记忆库），用于管理聊天对话历史。
    }

    @Bean
    public ChatClient serviceChatClient(
            OpenAiChatModel model,
            ChatMemory chatMemory,
            PurchasePartsTools purchasePartsTools) {
        return ChatClient.builder(model)
                .defaultSystem(CUSTOMER_SERVICE_SYSTEM)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build(),
                        new SimpleLoggerAdvisor()   //实现日志记录的SimpleLoggerAdvisor
                )
                .defaultTools(purchasePartsTools)
                .build();
    }
}

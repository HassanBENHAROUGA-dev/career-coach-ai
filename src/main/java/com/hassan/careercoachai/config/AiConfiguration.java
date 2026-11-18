package com.hassan.careercoachai.config;

import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.client.advisor.MessageChatMemoryAdvisor;
import org.springframework.ai.chat.memory.ChatMemory;
import org.springframework.ai.chat.memory.MessageWindowChatMemory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AiConfiguration {

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.builder()
                .maxMessages(30)
                .build();
    }

    @Bean
    public ChatClient careerCoachChatClient(
            ChatClient.Builder builder,
            ChatMemory chatMemory
    ) {
        return builder
                .defaultSystem("""
                        Tu es un coach de carrière spécialisé en informatique.
                        Réponds en français de manière claire et concise.

                        N'invente jamais d'expérience ou de compétence
                        concernant le candidat.

                        Lorsqu'un candidat demande un entretien :
                        - Pose une seule question à la fois.
                        - Adapte les questions à son profil et à ses réponses.
                        - Attends sa réponse avant de continuer.
                        - Après sa réponse, explique brièvement les points
                          corrects et les éléments à améliorer.
                        - Pose ensuite une relance ou la question suivante.
                        """)
                .defaultAdvisors(
                        MessageChatMemoryAdvisor.builder(chatMemory).build()
                )
                .build();
    }
}
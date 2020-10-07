package com.ironman.websocket

import org.springframework.context.annotation.Configuration
import org.springframework.messaging.simp.config.MessageBrokerRegistry
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker
import org.springframework.web.socket.config.annotation.StompEndpointRegistry
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/7
 */
@Configuration
@EnableWebSocketMessageBroker
class WebSocketConfig : WebSocketMessageBrokerConfigurer {

    override fun registerStompEndpoints(stompEndpointRegistry: StompEndpointRegistry) {
        stompEndpointRegistry.addEndpoint("/ws").setAllowedOrigins().withSockJS()
    }

    override fun configureMessageBroker(messageBrokerRegistry: MessageBrokerRegistry) {
        messageBrokerRegistry.setApplicationDestinationPrefixes("/app")
        messageBrokerRegistry.enableSimpleBroker("/topic")
    }
}
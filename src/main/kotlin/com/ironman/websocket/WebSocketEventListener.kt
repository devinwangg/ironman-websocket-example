package com.ironman.websocket

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.event.EventListener
import org.springframework.messaging.simp.SimpMessageSendingOperations
import org.springframework.messaging.simp.stomp.StompHeaderAccessor
import org.springframework.stereotype.Component
import org.springframework.web.socket.messaging.SessionConnectedEvent
import org.springframework.web.socket.messaging.SessionDisconnectEvent

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/7
 */
@Component
class WebSocketEventListener(@Autowired val simpleMessageSendingOperations: SimpMessageSendingOperations) {

    val logger: Logger = LoggerFactory.getLogger(WebSocketEventListener::class.java)

    /**
     * WebSocket 連線監聽器
     */
    @EventListener
    fun handleWebSocketConnectListener(sessionConnectedEvent: SessionConnectedEvent) {
        logger.info("接收到新的連線");
    }

    /**
     * WebSocket 中斷連線監聽器
     */
    @EventListener
    fun handleWebSocketDisconnectListener(sessionDisconnectEvent: SessionDisconnectEvent) {
        val stompHeaderAccessor: StompHeaderAccessor = StompHeaderAccessor.wrap(sessionDisconnectEvent.message)
        val username: String? = stompHeaderAccessor.sessionAttributes?.get("username") as String?

        if (username != null) {
            logger.info("$username 使用者離開聊天室");
            val chatMessage = ChatMessage(
                    type = MessageType.LEAVE,
                    sender = username
            )
            simpleMessageSendingOperations.convertAndSend("/topic/public", chatMessage);
        }
    }
}
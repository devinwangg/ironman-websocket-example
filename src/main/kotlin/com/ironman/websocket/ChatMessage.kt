package com.ironman.websocket

/**
 *
 * @author wei-xiang
 * @version 1.0
 * @date 2020/10/7
 */

enum class MessageType {
    CHAT,
    JOIN,
    LEAVE
}

data class ChatMessage(
        val type: MessageType,
        val content: String? = null,
        val sender: String
)
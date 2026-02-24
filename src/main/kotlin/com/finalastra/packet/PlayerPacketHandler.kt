package com.finalastra.packet

import com.finalastra.packet.handlers.ChatMessageHandler
import com.finalastra.packet.handlers.KeepAliveHandler
import com.finalastra.packet.handlers.MovementHandler

object PlayerPacketHandler {
    fun bootstrap(dispatcher: PacketDispatcher) {
        dispatcher.register(0, KeepAliveHandler)
        dispatcher.register(1, MovementHandler)
        dispatcher.register(2, ChatMessageHandler)
    }
}

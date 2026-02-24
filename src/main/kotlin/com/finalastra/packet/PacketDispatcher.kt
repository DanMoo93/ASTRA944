package com.finalastra.packet

import com.finalastra.game.player.Player
import io.netty.buffer.ByteBuf

class PacketDispatcher {
    private val handlers = mutableMapOf<Int, PacketHandler>()

    fun register(opcode: Int, handler: PacketHandler) {
        handlers[opcode] = handler
    }

    fun dispatch(player: Player, opcode: Int, payload: ByteBuf) {
        handlers[opcode]?.handle(player, payload)
    }
}

package com.finalastra.packet.handlers

import com.finalastra.game.player.Player
import com.finalastra.packet.PacketHandler
import io.netty.buffer.ByteBuf

object MovementHandler : PacketHandler {
    override fun handle(player: Player, payload: ByteBuf) {
        if (payload.readableBytes() >= 2) {
            val x = payload.readUnsignedByte().toInt()
            val y = payload.readUnsignedByte().toInt()
            player.moveTo(x, y)
        }
    }
}

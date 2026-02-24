package com.finalastra.packet.handlers

import com.finalastra.game.player.Player
import com.finalastra.packet.PacketHandler
import io.netty.buffer.ByteBuf

object KeepAliveHandler : PacketHandler {
    override fun handle(player: Player, payload: ByteBuf) {
        player.touch()
    }
}

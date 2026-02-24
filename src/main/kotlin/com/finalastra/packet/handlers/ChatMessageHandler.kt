package com.finalastra.packet.handlers

import com.finalastra.game.player.Player
import com.finalastra.packet.PacketHandler
import io.netty.buffer.ByteBuf
import io.netty.util.CharsetUtil

object ChatMessageHandler : PacketHandler {
    override fun handle(player: Player, payload: ByteBuf) {
        val msg = payload.toString(CharsetUtil.UTF_8)
        player.sendMessage("You said: $msg")
    }
}

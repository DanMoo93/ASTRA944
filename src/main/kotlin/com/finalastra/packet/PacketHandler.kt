package com.finalastra.packet

import com.finalastra.game.player.Player
import io.netty.buffer.ByteBuf

fun interface PacketHandler {
    fun handle(player: Player, payload: ByteBuf)
}

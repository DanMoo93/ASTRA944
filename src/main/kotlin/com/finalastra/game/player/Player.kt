package com.finalastra.game.player

import io.netty.channel.Channel

data class Position(var x: Int = 3200, var y: Int = 3200, var plane: Int = 0)

class Player(
    val username: String,
    val rights: Int,
    private val channel: Channel,
    val position: Position = Position()
) {
    fun moveTo(x: Int, y: Int) {
        position.x = x
        position.y = y
    }

    fun sendMessage(message: String) {
        channel.writeAndFlush(message)
    }

    fun touch() {
        // Keepalive hook.
    }
}

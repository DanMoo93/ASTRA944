package com.finalastra.network

import com.finalastra.config.ServerConfig
import com.finalastra.js5.Js5Service
import com.finalastra.network.decoder.GameDecoder
import com.finalastra.network.decoder.LoginDecoder
import com.finalastra.network.encoder.Js5Encoder
import com.finalastra.network.encoder.PacketEncoder
import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class GameChannelInitializer(
    private val config: ServerConfig,
    private val js5Service: Js5Service
) : ChannelInitializer<SocketChannel>() {
    override fun initChannel(ch: SocketChannel) {
        ch.pipeline().addLast("loginDecoder", LoginDecoder(config))
        ch.pipeline().addLast("gameDecoder", GameDecoder())
        ch.pipeline().addLast("js5Encoder", Js5Encoder(js5Service))
        ch.pipeline().addLast("packetEncoder", PacketEncoder())
    }
}

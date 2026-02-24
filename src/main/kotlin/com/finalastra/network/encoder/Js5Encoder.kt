package com.finalastra.network.encoder

import com.finalastra.js5.Js5Service
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class Js5Encoder(private val js5Service: Js5Service) : MessageToByteEncoder<Js5Request>() {
    override fun encode(ctx: ChannelHandlerContext, msg: Js5Request, out: ByteBuf) {
        val data = js5Service.getArchive(msg.index, msg.archive)
        out.writeByte(msg.index)
        out.writeShort(msg.archive)
        out.writeInt(data.size)
        out.writeBytes(data)
    }
}

data class Js5Request(val index: Int, val archive: Int)

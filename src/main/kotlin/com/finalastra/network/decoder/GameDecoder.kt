package com.finalastra.network.decoder

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class GameDecoder : ByteToMessageDecoder() {
    override fun decode(ctx: ChannelHandlerContext, `in`: ByteBuf, out: MutableList<Any>) {
        if (`in`.readableBytes() < 2) return
        `in`.markReaderIndex()
        val opcode = `in`.readUnsignedByte().toInt()
        val size = `in`.readUnsignedByte().toInt()
        if (`in`.readableBytes() < size) {
            `in`.resetReaderIndex()
            return
        }
        val payload = `in`.readRetainedSlice(size)
        out.add(GamePacket(opcode, payload))
    }
}

data class GamePacket(val opcode: Int, val payload: ByteBuf)

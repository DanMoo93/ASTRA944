package com.finalastra.network.encoder

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.MessageToByteEncoder

class PacketEncoder : MessageToByteEncoder<OutgoingPacket>() {
    override fun encode(ctx: ChannelHandlerContext, msg: OutgoingPacket, out: ByteBuf) {
        out.writeByte(msg.opcode)
        out.writeShort(msg.payload.size)
        out.writeBytes(msg.payload)
    }
}

data class OutgoingPacket(val opcode: Int, val payload: ByteArray)

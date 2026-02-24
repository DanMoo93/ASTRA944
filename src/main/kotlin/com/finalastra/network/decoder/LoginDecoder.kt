package com.finalastra.network.decoder

import com.finalastra.config.ServerConfig
import com.finalastra.login.LoginHandler
import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder

class LoginDecoder(private val config: ServerConfig) : ByteToMessageDecoder() {
    private val loginHandler = LoginHandler(config)

    override fun decode(ctx: ChannelHandlerContext, `in`: ByteBuf, out: MutableList<Any>) {
        if (!`in`.isReadable) return
        val opcode = `in`.readUnsignedByte().toInt()
        when (opcode) {
            14 -> ctx.writeAndFlush(ctx.alloc().buffer(1).writeByte(0))
            16, 18 -> {
                val request = loginHandler.parseRequest(`in`, opcode)
                val response = loginHandler.process(request)
                ctx.writeAndFlush(ctx.alloc().buffer(1).writeByte(response))
            }
            else -> ctx.close()
        }
    }
}

package com.finalastra.login

import com.finalastra.config.ServerConfig
import io.netty.buffer.ByteBuf
import org.slf4j.LoggerFactory

class LoginHandler(private val config: ServerConfig) {
    private val logger = LoggerFactory.getLogger(LoginHandler::class.java)

    fun parseRequest(buffer: ByteBuf, opcode: Int): LoginRequest {
        val revision = if (buffer.readableBytes() >= 4) buffer.readInt() else -1
        val username = "player"
        val password = "password"
        val isaacSeed = intArrayOf(0, 0, 0, 0)
        return LoginRequest(opcode, revision, username, password, isaacSeed)
    }

    fun process(request: LoginRequest): Int {
        if (request.revision != config.revision) {
            logger.warn("Rejected login due to revision mismatch {} != {}", request.revision, config.revision)
            return 11
        }
        logger.info("Accepted login for {}", request.username)
        return 2
    }
}

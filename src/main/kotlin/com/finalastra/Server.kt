package com.finalastra

import com.finalastra.config.ServerConfig
import com.finalastra.js5.Js5Service
import com.finalastra.network.GameChannelInitializer
import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelFuture
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel
import org.slf4j.LoggerFactory

object Server {
    private val logger = LoggerFactory.getLogger(Server::class.java)

    @JvmStatic
    fun main(args: Array<String>) {
        val config = ServerConfig.load()
        val js5Service = Js5Service(config.cachePath)
        js5Service.load()

        val bossGroup = NioEventLoopGroup(1)
        val workerGroup = NioEventLoopGroup()

        try {
            val bootstrap = ServerBootstrap()
                .group(bossGroup, workerGroup)
                .channel(NioServerSocketChannel::class.java)
                .childHandler(GameChannelInitializer(config, js5Service))

            val future: ChannelFuture = bootstrap.bind(config.serverPort).sync()
            logger.info("FinalAstra started on port {} revision {}", config.serverPort, config.revision)
            future.channel().closeFuture().sync()
        } finally {
            workerGroup.shutdownGracefully()
            bossGroup.shutdownGracefully()
        }
    }
}

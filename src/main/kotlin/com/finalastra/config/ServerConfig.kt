package com.finalastra.config

import com.moandjiezana.toml.Toml
import java.io.File

data class ServerConfig(
    val serverPort: Int,
    val revision: Int,
    val worldId: Int,
    val worldName: String,
    val worldAddress: String,
    val rsaModulusHex: String,
    val rsaExponentHex: String,
    val cachePath: String
) {
    companion object {
        private val configFile = File("data/config/server.toml")

        fun load(): ServerConfig {
            if (!configFile.exists()) {
                configFile.parentFile.mkdirs()
                configFile.writeText(
                    """
                    [server]
                    port = 43594
                    revision = 999
                    cache_path = "data/cache"

                    [world]
                    id = 1
                    name = "FinalAstra"
                    address = "127.0.0.1"

                    [rsa]
                    modulus = "REPLACE_WITH_MODULUS_HEX"
                    exponent = "10001"
                    """.trimIndent()
                )
            }

            val toml = Toml().read(configFile)
            val server = toml.getTable("server")
            val world = toml.getTable("world")
            val rsa = toml.getTable("rsa")

            return ServerConfig(
                serverPort = server.getLong("port").toInt(),
                revision = server.getLong("revision").toInt(),
                cachePath = server.getString("cache_path"),
                worldId = world.getLong("id").toInt(),
                worldName = world.getString("name"),
                worldAddress = world.getString("address"),
                rsaModulusHex = rsa.getString("modulus"),
                rsaExponentHex = rsa.getString("exponent")
            )
        }
    }
}

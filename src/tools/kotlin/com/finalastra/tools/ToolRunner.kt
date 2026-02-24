package com.finalastra.tools

object ToolRunner {
    @JvmStatic
    fun main(args: Array<String>) {
        when (args.firstOrNull()) {
            "rsa-key-generator" -> RsaKeyGenerator.run()
            "client-downloader" -> ClientDownloader.run()
            "client-patcher" -> ClientPatcher.run()
            "cache-downloader" -> CacheDownloader.run()
            else -> println("Usage: runTool --args=\"[rsa-key-generator|client-downloader|client-patcher|cache-downloader]\"")
        }
    }
}

package com.finalastra.tools

import java.io.File
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

object CacheDownloader {
    fun run() {
        val base = File("data/cache")
        base.mkdirs()
        val executor = Executors.newFixedThreadPool(8)

        for (index in 0..255) {
            executor.submit {
                File(base, "index_$index.reference").writeText("placeholder for index $index")
            }
        }

        executor.shutdown()
        executor.awaitTermination(1, TimeUnit.MINUTES)
        println("Cache placeholder download complete")
    }
}

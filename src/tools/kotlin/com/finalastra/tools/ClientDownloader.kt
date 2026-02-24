package com.finalastra.tools

import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.File

object ClientDownloader {
    private val client = OkHttpClient()

    fun run() {
        val configRequest = Request.Builder().url("https://world1.runescape.com/jav_config.ws").build()
        client.newCall(configRequest).execute().use { response ->
            val body = response.body?.string().orEmpty()
            File("data/clients/live/jav_config.ws").apply {
                parentFile.mkdirs()
                writeText(body)
            }
        }
        println("Downloaded jav_config.ws to data/clients/live/")
    }
}

package com.finalastra.tools

import java.io.File

object ClientPatcher {
    fun run() {
        val original = File("data/launchers/win/original.exe")
        if (!original.exists()) {
            println("Missing data/launchers/win/original.exe")
            return
        }

        val patched = File("data/clients/patched/RuneScape.exe")
        patched.parentFile.mkdirs()
        patched.writeBytes(original.readBytes())

        File("data/clients/patched/jav_config.ws").writeText(
            "param=10=localhost\nparam=11=43594\n"
        )
        println("Patched launcher copied to data/clients/patched/")
    }
}

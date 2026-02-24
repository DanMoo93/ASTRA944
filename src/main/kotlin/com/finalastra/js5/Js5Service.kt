package com.finalastra.js5

import java.io.File

class Js5Service(private val cacheRoot: String) {
    fun load() {
        File(cacheRoot).mkdirs()
    }

    fun getArchive(index: Int, archive: Int): ByteArray {
        val archiveFile = File(cacheRoot, "$index/$archive.dat")
        return if (archiveFile.exists()) archiveFile.readBytes() else ByteArray(0)
    }
}

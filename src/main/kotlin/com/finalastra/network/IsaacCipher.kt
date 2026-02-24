package com.finalastra.network

class IsaacCipher(seed: IntArray) {
    private val mm = IntArray(256)
    private val results = IntArray(256)
    private var aa = 0
    private var bb = 0
    private var cc = 0
    private var index = 0

    init {
        for (i in seed.indices.coerceAtMost(256)) {
            results[i] = seed[i]
        }
        init(true)
    }

    fun nextInt(): Int {
        if (index-- == 0) {
            isaac()
            index = 255
        }
        return results[index]
    }

    private fun isaac() {
        bb += ++cc
        for (i in 0 until 256) {
            val x = mm[i]
            aa = when (i and 3) {
                0 -> aa xor (aa shl 13)
                1 -> aa xor (aa ushr 6)
                2 -> aa xor (aa shl 2)
                else -> aa xor (aa ushr 16)
            }
            aa += mm[(i + 128) and 255]
            val y = mm[(x ushr 2) and 255] + aa + bb
            mm[i] = y
            bb = mm[(y ushr 10) and 255] + x
            results[i] = bb
        }
    }

    private fun init(flag: Boolean) {
        var a = -0x61c88647
        var b = -0x61c88647
        var c = -0x61c88647
        var d = -0x61c88647
        var e = -0x61c88647
        var f = -0x61c88647
        var g = -0x61c88647
        var h = -0x61c88647

        repeat(4) {
            a = a xor (b shl 11); d += a; b += c
            b = b xor (c ushr 2); e += b; c += d
            c = c xor (d shl 8); f += c; d += e
            d = d xor (e ushr 16); g += d; e += f
            e = e xor (f shl 10); h += e; f += g
            f = f xor (g ushr 4); a += f; g += h
            g = g xor (h shl 8); b += g; h += a
            h = h xor (a ushr 9); c += h; a += b
        }

        for (i in 0 until 256 step 8) {
            if (flag) {
                a += results[i]; b += results[i + 1]; c += results[i + 2]; d += results[i + 3]
                e += results[i + 4]; f += results[i + 5]; g += results[i + 6]; h += results[i + 7]
            }
            a = a xor (b shl 11); d += a; b += c
            b = b xor (c ushr 2); e += b; c += d
            c = c xor (d shl 8); f += c; d += e
            d = d xor (e ushr 16); g += d; e += f
            e = e xor (f shl 10); h += e; f += g
            f = f xor (g ushr 4); a += f; g += h
            g = g xor (h shl 8); b += g; h += a
            h = h xor (a ushr 9); c += h; a += b
            mm[i] = a; mm[i + 1] = b; mm[i + 2] = c; mm[i + 3] = d
            mm[i + 4] = e; mm[i + 5] = f; mm[i + 6] = g; mm[i + 7] = h
        }

        if (flag) {
            for (i in 0 until 256 step 8) {
                a += mm[i]; b += mm[i + 1]; c += mm[i + 2]; d += mm[i + 3]
                e += mm[i + 4]; f += mm[i + 5]; g += mm[i + 6]; h += mm[i + 7]
                a = a xor (b shl 11); d += a; b += c
                b = b xor (c ushr 2); e += b; c += d
                c = c xor (d shl 8); f += c; d += e
                d = d xor (e ushr 16); g += d; e += f
                e = e xor (f shl 10); h += e; f += g
                f = f xor (g ushr 4); a += f; g += h
                g = g xor (h shl 8); b += g; h += a
                h = h xor (a ushr 9); c += h; a += b
                mm[i] = a; mm[i + 1] = b; mm[i + 2] = c; mm[i + 3] = d
                mm[i + 4] = e; mm[i + 5] = f; mm[i + 6] = g; mm[i + 7] = h
            }
        }

        isaac()
        index = 255
    }
}

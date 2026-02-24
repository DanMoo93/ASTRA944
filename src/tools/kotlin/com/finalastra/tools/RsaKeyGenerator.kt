package com.finalastra.tools

import java.security.KeyPairGenerator
import java.security.interfaces.RSAPublicKey

object RsaKeyGenerator {
    fun run() {
        val keyPairGenerator = KeyPairGenerator.getInstance("RSA")
        keyPairGenerator.initialize(1024)
        val keyPair = keyPairGenerator.generateKeyPair()
        val publicKey = keyPair.public as RSAPublicKey

        println("modulus=${publicKey.modulus.toString(16)}")
        println("exponent=${publicKey.publicExponent.toString(16)}")
    }
}

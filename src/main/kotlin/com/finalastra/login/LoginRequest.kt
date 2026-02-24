package com.finalastra.login

data class LoginRequest(
    val opcode: Int,
    val revision: Int,
    val username: String,
    val password: String,
    val isaacSeed: IntArray
)

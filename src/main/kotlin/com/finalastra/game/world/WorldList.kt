package com.finalastra.game.world

data class World(val id: Int, val name: String, val address: String, val players: Int)

class WorldList {
    private val worlds = mutableListOf<World>()

    fun register(world: World) {
        worlds += world
    }

    fun all(): List<World> = worlds.toList()
}

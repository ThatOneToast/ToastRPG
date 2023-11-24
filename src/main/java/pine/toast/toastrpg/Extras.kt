package pine.toast.toastrpg

import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.World
import org.bukkit.block.Block
import org.bukkit.entity.LivingEntity
import kotlin.math.pow
import kotlin.math.sqrt

object Extras {

    /**
     * Calculate the distance between two living entities.
     * @param entity - The first entity
     * @param entity2 - The second entity
     */
    fun calculateDistanceFrom(entity: LivingEntity, entity2: LivingEntity): Double {
        val x1 = entity.x
        val y1 = entity.y
        val z1 = entity.z
        val x2 = entity2.x
        val y2 = entity2.y
        val z2 = entity2.z
        return sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0) + (z2 - z1).pow(2.0))
    }

    fun getAllLoadedChunks(world: World): Set<Chunk> {
        val loadedChunks = mutableSetOf<Chunk>()
        loadedChunks.addAll(world.loadedChunks)
        return loadedChunks
    }


    fun getSunlitLocations(world: World, chunks: Collection<Chunk>): Set<Location> {
        val sunlitBlockLocations = mutableSetOf<Location>()

        for (chunk in chunks) {
            for (x in 0 until 16) {
                for (z in 0 until 16) {
                    for (y in 0 until world.maxHeight) {
                        val block = chunk.getBlock(x, y, z)

                        if (isSunlitBlock(world, block)) {
                            sunlitBlockLocations.add(block.location)
                        }
                    }
                }
            }
        }

        return sunlitBlockLocations
    }



    /**
     * Gets all the player spawns in a world.
     * @param world - The world to get the player spawns of
     * @return A map of all the player spawns
     */
    fun getAllPlayerSpawns(world: World, chunks: Set<Chunk>): Set<Location> {
        val playerSpawns = mutableSetOf<Location>()

        for (chunk in chunks) {
            for (player in world.players) {
                if (player.location.chunk == chunk) {
                    playerSpawns.add(player.bedSpawnLocation ?: player.location)
                }
            }
        }

        return playerSpawns
    }


    /**
     * Gets all the player last death locations in a given chunk set
     * @param world - The world to get the player last death locations of
     * @param chunks - The chunks to get the player last death locations of
     */
    fun getAllPlayerLastDeathLocations(world: World, chunks: Set<Chunk>): Set<Location> {
        val playerLastDeathLocations = mutableSetOf<Location>()

        for (chunk in chunks) {
            for (player in world.players) {
                if (player.location.chunk == chunk) {
                    playerLastDeathLocations.add(player.lastDeathLocation ?: player.location)
                }
            }
        }

        return playerLastDeathLocations
    }


    fun findPopulatedAreas(world: World, chunks: Set<Chunk>, maxDensity: Int): Set<Chunk> {
        val highPopChunks: MutableSet<Chunk> = HashSet()

        highPopChunks.clear()

        for (centerChunk in chunks) {
            if (highPopChunks.size != 9) {

                for (xOffset in -1..1) {
                    for (yOffset in -1..1) {

                        val chunkX = centerChunk.x + xOffset
                        val chunkZ = centerChunk.z + yOffset
                        val surroundingChunk = world.getChunkAt(chunkX, chunkZ)
                        val density = getPlayerDensity(surroundingChunk, world)

                        if (density >= maxDensity) {
                            highPopChunks.add(surroundingChunk)
                        }

                    }
                }
            } else {
                break
            }
        }

        return highPopChunks

    }

    private fun getPlayerDensity(centerChunk: Chunk, world: World): Int {
        val chunkRadius = 1 // Considering a 3x3 chunk area (1 chunk radius in each direction)
        val minX = centerChunk.x - chunkRadius
        val maxX = centerChunk.x + chunkRadius
        val minZ = centerChunk.z - chunkRadius
        val maxZ = centerChunk.z + chunkRadius

        return world.players.count { player ->
            val playerChunkX = player.location.chunk.x
            val playerChunkZ = player.location.chunk.z

            playerChunkX in minX..maxX && playerChunkZ in minZ..maxZ
        }
    }


    /**
     * Gets the exposed blocks to the moon in a collection of chunks.
     * @param chunks - The chunks to get the exposed blocks of
     * @param world - The world to get the exposed blocks of
     * @return The exposed blocks to the moon
     */
    fun getMoonExposedBlocks(world: World, chunks: Set<Chunk>): Set<Location> {
        val exposedBlocks = HashSet<Location>()

        for (chunk in chunks) {
            for (x in 0..15) {
                for (z in 0..15) {
                    val block = world.getBlockAt(chunk.x * 16 + x, 0, chunk.z * 16 + z)

                    if (isExposedToMoon(world, block)) {
                        exposedBlocks.add(block.location.add(0.0, 1.0, 0.0))
                    }
                }
            }
        }

        return exposedBlocks
    }

    /**
     * Gets all the blocks in a collection of chunks.
     * @param world - The world to get the blocks of
     * @param chunks - The chunks to get the blocks of
     * @return All the blocks location in a set
     */
    fun getAllBlockLocations(world: World, chunks: Set<Chunk>): Set<Location> {
        val blocks = HashSet<Location>()

        for (chunk in chunks) {
            for (x in 0..15) {
                for (z in 0..15) {
                    val block = world.getBlockAt(chunk.x * 16 + x, 0, chunk.z * 16 + z)

                    if (world.getHighestBlockYAt(block.x, block.z) == block.y) {
                        blocks.add(block.location.add(0.0, 1.0, 0.0))
                    }
                }
            }
        }

        return blocks
    }

    fun findUndergroundSpawnLocation(world: World, startingChunks: Set<Chunk>): HashSet<Location> {
        val spawnableBlocks = HashSet<Location>()

        for (chunk in startingChunks) {
            for (x in chunk.x * 16 until (chunk.x + 1) * 16) {
                for (z in chunk.z * 16 until (chunk.z + 1) * 16) {
                    for (y in 1 until 5) {
                        val block = world.getBlockAt(x, y, z)

                        if (block.type == Material.AIR &&
                            world.getBlockAt(x, y + 1, z).type == Material.AIR &&
                            world.getBlockAt(x, y - 1, z).type == Material.AIR
                        ) {
                            if (checkSurroundingBlocks(world, x, y, z)) {
                                spawnableBlocks.add(Location(world, x.toDouble(), y.toDouble() + 1.0, z.toDouble()))
                            }
                        }
                    }
                }
            }
        }

        return spawnableBlocks
    }


    private fun checkSurroundingBlocks(world: World, x: Int, y: Int, z: Int): Boolean {
        val surroundingBlocks = arrayOf(
            world.getBlockAt(x - 1, y, z),
            world.getBlockAt(x + 1, y, z),
            world.getBlockAt(x, y, z - 1),
            world.getBlockAt(x, y, z + 1)
        )

        return surroundingBlocks.all { it.type == Material.AIR }
    }

    private fun isExposedToMoon(world: World, block: Block): Boolean {
        return block.lightLevel <= 8 && world.getHighestBlockYAt(block.x, block.z) == block.y
    }





    private fun isSunlitBlock(world: World, block: Block): Boolean {
        return world.isDayTime && world.getHighestBlockYAt(block.x, block.z) == block.y
    }
}

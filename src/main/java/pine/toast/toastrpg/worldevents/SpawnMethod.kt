package pine.toast.toastrpg.worldevents

import org.bukkit.Chunk
import org.bukkit.Location
import org.bukkit.World
import pine.toast.toastrpg.Extras

enum class SpawnMethod {
   LOADED_CHUNKS,
   POPULATION_DENSITY_HIGH,
   POPULATION_DENSITY_MEDIUM,
   POPULATION_DENSITY_LOW,
   PLAYER_SPAWNS,
   PLAYER_DEATHS,
   RANDOM;

   fun getRandomSpawnMethod(): SpawnMethod {
      val methodLists: List<SpawnMethod> = listOf(
         LOADED_CHUNKS,
         POPULATION_DENSITY_HIGH,
         POPULATION_DENSITY_MEDIUM,
         POPULATION_DENSITY_LOW,
         PLAYER_SPAWNS,
         PLAYER_DEATHS,
         RANDOM
      )

      return methodLists.random()
   }



   fun getSpawnLocations(method: SpawnMethod, world: World, rules: Set<SpawnRules>): Set<Location> {
      val methodLocations = mutableSetOf<Location>()
      val combinedRuleSet = mutableSetOf<Location>()

      val loadedChunks: Set<Chunk> = Extras.getAllLoadedChunks(world)
      val highDenseChunks: Set<Chunk> = Extras.findPopulatedAreas(world, loadedChunks, 30)
      val mediumDenseChunks: Set<Chunk> = Extras.findPopulatedAreas(world, loadedChunks, 15)
      val lowDenseChunks: Set<Chunk> = Extras.findPopulatedAreas(world, loadedChunks, 5)
      val playerSpawns: Set<Location> = Extras.getAllPlayerSpawns(world, loadedChunks)
      val playerDeaths: Set<Location> = Extras.getAllPlayerLastDeathLocations(world, loadedChunks)
      val random: Set<Location> = Extras.getAllBlockLocations(world, loadedChunks)

      when (method) {
         LOADED_CHUNKS -> methodLocations.addAll(Extras.getAllBlockLocations(world, loadedChunks))
         POPULATION_DENSITY_HIGH -> methodLocations.addAll(Extras.getAllBlockLocations(world, highDenseChunks))
         POPULATION_DENSITY_MEDIUM -> methodLocations.addAll(Extras.getAllBlockLocations(world, mediumDenseChunks))
         POPULATION_DENSITY_LOW -> methodLocations.addAll(Extras.getAllBlockLocations(world, lowDenseChunks))
         PLAYER_SPAWNS -> methodLocations.addAll(playerSpawns)
         PLAYER_DEATHS -> methodLocations.addAll(playerDeaths)
         RANDOM -> methodLocations.addAll(random)
      }

      when {
         rules.contains(SpawnRules.ABOVE_GROUND_DAY) -> combinedRuleSet.addAll(Extras.getSunlitLocations(world, loadedChunks))
         rules.contains(SpawnRules.ABOVE_GROUND_NIGHT) -> combinedRuleSet.addAll(Extras.getMoonExposedBlocks(world, loadedChunks))
         rules.contains(SpawnRules.BELOW_GROUND) -> combinedRuleSet.addAll(Extras.findUndergroundSpawnLocation(world, loadedChunks))
         rules.contains(SpawnRules.ANYWHERE) -> combinedRuleSet.addAll(Extras.getAllBlockLocations(world, loadedChunks))
      }

      methodLocations.retainAll(combinedRuleSet)
      return methodLocations
   }




}
package pine.toast

import org.bukkit.entity.LivingEntity
import org.bukkit.entity.Player
import kotlin.math.pow
import kotlin.math.sqrt

object Extras {
    fun calculateDistance(entity: LivingEntity, player: Player): Double {
        val x1 = entity.x
        val y1 = entity.y
        val z1 = entity.z
        val x2 = player.x
        val y2 = player.y
        val z2 = player.z
        return sqrt((x2 - x1).pow(2.0) + (y2 - y1).pow(2.0) + (z2 - z1).pow(2.0))
    }
}

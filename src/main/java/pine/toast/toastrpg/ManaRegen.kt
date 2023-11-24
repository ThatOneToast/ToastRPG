package pine.toast.toastrpg

import org.bukkit.Bukkit
import org.bukkit.scheduler.BukkitTask
import pine.toast.toastrpg.playerutils.PlayerAttributes

class ManaRegen {
    private var manaUpdateTask: BukkitTask? = null
    private var taskID = -1
    private fun updatePlayerMana() {
        for (player in Bukkit.getOnlinePlayers()) {
            var currentMana = PlayerAttributes.getMana(player)
            val manaPerSec = PlayerAttributes.getManaPerSec(player)
            currentMana += manaPerSec
            val maxMana = PlayerAttributes.getMaxMana(player)
            if (currentMana > maxMana) {
                currentMana = maxMana
            }
           PlayerAttributes.setMana(player, currentMana)
        }
    }

    /**
     * Call this method to start Mana Regeneration
     */
    fun startManaUpdateTask() {
        if (taskID == -1) {
            manaUpdateTask = Bukkit.getScheduler().runTaskTimer(
                ToastRPG.getPassedPlugin()!!,
                Runnable { updatePlayerMana() },
                0L,
                20L
            ) // Change the delay (0L) and interval (20L) as needed
            taskID = manaUpdateTask!!.taskId
        }
    }

    /**
     * Call this method to stop Mana Regeneration
     */
    fun stopManaUpdateTask() {
        if (taskID != -1) {
            Bukkit.getScheduler().cancelTask(taskID)
            taskID = -1
        }
    }
}

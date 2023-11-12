package toast.pine;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitTask;

public class ManaRegen {

    private BukkitTask manaUpdateTask;
    private int taskID = -1;

    private void updatePlayerMana() {
        for (Player player : Bukkit.getOnlinePlayers()) {
            double currentMana = PlayerAttributes.getMana(player);
            double manaPerSec = PlayerAttributes.getManaPerSec(player);

            currentMana += manaPerSec;

            double maxMana = PlayerAttributes.getMaxMana(player);
            if (currentMana > maxMana) {
                currentMana = maxMana;
            }

            PlayerAttributes.setMana(player, currentMana);
        }
    }


    /**
    * Call this method to start Mana Regeneration
     */
    public void startManaUpdateTask() {
        if (taskID == -1) {
            manaUpdateTask = Bukkit.getScheduler().runTaskTimer(ToastRPG.getPassedPlugin(), this::updatePlayerMana, 0L, 20L); // Change the delay (0L) and interval (20L) as needed
            taskID = manaUpdateTask.getTaskId();
        }
    }


    /**
    * Call this method to stop Mana Regeneration
     */
    public void stopManaUpdateTask() {
        if (taskID != -1) {
            Bukkit.getScheduler().cancelTask(taskID);
            taskID = -1;
        }
    }

}

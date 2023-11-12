package toast.pine.LevelSystem;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerExpChangeEvent;
import toast.pine.ToastRPG;

public class Experience implements Listener {


    @EventHandler
    public void onExperiencePickUp(PlayerExpChangeEvent event) {

        Player player = event.getPlayer();
        int currentLevel = ToastRPG.getLevelManager().getLevel(player);

        ToastRPG.getLevelManager().addExperience(player, event.getAmount());


    }

    /*

    @EventHandler
    private void onPlayerLevelUp(PlayerLevelUpEvent event){
        List<Rewards> rewards = Rewards.getRandomRewards(1, 1);
        Player player = event.getPlayer();

        for (Rewards reward : rewards) {
            player.getInventory().addItem(reward.getItem());
        }

    }

    */


}

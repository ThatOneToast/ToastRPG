package toast.pine.Classes.Quests.Objectives;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import toast.pine.Classes.Quests.QuestObjective;
import toast.pine.SocialSystem.PlayerSocial;

public class MonsterSlaying implements QuestObjective, Listener {
    private final int targetQuantity;
    private int currentProgress;
    private final PlayerSocial owner;

    public MonsterSlaying(int targetQuantity, PlayerSocial playerSocial) {
        this.targetQuantity = targetQuantity;
        this.currentProgress = 0;
        this.owner = playerSocial;

    }


    @Override
    public boolean isComplete() {
        if (currentProgress >= targetQuantity) {
            EntityDeathEvent.getHandlerList().unregister(this);
            return true;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "Kill " + targetQuantity + " monsters.";
    }

    @Override
    public void updateProgress() {
        currentProgress++;
    }

    @EventHandler
    private void onPlayerKill(EntityDeathEvent event) {
        if(isComplete()) return;
        if (event.getEntity().getKiller() == owner.getPlayer()) {
            updateProgress();
        }
    }

}

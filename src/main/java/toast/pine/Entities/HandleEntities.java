package toast.pine.Entities;

import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Keys;
import toast.pine.Monsters.Monster;
import toast.pine.PlayerAttributes;
import toast.pine.ToastRPG;

public class HandleEntities implements Listener {

    @EventHandler(priority = EventPriority.HIGH)
    public void onEntitySpawns(EntitySpawnEvent event) {


    }

    @EventHandler(priority = EventPriority.NORMAL)
    public void onEntityDeath(EntityDeathEvent event) {

        PersistentDataContainer container = event.getEntity().getPersistentDataContainer();

        if (container.has(Keys.MONSTER_TYPE)) {
            Monster monster = ToastRPG.getMonsterManager().getMonster(event.getEntity());

            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new MonsterDeathEvent(event.getEntity(), monster, monster.getType()));
        }

    }

    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        PlayerAttributes.setMana(event.getPlayer(), 0.0);
    }


}

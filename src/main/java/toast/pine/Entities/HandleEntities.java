package toast.pine.Entities;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.Events.MonsterDeathEvent;
import toast.pine.Events.MonsterSpawnEvent;
import toast.pine.Events.MonsterTargetPlayerEvent;
import toast.pine.Extras;
import toast.pine.Keys;
import toast.pine.Monsters.Monster;
import toast.pine.PlayerAttributes;
import toast.pine.ToastRPG;

public class HandleEntities implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onEntitySpawns(EntitySpawnEvent event) {

        PersistentDataContainer container = event.getEntity().getPersistentDataContainer();

        if (event.getEntity() instanceof LivingEntity entity) {
            if (container.has(Keys.MONSTER_TYPE)) {
                Monster monster = ToastRPG.getMonsterManager().getMonster(entity);

                ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new MonsterSpawnEvent(entity, monster, monster.getType()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onMonsterSpawn(EntityTargetLivingEntityEvent event) {
        if (event.getEntity() instanceof LivingEntity entity) {
            if (event.getTarget() instanceof Player player) {
                if (ToastRPG.getMonsterManager().isMonster(entity)){
                    Monster monster = ToastRPG.getMonsterManager().getMonster(entity);
                    Double distance = Extras.calculateDistance(entity, player);
                    ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new MonsterTargetPlayerEvent(monster, monster.getType(), distance, player));
                }
            }
        }
    }



    @EventHandler(priority = EventPriority.HIGHEST)
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

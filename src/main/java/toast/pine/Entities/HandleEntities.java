package toast.pine.Entities;

import org.bukkit.block.Block;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.*;
import toast.pine.Events.*;
import toast.pine.Monsters.Monster;

public class HandleEntities implements Listener {

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onEntitySpawns(EntitySpawnEvent event) {

        PersistentDataContainer container = event.getEntity().getPersistentDataContainer();

        if (event.getEntity() instanceof LivingEntity entity) {
            if (container.has(Keys.MONSTER_TYPE)) {
                Monster monster = ToastRPG.getMonsterManager().getMonster(entity);

                ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new MonsterSpawnEvent(entity, monster, monster.getType()));
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onMonsterSpawn(EntityTargetLivingEntityEvent event) {
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
    private void onEntityDeath(EntityDeathEvent event) {

        PersistentDataContainer container = event.getEntity().getPersistentDataContainer();

        if (container.has(Keys.MONSTER_TYPE)) {
            Monster monster = ToastRPG.getMonsterManager().getMonster(event.getEntity());

            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new MonsterDeathEvent(event.getEntity(), monster, monster.getType()));
        }

    }

    @EventHandler(priority = EventPriority.HIGHEST)
    private void onPlayerRightClickCall(PlayerInteractEvent event) {

        Player player = event.getPlayer();
        ItemStack mainHand = player.getInventory().getItemInMainHand();
        ItemStack offHand = player.getInventory().getItemInOffHand();
        Block block = player.getTargetBlock(null, 5);
        PlayerDirection direction = new PlayerDirection(player.getY(), player.getPitch());
        Boolean isSneaking = player.isSneaking();


        if (event.getAction().isLeftClick()) {
            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerLeftClickEvent(player, mainHand, offHand, block, direction, isSneaking));
        } else if (event.getAction().isRightClick()) {
            ToastRPG.getPassedPlugin().getServer().getPluginManager().callEvent(new PlayerRightCLickEvent(player, mainHand, offHand, block, direction, isSneaking));
        }
    }



    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event){
        PlayerAttributes.setMana(event.getPlayer(), 0.0);
    }


}

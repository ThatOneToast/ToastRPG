package toast.pine;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.persistence.PersistentDataContainer;
import toast.pine.SocialSystem.PlayerSocial;

public class PlayerJoin implements Listener {


    @EventHandler
    private void onPlayerJoin(PlayerJoinEvent event) {
        PersistentDataContainer playerData = event.getPlayer().getPersistentDataContainer();

        playerData.set(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter(), new PlayerSocial(null, event.getPlayer(), null, null));
    }
}

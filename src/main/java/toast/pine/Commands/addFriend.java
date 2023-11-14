package toast.pine.Commands;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import toast.pine.Keys;
import toast.pine.SocialSystem.PlayerSocial;
import toast.pine.ToastRPG;

public class addFriend {


    @Command({"addfriend", "af", "addf"})
    public void addFriend(Player stranger, @Default("self") Player sender) {
        PersistentDataContainer strangerPDC = stranger.getPersistentDataContainer();
        PersistentDataContainer senderPDC = sender.getPersistentDataContainer();

        PlayerSocial strangerSocial = strangerPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());
        PlayerSocial senderSocial = senderPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());

        senderSocial.getFriends().sendInvite(strangerSocial);
    }

}

package toast.pine.Commands;

import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataContainer;
import revxrsal.commands.annotation.Command;
import revxrsal.commands.annotation.Default;
import toast.pine.Keys;
import toast.pine.SocialSystem.PlayerSocial;
import toast.pine.ToastRPG;

public class removeFriend {

    @Command({"removefriend", "rfriend", "removef"})
    public void addFriend(Player friend, @Default("self") Player sender) {
        PersistentDataContainer friendPDC = friend.getPersistentDataContainer();
        PersistentDataContainer senderPDC = sender.getPersistentDataContainer();

        PlayerSocial friendSocial = friendPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());
        PlayerSocial senderSocial = senderPDC.get(Keys.SOCIAL_PROFILE, ToastRPG.getAdapterManager().getSocialProfileAdapter());

        senderSocial.getFriends().removeFriend(friendSocial);
        friendSocial.getFriends().removeFriend(senderSocial);

    }


}

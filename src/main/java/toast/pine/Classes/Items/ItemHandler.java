package toast.pine.Classes.Items;

import toast.pine.Events.PlayerLeftClickEvent;
import toast.pine.Events.PlayerRightCLickEvent;

public interface ItemHandler {

    void onPlayerRightClick(PlayerRightCLickEvent event);

    void onPlayerLeftClick(PlayerLeftClickEvent event);


}

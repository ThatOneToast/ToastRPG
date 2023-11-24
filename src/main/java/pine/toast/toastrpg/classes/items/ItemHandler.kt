package pine.toast.toastrpg.classes.items

import pine.toast.toastrpg.events.PlayerLeftClickEvent
import pine.toast.toastrpg.events.PlayerRightCLickEvent


interface ItemHandler {
    fun onPlayerRightClick(event: PlayerRightCLickEvent)
    fun onPlayerLeftClick(event: PlayerLeftClickEvent)

}

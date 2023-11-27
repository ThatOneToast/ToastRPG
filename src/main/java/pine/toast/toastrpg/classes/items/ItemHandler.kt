package pine.toast.toastrpg.classes.items

import pine.toast.toastrpg.events.PlayerLeftClickEvent
import pine.toast.toastrpg.events.PlayerRightClickEvent


interface ItemHandler {
    fun onPlayerRightClick(event: PlayerRightClickEvent)
    fun onPlayerLeftClick(event: PlayerLeftClickEvent)

}

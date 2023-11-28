package pine.toast.toastrpg.core.classes.items

import pine.toast.toastrpg.core.events.PlayerLeftClickEvent
import pine.toast.toastrpg.core.events.PlayerRightClickEvent


interface ItemHandler {
    fun onPlayerRightClick(event: PlayerRightClickEvent)
    fun onPlayerLeftClick(event: PlayerLeftClickEvent)

}

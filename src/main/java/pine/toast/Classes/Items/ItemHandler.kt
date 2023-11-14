package toast.pine.Classes.Items

import toast.pine.Events.PlayerLeftClickEvent
import toast.pine.Events.PlayerRightCLickEvent


interface ItemHandler {
    fun onPlayerRightClick(event: PlayerRightCLickEvent?)
    fun onPlayerLeftClick(event: PlayerLeftClickEvent?)
}

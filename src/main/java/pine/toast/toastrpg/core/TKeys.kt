package pine.toast.toastrpg.core

import org.bukkit.NamespacedKey

object TKeys {
    val LEVEL = NamespacedKey("toastrpg", "level")
    val MAX_LEVEL = NamespacedKey("toastrpg", "max_level")
    val EXP = NamespacedKey("toastrpg", "exp")
    val MAX_MANA = NamespacedKey("toastrpg", "max_mana")
    val MANA_PER = NamespacedKey("toastrpg", "mana_per")
    val MONSTER_TYPE = NamespacedKey("toastrpg", "monster_type")
    val ITEM = NamespacedKey("toastrpg", "item")
    val SOCIAL_PROFILE = NamespacedKey("toastrpg", "social_profile")
    val SKILL = NamespacedKey("toastrpg", "skill")

    /**
     * Creates a namespaced key with the namespace "toastrpg"
     * This is mainly used for the Playbook to create keys
     * @param key The key to create
     */
    fun createKey(key: String): NamespacedKey {
        return NamespacedKey("toastrpg", key)
    }
}

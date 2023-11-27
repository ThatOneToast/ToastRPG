package pine.toast.toastrpg.adapters

class AdapterManager {
    val monsterAdapter = MonsterAdapter()
    val monsterTypeAdapter = MonsterTypeAdapter()
    val itemAdapter = ItemAdapter()
    val itemMaterialAdapter = ItemMaterialAdapter()
    val socialProfileAdapter = SocialProfileAdapter()
    val skillAdapter = SkillAdapter()

    init {
        print(" - AdapterManager ~ Loaded")
    }
}

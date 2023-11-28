package pine.toast.toastrpg.core.adapters

import pine.toast.toastrpg.core.ToastRPG

class AdapterManager {
    val monsterAdapter = MonsterAdapter()
    val monsterTypeAdapter = MonsterTypeAdapter()
    val itemAdapter = ItemAdapter()
    val itemMaterialAdapter = ItemMaterialAdapter()
    val socialProfileAdapter = SocialProfileAdapter()
    val skillAdapter = SkillAdapter()

    init {
        ToastRPG.getPassedPlugin()!!.logger.info(" - AdapterManager ~ Loaded")
    }
}

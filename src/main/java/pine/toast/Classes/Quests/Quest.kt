package toast.pine.Classes.Quests

import toast.pine.socialSystem.PlayerSocial

class Quest(owner: PlayerSocial, name: String, description: String, objective: QuestObjective) {
    private val questOwner: PlayerSocial
    private val name: String
    private val description: String
    private val objective: QuestObjective
    private var completed: Boolean

    init {
        questOwner = owner
        this.name = name
        this.description = description
        this.objective = objective
        completed = false
    }

    fun updateProgress() {
        if (objective.isComplete) {
            completed = true
        } else objective.updateProgress()
    }
}

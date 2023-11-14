package toast.pine.Classes.Quests

interface QuestObjective {
    val isComplete: Boolean
    val description: String?
    fun updateProgress()

}

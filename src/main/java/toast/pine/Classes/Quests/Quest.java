package toast.pine.Classes.Quests;

import toast.pine.SocialSystem.PlayerSocial;

public class Quest {

    private PlayerSocial questOwner;
    private String name;
    private String description;
    private QuestObjective objective;
    private boolean completed;

    public Quest(PlayerSocial owner, String name, String description, QuestObjective objective) {
        this.questOwner = owner;
        this.name = name;
        this.description = description;
        this.objective = objective;
        this.completed = false;
    }
    
    public void updateProgress() {
        if(objective.isComplete()) {
            completed = true;
        } else objective.updateProgress();
    }


}

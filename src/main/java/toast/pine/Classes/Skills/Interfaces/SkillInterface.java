package toast.pine.Classes.Skills.Interfaces;

import toast.pine.Classes.CharacterClass;

import java.util.List;

public interface SkillInterface {

    String getSkillName();
    CharacterClass getIntendedClassUse();
    List<String> getSkillDescription();
    int getSkillLevel();
    int getSkillMaxLevel();
    int getSkillCost();
    int getSkillCooldown();

}

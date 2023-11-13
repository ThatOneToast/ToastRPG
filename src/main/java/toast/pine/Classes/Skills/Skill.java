package toast.pine.Classes.Skills;

import toast.pine.Classes.CharacterClass;
import toast.pine.Classes.Skills.Interfaces.SkillInterface;

import java.util.List;

public abstract class Skill extends CharacterClass implements SkillInterface {

    protected final String skillName;
    protected final CharacterClass intendedClassUse;
    protected final SkillAOE skillAOE;
    protected final SkillDuration skillDuration;
    protected final List<String> skillDescription;
    protected final int skillLevel;
    protected final int skillMaxLevel;
    protected final int skillCost;
    protected final int skillCooldown;

    public Skill(String skillName, SkillAOE skillAOE, SkillDuration skillDuration, CharacterClass intendedClassUse, List<String> skillDescription, int skillLevel, int skillMaxLevel, int skillCost, int skillCooldown) {
        super(intendedClassUse.getName(), intendedClassUse.getIcon(), intendedClassUse.getHealth(), intendedClassUse.getDamage(), intendedClassUse.getMaxMana(), intendedClassUse.getManaPerSec(), intendedClassUse.getDefense(), intendedClassUse.getMaxLevel());
        this.skillName = skillName;
        this.skillAOE = skillAOE;
        this.skillDuration = skillDuration;
        this.intendedClassUse = intendedClassUse;
        this.skillDescription = skillDescription;
        this.skillLevel = skillLevel;
        this.skillMaxLevel = skillMaxLevel;
        this.skillCost = skillCost;
        this.skillCooldown = skillCooldown;
    }

    public String getSkillName() {
        return skillName;
    }

    public SkillAOE getSkillAOE() {
        return skillAOE;
    }

    public SkillDuration getSkillDuration() {
        return skillDuration;
    }

    public CharacterClass getIntendedClassUse() {
        return intendedClassUse;
    }

    public List<String> getSkillDescription() {
        return skillDescription;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public int getSkillMaxLevel() {
        return skillMaxLevel;
    }

    public int getSkillCost() {
        return skillCost;
    }

    public int getSkillCooldown() {
        return skillCooldown;
    }
}

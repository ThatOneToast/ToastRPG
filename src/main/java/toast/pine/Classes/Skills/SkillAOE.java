package toast.pine.Classes.Skills;

import toast.pine.Classes.Skills.Interfaces.SkillAOEInterface;

public abstract class SkillAOE extends Skill implements SkillAOEInterface {

    protected final Skill skill;
    protected final int skillRadius;

    public SkillAOE(Skill skill, int skillRadius) {
        super(skill.getSkillName(), skill.getIntendedClassUse(), skill.getSkillDescription(), skill.getSkillLevel(), skill.getSkillMaxLevel(), skill.getSkillCost(), skill.getSkillCooldown());
        this.skill = skill;
        this.skillRadius = skillRadius;

    }

    public int getSkillRadius() {
        return skillRadius;
    }

    public Skill getSkill() {
        return skill;
    }
}

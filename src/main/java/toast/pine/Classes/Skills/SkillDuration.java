package toast.pine.Classes.Skills;

public abstract class SkillDuration extends Skill{
    protected Skill skill;
    protected double skillDuration;

    public SkillDuration(Skill skill, double skillDuration) {
        super(skill.getSkillName(), skill.getIntendedClassUse(), skill.getSkillDescription(), skill.getSkillLevel(), skill.getSkillMaxLevel(), skill.getSkillCost(), skill.getSkillCooldown());
        this.skill = skill;
        this.skillDuration = skillDuration;
    }

    public double getSkillDuration() {
        return skillDuration;
    }

    public Skill getSkill() {
        return skill;
    }

}

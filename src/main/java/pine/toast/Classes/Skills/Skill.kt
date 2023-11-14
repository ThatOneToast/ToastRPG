package toast.pine.Classes.Skills

import toast.pine.Classes.CharacterClass

abstract class Skill(
    skillName: String,
    intendedClassUse: CharacterClass,
    skillDescription: List<String>,
    skillLevel: Int,
    skillMaxLevel: Int,
    skillCost: Int,
    skillCooldown: Int
) : CharacterClass(
    intendedClassUse.getName(),
    intendedClassUse.getIcon(),
    intendedClassUse.getHealth().toDouble(),
    intendedClassUse.getDamage(),
    intendedClassUse.getMaxMana(),
    intendedClassUse.getManaPerSec(),
    intendedClassUse.getDefense(),
    intendedClassUse.getMaxLevel()
) {
    private val skillName: String
    private val intendedClassUse: CharacterClass
    private val skillDescription: List<String>
    private var skillLevel: Int
    private val skillMaxLevel: Int
    private var skillCost: Int
    private var skillCooldown: Int

    init {
        this.skillName = skillName
        this.intendedClassUse = intendedClassUse
        this.skillDescription = skillDescription
        this.skillLevel = skillLevel
        this.skillMaxLevel = skillMaxLevel
        this.skillCost = skillCost
        this.skillCooldown = skillCooldown
    }

    fun getIntendedClassUse(): CharacterClass {
        return intendedClassUse
    }

    fun getSkillDescription(): List<String> {
        return skillDescription
    }

    fun getSkillLevel(): Int {
        return skillLevel
    }

    fun getSkillMaxLevel(): Int {
        return skillMaxLevel
    }

    fun setSkillLevel(level: Int) {
        skillLevel = level
    }

    fun getSkillCost(): Int {
        return skillCost
    }

    fun setSkillCost(cost: Int) {
        skillCost = cost
    }

    fun getSkillCooldown(): Int {
        return skillCooldown
    }

    fun setSkillCooldown(cooldown: Int) {
        skillCooldown = cooldown
    }

    abstract fun getSkill(): Skill
    abstract fun getSkillName(): String






}

package pine.toast.toastrpg.library.socialsystem

import pine.toast.toastrpg.library.Colors

class Clan(

    private var name: String,
    private var description: ArrayList<String>?,
    private var tag: String,
    private var color: Colors,


    private var leader: Social,
    private var officers: ArrayList<Social>,
    private var members: ArrayList<Social>,
    private var motd: ArrayList<String>,
    private var joinCode: String?


) {

    fun promoteMember(member: Social) {
        if(members.contains(member)) {
            officers.add(member)
            member.getPlayer().sendMessage(Colors.GRAY + "You have been promoted to officer.")
        }
    }

    fun demoteMember(member: Social) {
        if (officers.contains(member)) {
            officers.remove(member)
            member.getPlayer().sendMessage(Colors.GRAY + "You have been demoted to member.")
        }
    }


    fun getJoinCode(): String? {
        return joinCode
    }


    fun getLeader(): Social {
        return leader
    }

    fun setLeader(leader: Social) {
        this.leader = leader
    }

    fun getOfficers(): ArrayList<Social> {
        return ArrayList(officers)
    }

    fun addOfficer(officer: Social) {
        officers.add(officer)
    }

    fun removeOfficer(officer: Social) {
        officers.remove(officer)
    }

    fun getMembers(): ArrayList<Social> {
        return ArrayList(members)
    }

    fun addMember(member: Social) {
        members.add(member)
    }

    fun removeMember(member: Social) {
        members.remove(member)
    }



    fun getName(): String {
        return name
    }

    fun setName(name: String) {
        this.name = name
    }

    fun getDescription(): ArrayList<String> {
        return ArrayList(description!!)
    }

    fun setDescription(description: ArrayList<String>) {
        this.description = description
    }



    fun getTag(): String {
        return tag
    }

    fun setTag(tag: String) {
        this.tag = tag
    }

    fun getColor(): Colors {
        return color
    }

    fun setColor(color: Colors) {
        this.color = color
    }




    fun getMotd(): ArrayList<String> {
        return motd
    }

    fun setMotd(motd: ArrayList<String>) {
        this.motd = motd
    }


}
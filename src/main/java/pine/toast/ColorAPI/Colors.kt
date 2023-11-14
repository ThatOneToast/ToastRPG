package toast.pine.ColorAPI

enum class Colors(val code: String) {
    // Text color
    BLACK("§0"),
    DARK_BLUE("§1"),
    DARK_GREEN("§2"),
    DARK_AQUA("§3"),
    DARK_RED("§4"),
    DARK_PURPLE("§5"),
    GOLD("§6"),
    GRAY("§7"),
    DARK_GRAY("§8"),
    BLUE("§9"),
    GREEN("§a"),
    AQUA("§b"),
    RED("§c"),
    LIGHT_PURPLE("§d"),
    YELLOW("§e"),
    WHITE("§f"),

    // Formatting
    RESET("§r"),
    BOLD("§l"),
    ITALIC("§o"),
    UNDERLINE("§n"),
    STRIKETHROUGH("§m");


    operator fun plus(s: String): String {
        return (this + s).replace("&", "§")
    }

}
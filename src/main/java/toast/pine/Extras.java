package toast.pine;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Extras {

    public static double calculateDistance(LivingEntity entity, Player player) {
        double x1 = entity.getX();
        double y1 = entity.getY();
        double z1 = entity.getZ();

        double x2 = player.getX();
        double y2 = player.getY();
        double z2 = player.getZ();

        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }

}

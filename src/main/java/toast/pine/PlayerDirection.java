package toast.pine;

public class PlayerDirection {
    double yaw;
    double pitch;

    public PlayerDirection(double yaw, double pitch) {
        this.yaw = yaw;
        this.pitch = pitch;
    }

    public double getYaw() {
        return yaw;
    }

    public void setYaw(double yaw) {
        this.yaw = yaw;
    }

    public double getPitch() {
        return pitch;
    }

    public void setPitch(double pitch) {
        this.pitch = pitch;
    }

    public String getCardinalDirection() {
        double yaw = this.yaw;
        if (yaw < 0) {
            yaw += 360;
        }
        yaw %= 360;

        if (yaw >= 315 || yaw < 45) {
            return "North";
        } else if (yaw < 135) {
            return "East";
        } else if (yaw < 225) {
            return "South";
        } else {
            return "West";
        }
    }

    public String getIntermediateDirection() {
        double yaw = this.yaw;
        if (yaw < 0) {
            yaw += 360;
        }
        yaw %= 360;

        if (yaw >= 337.5 || yaw < 22.5) {
            return "North";
        } else if (yaw < 67.5) {
            return "Northeast";
        } else if (yaw < 112.5) {
            return "East";
        } else if (yaw < 157.5) {
            return "Southeast";
        } else if (yaw < 202.5) {
            return "South";
        } else if (yaw < 247.5) {
            return "Southwest";
        } else if (yaw < 292.5) {
            return "West";
        } else {
            return "Northwest";
        }
    }

}
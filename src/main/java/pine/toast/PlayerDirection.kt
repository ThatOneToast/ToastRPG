package pine.toast

class PlayerDirection(var yaw: Double, var pitch: Float) {

    val cardinalDirection: String
        get() {
            var yaw = yaw
            if (yaw < 0) {
                yaw += 360.0
            }
            yaw %= 360.0
            return if (yaw >= 315 || yaw < 45) {
                "North"
            } else if (yaw < 135) {
                "East"
            } else if (yaw < 225) {
                "South"
            } else {
                "West"
            }
        }
    val intermediateDirection: String
        get() {
            var yaw = yaw
            if (yaw < 0) {
                yaw += 360.0
            }
            yaw %= 360.0
            return if (yaw >= 337.5 || yaw < 22.5) {
                "North"
            } else if (yaw < 67.5) {
                "Northeast"
            } else if (yaw < 112.5) {
                "East"
            } else if (yaw < 157.5) {
                "Southeast"
            } else if (yaw < 202.5) {
                "South"
            } else if (yaw < 247.5) {
                "Southwest"
            } else if (yaw < 292.5) {
                "West"
            } else {
                "Northwest"
            }
        }
}
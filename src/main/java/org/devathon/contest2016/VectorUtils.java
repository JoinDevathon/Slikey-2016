package org.devathon.contest2016;

import org.bukkit.util.Vector;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * @author kevin
 * @since 05.11.2016
 */
public class VectorUtils {

    public static void rotate2d(Vector v, double yaw) {
        double x, z, cos, sin;
        cos = cos(yaw);
        sin = sin(yaw);
        x = v.getX() * cos + v.getZ() * sin;
        z = v.getX() * -sin + v.getZ() * cos;
        v.setX(x).setZ(z);
    }

    public static double degToRadians(double angle) {
        return angle * Math.PI / 180;
    }

}

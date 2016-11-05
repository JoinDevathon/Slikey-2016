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
        double x, z ;
        x = cos(yaw) * v.getX() - sin(yaw) * v.getZ();
        z = sin(yaw) * v.getX() + cos(yaw) * v.getZ();
        v.setX(x).setZ(z);
    }

    public static double degToRadians(double angle) {
        return angle * Math.PI / 180;
    }

}

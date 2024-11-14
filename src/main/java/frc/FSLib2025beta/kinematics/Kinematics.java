package frc.FSLib2025beta.kinematics;

public class Kinematics {

    // Function 1: Forward Kinematics for a 2-joint Robot Arm
    public static double[] forwardKinematics2Joint(double l1, double l2, double theta1, double theta2) {
        // Convert angles from degrees to radians
        double theta1Rad = Math.toRadians(theta1);
        double theta2Rad = Math.toRadians(theta2);

        // Calculate the (x, y) position of the end-effector
        double x = l1 * Math.cos(theta1Rad) + l2 * Math.cos(theta1Rad + theta2Rad);
        double y = l1 * Math.sin(theta1Rad) + l2 * Math.sin(theta1Rad + theta2Rad);

        // Return the end-effector's coordinates as [x, y]
        return new double[]{x, y};
    }

    // Function 2: Inverse Kinematics for a 2-joint Robot Arm
    public static double[] inverseKinematics2Joint(double l1, double l2, double x, double y) {
        // Calculate the distance to the target point
        double distance = Math.sqrt(x * x + y * y);

        // Check if the target is reachable
        if (distance > (l1 + l2) || distance < Math.abs(l1 - l2)) {
            throw new IllegalArgumentException("Target position is not reachable.");
        }

        // Using the law of cosines to calculate angles
        double cosAngle2 = (x * x + y * y - l1 * l1 - l2 * l2) / (2 * l1 * l2);
        double sinAngle2 = Math.sqrt(1 - cosAngle2 * cosAngle2); // Using sin^2 + cos^2 = 1

        // Calculate joint angles in radians
        double theta2 = Math.atan2(sinAngle2, cosAngle2);
        double k1 = l1 + l2 * cosAngle2;
        double k2 = l2 * sinAngle2;
        double theta1 = Math.atan2(y, x) - Math.atan2(k2, k1);

        // Convert angles to degrees
        double theta1Deg = Math.toDegrees(theta1);
        double theta2Deg = Math.toDegrees(theta2);

        // Return joint angles as [theta1, theta2]
        return new double[]{theta1Deg, theta2Deg};
    }

    // Function 3: Forward Kinematics for a 2-joint Robot Arm with a Turntable
    public static double[] forwardKinematics2JointWithTurntable(double l1, double l2, double thetaBase, double theta1, double theta2) {
        // Convert angles from degrees to radians
        double thetaBaseRad = Math.toRadians(thetaBase);
        double theta1Rad = Math.toRadians(theta1);
        double theta2Rad = Math.toRadians(theta2);

        // Calculate the (x, y) position of the end-effector in the arm's plane
        double x = l1 * Math.cos(theta1Rad) + l2 * Math.cos(theta1Rad + theta2Rad);
        double y = l1 * Math.sin(theta1Rad) + l2 * Math.sin(theta1Rad + theta2Rad);

        // Adjust coordinates by the base rotation
        double finalX = x * Math.cos(thetaBaseRad) - y * Math.sin(thetaBaseRad);
        double finalY = x * Math.sin(thetaBaseRad) + y * Math.cos(thetaBaseRad);

        // Return the end-effector's coordinates as [x, y] with turntable rotation
        return new double[]{finalX, finalY};
    }

    // Function 4: Inverse Kinematics for a 2-joint Robot Arm with a Turntable
    public static double[] inverseKinematics2JointWithTurntable(double l1, double l2, double x, double y, double thetaBase) {
        // Convert base angle from degrees to radians
        double thetaBaseRad = Math.toRadians(thetaBase);

        // Adjust target coordinates to remove base rotation effect
        double adjustedX = x * Math.cos(-thetaBaseRad) - y * Math.sin(-thetaBaseRad);
        double adjustedY = x * Math.sin(-thetaBaseRad) + y * Math.cos(-thetaBaseRad);

        // Calculate the distance to the target point
        double distance = Math.sqrt(adjustedX * adjustedX + adjustedY * adjustedY);

        // Check if the target is reachable
        if (distance > (l1 + l2) || distance < Math.abs(l1 - l2)) {
            throw new IllegalArgumentException("Target position is not reachable.");
        }

        // Using the law of cosines to calculate angles
        double cosAngle2 = (adjustedX * adjustedX + adjustedY * adjustedY - l1 * l1 - l2 * l2) / (2 * l1 * l2);
        double sinAngle2 = Math.sqrt(1 - cosAngle2 * cosAngle2);

        // Calculate joint angles in radians
        double theta2 = Math.atan2(sinAngle2, cosAngle2);
        double k1 = l1 + l2 * cosAngle2;
        double k2 = l2 * sinAngle2;
        double theta1 = Math.atan2(adjustedY, adjustedX) - Math.atan2(k2, k1);

        // Convert angles to degrees
        double theta1Deg = Math.toDegrees(theta1);
        double theta2Deg = Math.toDegrees(theta2);

        // Return joint angles as [theta1, theta2] with turntable rotation
        return new double[]{theta1Deg, theta2Deg};
    }
}


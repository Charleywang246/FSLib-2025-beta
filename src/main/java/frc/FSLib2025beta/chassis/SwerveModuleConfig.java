package frc.FSLib2025beta.chassis;

import edu.wpi.first.math.geometry.Rotation2d;

public class SwerveModuleConfig {

    public int driveMotorId;
    public int angleMotorId;
    public int cancoderId;
    public Rotation2d angleOffset;
    
    public SwerveModuleConfig (int driveMotorId, int angleMotorId, int cancoderId, Rotation2d angleOffset) {
        this.driveMotorId = driveMotorId;
        this.angleMotorId = angleMotorId;
        this.cancoderId = cancoderId;
        this.angleOffset = angleOffset;
    }
}

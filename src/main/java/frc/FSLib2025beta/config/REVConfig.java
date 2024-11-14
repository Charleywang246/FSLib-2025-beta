package frc.FSLib2025beta.config;

import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

public class REVConfig {

    public void foo () {
        SparkMax leader = new SparkMax(0, MotorType.kBrushless);
        SparkMax follower = new SparkMax(1, MotorType.kBrushless);
        setInverted(leader, false);
        setIdleMode(leader, IdleMode.kBrake);
        setFollow(follower, leader);
        setSmartCurrentLimit(leader, 80);
        setSmartCurrentLimit(leader, 80, 20);
        setEncoderInverted(leader, false);
        setEncoderConversionFactor(leader, 4.28);
    }

    public static SparkMaxConfig getFollowConfig (SparkMax leader) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.follow(leader);
        return config;
    }

    public static void setFollow (SparkMax follower, SparkMax leader) {
        configSparkMax(follower, getFollowConfig(leader));
    }

    public static SparkMaxConfig getIdleConfig (IdleMode idleMode) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.idleMode(idleMode);
        return config;
    }

    public static void setIdleMode (SparkMax motor, IdleMode idleMode) {
        configSparkMax(motor, getIdleConfig(idleMode));
    }

    public static SparkMaxConfig getInvertedConfig (boolean inverted) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.inverted(inverted);
        return config;
    }

    public static void setInverted (SparkMax motor, boolean inverted) {
        configSparkMax(motor, getInvertedConfig(inverted));
    }

    public static SparkMaxConfig getSmartCurrentLimitConfig (int stallCurrent) {
        return getSmartCurrentLimitConfig(stallCurrent, 0);
    }

    public static SparkMaxConfig getSmartCurrentLimitConfig (int stallCurrent, int freeCurrent) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.smartCurrentLimit(stallCurrent, freeCurrent);
        return config;
    }

    public static void setSmartCurrentLimit (SparkMax motor, int stallCurrent) {
        configSparkMax(motor, getSmartCurrentLimitConfig(stallCurrent));
    }

    public static void setSmartCurrentLimit (SparkMax motor, int stallCurrent, int freeCurrent) {
        configSparkMax(motor, getSmartCurrentLimitConfig(stallCurrent, freeCurrent));
    }

    public static SparkMaxConfig getEncoderInvertedConfig (boolean inverted) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.encoder.inverted(inverted);
        return config;
    }

    public static void setEncoderInverted (SparkMax motor, boolean inverted) {
        configSparkMax(motor, getEncoderInvertedConfig(inverted));
    }

    public static SparkMaxConfig getEncoderConversionFactorConfig (double conversionFactor) {
        SparkMaxConfig config = new SparkMaxConfig();
        config.encoder.positionConversionFactor(conversionFactor);
        config.encoder.velocityConversionFactor(conversionFactor);
        return config;
    }

    public static void setEncoderConversionFactor (SparkMax motor, double conversionFactor) {
        configSparkMax(motor, getEncoderConversionFactorConfig(conversionFactor));
    }

    private static void configSparkMax (SparkMax motor, SparkMaxConfig config) {
        motor.configure(config, ResetMode.kNoResetSafeParameters, PersistMode.kNoPersistParameters);
    }

}

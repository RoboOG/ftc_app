package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.util.ElapsedTime;
import org.firstinspires.ftc.robotcontroller.external.samples.HardwarePushbot;

@Autonomous(name="Auto Drive By Encoder", group="Pushbot")
//@Disabled
public class Automatic_Encoder_Op_Mode extends LinearOpMode {

    /* Declare OpMode members. */
    HardwarePushbot robot   = new HardwarePushbot();   // Use a Pushbot's hardware
    private ElapsedTime runtime = new ElapsedTime();

    static final double COUNTS_PER_MOTOR_REV = 2240;    // eg: TETRIX Motor Encoder
    static final double DRIVE_GEAR_REDUCTION = 1.0;     // This is < 1.0 if geared UP
    static final double WHEEL_DIAMETER_INCHES = 3.54;     // For figuring circumference
    static final double COUNTS_PER_INCH = (COUNTS_PER_MOTOR_REV * DRIVE_GEAR_REDUCTION) /
            (WHEEL_DIAMETER_INCHES * 3.14159265363);
    static final double DRIVE_SPEED = 2.0;
    static final double TURN_SPEED = 0.5;


    @Override
    public void runOpMode() {

        /*
         * Initialize the drive system variables.
         * The init() method of the hardware class does all the work here
         */
        robot.init(hardwareMap);

        // Send telemetry message to signify robot waiting;
        telemetry.addData("Status", "Resetting Encoders");    //
        telemetry.update();

        robot.leftDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Send telemetry message to indicate successful Encoder reset
        telemetry.addData("Path0", "Starting at %7d :%7d",
                robot.leftDrive.getCurrentPosition(),
                robot.rightDrive.getCurrentPosition());
        telemetry.update();

        telemetry.addData("Status", "Initialized");

        // Wait for the game to start (driver presses PLAY)
        waitForStart();

        robot.leftDrive.setPower(-DRIVE_SPEED);
        robot.rightDrive.setPower(DRIVE_SPEED);

        robot.leftDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robot.rightDrive.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //ZAČÁTEK NA STRANĚ S KRÁTEREM
        //Základní trasa, otočky můžeme dopsat až s definitivně připevněnýma kolečkama
        //Vzdálenosti jsou jenom odhady by oko podle obrázků

        encoderDrive(DRIVE_SPEED, 24, 24,0.1);
        //před 3 minerálama, Truny přinesl jeden Colour Sensor a Distance sensor, můžeme zkusit příště
        encoderDrive(DRIVE_SPEED, 36, 36,0.1);
        //zatáčka na cestě do team depotu v rohu
        encoderDrive(DRIVE_SPEED, 52, 52, 0.1);
        //team depot v rohu, musíme vyhodit marker
        encoderDrive(DRIVE_SPEED,80, 80, 0.1);
        //cesta do kráteru


        robot.leftDrive.setPower(0);
        robot.rightDrive.setPower(0);

        sleep(1000);     // pause for servos to move

        telemetry.addData("Path", "Complete");
        telemetry.update();
    }

    private void encoderDrive(double driveSpeed, int i, int i1, double v) {

    }

}

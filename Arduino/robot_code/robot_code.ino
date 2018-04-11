#include <SPI.h>
#include <Ethernet.h>
#include <Servo.h>
#include <EEPROM.h>
#include <RobotOpenSH.h>
#include <math.h>
#define NEUTRAL 127
#define FORWARD 255
#define REVERSE 0
#define FORWARD_MS 2000
#define REVERSE_MS 1000
#define NEUTRAL_MS 1500


//Declaring which joystick we are using
ROJoystick usb1(1);


Servo left;
Servo right;
Servo actuator1;
Servo actuator2;
Servo actuator3;

//Delay time for calibration
int d = 250;

//IP Address of the Arduino
IPAddress ip ( 10, 5, 4, 50);     //<=== Make your own IP Address

void setup() {

  right.attach(7);
  left.attach(6);
  //  actuator1.attach(3);
  //  actuator2.attach(5);
  //  actuator3.attach(4);


  //This function makes sure the speed controller and the Arduino understand each other.
  //calibrate();

  //Communicates with the RobotOpen Driver Station
  RobotOpen.setIP(ip);

  //Begins the RobotOpen tasks
  RobotOpen.begin(&enabled, &disabled, &timedtasks);
}

void enabled() {

  //Records the value of the left analog stick on our selected joystick.
  //The motors move slightly slower when moving in reverse than when moving forward. The bias() function takes care of this problem.
  //Adjustmants may be needed depending on your setup.
  int valR = bias(usb1.rightY());
  int valL = bias(255 - usb1.leftY());

  //Maps valL and valR on a scale of 1000 - 2000.
  //The first two numbers are your high and low for the former scale and the last two are the high and low for the new scale.
  int spdL = map (valL, 0, 255, 1000, 2000);
  int spdR = map (valR, 0, 255, 1000, 2000);

  if ((valR > 125 && valR < 190) || (valR < 125 && valR > 62)) {
    int spdL = map (valL, 0, 255, 1400, 1600);
    int spdR = map (valR, 0, 255, 1400, 1600);
  }



  //The value becomes our wheel speeds
  left.writeMicroseconds(spdL);
  right.writeMicroseconds(spdR);

  int actOFF = 1500;
  int actUP = 2000;
  int actDOWN = 1000;

  if (usb1.btnLShoulder()) {
    actuator1.writeMicroseconds(actUP);
  }
  else if (usb1.lTrigger()) {
    actuator1.writeMicroseconds(actDOWN);
  }
  else {
    actuator1.writeMicroseconds(actOFF);
  }

  if (usb1.btnRShoulder()) {
    actuator2.writeMicroseconds(actUP);
  }
  else if (usb1.rTrigger()) {
    actuator2.writeMicroseconds(actDOWN);
  }
  else {
    actuator2.writeMicroseconds(actOFF);
  }
  if (usb1.btnB()) {
    actuator3.writeMicroseconds(actUP);
  }
  else if (usb1.btnA()) {
    actuator3.writeMicroseconds(actDOWN);
  }
  else {
    actuator3.writeMicroseconds(actOFF);
  }
}


void disabled() {
  // safety code
  //  left.writeMicroseconds(NEUTRAL_MS);
  //  leftFront.writeMicroseconds(NEUTRAL_MS);
  //  right.writeMicroseconds(NEUTRAL_MS);
  //  rightFront.writeMicroseconds(NEUTRAL_MS);
  left.writeMicroseconds(NEUTRAL_MS);
  right.writeMicroseconds(NEUTRAL_MS);
  actuator1.writeMicroseconds(NEUTRAL_MS);
  actuator2.writeMicroseconds(NEUTRAL_MS);
  actuator3.writeMicroseconds(NEUTRAL_MS);
}

void timedtasks() {
  //Publishing the values of the analog sticks to the RobotOpenDS
    RODashboard.publish("usb1.leftY()", usb1.leftY());
    RODashboard.publish("usb1.leftX()", usb1.leftX());
    RODashboard.publish("usb1.rightY()", usb1.rightY());
    RODashboard.publish("usb1.rightX()", usb1.rightX());
  
    RODashboard.publish("btnLShoulder()", usb1.btnLShoulder());
    RODashboard.publish("lTrigger()", usb1.lTrigger());
  
    RODashboard.publish("btnRShoulder()", usb1.btnRShoulder());
    RODashboard.publish("rTrigger()", usb1.rTrigger());
    RODashboard.publish("btnA()", usb1.btnA());
    RODashboard.publish("btnB()", usb1.btnB());

  RODashboard.publish("light", analogRead(A0));
  RODashboard.publish("temp", getTemp());
  RODashboard.publish("Uptime Seconds", ROStatus.uptimeSeconds());
}

void calibrate() {
  //Calibrating the Contorllers. See Appendix A in the Arduino Tutorial for more info
  left.writeMicroseconds(NEUTRAL_MS);
  right.writeMicroseconds(NEUTRAL_MS);
  delay(d);
  left.writeMicroseconds(FORWARD_MS);
  right.writeMicroseconds(FORWARD_MS);
  delay(d);
  left.writeMicroseconds(REVERSE_MS);
  right.writeMicroseconds(REVERSE_MS);
  delay(d);
  left.writeMicroseconds(NEUTRAL_MS);
  right.writeMicroseconds(NEUTRAL_MS);
}

//We declare that we have a function that is going to take an integer
int bias (int motor) {

  //The motors move around 15% slower when in reverse.
  float reverseBias = .85;

  //If the motor is moving forward we want to slow it down by 15%
  if (motor > NEUTRAL) {
    //Maps a new scale that will match the speed of the motors running in reverse
    //SEE APPENDIX C IN THE TUTORIAL FOR MORE INFO
    return map(motor, REVERSE , FORWARD, (FORWARD * (1.0 - reverseBias)), (FORWARD * reverseBias));

  } else {
    //Maps a new scale that will match the speed of the motors running in reverse
    return motor;
  }
}

void loop() {
  //Continually communicates with the Robot Open Driver Station
  RobotOpen.syncDS();
}

float getTemp() {
 int RawADC = analogRead(A1);
 float Temp;
 Temp = log(10000.0*((1024.0/RawADC-1))); 
//         =log(10000.0/(1024.0/RawADC-1)) // for pull-up configuration
 Temp = 1 / (0.001129148 + (0.000234125 + (0.0000000876741 * Temp * Temp ))* Temp );
 Temp = Temp - 273.15;            // Convert Kelvin to Celcius
 Temp = (Temp * 9.0)/ 5.0 + 32.0; // Convert Celcius to Fahrenheit
 return Temp;
}




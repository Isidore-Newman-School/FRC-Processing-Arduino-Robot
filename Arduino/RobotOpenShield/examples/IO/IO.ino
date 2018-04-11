#include <SPI.h>
#include <Ethernet.h>
#include <EEPROM.h>
#include <RobotOpenSH.h>




void setup()
{
  /* Initiate comms */
  RobotOpen.begin(&enabled, &disabled, &timedtasks);

  // Configure Digital I/O
  pinMode(SIDECAR_DIGITAL1, OUTPUT);
  pinMode(SIDECAR_DIGITAL2, OUTPUT);
}


/* This is your primary robot loop - all of your code
 * should live here that allows the robot to operate
 */
void enabled() {
  digitalWrite(SIDECAR_DIGITAL1, HIGH);
  digitalWrite(SIDECAR_DIGITAL2, HIGH);
}


/* This is called while the robot is disabled
 * PWMs and Solenoids are automatically disabled
 */
void disabled() {
  digitalWrite(SIDECAR_DIGITAL1, LOW);
  digitalWrite(SIDECAR_DIGITAL2, LOW);
}


/* This loop ALWAYS runs - only place code here that can run during a disabled state
 * This is also a good spot to put driver station publish code
 */
void timedtasks() {
  RODashboard.publish("Analog 0", analogRead(ANALOG0));
  RODashboard.publish("Uptime Seconds", ROStatus.uptimeSeconds());
}


// !!! DO NOT MODIFY !!!
void loop() {
  RobotOpen.syncDS();
}
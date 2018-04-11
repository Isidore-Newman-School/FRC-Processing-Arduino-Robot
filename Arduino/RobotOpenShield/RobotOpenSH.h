/*
  RobotOpen.h - Library implementation of the RobotOpen Hardware found at www.RobotOpen.biz
  Created by Eric Barch, September 27, 2012.
*/

#ifndef RobotOpen_h
#define RobotOpen_h

#include "Arduino.h"
#include <ROJoystick.h>
#include <ROPWM.h>
#include <ROAnalog.h>
#include <RODigitalIO.h>
#include <RODashboard.h>
#include <ROSolenoid.h>
#include <ROStatus.h>
#include <ROTimer.h>
#include <ROParameter.h>
#include "IPAddress.h"

// Sidecar Mapping
#define ANALOG0 0
#define ANALOG1 1
#define ANALOG2 2
#define ANALOG3 3
#define ANALOG4 4
#define ANALOG5 5
#define SIDECAR_DIGITAL1 7
#define SIDECAR_DIGITAL2 6
#define SIDECAR_DIGITAL3 5
#define SIDECAR_DIGITAL4 4
#define SIDECAR_DIGITAL5 3
#define SIDECAR_DIGITAL6 2
#define SIDECAR_DIGITAL7 9
#define SIDECAR_DIGITAL8 8



typedef void LoopCallback();


class RobotOpenClass {
public:
    // Set the IP address that the Robot controller will have
    static void setIP(IPAddress newIP);
    
    // Set the Subnet Mask that the Robot controller will have
    static void setSubnet(IPAddress newSubnet);
    
    // Set the Gateway router that the Robot controller will have
    static void setGateway(IPAddress newGateway);
    
    // Configure timeout until robot considers itself disconnected
    static void setTimeout(int new_timeout);

    // Fire up the RobotOpen object and get things running
    static void begin(LoopCallback *enabledCallback, LoopCallback *disabledCallback, LoopCallback *timedtasksCallback);
    
    // Exchange data with DS
    static void syncDS();

    // Log data to DS
    static void log(String data);
    
    // Tells us if the robot is enabled
    static boolean enabled();

    // How many joysticks are being received
    static int numJoysticks();

    // Calls for ROShield
    static void xmitPWM();
    static void xmitSolenoid();

    // Overloaded calls to publish back data
    static boolean publish(String id, byte val);
    static boolean publish(String id, int val);
    static boolean publish(String id, long val);
    static boolean publish(String id, float val);

    static char* getJoystick(char index);

    static void writePWM(byte channel, byte pwmVal);
    static void writeSolenoid(byte channel, uint8_t state);

    static void addParameter(ROParameter* param);

private:
    // Dumps data back to the DS
    static void publishDS();

    // This gets called once when the robot becomes disconnected or disabled
    static void onDisable();

    // Parse out a DS packet
    static void parsePacket();

    // Grab UDP data
    static void handleData();

    // Update parameter in EEPROM
    static void writeParameter(uint8_t location, unsigned int firstByte);

    // Send all parameters to DS
    static void sendParameters();
    
    // CRC16 checksum function
    static unsigned int calc_crc16(unsigned char *buf, unsigned short len);
};

extern RobotOpenClass RobotOpen;

#endif
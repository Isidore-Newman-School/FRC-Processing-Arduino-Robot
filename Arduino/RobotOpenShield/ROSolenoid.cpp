#include "RobotOpenSH.h"

/* Constructor */
ROSolenoid::ROSolenoid(uint8_t channel)
{
    _channel = channel;
}

void ROSolenoid::on() {
	RobotOpen.writeSolenoid(_channel, 0xFF);
}

void ROSolenoid::off() {
	RobotOpen.writeSolenoid(_channel, 0x00);
}
#ifndef ROStatus_h
#define ROStatus_h

#include "RobotOpenSH.h"



class ROStatusClass {
public:
	static boolean isEnabled();

	static int uptimeSeconds();

};

extern ROStatusClass ROStatus;

#endif
#include "RobotOpenSH.h"

// Class constructor
ROStatusClass ROStatus;




boolean ROStatusClass::isEnabled() {
    return RobotOpen.enabled();
}

int ROStatusClass::uptimeSeconds() {
    return (int)(millis()/1000);
}
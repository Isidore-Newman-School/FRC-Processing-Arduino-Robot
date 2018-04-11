#ifndef ROAnalog_h
#define ROAnalog_h

#include "RobotOpenSH.h"


class ROAnalog
{
  public:
    ROAnalog(uint8_t);

    int read();
    
  private:
    uint8_t _channel;
};



#endif
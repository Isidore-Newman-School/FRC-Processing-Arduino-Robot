
import java.net.InetAddress;
import robotopencontrol.*;
RORobotInstance robotInstance;

int light = 0;
float temp = 0;

void setup() {
  size(400, 400);
  initConnectButtons();
  robotInstance = new RORobotInstance(true);
}

void draw() {
  background(0);
  updateConnection();
  updateDashboard(1000);
  drawDashboard();
}

void keyPressed() {
  if (key == 'a') {
    robotInstance.setAnalogLeftY(100);
    robotInstance.setAnalogRightX(100);
    robotInstance.setAnalogRightY(100);
  }
}

void keyReleased() {
  if (key == 'a') {
    robotInstance.setAnalogLeftY(127);
    robotInstance.setAnalogRightY(127);
  }
}


void drawDashboard() {
  stroke(255);
  fill(255);
  text(light, 300, 100);
  text(temp + " ºF", 300, 150);
}


/*
  These are the methods available for controlling the robot
  (as if you were using a joystick / game controller).
  
  Currently the only functionality that is setup is the
  analogY (left and right motors). It is possible to add
  additional functionality.
*/

void initRobotControls() {
  robotInstance.setAnalogLeftX(127); // 0 is totally back, 127 is off, 255 is totally forward
  robotInstance.setAnalogLeftY(127);
  robotInstance.setAnalogRightX(127);
  robotInstance.setAnalogRightY(127);
  robotInstance.setLeftTrigger(127);
  robotInstance.setRightTrigger(127);
  robotInstance.setAux1(127);
  robotInstance.setAux2(127);
  robotInstance.setAux3(127);
  robotInstance.setAux4(127);

  robotInstance.setAButton(false);
  robotInstance.setBButton(false);
  robotInstance.setXButton(false);
  robotInstance.setYButton(false);
  robotInstance.setXShoulder(false);
  robotInstance.setYShoulder(false);
  
  robotInstance.setSelect(false);
  robotInstance.setStart(false);
  robotInstance.setLeftStickButton(false);
  robotInstance.setRightStickButton(false);
  robotInstance.setDUp(false);
  robotInstance.setDDown(false);
  robotInstance.setDLeft(false);
  robotInstance.setDRight(false);
}

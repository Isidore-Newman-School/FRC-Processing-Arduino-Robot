/*
  The code in this tab handles connected to the robot over
  a WiFi network. 
  
  You shouldn't need to modify this code.
*/

import controlP5.*;
ControlP5 cp5;
Button connectButton;
Button enableButton;

String IP = "10.5.4.50";
boolean tryingConnection = false;
boolean connected = false;
boolean enabled = false;
String roboStatus = "Disconnected";
long lastChecked = 0;
long  tryConnectionTime = 0;

void initConnectButtons() {
  cp5 = new ControlP5(this);

  // create a new button
  connectButton = cp5.addButton("connect")
    .setLabel("connect")
    .setSwitch(false)
    .setPosition(100, 100)
    .setSize(80, 20)
    ;

  enableButton = cp5.addButton("enable")
    .setLabel("enabled")
    .setSwitch(false)
    .setPosition(100, 150)
    .setSize(80, 20) 
    ;
}

void drawConnectionStatus() {
  stroke(255);
  fill(255);
  text(roboStatus, 100, 50);
  drawStatus();
}

void tryToConnect() {
  if (millis() -  tryConnectionTime < 5000) {
    int rxCount = robotInstance.getDashboardData().getValidRxCount();
    println("RX Count: " + rxCount);
    if (rxCount > 30) {
      roboStatus = "Broadcasting (Disabled)";
      println(roboStatus);
      connected = true;
      initRobotControls();
      connectButton.setLabel("Disconnect");
      tryingConnection = false;
    }
  } else {
    disconnectRobot();
  }
}

void connect() {
  if (!connected && !tryingConnection) {

    try {
      InetAddress.getByName(IP);
      robotInstance.getPacketTransmitter().setRemoteTarget(IP, 2);
      robotInstance.getPacketTransmitter().beginXmit();

      tryingConnection = true;
      tryConnectionTime = millis();

      connectButton.setLabel("Trying to connect");
      roboStatus = "Trying to connect";


      println(roboStatus);
    }
    catch (Exception e) {
      System.out.println("unable to connect");
      tryingConnection = false;
      //System.out.println(e.toString());
    }
  } else {
    disconnectRobot();
  }
}

void enable() {
  if (connected) {
    if (!enabled) {
      try {
        robotInstance.getPacketTransmitter().setEnabled();
        enableButton.setLabel("Disable");
        roboStatus = "Broadcasting (Enabled)";
        enabled = true;
        println(roboStatus);
      }
      catch(Exception e) {
        println("unable to enable");
      }
    } else {
      robotInstance.getPacketTransmitter().setDisabled();
      enableButton.setLabel("Enable");
      roboStatus = "Broadcasting (Disabled)";
      enabled = false;
      println(roboStatus);
    }
  }
}

void disableRobot() {
  if (enabled) {
    robotInstance.getPacketTransmitter().setDisabled();
    enableButton.setLabel("Enable");
    roboStatus = "Connected (Disabled)";
    enabled = false;
    println(roboStatus);
  }
}

void disconnectRobot() {
  if (connected || tryingConnection) {
    robotInstance.getPacketTransmitter().terminate();
    enableButton.setLabel("Enable");
    connectButton.setLabel("Connect");
    roboStatus= "Disconnected";
    enabled = false;
    connected = false;
    tryingConnection = false;
    println(roboStatus);
  }
}

void updateDashboard(int t) {
  if (connected) {
    if (millis() - lastChecked > t) {
      lastChecked = millis();
      updateDashboardData();
    }
  }
}

void updateDashboardData() {
  try {
    ArrayList<Bundle> bundles = robotInstance.getDashboardData().getBundles();
    light = (int)(bundles.get(10).getValue());
    temp = (float) bundles.get(11).getValue();
    
    for (int i = 0; i < bundles.size(); i++) {
      println(i + " " + bundles.get(i));
    }
  }
  catch(Exception e) {
    println("no data for dashboard?");
    println(e);
  }
}

void drawStatus() {
  noStroke();
  if (connected) fill(0, 255, 0);
  else if (tryingConnection) fill(255, 255, 0);
  else fill(255, 0, 0);
  float[] xy = connectButton.getPosition();
  float h = connectButton.getHeight();
  rect(xy[0]-h, xy[1], h, h);

  xy = enableButton.getPosition();
  if (enabled) fill(0, 255, 0);
  else fill(255, 0, 0);
  rect(xy[0]-h, xy[1], h, h);
}

void updateConnection() {
  drawConnectionStatus();
  if (tryingConnection) tryToConnect();
}

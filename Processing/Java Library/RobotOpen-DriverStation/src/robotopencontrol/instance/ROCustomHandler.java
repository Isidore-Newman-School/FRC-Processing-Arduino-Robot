package robotopencontrol.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Observable;


/**
* @author Jenna deBoisblanc
*/
public class ROCustomHandler extends Observable {

  private CustomController activeController;

  // Our constructor for the ROJoystickHandler object
  public ROCustomHandler() {
    activeController = new CustomController();
  }

  public byte[] exportValues() {
    try {
      //byte[] exportValues = new byte[getExportSize()];
      return activeController.getExportValues();
    }
    catch (Exception e) {
      System.exit(0);
      return null;
    }
  }
  
  private int getExportSize() {
    return 24;
  }

  // 0 - 3, analog leftX, lefty, rightX, rightY (0x00-0xFF)
  public byte getAnalogLeftX() {
    return activeController.getAnalogLeftX();
  }
  public byte getAnalogLeftY() {
    return activeController.getAnalogLeftY();
  }
  public byte getAnalogRightX() {
    return activeController.getAnalogRightX();
  }
  public byte getAnalogRightY() {
    return activeController.getAnalogRightY();
  }
  public void setAnalogLeftX(int val) {
    activeController.setAnalogLeftX(val);
  }
  public void setAnalogLeftY(int val) {
    activeController.setAnalogLeftY(val);
  }
  public void setAnalogRightX(int val) {
    activeController.setAnalogRightX(val);
  }
  public void setAnalogRightY(int val) {
    activeController.setAnalogRightY(val);
  }
  public void setAnalogPad(int p, int val) {
    activeController.setAnalogPad(p, val);
  }

  // 4-7, buttons A,B,X,Y (0x00 off, 0xFF on)
  public byte getAButton() {
    return activeController.getAButton();
  }
  public byte getBButton() {
    return activeController.getBButton();
  }
  public byte getXButton() {
    return activeController. getXButton();
  }
  public byte getYButton() {
    return activeController.getYButton();
  }
  public void setAButton(boolean on) {
    activeController.setAButton(on);
  }
  public void setBButton(boolean on) {
    activeController.setBButton(on);
  }
  public void setXButton(boolean on) {
    activeController.setXButton(on);
  }
  public void setYButton(boolean on) {
    activeController.setYButton(on);
  }
  public void setButtons(int b, boolean on) {
    activeController.setButtons(b, on);
  }

  // 8-9, buttons leftshoulder, rightshoulder (0x00 off, 0xFF on)
  public byte getXShoulder() {
    return activeController.getXShoulder();
  }
  public byte getYShoulder() {
    return activeController.getYShoulder();
  }
  public void setXShoulder(boolean on) {
    activeController.setXShoulder(on);
  }
  public void setYShoulder(boolean on) {
    activeController.setYShoulder(on);
  }

  // 10-11, Left Trigger, right trigger (0x00-0xFF)
  public byte getLeftTrigger() {
    return activeController.getLeftTrigger();
  }
  public byte getRightTrigger() {
    return activeController.getRightTrigger();
  }
  public void setLeftTrigger(int val) {
    activeController.setLeftTrigger(val);
  }
  public void setRightTrigger(int val) {
    activeController.setRightTrigger(val);
  }

  // 12-13, select, start (0x00 off, 0xFF on)
  public byte getSelect() {
    return activeController.getSelect();
  }
  public byte getStart() {
    return activeController.getStart();
  }
  public void setSelect(boolean on) {
    activeController.setSelect(on);
  }
  public void setStart(boolean on) {
    activeController.setStart(on);
  }

  // 14-15, left stick button, right stick button (0x00 off, 0xFF on)
  public byte getLeftStickButton() {
    return activeController.getLeftStickButton();
  }
  public byte getRightStickButton() {
    return activeController.getRightStickButton();
  }
  public void setLeftStickButton(boolean on) {
    activeController.setLeftStickButton(on);
  }
  public void setRightStickButton(boolean on) {
    activeController.setRightStickButton(on);
  }

  // 16-19, D-pad up, down, left, right (0x00 off, 0xFF on)
  public byte getDUp() {
    return activeController.getDUp();
  }
  public byte getDDown() {
    return activeController.getDDown();
  }
  public byte getDLeft() {
    return activeController.getDLeft();
  }
  public byte getDRight() {
    return activeController.getDRight();
  }
  public void setDUp(boolean on) {
    activeController.setDUp(on);
  }
  public void setDDown(boolean on) {
    activeController.setDDown(on);
  }
  public void setDLeft(boolean on) {
    activeController.setDLeft(on);
  }
  public void setDRight(boolean on) {
    activeController.setDRight(on);
  }
  public void setDPad(int p, boolean on) {
    activeController.setDPad(p, on);
  }

  // 20-23, Aux 1-4 (0x00-0xFF)
  public byte getAux1() {
    return activeController.getAux1();
  }
  public byte getAux2() {
    return activeController.getAux2();
  }
  public byte getAux3() {
    return activeController.getAux3();
  }
  public byte getAux4() {
    return activeController.getAux4();
  }
  public void setAux1(int val) {
    activeController.setAux1(val);
  }
  public void setAux2(int val) {
    activeController.setAux2(val);
  }
  public void setAux3(int val) {
    activeController.setAux3(val);
  }
  public void setAux4(int val) {
    activeController.setAux4(val);
  }
  public void setAuxPad(int p, int val) {
    activeController.setAuxPad(p, val);
  }
}

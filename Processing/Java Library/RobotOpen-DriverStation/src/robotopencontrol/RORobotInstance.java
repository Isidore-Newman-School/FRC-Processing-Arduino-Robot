package robotopencontrol;

import robotopencontrol.instance.RODashboardData;
import robotopencontrol.instance.ROJoystickHandler;
import robotopencontrol.instance.ROCustomHandler;
import robotopencontrol.instance.ROPacketTransmitter;

/**
* @author ebarch
* modified by jdeboi
*/
public class RORobotInstance {
	private ROJoystickHandler joystickHandler;
	private ROCustomHandler customHandler;
	private RODashboardData dashboardData;
	private ROPacketTransmitter packetTransmitter;
	private boolean usingCustomController;

	
	public RORobotInstance(boolean custom) {
		usingCustomController = custom;
		if (custom) {
			
			customHandler = new ROCustomHandler();
			packetTransmitter = new ROPacketTransmitter(customHandler);
		}

		else {
			joystickHandler = new ROJoystickHandler();
			packetTransmitter = new ROPacketTransmitter(joystickHandler);
		}
		dashboardData = new RODashboardData();
		packetTransmitter.setDashboardData(dashboardData);

	}

	public void reInitJoystickHandler() {
		joystickHandler = new ROJoystickHandler();
	}

	public ROJoystickHandler getJoystickHandler() {
		return joystickHandler;
	}

	public ROCustomHandler getCustomHandler() {
		return customHandler;
	}

	public void setCustomHandler(ROCustomHandler k) {
		customHandler = k;
	}

	public RODashboardData getDashboardData() {
		return dashboardData;
	}

	public ROPacketTransmitter getPacketTransmitter() {
		return packetTransmitter;
	}

	// 0 - 3, analog leftX, lefty, rightX, rightY (0x00-0xFF)
	public byte getAnalogLeftX() {
		return customHandler.getAnalogLeftX();
	}
	public byte getAnalogLeftY() {
		return customHandler.getAnalogLeftY();
	}
	public byte getAnalogRightX() {
		return customHandler.getAnalogRightX();
	}
	public byte getAnalogRightY() {
		return customHandler.getAnalogRightY();
	}
	public void setAnalogLeftX(int val) {
		customHandler.setAnalogLeftX(val);
	}
	public void setAnalogLeftY(int val) {
		customHandler.setAnalogLeftY(val);
	}
	public void setAnalogRightX(int val) {
		customHandler.setAnalogRightX(val);
	}
	public void setAnalogRightY(int val) {
		customHandler.setAnalogRightY(val);
	}
	public void setAnalogPad(int p, int val) {
		customHandler.setAnalogPad(p, val);
	}

	// 4-7, buttons A,B,X,Y (0x00 off, 0xFF on)
	public byte getAButton() {
		return customHandler.getAButton();
	}
	public byte getBButton() {
		return customHandler.getBButton();
	}
	public byte getXButton() {
		return customHandler. getXButton();
	}
	public byte getYButton() {
		return customHandler.getYButton();
	}
	public void setAButton(boolean on) {
		customHandler.setAButton(on);
	}
	public void setBButton(boolean on) {
		customHandler.setBButton(on);
	}
	public void setXButton(boolean on) {
		customHandler.setXButton(on);
	}
	public void setYButton(boolean on) {
		customHandler.setYButton(on);
	}
	public void setButtons(int b, boolean on) {
		customHandler.setButtons(b, on);
	}

	// 8-9, buttons leftshoulder, rightshoulder (0x00 off, 0xFF on)
	public byte getXShoulder() {
		return customHandler.getXShoulder();
	}
	public byte getYShoulder() {
		return customHandler.getYShoulder();
	}
	public void setXShoulder(boolean on) {
		customHandler.setXShoulder(on);
	}
	public void setYShoulder(boolean on) {
		customHandler.setYShoulder(on);
	}

	// 10-11, Left Trigger, right trigger (0x00-0xFF)
	public byte getLeftTrigger() {
		return customHandler.getLeftTrigger();
	}
	public byte getRightTrigger() {
		return customHandler.getRightTrigger();
	}
	public void setLeftTrigger(int val) {
		customHandler.setLeftTrigger(val);
	}
	public void setRightTrigger(int val) {
		customHandler.setRightTrigger(val);
	}

	// 12-13, select, start (0x00 off, 0xFF on)
	public byte getSelect() {
		return customHandler.getSelect();
	}
	public byte getStart() {
		return customHandler.getStart();
	}
	public void setSelect(boolean on) {
		customHandler.setSelect(on);
	}
	public void setStart(boolean on) {
		customHandler.setStart(on);
	}

	// 14-15, left stick button, right stick button (0x00 off, 0xFF on)
	public byte getLeftStickButton() {
		return customHandler.getLeftStickButton();
	}
	public byte getRightStickButton() {
		return customHandler.getRightStickButton();
	}
	public void setLeftStickButton(boolean on) {
		customHandler.setLeftStickButton(on);
	}
	public void setRightStickButton(boolean on) {
		customHandler.setRightStickButton(on);
	}

	// 16-19, D-pad up, down, left, right (0x00 off, 0xFF on)
	public byte getDUp() {
		return customHandler.getDUp();
	}
	public byte getDDown() {
		return customHandler.getDDown();
	}
	public byte getDLeft() {
		return customHandler.getDLeft();
	}
	public byte getDRight() {
		return customHandler.getDRight();
	}
	public void setDUp(boolean on) {
		customHandler.setDUp(on);
	}
	public void setDDown(boolean on) {
		customHandler.setDDown(on);
	}
	public void setDLeft(boolean on) {
		customHandler.setDLeft(on);
	}
	public void setDRight(boolean on) {
		customHandler.setDRight(on);
	}
	public void setDPad(int p, boolean on) {
		customHandler.setDPad(p, on);
	}

	// 20-23, Aux 1-4 (0x00-0xFF)
	public byte getAux1() {
		return customHandler.getAux1();
	}
	public byte getAux2() {
		return customHandler.getAux2();
	}
	public byte getAux3() {
		return customHandler.getAux3();
	}
	public byte getAux4() {
		return customHandler.getAux4();
	}
	public void setAux1(int val) {
		customHandler.setAux1(val);
	}
	public void setAux2(int val) {
		customHandler.setAux2(val);
	}
	public void setAux3(int val) {
		customHandler.setAux3(val);
	}
	public void setAux4(int val) {
		customHandler.setAux4(val);
	}
	public void setAuxPad(int p, int val) {
		customHandler.setAuxPad(p, val);
	}
}

/*
 * Copyright (c) 2002-2008 LWJGL Project
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 * * Redistributions of source code must retain the above copyright
 *   notice, this list of conditions and the following disclaimer.
 *
 * * Redistributions in binary form must reproduce the above copyright
 *   notice, this list of conditions and the following disclaimer in the
 *   documentation and/or other materials provided with the distribution.
 *
 * * Neither the name of 'LWJGL' nor the names of
 *   its contributors may be used to endorse or promote products derived
 *   from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED
 * TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package robotopencontrol.instance;

/**
 * Making a custom controller based on joystick inputs (?)
 *
 * modified by Jenna deBoisblanc
 */
class CustomController {


	private byte [] inputs;

	CustomController() {
		inputs = new byte[24];
	}
	// 0 - 3, analog leftX, lefty, rightX, rightY (0x00-0xFF)
	public byte getAnalogLeftX() {
			return inputs[0];
	}
	public byte getAnalogLeftY() {
			return inputs[1];
	}
	public byte getAnalogRightX() {
			return inputs[2];
	}
	public byte getAnalogRightY() {
			return inputs[3];
	}
	public void setAnalogLeftX(int val) {
			inputs[0] = (byte) val;
	}
	public void setAnalogLeftY(int val) {
			inputs[1] = (byte) val;
	}
	public void setAnalogRightX(int val) {
			inputs[2] = (byte) val;
	}
	public void setAnalogRightY(int val) {
			inputs[3] = (byte) val;
	}
	public void setAnalogPad(int p, int val) {
			if (p >= 0 && p <= 3)
				inputs[p] = (byte) val;
	}

	// 4-7, buttons A,B,X,Y (0x00 off, 0xFF on)
	public byte getAButton() {
			return inputs[4];
	}
	public byte getBButton() {
		return inputs[5];
	}
	public byte getXButton() {
			return inputs[6];
	}
	public byte getYButton() {
		return inputs[7];
	}
	public void setAButton(boolean on) {
			inputs[4] = (byte)(on?1:0);
	}
	public void setBButton(boolean on) {
			inputs[5] = (byte)(on?1:0);
	}
	public void setXButton(boolean on) {
			inputs[6] = (byte)(on?1:0);
	}
	public void setYButton(boolean on) {
			inputs[7] = (byte)(on?1:0);
	}
	public void setButtons(int b, boolean on) {
		b += 4;
		if (b >= 4 && b <=7) {
			inputs[b] = (byte)(on?1:0);
		}
	}

	// 8-9, buttons leftshoulder, rightshoulder (0x00 off, 0xFF on)
	public byte getXShoulder() {
			return inputs[8];
	}
	public byte getYShoulder() {
		return inputs[9];
	}
	public void setXShoulder(boolean on) {
			inputs[8] = (byte)(on?1:0);
	}
	public void setYShoulder(boolean on) {
		inputs[9] = (byte)(on?1:0);
	}

	// 10-11, Left Trigger, right trigger (0x00-0xFF)
	public byte getLeftTrigger() {
			return inputs[10];
	}
	public byte getRightTrigger() {
		return inputs[11];
	}
	public void setLeftTrigger(int val) {
			inputs[10] = (byte)val;
	}
	public void setRightTrigger(int val) {
		inputs[11] = (byte)val;
	}

	// 12-13, select, start (0x00 off, 0xFF on)
	public byte getSelect() {
			return inputs[12];
	}
	public byte getStart() {
		return inputs[13];
	}
	public void setSelect(boolean on) {
			inputs[12] = (byte)(on?1:0);
	}
	public void setStart(boolean on) {
		inputs[13] = (byte)(on?1:0);
	}

	// 14-15, left stick button, right stick button (0x00 off, 0xFF on)
	public byte getLeftStickButton() {
			return inputs[14];
	}
	public byte getRightStickButton() {
		return inputs[15];
	}
	public void setLeftStickButton(boolean on) {
			inputs[14] = (byte)(on?1:0);
	}
	public void setRightStickButton(boolean on) {
		inputs[15] = (byte)(on?1:0);
	}

	// 16-19, D-pad up, down, left, right (0x00 off, 0xFF on)
	public byte getDUp() {
			return inputs[16];
	}
	public byte getDDown() {
		return inputs[17];
	}
	public byte getDLeft() {
			return inputs[18];
	}
	public byte getDRight() {
		return inputs[19];
	}
	public void setDUp(boolean on) {
			inputs[16] = (byte)(on?1:0);
	}
	public void setDDown(boolean on) {
			inputs[17] = (byte)(on?1:0);
	}
	public void setDLeft(boolean on) {
			inputs[18] = (byte)(on?1:0);
	}
	public void setDRight(boolean on) {
			inputs[19] = (byte)(on?1:0);
	}
	public void setDPad(int p, boolean on) {
		p+=16;
		if (p >= 16 && p <= 19) {
			inputs[p] = (byte)(on?1:0);
		}
	}

	// 20-23, Aux 1-4 (0x00-0xFF)
	public byte getAux1() {
			return inputs[20];
	}
	public byte getAux2() {
			return inputs[21];
	}
	public byte getAux3() {
			return inputs[22];
	}
	public byte getAux4() {
			return inputs[23];
	}
	public void setAux1(int val) {
			inputs[20] = (byte) val;
	}
	public void setAux2(int val) {
			inputs[21] = (byte) val;
	}
	public void setAux3(int val) {
			inputs[22] = (byte) val;
	}
	public void setAux4(int val) {
			inputs[23] = (byte) val;
	}
	public void setAuxPad(int p, int val) {
			if (p >= 0 && p <= 3)
				inputs[p] = (byte) val;
	}

	public byte[] getExportValues() {
		return inputs;
	}
}

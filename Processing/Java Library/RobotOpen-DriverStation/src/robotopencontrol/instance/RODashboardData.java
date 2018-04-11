package robotopencontrol.instance;

import java.util.ArrayList;
import java.util.Arrays;
import java.nio.*;
import java.util.Observable;

/**
 * original author Eric Barch
 * modified by Jenna deBoisblanc
 */
public class RODashboardData extends Observable {
	// Timestamp of the last received valid packet
	private int lastPacketTime;
	// Last uptime reported
	private int lastUptime;
	// State of the robot
	private int robotState;
	// Last firmware version reported
	private int lastFirmwareVer;
	// Valid RX count
	private int validRxPackets;
	// Invalid RX count
	private int invalidRxPackets;
	// Stores all of the data received from the RC receiver
	ArrayList<Bundle> bundles;
	
	/* Our constructor for the RODashboardData object */
	public RODashboardData() {
		lastPacketTime = -1;
		lastUptime = -1;
		robotState = -1;
		lastFirmwareVer = -1;
		validRxPackets = 0;
		invalidRxPackets = 0;
		bundles = new ArrayList<Bundle>();
	}

	public void updatePacketTime(int packetTime) {
		lastPacketTime = packetTime;
	}

	public void updateUptime(int uptime) {
		lastUptime = uptime;
	}

	public void updateFirmwareVer(int firmwareVer) {
		lastFirmwareVer = firmwareVer;
	}

	public void updateRobotState(int currentState) {
		robotState = currentState;
	}

	public void incrementValidRxCount() {
		validRxPackets++;
		setChanged();
		notifyObservers();
	}

	public void incrementInvalidRxCount() {
		invalidRxPackets++;
		setChanged();
		notifyObservers();
	}

	public void updateBundles(byte[] data) {
		bundles.clear();
		int byteOffset = 1; // skip the identifier for the packet type
		while (byteOffset < data.length) {

			float value = 0;
			int packetLengthOffset;
			char typeChar;
			packetLengthOffset = data[byteOffset] + byteOffset++;
			typeChar = (char) data[byteOffset++];

			if (typeChar == 'b') {
				if (data[byteOffset] > 0) {
					value = 100;
				} else {
					value = 0;
				}
				byteOffset += 1;
			} else if (typeChar == 'c') {
				value = data[byteOffset];
				byteOffset += 1;
			} else if (typeChar == 'i') { // integer
				value = data[byteOffset];
				byte[] arr = { data[byteOffset], data[byteOffset + 1] };
				ByteBuffer wrapped = ByteBuffer.wrap(arr); // big-endian by
															// default
				short num = wrapped.getShort(); // 1
				value = num;
				byteOffset += 2;
			} else if (typeChar == 'l') {
				ByteBuffer bb = ByteBuffer.wrap(new byte[] { data[byteOffset], data[byteOffset + 1],
						data[byteOffset + 2], data[byteOffset + 3] });
				long l = bb.getInt();
				value = l;
				byteOffset += 4;
			} else if (typeChar == 'f') {
				ByteBuffer bb = ByteBuffer.wrap(new byte[] { data[byteOffset], data[byteOffset + 1],
						data[byteOffset + 2], data[byteOffset + 3] });
				float f = bb.getFloat();
				value = f;
				byteOffset += 4;
			} else {
				// not good, give up
				// System.out.println("Bad DS Packet!");
				return;
			}

			int remainingLength = 0;
			byte[] stringbytes;
			String bundleName = "";
			remainingLength = packetLengthOffset - byteOffset;
			stringbytes = Arrays.copyOfRange(data, byteOffset, byteOffset + remainingLength);
			bundleName = new String(stringbytes);
			bundles.add(new Bundle(typeChar, bundleName, value));
			byteOffset += remainingLength;
		}

	}

	public int getPacketTime() {
		return lastPacketTime;
	}

	public int getUptime() {
		return lastUptime;
	}

	public int getFirmwareVer() {
		return lastFirmwareVer;
	}

	public int getRobotState() {
		return robotState;
	}

	public int getValidRxCount() {
		return validRxPackets;
	}

	public int getInvalidRxCount() {
		return invalidRxPackets;
	}

	public ArrayList<Bundle> getBundles() {
		return bundles;
	}
}
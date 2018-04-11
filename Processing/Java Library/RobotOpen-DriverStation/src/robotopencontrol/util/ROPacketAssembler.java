package robotopencontrol.util;

import robotopencontrol.instance.ROJoystickHandler;
import robotopencontrol.instance.ROCustomHandler;

/* ---PACKET FORMAT---
 * Message Type (1 byte)
 * Protocol Version (1 byte)
 * Device ID (1 byte)
 * ...Bundle Payloads... (1 length byte + max of 255 payload bytes for each bundle)
 * CRC16 Checksum (2 bytes)
 */

/**
 * @author Eric Barch
 */
public class ROPacketAssembler {

    public static byte[] getXmitBytes(ROJoystickHandler joystickHandler, boolean heartbeat) {
    	byte messageType;
    	if (heartbeat)
    		messageType = ROMessageTypes.HEARTBEAT_PACKET;
    	else
    		messageType = ROMessageTypes.CONTROL_PACKET;

        byte[] header = { messageType };
        if (!heartbeat) {
        	byte[] joystickPayload = combineByteArrays(header, joystickHandler.exportValues());
        	byte[] checksum = CRC16.genCRC16(joystickPayload);
        	return combineByteArrays(joystickPayload, checksum);
        }
        else {
        	byte[] body = {(byte)0xEE, (byte)0x01};
        	return combineByteArrays(header, body);
        }
    }

    public static byte[] getXmitBytes(ROCustomHandler customHandler, boolean heartbeat) {
    	byte messageType;
    	if (heartbeat)
    		messageType = ROMessageTypes.HEARTBEAT_PACKET;
    	else
    		messageType = ROMessageTypes.CONTROL_PACKET;

        byte[] header = { messageType };
        if (!heartbeat) {
        	byte[] customPayload = combineByteArrays(header, customHandler.exportValues());
        	byte[] checksum = CRC16.genCRC16(customPayload);
        	return combineByteArrays(customPayload, checksum);
        }
        else {
        	byte[] body = {(byte)0xEE, (byte)0x01};
        	return combineByteArrays(header, body);
        }
    }

    private static byte[] combineByteArrays(byte[] array1, byte[] array2) {
        byte[] result = new byte[array1.length + array2.length];
        System.arraycopy(array1, 0, result, 0, array1.length);
        System.arraycopy(array2, 0, result, array1.length, array2.length);
        return result;
    }
}

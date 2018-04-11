package robotopencontrol.util;

import java.util.Arrays;
import robotopencontrol.instance.RODashboardData;


/**
 * @author Eric Barch
 */
public class ROPacketParser {

	public static void parsePacket(byte[] packet, int length, RODashboardData dashboardData) {
		if (packet.length > 0) {
			dashboardData.incrementValidRxCount();
			dashboardData.updateUptime((int) (System.currentTimeMillis() / 1000L));
			dashboardData.updateBundles(packet);
			dashboardData.updatePacketTime((int) (System.currentTimeMillis() / 1000L));
		} else {
			dashboardData.incrementInvalidRxCount();
		}
	}

}
// public class ROPacketParser {
//
// public static void parsePacket(byte[] packet, int length, RODashboardData
// dashboardData) {
// // Split up the packet
// byte[] packetPayload = Arrays.copyOfRange(packet, 0, length-2);
// byte[] feedbackData = Arrays.copyOfRange(packet, 6, length-2);
// byte[] packetChecksum = Arrays.copyOfRange(packet, length-2, length);
//
// // Calculate the CRC16
// byte[] checksum = CRC16.genCRC16(packetPayload);
//
// System.out.println(checksum[0] + " check and packet " + packetChecksum[0]);
// if (checksum[0] == packetChecksum[0]) {
// System.out.println("well checksum is the packsum at 0?");
// }
// if (checksum[0] == packetChecksum[0] && checksum[1] == packetChecksum[1]) {
// //&& packetPayload[1] == ROParameters.PROTOCOL_VER) {
// dashboardData.incrementValidRxCount();
// if (packetPayload[0] == ROMessageTypes.HEARTBEAT_PACKET) {
// dashboardData.updateFirmwareVer(packetPayload[3]);
// dashboardData.updateRobotState(packetPayload[4]);
// dashboardData.updateUptime(packetPayload[5]);
// dashboardData.updateBundles(feedbackData);
// dashboardData.updatePacketTime((int)(System.currentTimeMillis() / 1000L));
// }
// }
// else {
// if (packetPayload[1] != ROParameters.PROTOCOL_VER) {
// System.out.println("Incompatible protocol version packet received!");
// }
// else {
// System.out.println("Invalid CRC!");
// }
// dashboardData.incrementInvalidRxCount();
// }
// }
// }
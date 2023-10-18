package PMP_Lb1_RudenkoKyryl;

import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

public class Client {
	private static InetAddress address;
	private static byte[] buffer;
	private static DatagramPacket packet;
	private static String str;
	private static MulticastSocket socket;
	
	@SuppressWarnings("deprecation")
	public static void main (String arg[])throws Exception{
		System.out.println("waiting massage from server");
		try {
			socket = new MulticastSocket(1502);
			address=InetAddress.getByName("224.0.0.2");
			socket.joinGroup(address);
			while(true) {
				buffer = new byte[256];
				packet = new DatagramPacket(buffer, buffer.length);
				socket.receive(packet);
				str = new String(packet.getData());
				System.out.println("ServerMessage: "+ str.trim());
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				socket.leaveGroup (address);
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}


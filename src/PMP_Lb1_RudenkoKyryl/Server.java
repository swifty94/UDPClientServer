package PMP_Lb1_RudenkoKyryl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class Server {
	
	private BufferedReader keyboardInput= null;
	private String str = null;
	private byte[] buffer;
	private DatagramPacket packet;
	private InetAddress address;
	private DatagramSocket socket;
	
	public Server() throws IOException {
		System.out.println("Sending messages");
		socket = new DatagramSocket();
		transmit();
	}
	
	public void transmit() {
		try {
			keyboardInput = new BufferedReader(new InputStreamReader(System.in));
			while (true) {
				System.out.println ("Enter your message");
				str = keyboardInput.readLine();
				buffer = str.getBytes();
				address = InetAddress.getByName("224.0.0.2");
				packet = new DatagramPacket (buffer, buffer.length, address, 1502);
				socket.send(packet);
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				keyboardInput.close();
				socket.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		new Server();
	}

}

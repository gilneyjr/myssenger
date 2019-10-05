package ufrn.br.myssenger_client.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import ufrn.br.myssenger_client.model.User;


public class ClientController {
	private DatagramSocket socket;
	private static final String SERVERNAME =  "ec2-52-14-230-186.us-east-2.compute.amazonaws.com";
	private static final int SERVERPORT = 9000;
	private static final int CLIENTPORT = 9001;
	private static final int MAX_MSG_SIZE = 1024;
	
	public void send(String msg) {
		new Thread(() -> {
			try {
				InetAddress address = InetAddress.getByName(SERVERNAME);
				byte[] buf = msg.getBytes();
				DatagramPacket packet = new DatagramPacket(buf, buf.length, address, SERVERPORT);
				socket.send(packet);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}).start();
	}
	
	public void close() {
		socket.close();
	}

	public ClientController() {
		try {
			socket = new DatagramSocket(CLIENTPORT);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
	
	public void signIn(String username, String password) {
		String msg = "sign_in\n\r" + username + ";" + password + "\n\r\n\r";
		System.out.println("[" + msg + "]");
	}
	
	public void signUp(String username, String password) {
		String msg = "sign_up\n\r" + username + "\n\r" + password + "\n\r\n\r";
		
		try {
			InetAddress inetAddress = InetAddress.getByName(SERVERNAME);
			System.out.println("Server ip: " + inetAddress.getHostAddress());
			byte[] bytesMsg = msg.getBytes();
			DatagramPacket packet = new DatagramPacket(bytesMsg, bytesMsg.length, inetAddress, SERVERPORT);
			socket.send(packet);
			
			bytesMsg = new byte[MAX_MSG_SIZE];
			packet = new DatagramPacket(bytesMsg, bytesMsg.length);
			socket.receive(packet);
			System.out.println("received from server: [" + new String(packet.getData()) + "]");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {}
	}
	
	
	
	// New part
	private byte[] convertToBytes(Object object) {
	    try (ByteArrayOutputStream bos = new ByteArrayOutputStream();
	         ObjectOutput out = new ObjectOutputStream(bos)) {
	        out.writeObject(object);
	        return bos.toByteArray();
	    } catch(IOException e) { return null; }
	}
	
	private Object convertFromBytes(byte[] bytes) {
	    try (ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
	         ObjectInput in = new ObjectInputStream(bis)) {
	        return in.readObject();
	    } catch(IOException | ClassNotFoundException e) { return null; }
	}
	
	public void signUp(User u) {
		System.out.println("username: " + u.getUsername() +
				 "; password: " + u.getPassword());
		byte[] b = convertToBytes(u);
		u = (User) convertFromBytes(b);
		System.out.println("username: " + u.getUsername() +
				"; password: " + u.getPassword());
	}
}
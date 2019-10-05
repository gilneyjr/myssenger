package ufrn.br.myssenger_client.controller;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeoutException;

import org.json.JSONObject;


public class ClientController {
	private DatagramSocket socket;
	private static final String SERVERNAME =  "ec2-3-15-213-254.us-east-2.compute.amazonaws.com";
	private static final int SERVERPORT = 9000;
	private static final int CLIENTPORT = 9001;
	//private static final int MAX_MSG_SIZE = 1024;
	
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
	
//	public void signUp(String username, String password) {
//		String msg = "sign_up\n\r" + username + "\n\r" + password + "\n\r\n\r";
//		
//		try {
//			InetAddress inetAddress = InetAddress.getByName(SERVERNAME);
//			System.out.println("Server ip: " + inetAddress.getHostAddress());
//			byte[] bytesMsg = msg.getBytes();
//			DatagramPacket packet = new DatagramPacket(bytesMsg, bytesMsg.length, inetAddress, SERVERPORT);
//			socket.send(packet);
//			
//			bytesMsg = new byte[MAX_MSG_SIZE];
//			packet = new DatagramPacket(bytesMsg, bytesMsg.length);
//			socket.receive(packet);
//			System.out.println("received from server: [" + new String(packet.getData()) + "]");
//		} catch (UnknownHostException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		
//		try {
//			Thread.sleep(2000);
//		} catch (InterruptedException e) {}
//	}
	
	
	
	// New part =======================================================================

	public void signUp(String username, String password) throws Exception {
		try(RPCClient rpc = new RPCClient()) {
			JSONObject obj = new JSONObject();
			obj.put("type", "sign_up");
			obj.put("username", username);
			obj.put("password", password);			
			
			byte[] response = rpc.call(obj.toString().getBytes("UTF-8")); 			
			
			// Ver exception
			JSONObject resp_obj = new JSONObject(new String(response, "UTF-8"));
			
			// Error Detection
			if(resp_obj.getString("type").equals("response"))
				if(resp_obj.getString("status").equals("error"))
					throw new Exception(resp_obj.getString("msg"));
				else if(resp_obj.getString("status").equals("ok"))
					/* Empty */;
				else
					throw new Exception("Unexpected error! Please try again");
			else
				throw new Exception("Unexpected error! Please try again");
		} catch (IOException e) {
			e.printStackTrace();
		} catch (TimeoutException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
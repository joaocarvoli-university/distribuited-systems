package server_test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;

import javax.sound.midi.Receiver;

public class ServerTest{
	
	DatagramSocket sSocket;
	DatagramPacket dataPacket;
	
	int serverPort = 7889;
	boolean done;
	
	public ServerTest() {
		done = false;
	}
	
	public void run( ) {
		try {
			this.sSocket = new DatagramSocket( serverPort );
			byte [] buffer = new byte[1024];
			
			while( !done ) {
				this.dataPacket = new DatagramPacket( buffer, buffer.length );
				sSocket.receive( dataPacket );
				
				String message = new String( dataPacket.getData() );
				System.out.println("Mensagem do cliente: " + message );
				
				
				String str = "recebido";
				byte [] respMessage = str.getBytes();
				DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
				sSocket.send(response);
			}
						
		} catch ( SocketException e ) {
			System.out.println( "SocketException error: " + e.getMessage() );
		} catch ( IOException e) {
			System.out.println( "IOException error: " + e.getMessage() );
		} finally {
			if( sSocket != null ) {
				this.done = true;
				sSocket.close();
			}
		}
	}
	
	
	public static void main( String[] args ) {
		ServerTest serv = new ServerTest();
		
		serv.run();
	}
}


//private ServerSocket sSocket;
//private boolean done;
//
//public ServerTest( ){
//	done = false;
//	
//}
//
//
//public void run( ) {
//	try {
//		sSocket = new ServerSocket( 7998 );			
//		System.out.println("Servidor UPD iniciado, escutando na porta: " + sSocket.getLocalPort() + "...");
//		
//		while (!done) {
//			Socket sClient = sSocket.accept();
//			UDPSocket uSckt = new UDPSocket( sClient );
//			uSckt.run();
//			
//		}
//		
//	} catch ( Exception e) {
//		System.out.println("ServerTest.run error: " + e.getMessage());
//	}
//}
//
//
//class UDPSocket implements Runnable{
//	private Socket client;
//	DatagramSocket sDatagram;
//	
//	public UDPSocket ( Socket client ) {
//		try {
//			this.client = client;
//			this.sDatagram = new DatagramSocket();
//		}catch( Exception e) {
//			//TODO: error and exception on constructor;
//		}
//	}
//	
//	@Override
//	public void run( ) {
//		try {
//			System.out.println("UDPSocket instanciado\n");
//			//DatagramSocket sDatagram = new DatagramSocket();
//			//sDatagram = new DatagramSocket();
//			byte [ ] buffer = new byte[1024];
//			DatagramPacket sDataPacket = new DatagramPacket(buffer, 1024);
//			
//			this.sDatagram.receive( sDataPacket );
//			
//			String received = new String( sDataPacket.getData(), 0 , sDataPacket.getLength() );
//			
//			this.sDatagram.close();
//			
//			System.out.println("Mensagem recebida: " + received  );
//		}catch (Exception e ) {
//			System.out.println("UDPSocket.run error: " + e.getMessage() );
//		}
//	}
//}
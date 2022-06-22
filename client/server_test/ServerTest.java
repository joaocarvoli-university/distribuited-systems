package server_test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;

public class ServerTest{
	
	ServerSocket sSocket;
	int serverPort = 7889;
	boolean done;
	
	public ServerTest() {
		done = false;
	}
	
	public void run( ) {
		try {
			this.sSocket = new ServerSocket ( serverPort );
			DatagramSocket dataSocket = new DatagramSocket( sSocket.getLocalPort() );
			
			
			while( !this.done ) {
				byte [] buffer = new byte[1024];
				DatagramPacket dataPacket = new DatagramPacket( buffer, buffer.length );

				dataSocket.receive( dataPacket );
				
				String message = new String ( dataPacket.getData() );
				System.out.println("Mensagem do cliente: " + message );
				message = null;
				
				String str = "recebido";
				byte [] respMessage = str.getBytes();
				
				DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
				dataSocket.send( response );
				
				
			}
		} catch ( SocketException e ) {
			System.out.println( "SocketException error: " + e.getMessage() );
		} catch ( IOException e) {
			System.out.println( "IOException error: " + e.getMessage() );
//		} finally {
//			if( sSocket.isClosed() ) {
//				this.done = true;
//				sSocket.close();
//			}
		}
	}
	
	
	public static void main( String[] args ) {
		ServerTest serv = new ServerTest();
		
		serv.run();
	}
}

package client_Udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;


//ClientUDP recebe de Despachante e envia para UDPServer
public class ClientUDP{
			
	DatagramSocket aSocket;
	DatagramPacket dataPacket;
	InetAddress servip;
	int serport;
	String message;
	
	public ClientUDP( String serverHost, int portNumber ){
			try {
				this.aSocket = new DatagramSocket();
				this.servip = InetAddress.getByName( serverHost );
				this.serport = portNumber;

			} catch ( Exception e){
				System.out.println("ClientUPD Exception: " + e.getMessage());
			}
			
		}
	
	
	
	//sendRequest( ) envia uma mensagem em bytes
	public void sendRequest( String strMessage  ) {
		try {
			this.aSocket = new DatagramSocket();
			this.message = strMessage;
			
			byte [] mByte = message.getBytes();
			this.dataPacket = new DatagramPacket( mByte, message.length(), this.servip, this.serport);
			
			this.aSocket.send(dataPacket);
			
		} catch ( SocketException e ) {
			System.out.println("ClientUPD SocketException: " + e.getMessage());
		} catch ( IOException e ) {
			System.out.println("ClientUPD IOException: " + e.getMessage());
		}
		
		
	}
	
	//getResponse( ) recebe uma mensagem em bytes[1024]	
	public String getResponse( ) {
		String inResponse = null;
		try {

			byte [] buffer = new byte[1024];
			this.dataPacket = new DatagramPacket( buffer, buffer.length );
			this.aSocket.receive(this.dataPacket);
			inResponse = new String( dataPacket.getData() );
				
		} catch ( SocketException e ) {
			System.out.println("ClientUPD SocketException: " + e.getMessage());
		} catch ( IOException e ) {
			System.out.println("ClientUPD IOException: " + e.getMessage());
		}		
		return inResponse;
	}
		
	
//	public static void main( String[] args ) {
//		ClientUDP cli = new ClientUDP("localhost", 7889);
//		
//		cli.sendRequest("Mensagem ao servidor");
//		System.out.println( cli.getResponse() );
//	}

}

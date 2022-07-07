package server_test;

import java.io.IOException;
import java.lang.runtime.ObjectMethods;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;


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
			
			String serviceC = "CurrencyConverter";
			String serviceW = "WheaterAPI";
			
			List <String> arrRates = new ArrayList<>();
			arrRates.add("AED");	arrRates.add("AFN");	arrRates.add("ANG");	arrRates.add("AZN");	arrRates.add("BAM");	arrRates.add("BBD");
			arrRates.add("BND");	arrRates.add("BRL");	arrRates.add("BSD");	arrRates.add("BTC");	arrRates.add("CAD");	arrRates.add("CLP");
			arrRates.add("COP");	arrRates.add("EUR");	arrRates.add("GEL");	arrRates.add("GHS");	arrRates.add("GNF");	arrRates.add("GTQ");
			arrRates.add("IMP");	arrRates.add("JPY");	arrRates.add("LRD");			
			
			while( !this.done ) {
				byte [] buffer = new byte[1024];
				DatagramPacket dataPacket = new DatagramPacket( buffer, buffer.length );

				dataSocket.receive( dataPacket );
				
				String message = new String ( dataPacket.getData() );
				System.out.println("Mensagem do cliente: " + message );
				
				JSONObject sampleObj = new JSONObject( message );				
				String str = "recebido";
				
				// Checking which service was called
				if( sampleObj.get( "serviceName" ).equals( serviceC ) ) {
//					System.out.println( "entrei no service CurrencyConverter");
					
					// Checking which method was called
					if( sampleObj.get( "methodName" ).equals( "convert" ) ) {
						JSONArray jsonArgmnts = new JSONArray( );
						jsonArgmnts = sampleObj.getJSONArray("arguments");
						
						String [] fromCurr = jsonArgmnts.get(0).toString().split(":");
						String [] toCurr   = jsonArgmnts.get(1).toString().split(":");
						String [] amon	   = jsonArgmnts.get(2).toString().split(":");
						
						float amount = Float.parseFloat( amon[1]);
						
						float value = amount * (float) 5.025;
						
						sampleObj.put("messageType", 1);
						List <String> args = new ArrayList<>();
						args.add( "fromCurrency:" + fromCurr[1] );
						args.add( "toCurrency:" + toCurr[1] );
						args.add( "amount:" + Float.toString(value) );
						sampleObj.put("arguments", args);
						
						str = sampleObj.toString();	
						byte [] respMessage = str.getBytes();
					
						DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
						dataSocket.send( response );
						response = null;
//						this.done = true;
//						dataSocket.close();

					}
					if( sampleObj.get( "methodName" ).equals( "currency_exists" ) ) {
						JSONArray jsonArgmnts = new JSONArray( );
						jsonArgmnts = sampleObj.getJSONArray("arguments");
						
						String [] checkCurrency = jsonArgmnts.get(0).toString().split(":");
						
						boolean confirmation = false;
						for ( String ocurr : arrRates ) {
							if ( ocurr.equals( checkCurrency[1].toUpperCase() )) {
								confirmation = true;
							}
						}
						sampleObj.put("messageType", 1);
						List< String >args = new ArrayList<>();
						args.add( "response:" + Boolean.toString( confirmation ) ); 
						sampleObj.put("arguments", args);
						
						str = sampleObj.toString();
						byte [] respMessage = str.getBytes();
						
						DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
						dataSocket.send( response );
						response = null;
					}
					if( sampleObj.get( "methodName" ).equals( "currencies_available" ) ) {
//						System.out.println( '3' );
						List< String >args = new ArrayList<>();
						for( String ocurr : arrRates ) {
							args.add( "currency:" + ocurr );
						}
						sampleObj.put("messageType", 1);
						sampleObj.put("arguments", args);
						
						str = sampleObj.toString();
						byte [] respMessage = str.getBytes();
						
						DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
						dataSocket.send( response );
						response = null;
					}					
				}
				
				else if( sampleObj.get( "serviceName" ).equals( serviceW ) ) {
					System.out.println( "entrei no service WheaterAPI");
					
					if( sampleObj.get( "methodName" ).equals( "get_Wheter_Temperature" ) ) {
//						System.out.println( '1' );
						
						
						
						byte [] respMessage = str.getBytes();
						
						DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
						dataSocket.send( response );
						response = null;
					}
					if( sampleObj.get( "methodName" ).equals( "get_Wheter_Clouds" ) ) {
//						System.out.println( '2' );
						
						
						
						
						byte [] respMessage = str.getBytes();
						
						DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
						dataSocket.send( response );
						response = null;
					}
					if( sampleObj.get( "methodName" ).equals( "get_Wheter_Wind" ) ) {
//						System.out.println( '3' );
						
						
						
						
						byte [] respMessage = str.getBytes();
						
						DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
						dataSocket.send( response );
						response = null;
					}
				}
				else {
					
					byte [] respMessage = str.getBytes();
					
					DatagramPacket response = new DatagramPacket( respMessage, respMessage.length, dataPacket.getAddress(), dataPacket.getPort() );
					dataSocket.send( response );
//					this.done = true;
//					dataSocket.close();
				}

				
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

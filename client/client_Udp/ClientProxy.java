package client_Udp;

import java.rmi.server.RemoteObject;
import java.util.List;

//ClientProxy recebe de UserClass e manda para ForwarderAgent

public class ClientProxy{
	
	private ClientUDP client;
	
	
	
	public ClientProxy( String host, int portNumber ) {
		try {
			client = new ClientUDP(host, portNumber);
			
			//client.sendRequest( "mensagem ao servidor :D" );
			//System.out.println( client.getResponse() );
			
			
		}catch( Exception e ) {
			System.out.println("ClientProxy error: " + e.getMessage());
		}
	}
	
	
	
	//--------- CurrencyConverter Service functions ---------//
	public float convert (String fromCurrency, String toCurrency, float value ) {
		float result = 0;
		
		
		return result;
	}
	
	
	public boolean currencyExists ( String currency ) {
		boolean answer = false;
		
		
		return answer;
	}
	
	public List<String> currencyExists ( ) {
		List<String> lst = null;
		
		return lst;
	}
	
	//--------- Wheater Service functions ---------//
	
	public String getWheter_Temperature( ) {
		String answer = null;
		
		return answer;
	}
	
	
	public String getWheter_Clouds( ) {
		String answer = null;
		
		return answer;
	}
	
	
	public String getWheter_Wind( ) {
		String answer = null;
		
		return answer;
	}
	
	//--------- Remote Object functions ---------//
	
	public void doOperation( RemoteObject obj, int methodId, byte [] arguments ) {
		
	}
	
	
	
	
	
	
	
	public static void main ( String[] args) {
		ClientProxy prox = new ClientProxy("localhost", 7889);
		
	}
	
}
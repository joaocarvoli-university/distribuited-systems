package client_Udp;

import java.rmi.server.RemoteObject;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



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
	
	
	
	/**Modelo de como as mensagens devem ser empacotadas:
	*	messageType 
	*	requestId
	*	serviceName
	*	methodName
	*	arguments
	*/
	//--------- CurrencyConverter Service functions ---------//
	public float convert (String fromCurrency, String toCurrency, float amount ) {
		float result = 0;
		
		JSONObject sampleObjetct = new JSONObject( );
		
		sampleObjetct.put("messageType", amount);
		sampleObjetct.put("requestId", fromCurrency );
		sampleObjetct.put("methodId", toCurrency );
		sampleObjetct.put("arguments", "CurrencyConverter");

		
		
		
		this.client.sendRequest( sampleObjetct.toString()  );
		//this.client.getResponse();
		System.out.println( client.getResponse() );
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
		//ClientProxy prox = new ClientProxy("localhost", 7889);
		//ClientProxy prox = new ClientProxy("172.18.104.226", 12000 );
		float value = (float) 5.068;
		prox.convert("USD", "BRL", value );
		
	}
	
}
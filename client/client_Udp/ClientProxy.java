package client_Udp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONObject;



//ClientProxy recebe de UserClass e manda para ForwarderAgent

public class ClientProxy{
	
	private ClientUDP client;
	int count;
	
	
	
	public ClientProxy( String host, int portNumber ) {
		try {
			client = new ClientUDP(host, portNumber);
			count = 1;
			
//			client.sendRequest( "Mensagem :D" );
//			System.out.println( client.getResponse() );

			this.convert("USD", "BRL", (float) 5.068 );
			this.currencyExists( "CAD" );
			this.currencysAvailable();
			this.getWheterTemperature();
			this.getWheterClouds();
			this.getWheterWind();
						
		}catch( Exception e ) {
			System.out.println("ClientProxy error: " + e.getMessage());
		}
	}
	

	
	/**Modelo de como as mensagens devem ser empacotadas:
	*	messageType :: int 	( 0 = Request, 1 = Reply )
	*	requestId	:: int	( 0... requisit number )
	*	serviceName	:: String	( CurrencyConverter / Wheater )
	*	methodName	:: String	( name of the method )
	*	arguments	:: byte []	( arguments from method )
	*/
	//--------- CurrencyConverter Service functions ---------//
	public float convert ( String fromCurrency, String toCurrency, float amount ){
		RemoteObject object = new RemoteObject("CurrencyConverter", "convert");
		
		List<String> args = new ArrayList<>();
		args.add( "fromCurrency:" + fromCurrency );
		args.add( "toCurrency:" + toCurrency);
		args.add( "amount:" + Float.toString(amount) );
		
		doOperation( 0, object, ( this.count++ ) , args );
		
		
		//TODO operacoes de acordo com o metodo
		float result = 0;
		return result;
	}
	
	
	public boolean currencyExists ( String currency ) {
		RemoteObject object = new RemoteObject( "CurrencyConverter", "currency_exists");
		
		List<String> args = new ArrayList<>();
		args.add("currency:" + currency);
		
		doOperation( 0, object, ( this.count++ ) , args);
		
		
		//TODO operacoes de acordo com o metodo
		boolean answer = false;
		return answer;
	}
	
	public List<String> currencysAvailable ( ) {
		RemoteObject object = new RemoteObject( "CurrencyConverter", "currencies_available" );
		
		List<String> args = new ArrayList<>();
		
		doOperation(0, object, ( this.count++ ) , args);
		
		
		//TODO operacoes de acordo com o metodo
		List<String> lst = null;
		return lst;
	}
	
	//--------- Wheater Service functions ---------//
	
	public String getWheterTemperature( String cityName ) {
		RemoteObject object = new RemoteObject( "WheaterAPI", "get_Wheter_Temperature" );
		
		List<String> args = new ArrayList<>();
		
		doOperation( 0, object, ( this.count++ ) , args);
		
		//TODO operacoes de acordo com o metodo
		String answer = null;
		return answer;
	}
	
	
	public String getWheterClouds( String cityName ) {
		RemoteObject object = new RemoteObject( "WheaterAPI", "get_Wheter_Clouds" );
		
		List<String> args = new ArrayList<>();
		
		doOperation( 0, object, ( this.count++ ) , args);
		
		
		//TODO operacoes de acordo com o metodo
		String answer = null;
		return answer;
	}
	
	
	public String getWheterWind( String cityName ) {
		RemoteObject object = new RemoteObject("WheaterAPI", "get_Wheter_Wind" );
		
		List<String> args = new ArrayList<>();
		
		doOperation( 0, object, ( this.count++ ) , args);
		
		
		//TODO operacoes de acordo com o metodo
		String answer = null;
		return answer;
	}
	
	//--------- For Remote Object functions ---------//	
	public void doOperation( int messageType, RemoteObject obj, int requestId, List<String> args ) {
		String request = packMessage( messageType , requestId, obj.getServiceString(), obj.getMethodString(), args);
		client.sendRequest(request);

		
		
		//TODO implementar o client.getResponse( );
		String response = client.getResponse();
		System.out.println("servidor retornou: " + response );
		

	}
	
	
	private String packMessage(int messageType, int requestId, String serviceNam, String methodNam, List<String> arguments) {
		
		JSONObject sampleObj = new JSONObject();
		sampleObj.put( "messageType", messageType );
		sampleObj.put( "requestId", requestId );
		sampleObj.put( "serviceName", serviceNam );
		sampleObj.put( "methodName", methodNam );
		sampleObj.put( "arguments", arguments );
				
		String packge = sampleObj.toString(); 
		return packge;
	}
	
	private String unpackMessage( String response ) {
		
		
		String unPackge = "";
		return unPackge;
	}
	
	
	
	class RemoteObject{
		private String serviceString;
		private String methodString;
		private List<String> argments;
		
		public RemoteObject( String service, String method) {
			this.serviceString = service;
			this.methodString  = method;
		}
		
		public String getServiceString() {
			return serviceString;
		}
		public String getMethodString() {
			return methodString;
		}
		
		public void setServiceString(String serviceString) {
			this.serviceString = serviceString;
		}
		public void setMethodString(String methodString) {
			this.methodString = methodString;
		}
		public void setArgs( List<String> args ) {
			this.argments = args;
		}
		public List<String> getArgments ( ){
			return argments;
		}
	}

	public static void main ( String[] args) {
		ClientProxy prox = new ClientProxy("localhost", 7889);
//		ClientProxy prox = new ClientProxy("172.18.104.91", 7889 );
	}
	
}
package client_Udp;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;



//ClientProxy recebe de UserClass e manda para ForwarderAgent

public class ClientProxy{
	
	private ClientUDP client;
	int count;
	
	
	
	public ClientProxy( String host, int portNumber ) {
		try {
			client = new ClientUDP(host, portNumber);
			count = 0;

			this.convert("USD", "BRL", (float) 5.068 );
			this.currencyExists( "CAD" );
//			this.currencysAvailable();
			this.getWheaterTemperature("Mountain View");
			this.getWheaterClouds("Mountain View");
			this.getWheaterWind("Mountain View");

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
		MessageObject remoteObject = new MessageObject("CurrencyConverter", "convert");
		
		List<String> args = new ArrayList<>();
		args.add( "fromCurrency:" + fromCurrency );
		args.add( "toCurrency:" + toCurrency);
		args.add( "amount:" + Float.toString(amount) );
		remoteObject.setArgments( args );
		remoteObject.setMessageType( 0 );
		
//		System.out.println( this.count );
		remoteObject.setRequestId( this.count++ );
			
		var responseObject =  doOperation( remoteObject );
		List <String> respArgs = responseObject.getArgments( );
		String [] amon = respArgs.get(0).split(":");
		float result = Float.parseFloat( amon[1] );
		System.out.println(result);
		return result;
	}
	
	
	public boolean currencyExists ( String currency ) {
		MessageObject remoteObject = new MessageObject( "CurrencyConverter", "currency_exists");
		
		List<String> args = new ArrayList<>();
		args.add("currency:" + currency);
		remoteObject.setArgments( args );
		remoteObject.setMessageType( 0 );
		
//		System.out.println(this.count );
		remoteObject.setRequestId( this.count++ );
		
		var responseObject =  doOperation( remoteObject );		
		List<String> respArgs = responseObject.getArgments( );
		String [] argmts = respArgs.get(0).split(":");
		boolean answer = Boolean.parseBoolean( argmts[1] );
		System.out.println( answer );
		return answer;
	}
	
	
	public List<String> currencysAvailable ( ) {
		MessageObject remoteObject = new MessageObject( "CurrencyConverter", "currencies_available" );
		
		List<String> args = new ArrayList<>();
		remoteObject.setArgments( args );
		remoteObject.setMessageType( 0 );
		remoteObject.setRequestId( this.count++ );
		
		var responseObject = doOperation( remoteObject );
		List<String> respArgs = responseObject.getArgments( );
		List<String> lst = new ArrayList<>();
		
		for (String ocurr : respArgs) {
			String [] str = ocurr.split(":");
			lst.add( str[1] );
//			System.out.println(str[1]);
		}
		
		return lst;
	}
	
	
	//--------- Wheater Service functions ---------//
	
	public String getWheaterTemperature( String cityName ) {
		MessageObject remoteObject = new MessageObject( "WeatherAPI", "get_weather_temperature" );
		
		List<String> args = new ArrayList<>();
		args.add("city:" + cityName);
		remoteObject.setArgments( args );
		remoteObject.setMessageType( 0 );
		
//		System.out.println( this.count );
		remoteObject.setRequestId( this.count++ );
		
		
		var responseObject = doOperation( remoteObject );
		List<String> respArgs = responseObject.getArgments( );
		String [] cityTemp = respArgs.get(0).split(":");
		String answer = cityTemp[1];
//		System.out.println( answer );
		return answer;
	}
	
	
	public String getWheaterClouds( String cityName ) {
		MessageObject remoteObject = new MessageObject( "WeatherAPI", "get_weather_clouds" );
		
		List<String> args = new ArrayList<>();
		args.add("city:" + cityName);
		remoteObject.setArgments( args );
		remoteObject.setMessageType( 0 );
//		System.out.println( this.count );
		remoteObject.setRequestId( this.count++ );
		
		
		var responseObject = doOperation( remoteObject );
		List<String> respArgs = responseObject.getArgments( );
		String [] cityClouds = respArgs.get(0).split(":");
		String answer = cityClouds[1];
//		System.out.println( answer );
		return answer;
	}
	
	
	public String getWheaterWind( String cityName ) {
		MessageObject remoteObject = new MessageObject("WeatherAPI", "get_weather_wind" );
		
		List<String> args = new ArrayList<>();
		args.add("city:" + cityName);
		remoteObject.setArgments( args );
		remoteObject.setMessageType( 0 );
//		System.out.println( this.count );
		remoteObject.setRequestId( this.count++ );
		
		
		var responseObject = doOperation( remoteObject );
		List<String> respArgs = responseObject.getArgments( );
		String [] cityWind = respArgs.get(0).split(":");
		String answer = cityWind[1];
		System.out.println( answer );
		return answer;
	}
	
	
	//--------- For Remote Object functions ---------//	
	public MessageObject doOperation( MessageObject objectMessage  ) {
		String request = packMessage( objectMessage.getMessageType() , objectMessage.getRequestId(), objectMessage.getServiceString(), objectMessage.getMethodString(), objectMessage.getArgments());
		client.sendRequest( request );

		String responseJSON = client.getResponse( );
		responseJSON = responseJSON.replace("\'", "\"");
		responseJSON = responseJSON.replace(" ", "");
//		System.out.println("servidor retornou: " + responseJSON );
		var responseObj = unpackMessage( responseJSON );
		return responseObj;
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
	
	private MessageObject unpackMessage( String response ) {
		JSONObject sampleObj 	= new JSONObject( response );
		var responseObj 		= new MessageObject( sampleObj.getString("serviceName"), sampleObj.getString( "methodName" ) );
		JSONArray jsonArgments 	= new JSONArray();
		jsonArgments 			= sampleObj.getJSONArray( "arguments" );
		List<String> args 		= new ArrayList<>();
		for ( Object Argument : jsonArgments ) {
			args.add(  (String) Argument );
		}
		responseObj.setArgments( args );
		responseObj.setMessageType( sampleObj.getInt( "messageType" ) );
		responseObj.setRequestId( sampleObj.getInt( "requestId" ) );
		return responseObj;
	}
	
	
	
	class MessageObject{
		private String serviceName;
		private String methodName;
		private int messageType;
		private int requestId;
		private List<String> argments;
		
		public MessageObject(){
		}
		
		public MessageObject( String service, String method) {
			this.serviceName = service;
			this.methodName  = method;
		}
		
		public String getServiceString() {
			return serviceName;
		}
		public String getMethodString() {
			return methodName;
		}
		
		public void setServiceString(String serviceString) {
			this.serviceName = serviceString;
		}
		public void setMethodString(String methodString) {
			this.methodName = methodString;
		}
		public void setArgments( List<String> args ) {
			this.argments = args;
		}
		public List<String> getArgments ( ){
			return argments;
		}

		public int getMessageType() {
			return messageType;
		}

		public void setMessageType(int messageType) {
			this.messageType = messageType;
		}

		public int getRequestId() {
			return requestId;
		}

		public void setRequestId(int requestId) {
			this.requestId = requestId;
		}
		
		
	}

//	public static void main (String[] args) {
//		ClientProxy prox = new ClientProxy("localhost", 7889);
////		ClientProxy prox = new ClientProxy("172.18.102.216", 7889 );
//	}
	
}
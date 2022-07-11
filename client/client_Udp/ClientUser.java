package client_Udp;

import java.util.Scanner;

public class ClientUser {
	
	ClientProxy clientpx;
	int portNumber = 7889; 
	
	
	public ClientUser( String localhost ) {
		this.clientpx = new ClientProxy(localhost, portNumber );
	}
	
	
	
	public static void main (String[] args) {
		Scanner scan = new Scanner( System.in );
		System.out.println( "Informar endereço IP do servidor, localhost se for local:");
		String host = scan.nextLine();
		
		ClientUser client = new ClientUser( host);
		boolean done = false;
			
		while( !done ) {
			System.out.println(
								"Cotar: Perguntar qual a cotação atual da moeda A -> B ( ex: USD : BRL : 5.068 )\n"
							+   "Exist: Checar se uma determinada moeda existe na API ( ex: USD, CAD, AUS, BRL, BTC,...\n"
							+   "List: Listar modeas disponíveis para consulta na API\n"
							+ 	"Temp: Clouds: Wind: ");
			String line = scan.nextLine();
			
			if( line.equals( "sair") ) {
				done = true;
			}
			if( line .equals("Cotar" ) ) {
				
			}
			if( line .equals("Exist" ) ) {
				
			}
			if( line .equals("List" ) ) {
				
			}
			if( line .equals("Temp" ) ) {
				
			}
			if( line .equals("Clouds" ) ) {
	
			}
			if( line .equals("Wind" ) ) {
				
			}
		}
		
		
		
		
		
	}
}

package client_Udp;

import java.util.Scanner;

public class ClientUser {
	
	private ClientProxy clientpx;
	int portNumber = 7889; 
	
	
	public ClientUser( String localhost ) {
		this.clientpx = new ClientProxy(localhost, portNumber );
	}
	
	public void cotar(String texto ) {
		float amount =0 ;
		this.clientpx.convert("texto", "texto", amount);
	}
	
	
	
	
	public static void main (String[] args) {
		Scanner scan = new Scanner( System.in );
		System.out.println( "Informar endereço IP do servidor, localhost se for local:");
		String host = scan.nextLine();
		
		ClientUser client = new ClientUser( host );
		boolean done = false;
			
		while( !done ) {
			System.out.println(
								"Cotar: Perguntar qual a cotação atual da moeda A -> B ( ex: Cotar: USD : BRL : 5.068 )\n"
							+   "Exist: Checar se uma determinada moeda existe na API ( ex: Exist: USD, Exist: CAD, Exist: AUS, Exist: BRL, Exist: BTC...)\n"
							+   "List: Listar modeas disponíveis para consulta na API\n"
							+ 	"Temp: Checar a temperatura em uma cidade ( Fortaleza, Maracanaú... )\n"
							+   "Clouds: Checar a nebulosidade em uma cidade ( Fortaleza, Maracanaú... )\n"
							+   "Wind: Checar forçar dos ventos em uma cidade ( Fortaleza, Maracanaú... )" );
			String line = scan.nextLine();
			String [] lineSplitted = line.split(":");
//			System.out.println( lineSplitted[1].toString() );
			
			if( lineSplitted[0].toLowerCase().equals( "sair") ) {
				System.out.println("Encerrando...\n(-----------------------------)\"");
				done = true;
			}
			else if( lineSplitted[0].toLowerCase().equals("cotar" ) ) {
				String ask1 = lineSplitted[1].toUpperCase();
				ask1 = ask1.replace(" ", "");
				System.out.println(ask1);
				String ask2 = lineSplitted[2].toUpperCase();
				ask2 = ask2.replace(" ", "");
				System.out.println(ask2);
				
				float amount = Float.parseFloat(lineSplitted[3]);
				float result = client.clientpx.convert(ask1, ask2, amount);
				System.out.println("O valor convertido é: " + result + "\n(-----------------------------)");
			}
			else if( lineSplitted[0].toLowerCase().equals("exist" ) ) {
				String ask = lineSplitted[1].toUpperCase();
				ask = ask.replace(" ", "");
				System.out.println(ask);
				boolean result = client.clientpx.currencyExists(ask);
				if( !result ) {
					System.out.println("Desculpe, não foi encontrado nada sobre " + ask + "\n(-----------------------------)");
				} else {
					System.out.println("Sim, " + ask + " é uma das moedas reconhecidas pela aplicação :D\n(-----------------------------)");
				}
				
			}
			else if( lineSplitted[0].toLowerCase().equals("list" ) ) {
				System.out.println("Desculpa, função ainda precisa de ajustes...\n(-----------------------------)\"");
			}
			else if( lineSplitted[0].toLowerCase().equals("temp" ) ) {
				String ask = lineSplitted[1].toLowerCase();
				String result = client.clientpx.getWheaterTemperature( ask );
				System.out.println(lineSplitted[1].toString() + " está a " + result + " graus\n(-----------------------------)" );
			}
			else if( lineSplitted[0].toLowerCase().equals("clouds" ) ) {
				String ask = lineSplitted[1].toLowerCase();
				String result = client.clientpx.getWheaterClouds( ask );
				System.out.println( "As nuvens em " + lineSplitted[1].toString() + " estão " + result + "\n(-----------------------------)");
			}
			else if( lineSplitted[0].toLowerCase().equals("wind" ) ) {
				String ask = lineSplitted[1].toLowerCase();
				String result = client.clientpx.getWheaterWind( ask );
				System.out.println("Os ventos em " + lineSplitted[1].toString() + " estão a " + result + " nós\n(-----------------------------)" );
			}else {
				System.out.println("Desculpe, comando fora dos padrões reconhecidos\n(-----------------------------)");
			}
		}
		
		
		
		
		
	}
}

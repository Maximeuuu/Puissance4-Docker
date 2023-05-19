import java.net.*;
import java.io.*;
//import iut.algo.*;
import java.io.Console;

public class ClientPuissance4
{
	private String host;
	private int port;
	private Socket toServer;
	private String utilisateur;

	public ClientPuissance4( String host, int port, String utilisateur )
	{
		Console console;
		BufferedReader in;
		PrintWriter out;
		String message="";
		String envoyer="";

		console = System.console();
		
		try
		{
			this.toServer = new Socket( host, port );
			this.utilisateur = utilisateur;

			in = new BufferedReader( new InputStreamReader( this.toServer.getInputStream() ));
			out = new PrintWriter(this.toServer.getOutputStream(), true);

			out.println(this.utilisateur); //envoyer le pseudo
			message = in.readLine(); //lire le message de Bienvenue
			System.out.println( message );

			message = in.readLine(); //connexion réussie
			System.out.println( message );

			do
			{
				message = in.readLine(); //lecture du tour
				System.out.println( message );

				if( message.equals("Vous avez Gagné !") || 
				    message.equals("Vous avez Perdu !") ||
					message.equals("Egalité !" )) break;
				
				if( message.equals("rejouer") )
				{
					System.out.println("Console : coup invalide, veuillez rejouer");
					//envoyer =  Clavier.lire_int() + "";
					envoyer = console.readLine(">") + "";
					out.println( envoyer );
				}

				if( message.equals("jouer") ) //ou non null...
				{
					System.out.println("Console : à vous de jouer");
					//envoyer =  Clavier.lire_int() + "";
					envoyer = console.readLine(">") + "";
					out.println( envoyer );
				}

				if( message.equals("X") )
				{
					System.out.println("Console : Tour adverse");
					//message = in.readLine();
				}

				for(int cpt=0;cpt<9;cpt++)
					System.out.println( in.readLine() );

				System.out.println("");
				
			} while ( !(message == null || message.equals("")) ); //lire les messages en boucle

			System.out.println("Déconnexion.");
			in.close();
			out.close();
			this.toServer.close();
		}
		catch( IOException e )
		{
			System.out.println("Erreur.");
		}

	}

	public String toString()
	{
		return "Client : " + this.utilisateur + " (" + this.host + " : " + this.port + ") ";
	}

	public static void main( String[] args )
	{
		new ClientPuissance4
		( args[0], 9000, args[1] );
	}
}

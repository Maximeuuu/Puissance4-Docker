import java.net.*;
import java.io.*;
import iut.algo.*;

public class ClientTest
{
	public static void main( String[] args )
	{
		try
		{
			String serveur;
			int portNumber = Integer.parseInt( args[ args.length - 1 ] );
			
			int cpt=0;
			while( !args[cpt].startsWith("di") )
			{
				serveur = args[cpt];
				
				
				System.out.println("connexion au serveur...");
				
				Socket toServer = new Socket(serveur, portNumber);
				System.out.println("connecté...");
				
				BufferedReader in = new BufferedReader( new InputStreamReader(toServer.getInputStream()));
				
				System.out.println( in.readLine() );
				
				PrintWriter out;
				String message = "";
				do
				{
					out = new PrintWriter(toServer.getOutputStream(), true);
					message = Clavier.lireString();
					out.println( message );
				}while( message != null && !message.equals(" ") );
				
				out.close();
				in.close();
				toServer.close();
				
				cpt++;
			}
		}catch( IOException e ){ System.out.println("erreur de connection"); }
	}
}

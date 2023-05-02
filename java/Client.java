import java.net.*;
import java.io.*;

public class Client
{
	private Controleur ctrl;
	
	public Client ( String ip, int portNumber, Controleur ctrl )
	{
		try
		{
			Socket toServer = new Socket(ip, portNumber);
			
			this.ctrl.lancerPartie();
			
			BufferedReader in = new BufferedReader( new InputStreamReader(toServer.getInputStream()));
			PrintWriter out   = new PrintWriter(toServer.getOutputStream(), true);

			int cpt = 0;
			while( this.ctrl.estFini() )
			{
				if( cpt % 2 == 0 )
				{
					this.ctrl.ajouterPiece( this.recevoir() );
				}
				
				if( cpt % 2 == 1 )
				{
					this.envoyer();
				}
				
				cpt++;
			}
			
			out.close();
			in.close();
			toServer.close();
			
		
		}catch( IOException e ){ System.out.println("erreur de connection"); }
	}
	
	public void envoyer()
	{
		while ( this.ctrl.metier.getDernierCoup() == -1)
		{
			
		}
		PrintWriter out   = new PrintWriter(toServer.getOutputStream(), true);
		out.println( this.ctrl.metier.getDernierCoup() + "" );
		
		this.ctrl.metier.resetCoup();
	}
	
	public int recevoir()
	{
		int pos = -1;
		while ( pos == -1 )
		{
			BufferedReader in = new BufferedReader( new InputStreamReader(toServer.getInputStream()));
			pos = Integer.parseInt( in.readLine() );
		}
		return pos;
	}
}

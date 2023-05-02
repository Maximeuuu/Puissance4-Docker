import java.net.*;
import java.io.*;

public class Client
{
	private Controleur ctrl;
	private Socket toServer;
	
	public Client ( String ip, int portNumber, Controleur ctrl )
	{
		try
		{
			this.toServer = new Socket(ip, portNumber);
			
			this.ctrl.lancerPartie();

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
			
			toServer.close();
			
		
		}catch( IOException e ){ System.out.println("erreur de connection"); }
	}
	
	public void envoyer()
	{
		while ( this.ctrl.getDernierCoup() == -1)
		{
			
		}
		try
		{
			PrintWriter out   = new PrintWriter(this.toServer.getOutputStream(), true);
			out.println( this.ctrl.getDernierCoup() + "" );
			out.close();
		}catch( IOException e ){}
		
		this.ctrl.resetCoup();
	}
	
	/*public int recevoir()
	{
		int pos = -1;
		
		try{
			BufferedReader in = new BufferedReader( new InputStreamReader(this.toServer.getInputStream()));
			while ( pos == -1 )
			{
				String text = in.readLine();
				pos = Integer.parseInt( text );
			}
			in.close();
		}catch( IOException e ){}
		
		return pos;
	}*/
	
	public int recevoir() {
    int pos = -1;
    
    try {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.toServer.getInputStream()));
        String text = in.readLine(); // Lecture d'une ligne du flux d'entrée
        
        // Utilisation d'une boucle pour gérer les lectures multiples
        while (text != null) {
            try {
                pos = Integer.parseInt(text); // Conversion en entier
                break; // Sortie de la boucle si la conversion a réussi
            } catch (NumberFormatException e) {
                // Gestion de l'exception si la chaîne ne peut pas être convertie en entier
                System.err.println("Erreur de conversion en entier : " + text);
            }
            text = in.readLine(); // Lecture de la ligne suivante
        }
        
        in.close(); // Fermeture du flux d'entrée
    } catch (IOException e) {
        // Gestion de l'exception d'E/S
        System.err.println("Erreur d'E/S : " + e.getMessage());
    }
    
    return pos;
	}
}

import java.net.*;
import java.io.*;

public class Serveur
{
	private Controleur ctrl;
	private int        port;
	private Socket toClient;

	public Serveur (int port, Controleur ctrl)
	{
		this.port = port;
		this.ctrl = ctrl;

		System.out.println("En attente d'un client...");
		try
		{
			ServerSocket ss = new ServerSocket(port);

			while (true)
			{
				this.toClient = ss.accept();

				this.ctrl.lancerPartie();

				int cpt = 0;
				while ( !this.ctrl.estFini() )
				{
					if (cpt % 2 == 0)
					{
						this.envoyer();
					}
					if (cpt % 2 == 1)
					{
						this.ctrl.ajouterPiece( this.recevoir() );
					}
				}

				//out.println();
				//in.readLine();

				toClient.close();
			}
		}
		catch(Exception e)
		{
			System.out.println(e);
		}

	}


	public void envoyer ()
	{
		while ( this.ctrl.getDernierCoup() == -1 )
		{
			
		}

		try{
		PrintWriter out = new PrintWriter(this.toClient.getOutputStream(), true);
		out.println ( this.ctrl.getDernierCoup() + "" );
		out.close();
		}catch( IOException e ){}

		this.ctrl.resetCoup();
	}

	/*public int recevoir()
	{
		int pos = -1;
		
		BufferedReader in = new BufferedReader( new InputStreamReader(this.toClient.getInputStream()));
		while ( pos == -1 )
		{
			String text = in.readLine();
			pos = Integer.parseInt( text );
		}
		in.close();
		
		
		return pos;
	}*/
	public int recevoir() {
    int pos = -1;
    
    try {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.toClient.getInputStream()));
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

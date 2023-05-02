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

			
			this.toClient = ss.accept();

			this.ctrl.lancerPartie();

			/*int cpt = 0;
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
				
				cpt++;
			}*/

			//toClient.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	public void envoyer() {
    while ( ctrl.getTour() != 1) {}
    try {
        PrintWriter out = new PrintWriter(this.toClient.getOutputStream(), true);
        out.println(ctrl.getDernierCoup() + "");
        out.close();
    } catch (IOException e) {
    }

    ctrl.resetCoup();
	}

	
	public int recevoir() {
    int pos = -1;

    try {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.toClient.getInputStream()));
        String text = in.readLine();

        while (ctrl.getTour() != 0) {}

        while (text != null) {
            try {
                pos = Integer.parseInt(text);
                break;
            } catch (NumberFormatException e) {
                System.err.println("Erreur de conversion en entier : " + text);
            }
            text = in.readLine();
        }

        in.close();
    } catch (IOException e) {
        System.err.println("Erreur d'E/S : " + e.getMessage());
    }

    ctrl.resetCoup();

    return pos;
	}
}

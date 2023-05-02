import java.net.*;
import java.io.*;

public class Client
{
	private Controleur ctrl;
	private Socket toServer;
	
	public Client ( String ip, int portNumber, Controleur ctrl )
	{
		this.ctrl = ctrl;
		
		try
		{
			this.toServer = new Socket(ip, portNumber);
			
			this.ctrl.lancerPartie();

			/*int cpt = 0;
			while( !this.ctrl.estFini() )
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
			}*/
			
			//toServer.close();
			
		
		}catch( IOException e ){ System.out.println("erreur de connection"); }
		
	}
	
	public void envoyer() {
    while (ctrl.getDernierCoup() == -1 || ctrl.getTour() != 0) {
    }
    try {
        PrintWriter out = new PrintWriter(this.toServer.getOutputStream(), true);
        out.println(ctrl.getDernierCoup() + "");
        out.close();
    } catch (IOException e) {
    }

    ctrl.resetCoup();
	}
	
	
	public int recevoir() {
    int pos = -1;

    try {
        BufferedReader in = new BufferedReader(new InputStreamReader(this.toServer.getInputStream()));
        String text = in.readLine();

        while (ctrl.getTour() != 1) {
        }

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

import java.net.*;
import java.io.*;

public class Serveur
{
	private Controleur ctrl;
	private int        port;

	public Serveur (int port, ctrl Controleur)
	{
		this.port = port;
		this.ctrl = ctrl;

		System.out.println("En attente d'un client...");
		try
		{
			ServerSocket ss = new ServerSocket(port);

			while (true)
			{
				Socket toClient = ss.accept(); // bloqué tant que le client n’arrive pas
				System.out.println(toClient.getInetAddress() + " est connecté");

				this.ctrl.lancerPartie();

				PrintWriter out = new PrintWriter(toClient.getOutputStream(), true);
				BufferedReader in = new BufferedReader(new InputStreamReader(toClient.getInputStream()));


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

				in.close();
				out.close();
				toClient.close();
			}
			catch(Exception e)
			{
				System.out.println(e);
			}
		}
	}


	public void envoyer ()
	{
		while ( this.ctrl.metier.getDernierCoup() == -1 )
		{
			
		}

		PrintWriter out = new PrintWriter(toClient.getOutputStream(), true);
		out.println ( this.ctrl.metier.getDernierCoup() + "" );

		this.ctrl.metier.resetCoup();
	}

	public int  recevoir
	{
		int pos -1;
		while ( pos == -1 )
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(toClient.getInputStream()));
			pos = Integer.parseInt( in.readLine() );
		}

		return pos;
	}
}

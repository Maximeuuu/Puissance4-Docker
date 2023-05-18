import java.net.*;
import java.io.*;

public class ServeurPuissance4
{
	private int port;

	private ServerSocket ss;
	private Socket toClient1;
	private Socket toClient2;

	private Puissance4 puissance4;

	public ServeurPuissance4( int port )
	{
		String msg1;
		String msg2;
		String message="";
		BufferedReader in1;
		BufferedReader in2;
		PrintWriter out1;
		PrintWriter out2;
		this.port = port;

		try
		{
			this.ss = new ServerSocket( this.port );
			System.out.println( "Serveur : "+InetAddress.getLocalHost() );

			this.toClient1 = this.ss.accept();
			out1 = new PrintWriter(this.toClient1.getOutputStream(), true);
			in1 = new BufferedReader( new InputStreamReader( this.toClient1.getInputStream() ));

			msg1 = in1.readLine(); //récupérer le pseudo
			System.out.println( "Serveur : Client "+msg1+" est arrivé.");
			out1.println("Bienvenue Joueur "+msg1); //envoyer le message

			this.toClient2 = this.ss.accept();
			out2 = new PrintWriter(this.toClient2.getOutputStream(), true);
			in2 = new BufferedReader( new InputStreamReader( this.toClient2.getInputStream() ));

			msg2 = in2.readLine(); //récupérer le pseudo
			System.out.println( "Serveur : Client "+msg2+" est arrivé.");
			out2.println("Bienvenue Joueur "+msg2); //envoyer le message

			out1.println("Connexion des deux clients réussie."); //envoyer les messages
			out2.println("Connexion des deux clients réussie."); //envoyer les messages

			this.puissance4 = new Puissance4();
			int col = 0;

			do
			{
				//joueur 1
				System.out.println("Serveur : Tour du joueur 1");
				out1.println("jouer");
				out2.println("X");
				do{
					message = in1.readLine();
					col = Integer.parseInt(message) - 1;
	
					if( !this.puissance4.verif(col ))
					{
						out1.println(this.puissance4.envoyerPlateau());
						out1.println("rejouer");
					}
						
				}while( !this.puissance4.verif(col) );

				System.out.println( "joueur 1 : "+message );

				this.puissance4.placer( Integer.parseInt(message) - 1);
				out1.println(this.puissance4.envoyerPlateau());
				out2.println(this.puissance4.envoyerPlateau());

				if( this.puissance4.getFin() )
				{
					if( this.puissance4.getEgalite() )
					{
						out1.println("Egalité !");
						out2.println("Egalité !");
					}
					else
					{
						out1.println("Vous avez Gagné !");
						out2.println("Vous avez Perdu !");
					}
					break;
				}

				//joueur 2
				System.out.println("Tour du joueur 2");
				out1.println("X");
				out2.println("jouer");
				do{
					message = in2.readLine();
					col = Integer.parseInt(message) - 1;

					if( !this.puissance4.verif(col ))
					{
						out2.println(this.puissance4.envoyerPlateau());
						out2.println("rejouer");
					}

				}while(!this.puissance4.verif(col));
				
				System.out.println( "joueur 2 : "+message );

				this.puissance4.placer( Integer.parseInt(message) - 1);
				out1.println(this.puissance4.envoyerPlateau());
				out2.println(this.puissance4.envoyerPlateau());

				if( this.puissance4.getFin() )
				{
					if( this.puissance4.getEgalite() )
					{
						out2.println("Egalité !");
						out1.println("Egalité !");
					}
					else
					{
						out2.println("Vous avez Gagné !");
						out1.println("Vous avez Perdu !");
					}
					break;
				}

			} while ( message != "" && message != null ); //lire les messages en boucle

			System.out.println("Arret du serveur.");

			out1.close();
			in1.close();
			out2.close();
			in2.close();
			this.toClient1.close();
			this.toClient2.close();
		}
		catch( IOException e )
		{
			System.out.println("Déconnexion du serveur.");
		}
	}



	public static void main( String[] args )
	{
		new ServeurPuissance4( 9000 );
	}
}

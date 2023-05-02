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
			
			//toClient.close();
			
		}
		catch(Exception e)
		{
			System.out.println(e);
		}
		
	}

	public void envoyer(int col) 
	{
		try {
			PrintWriter out = new PrintWriter(this.toClient.getOutputStream(), true);
			out.println( col + "");
			out.close();
		}
		catch (IOException e) {
	}

	}

	
	public int recevoir() 
	{
		int pos = -1;

		try 
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(this.toClient.getInputStream()));
			String text = in.readLine();

			while (text == null) 
			{
				text = in.readLine();
				System.out.println(text);
			}
			pos = Integer.parseInt(text);

			in.close();
		} 
		catch (IOException e) 
		{
			System.err.println("Erreur d'E/S : " + e.getMessage());
		}
		System.out.println(pos);
		return pos;
	}
}

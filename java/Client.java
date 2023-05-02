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
			
			//toServer.close();
			
		
		}catch( IOException e ){ System.out.println("erreur de connection"); }
		
	}
	
	public void envoyer(int col) 
	{
		try {
			PrintWriter out = new PrintWriter(this.toServer.getOutputStream(), true);
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
			BufferedReader in = new BufferedReader(new InputStreamReader(this.toServer.getInputStream()));
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

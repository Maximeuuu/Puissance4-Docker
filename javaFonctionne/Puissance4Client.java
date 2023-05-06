import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;
import java.net.*;

public class Puissance4Client {

    private FramePuissance4      frame;
    private JButton[]   buttons;
    private JLabel      statusLabel;
    
    private JPanel      boardPanel;
    private JLabel[][]  plateau;
    
    public BufferedReader in;
    private PrintWriter out;
    
    private int col = -1;
    
    public Puissance4Client(int portNumber, String ip) {
        try {
            Socket toServer = new Socket(ip, portNumber);
            
            this.out = new PrintWriter(toServer.getOutputStream(), true);
			this.in  = new BufferedReader(new InputStreamReader(toServer.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public Puissance4Client(Socket socket) {
        try {
            
            this.out = new PrintWriter(socket.getOutputStream(), true);
			this.in  = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        frame = new FramePuissance4(this);
    }
    
    public void updateBoard(int[][] board) {
        frame.majGrille(board);
	}

	public void setStatus(String message) {
		frame.setTexteJoueur(message);
	}

	public void showWinDialog(String message) {
		//JOptionPane.showMessageDialog(frame, message, "Victoire!", JOptionPane.INFORMATION_MESSAGE);
		
		frame.setTexteVictoire(message);
	}

	public void showDrawDialog() {
		//JOptionPane.showMessageDialog(frame, "Match nul!", "Match nul", JOptionPane.INFORMATION_MESSAGE);
		
		frame.setTexteVictoire("Match nul!");
	}
	
	public void envoyer(int column)
	{
		this.out.println( column );
		this.out.flush();
	}
	
	public int getColonne()
	{
		return this.col;
	}
	
	public void setColonne(int col)
	{
		this.col = col;
	}
	
	public static void main(String[] args) {
        new Puissance4Client(9000, "di-715-12");
    }
}

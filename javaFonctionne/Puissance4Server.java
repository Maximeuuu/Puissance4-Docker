import java.net.*;
import java.io.*;

public class Puissance4Server {
    private ServerSocket serverSocket;
    private Socket player1Socket;
    private Socket player2Socket;
    
    public Puissance4Server(int portNumber) {
        try {
            serverSocket = new ServerSocket(portNumber);
            
            
            //toServer = new Socket(ip, portNumber);
            
            //out = new PrintWriter(this.toServer.getOutputStream(), true);
			//in  = new BufferedReader(new InputStreamReader(this.toServer.getInputStream()));
			
            System.out.println("Waiting for player 1 to connect...");
            player1Socket = serverSocket.accept();
            System.out.println("Player 1 connected.");
            
            System.out.println("Waiting for player 2 to connect...");
            player2Socket = serverSocket.accept();
            System.out.println("Player 2 connected.");
            
            // Lancer une nouvelle instance de Puissance4Game pour gérer le jeu
            new Puissance4Game(player1Socket, player2Socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String[] args) {
        new Puissance4Server(9000);
    }
}

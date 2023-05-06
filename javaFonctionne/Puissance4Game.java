import java.net.*;
import java.io.*;
public class Puissance4Game {
	private int[][] board;
	private Puissance4Client player1;
	private Puissance4Client player2;
	private int currentPlayer;

	public Puissance4Game(Socket player1Socket, Socket player2Socket) {
		board = new int[6][7];
		player1 = new Puissance4Client(player1Socket);
		player2 = new Puissance4Client(player2Socket);
		this.currentPlayer = 1;
		
		startGame();
	}

	public void startGame() {
		
		player1.setStatus("C'est à votre tour.");
		player2.setStatus("En attente du joueur 1...");
		
		while (true) {
			player1.updateBoard(board);
			player2.updateBoard(board);
			
			Puissance4Client currentPlayerClient = (this.currentPlayer == 1) ? player1 : player2;
			Puissance4Client otherPlayerClient = (this.currentPlayer == 1) ? player2 : player1;
			
			currentPlayerClient.setStatus("C'est à votre tour.");
			otherPlayerClient.setStatus("En attente de l'autre joueur...");
			
			int column = getColumnFromPlayer(currentPlayerClient, otherPlayerClient);
			
			if (column == -1) {
				break;
			}
			
			int row = dropPiece(column);
			
			if (isWinningMove(row, column)) {
				currentPlayerClient.showWinDialog("Vous avez gagné!");
				otherPlayerClient.showWinDialog("Vous avez perdu!");
				
				player1.updateBoard(board);
				player2.updateBoard(board);
				break;
			} else if (isBoardFull()) {
				player1.showDrawDialog();
				player2.showDrawDialog();
				
				player1.updateBoard(board);
				player2.updateBoard(board);
				break;
			} else {
				currentPlayer = (currentPlayer == 1) ? 2 : 1;
		    }
		}
		
		player1.setStatus("Le jeu est terminé.");
		player2.setStatus("Le jeu est terminé.");
	}

	private int getColumnFromPlayer(Puissance4Client currentPlayerClient, Puissance4Client otherPlayerClient) {
		int column = -1;
		while (true) {
			column = currentPlayerClient.getColonne();
			
			if (isValidMove(column)) {
				break;
			} else {
				currentPlayerClient.setStatus("C'est à votre tour.");
			}
		};
		currentPlayerClient.setColonne(-1);
		
		return column;
	}

	private boolean isValidMove(int column) {
		if (column < 0 || column >= 7) {
			return false;
		}
		
		return board[0][column] == 0;
	}

	private int dropPiece(int column) {
		
		if (board[0][column] != 0) {
			// La colonne est pleine
			return -1;
		}

		// Parcourir la colonne de bas en haut pour trouver la première case vide
		int row = 5;
		while (row >= 0 && board[row][column] != 0) {
			row--;
		}

		// Placer le jeton du joueur courant dans la première case vide
		board[row][column] = currentPlayer;

		// Passer le tour au joueur suivant
		//currentPlayer = currentPlayer == 1 ? 2 : 1;

		for (int i = 0; i < 6; i++) {
			for (int j = 0; j < 7; j++) {
				System.out.print(board[i][j]);
			}
			System.out.println();
		}
		
		// Retourner la ligne où le jeton a été placé
		return row;
	}
	
	private boolean isWinningMove(int row, int col) {
		// Vérification de la victoire horizontale
		int count = 0;
		
		for (int i = 0; i < 7; i++) {
			if (board[row][i] == currentPlayer) {
				count++;
			} else {
				count = 0;
			}
			
			if (count >= 4) {
				return true;
			}
		}
		
		// Vérification de la victoire verticale
		count = 0;
        
        for (int i = 0; i < 6; i++) {
            if (board[i][col] == currentPlayer) {
                count++;
            } else {
                count = 0;
            }
            
            if (count >= 4) {
                return true;
            }
        }
        
        // Vérification de la victoire diagonale (haut-gauche -> bas-droit)
        int startRow = Math.max(row - 3, 0);
        int startCol = Math.max(col - 3, 0);
        int endRow = Math.min(row + 3, 5);
        int endCol = Math.min(col + 3, 6);
        
        count = 0;
        
        for (int i = startRow, j = startCol; i <= endRow && j <= endCol; i++, j++) {
            if (board[i][j] == currentPlayer) {
                count++;
            } else {
                count = 0;
            }
            
            if (count >= 4) {
                return true;
            }
        }
        
        // Vérification de la victoire diagonale (bas-gauche -> haut-droit)
        startRow = Math.min(row + 3, 5);
        startCol = Math.max(col - 3, 0);
        endRow = Math.max(row - 3, 0);
        endCol = Math.min(col + 3, 6);
        
        count = 0;
        
        for (int i = startRow, j = startCol; i >= endRow && j <= endCol; i--, j++) {
            if (board[i][j] == currentPlayer) {
                count++;
            } else {
                count = 0;
            }
            
            if (count >= 4) {
                return true;
            }
        }
        
        return false;
    }
    
    private boolean isBoardFull() {
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < 7; j++) {
                if (board[i][j] == 0) {
                    return false;
                }
            }
        }
        
        return true;
    }
}

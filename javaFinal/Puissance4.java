
public class Puissance4
{
	private static final int LIGNES = 6;
	private static final int COLONNES = 7;
	
	public static final char VIDE = ' ';
	public static final char JOUEUR1 = 'X';
	public static final char JOUEUR2 = 'O';

	private char[][] plateau;
	private char joueurActuel;

	private boolean jeuFini = false;
	private boolean egalite = false;
	
	//private Controleur ctrl;

	public Puissance4() //(Controleur ctrl)
	{
		//this.ctrl = ctrl;

		plateau = new char[LIGNES][COLONNES];
		for (int i = 0; i < LIGNES; i++)
		{
			for (int j = 0; j < COLONNES; j++)
			{
				plateau[i][j] = VIDE;
			}
		}
		joueurActuel = JOUEUR1;
	}

	public void placer( int col )
	{
		boolean coupValide = false;
		while (!coupValide)
		{
			System.out.print("Joueur " + joueurActuel );
			int colonne = col;
			if (colonne >= 0 && colonne < COLONNES && plateau[0][colonne] == VIDE)
			{
				for (int i = LIGNES - 1; i >= 0; i--)
				{
					if (plateau[i][colonne] == VIDE)
					{
						plateau[i][colonne] = joueurActuel;
						coupValide = true;
						break;
					}
				}
			} else
			{
				System.out.println("Coup invalide. Veuillez choisir une colonne valide.");
			}
		}
		if (verifierVictoire(joueurActuel))
		{
			this.jeuFini = true;
		} else if (verifierEgalite())
		{
			this.egalite = true;
			this.jeuFini = true;
		} else
		{
			joueurActuel = (joueurActuel == JOUEUR1) ? JOUEUR2 : JOUEUR1;
		}
		
		//this.ctrl.setTexteJoueur( joueurActuel );
	}

	public boolean getFin()
	{
		return this.jeuFini;
	}

	public boolean getEgalite()
	{
		return this.egalite;
	}

	public String envoyerPlateau()
	{
		String s=" 1 2 3 4 5 6 7 \n";
		for (int i = 0; i < LIGNES; i++)
		{
			for (int j = 0; j < COLONNES; j++)
			{
				s+="|" + plateau[i][j];
			}
			s+="|\n";
		}
		s+="---------------\n";

		return s;
	}

public boolean verifierVictoire(char joueur)
{
		// Vérification des lignes
		for (int i = 0; i < LIGNES; i++)
		{
			for (int j = 0; j <= COLONNES - 4; j++)
			{
				if (plateau[i][j] == joueur && plateau[i][j + 1] == joueur && plateau[i][j + 2] == joueur && plateau[i][j + 3] == joueur)
				{
					return true;
				}
			}
		}

		// Vérification des colonnes
		for (int i = 0; i <= LIGNES - 4; i++)
		{
			for (int j = 0; j < COLONNES; j++)
			{
				if (plateau[i][j] == joueur && plateau[i + 1][j] == joueur && plateau[i + 2][j] == joueur && plateau[i + 3][j] == joueur)
				{
					return true;
				}
			}
		}

		// Vérification des diagonales ascendantes
		for (int i = 3; i < LIGNES; i++)
		{
			for (int j = 0; j <= COLONNES - 4; j++)
			{
				if (plateau[i][j] == joueur && plateau[i - 1][j + 1] == joueur && plateau[i - 2][j + 2] == joueur && plateau[i - 3][j + 3] == joueur)
				{
					return true;
				}
			}
		}

		// Vérification des diagonales descendantes
		for (int i = 0; i <= LIGNES - 4; i++)
		{
			for (int j = 0; j <= COLONNES - 4; j++)
			{
				if (plateau[i][j] == joueur && plateau[i + 1][j + 1] == joueur && plateau[i + 2][j + 2] == joueur && plateau[i + 3][j + 3] == joueur)
				{
					return true;
				}
			}
		}

		return false;
	}

	public boolean verifierEgalite()
	{
		for (int i = 0; i < LIGNES; i++)
		{
			for (int j = 0; j < COLONNES; j++)
			{
				if (plateau[i][j] == VIDE)
				{
					return false;
				}
			}
		}
		return true;
	}
	
	public char getCase( int lig, int col )
	{
		return this.plateau[lig][col];
	}

	public boolean verif( int col )
	{
		if( col < 0 || col > 6) return false;
		if( this.plateau[0][col] != ' ' ) return false;

		return true;
	}

	/*public static void main(String[] args)
	{
		Puissance4 jeu = new Puissance4();
		jeu.jouer();
	}*/
}



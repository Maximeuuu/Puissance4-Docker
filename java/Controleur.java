public class Controleur
{
	private Puissance4 metier;
	private FramePuissance4 ihm;
	
	private Frame accueil;
	
	public Controleur()
	{
		this.accueil = new Frame( this );
		
	}
	
	public void lancerPartie()
	{
		this.metier = new Puissance4( this );
		this.ihm    = new FramePuissance4( this );
		
		this.ihm.majGrille();
	}
	
	public static void main( String[] args )
	{
		new Controleur();
	}
	
	public char getCase( int lig, int col )
	{
		return this.metier.getCase(lig, col);
	}
	
	public void bougerFleche( char col )
	{
		this.ihm.bougerFleche( col );
	}
	
	public void ajouterPiece( int col )
	{
		this.metier.placer( col );
	}
	
	public void setTexteJoueur( char joueur )
	{
		this.ihm.setTexteJoueur( joueur );
	}
	
	public void setTexteErreur( String message )
	{
		this.ihm.setTexteErreur( message );
	}
}

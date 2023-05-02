public class Controleur
{
	private Puissance4      metier;
	private FramePuissance4 ihm;
	
	private Frame           accueil;

	private Serveur         serveur;
	private Client          client;

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
	
	public char getCase( int lig, int col )
	{
		return this.metier.getCase(lig, col);
	}
	
	public boolean estFini()
	{
		return this.metier.estFini();
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
	
	public int getDernierCoup()
	{
		return this.metier.getDernierCoup();
	}
	
	public void resetCoup()
	{
		this.metier.resetCoup();
	}
	
	public static void main( String[] args )
	{
		new Controleur();
	}


	public void creerServeur( int port )
	{
		this.serveur = new Serveur ( port, this);
	}

	public void creerClient ( int port, String ip )
	{
		this.client  = new Client  ( ip, port, this );
	}

	public void cacherFrame()
	{
		this.accueil.cacherFrame();
	}
}

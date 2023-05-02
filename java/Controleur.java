public class Controleur
{
	private Puissance4      metier;
	private FramePuissance4 ihm;
	
	private Frame           accueil;

	private Serveur         serveur;
	private Client          client;
	
	private int tour = -1;

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
		if (tour == 0) {
		    // Ajouter la pièce du joueur 1
		    tour = 1;
		} else {
		    // Ajouter la pièce du joueur 2
		    tour = 0;
    	}
	}
	
	public int getTour()
	{
		return this.tour;
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
		this.tour = 0;
	}

	public void creerClient ( int port, String ip )
	{
		this.client  = new Client  ( ip, port, this );
		this.tour = 1;
	}

	public void cacherFrame()
	{
		this.accueil.cacherFrame();
	}
	
	public void envoyer( int col )
	{
		if( this.client  != null ) this.client.envoyer(col);
		if( this.serveur != null ) this.serveur.envoyer(col);
	}
	
	public int recevoir()
	{
		if( this.client  != null ) return this.client.recevoir();
		if( this.serveur != null ) return this.serveur.recevoir();
		return 0;
	}
}

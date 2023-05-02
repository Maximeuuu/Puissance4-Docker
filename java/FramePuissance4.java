import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class FramePuissance4 extends JFrame implements KeyListener
{
	private PanelFleche pnlFleche;
	private PanelGrille pnlGrille;
	private PanelAffichage pnlAffichage;
	
	private Controleur ctrl;
	
	public FramePuissance4( Controleur ctrl )
	{
		this.ctrl = ctrl;
		this.setSize( 500, 500 );
		this.setTitle( "Puissance 4" );
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		//Création des composants
		this.pnlFleche    = new PanelFleche( ctrl );
		this.pnlGrille    = new PanelGrille( ctrl );
		this.pnlAffichage = new PanelAffichage( ctrl );
		
		//Positionnement des composants
		this.add( this.pnlFleche   , BorderLayout.NORTH );
		this.add( this.pnlGrille   , BorderLayout.CENTER);
		this.add( this.pnlAffichage, BorderLayout.SOUTH );
		
		//Activation des composants
		this.setFocusable(true);
		this.addKeyListener( this );
		this.setVisible( true );
	}
	
	public void majGrille()
	{
		this.pnlGrille.majGrille();
	}
	
	public void bougerFleche( char dir )
	{
		this.pnlFleche.bougerFleche( dir );
	}
	
	public void setTexteJoueur( char joueur )
	{
		this.pnlAffichage.setTexteJoueur( joueur );
	}
	
	public void setTexteErreur( String message )
	{
		this.pnlAffichage.setTexteErreur( message );
	}
	
	@Override
	public void keyPressed( KeyEvent e )
	{
		if (this.ctrl.estFini())
		{
			this.setVisible(false);
		}
		

		switch ( e.getKeyCode() )
		{
			case KeyEvent.VK_LEFT -> this.ctrl.bougerFleche('G');
			case KeyEvent.VK_RIGHT -> this.ctrl.bougerFleche('D');
		}
		
		if(this.ctrl.getTour() == 1 && e.getKeyCode() == KeyEvent.VK_DOWN)
			this.ctrl.ajouterPiece( this.pnlFleche.getColonne() );
		
		// Redessine la grille dans l'interface graphique
		this.majGrille();
	}
	
	@Override
	public void keyReleased(KeyEvent e)
	{
	}

	@Override
	public void keyTyped(KeyEvent e)
	{
	}
}

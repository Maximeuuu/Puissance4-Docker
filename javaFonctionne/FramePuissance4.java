import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.*;

public class FramePuissance4 extends JFrame implements KeyListener
{
	private PanelFleche pnlFleche;
	private PanelGrille pnlGrille;
	private PanelAffichage pnlAffichage;
	
	private Puissance4Client client;
	
	public FramePuissance4( Puissance4Client client )
	{
		this.client = client;
		this.setSize( 500, 500 );
		this.setTitle( "Puissance 4" );
		this.setLayout( new BorderLayout() );
		this.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
		
		//Création des composants
		this.pnlFleche    = new PanelFleche();
		this.pnlGrille    = new PanelGrille();
		this.pnlAffichage = new PanelAffichage();
		
		//Positionnement des composants
		this.add( this.pnlFleche   , BorderLayout.NORTH );
		this.add( this.pnlGrille   , BorderLayout.CENTER);
		this.add( this.pnlAffichage, BorderLayout.SOUTH );
		
		//Activation des composants
		this.setFocusable(true);
		this.addKeyListener( this );
		this.setVisible( true );
	}
	
	public void majGrille(int[][] board)
	{
		this.pnlGrille.majGrille(board);
	}
	
	public void bougerFleche( char dir )
	{
		this.pnlFleche.bougerFleche( dir );
	}
	
	public void setTexteJoueur( String message )
	{
		this.pnlAffichage.setTexteJoueur( message );
	}
	
	public void setTexteVictoire( String message )
	{
		this.pnlAffichage.setTexteVictoire( message );
	}
	
	@Override
	public void keyPressed( KeyEvent e )
	{
		/*if (this.ctrl.estFini())
		{
			this.setVisible(false);
		}*/
		

		switch ( e.getKeyCode() )
		{
			case KeyEvent.VK_LEFT  -> this.pnlFleche.bougerFleche('G');
			case KeyEvent.VK_RIGHT -> this.pnlFleche.bougerFleche('D');
			//case KeyEvent.VK_DOWN  -> this.client.envoyer( this.pnlFleche.getColonne() );
			case KeyEvent.VK_DOWN  -> this.client.setColonne( this.pnlFleche.getColonne() );
		}
		
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

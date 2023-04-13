import javax.swing.*;
import java.awt.GridLayout;

public class PanelAffichage extends JPanel
{
	private Controleur ctrl;
	private JLabel lblJoueur;
	private JLabel lblErreur;
	
	public PanelAffichage( Controleur ctrl )
	{
		this.ctrl = ctrl;
		
		this.setLayout( new GridLayout( 1, 2) );
		
		this.lblJoueur = new JLabel( "Début de la partie" );
		this.lblErreur = new JLabel( "" );
		
		this.add( lblJoueur );
		this.add( lblErreur );
	}
	
	public void setTexteJoueur( char joueur )
	{
		switch( joueur )
		{
			case Puissance4.JOUEUR1 -> this.lblJoueur.setText( "Tour du joueur Rouge" );
			case Puissance4.JOUEUR2 -> this.lblJoueur.setText( "Tour du joueur Jaune" );
			default                 -> this.lblErreur.setText( "Erreur" );
		}
	}
	
	public void setTexteErreur( String message )
	{
		this.lblErreur.setText( message );
	}
}

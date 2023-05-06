import javax.swing.*;
import java.awt.GridLayout;

public class PanelAffichage extends JPanel
{
	private JLabel lblJoueur;
	private JLabel lblErreur;
	
	public PanelAffichage()
	{
		this.setLayout( new GridLayout( 1, 2) );
		
		this.lblJoueur = new JLabel( "Début de la partie" );
		this.lblErreur = new JLabel( "" );
		
		this.add( lblJoueur );
		this.add( lblErreur );
	}
	
	public void setTexteJoueur( String message )
	{
		/*switch( joueur )
		{
			case Puissance4.JOUEUR1 -> this.lblJoueur.setText( "Tour du joueur Rouge" );
			case Puissance4.JOUEUR2 -> this.lblJoueur.setText( "Tour du joueur Jaune" );
			default                 -> this.lblErreur.setText( "Erreur" );
		}*/
		
		this.lblJoueur.setText( message );
	}
	
	public void setTexteVictoire( String message )
	{
		this.lblErreur.setText( message );
	}
}

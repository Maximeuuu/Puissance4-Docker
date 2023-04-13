import java.awt.GridLayout;
import javax.swing.*;

import java.awt.event.*;

public class PanelFleche extends JPanel implements ActionListener
{
	private Controleur ctrl;
	private JButton[]  tabButton;
	
	private ImageIcon iconVide;
	private ImageIcon iconFleche;
	
	public PanelFleche( Controleur ctrl )
	{
		this.ctrl = ctrl;
		
		this.iconVide   = new ImageIcon("./image/vide.gif");
		this.iconFleche = new ImageIcon("./image/fleche.gif");
		
		this.setLayout( new GridLayout(1, 7) );
		
		/*------------------------------*/
		/* Création des composants      */
		/*------------------------------*/
		
		this.tabButton = new JButton[7];
		
		for( int cpt = 0; cpt < tabButton.length; cpt++ )
			this.tabButton[cpt] = new JButton( this.iconVide );
		
		/*------------------------------*/
		/* Postionnement des composants */
		/*------------------------------*/
		
		for( int cpt = 0; cpt < tabButton.length; cpt++ )
			this.add( this.tabButton[cpt] );
		
		this.tabButton[0].setIcon( this.iconFleche );
		
		/*------------------------------*/
		/* Activation des composants    */
		/*------------------------------*/
		
		for (int cpt=0; cpt<this.tabButton.length; cpt++)
			this.tabButton[cpt].addActionListener(this);
	}
	
	
	public void bougerFleche( int col )
	{
		for( int cpt = 0; cpt < tabButton.length; cpt++ )
			this.tabButton[cpt].setIcon( this.iconVide );
		
		this.tabButton[col].setIcon( this.iconFleche );
	}
	
	public void bougerFleche( char dir )
	{
		int col = 0;
		for( int cpt = 0; cpt < tabButton.length; cpt++ )
			if( this.tabButton[cpt].getIcon() == this.iconFleche )
				col = cpt;
		
		if( dir == 'G' ) col--;
		if( dir == 'D' ) col++;
		
		if( col < 0 ) col = 6;
		if( col > 6 ) col = 0;
		
		this.bougerFleche( col );
	}
	
	
	public int getColonne()
	{
		for( int cpt = 0; cpt < tabButton.length; cpt++ )
			if( this.tabButton[cpt].getIcon() == this.iconFleche )
				return cpt;
		return -1;
	}
	
	
	public void actionPerformed ( ActionEvent e)
	{
		for( int cpt = 0; cpt < tabButton.length; cpt++ )
			if( e.getSource() == tabButton[cpt] )
				this.bougerFleche( cpt );
		
	}
	
}

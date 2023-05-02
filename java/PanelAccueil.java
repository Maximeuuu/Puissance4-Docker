import javax.swing.*;
import java.awt.event.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class PanelAccueil extends JPanel implements ActionListener
{
	private Controleur ctrl;
	
	private JTextField txtPseudo;
	private JTextField txtPortServ;
	private JTextField txtPortCli;
	private JTextField txtIpCli;
	
	private JButton btnCreer;
	private JButton btnRejoindre;
	
	public PanelAccueil( Controleur ctrl )
	{
		this.setLayout( new BorderLayout() );
		
		this.ctrl = ctrl;
		
		this.txtPseudo   = new JTextField("Joueur");
		this.txtPortServ = new JTextField("9000");
		this.txtPortCli  = new JTextField("9000");
		this.txtIpCli    = new JTextField();
		
		JPanel pnlMid = new JPanel();
		pnlMid.setLayout( new GridLayout( 5, 2, 10, 5) );
		
		this.btnCreer     = new JButton("Créer une Salle");
		this.btnRejoindre = new JButton("Rejoindre une Salle");
		
		this.btnCreer    .addActionListener(this);
		this.btnRejoindre.addActionListener(this);
		
		this.add( new JLabel("Pseudo : "), BorderLayout.NORTH );
		this.add( this.txtPseudo         , BorderLayout.NORTH );
		
		this.add( pnlMid, BorderLayout.CENTER );
		
		pnlMid.add( new JLabel ("Port de la Salle : ") );
		pnlMid.add( new JLabel ("Port de la Salle : ") );
		
		pnlMid.add( this.txtPortServ );
		pnlMid.add( this.txtPortCli );

		pnlMid.add( new JLabel (""));
		pnlMid.add( new JLabel ("IP du serveur : "));

		pnlMid.add( new JLabel (""));
		pnlMid.add( this.txtIpCli );

		pnlMid.add( this.btnCreer );
		pnlMid.add( this.btnRejoindre );
	}
	
	public void actionPerformed ( ActionEvent e)
	{
		if( e.getSource() == this.btnCreer )
		{
			this.ctrl.creerServeur ( Integer.parseInt ( txtPortServ.getText() )           );
			this.setVisible(false);
		}

		if( e.getSource() == this.btnRejoindre )
		{
			this.ctrl.creerClient  ( Integer.parseInt ( this.txtPortServ.getText() ), this.txtIpCli.getText()  );
			this.setVisible(false);
		}
	}
}

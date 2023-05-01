import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class PanelAccueil extends JPanel
{
	private JTextField txtPseudo;
	private JTextField txtPortServ;
	private JTextField txtPortCli;
	
	public PanelAccueil()
	{
		this.txtPseudo = new JTextField();
		
		this.add( this.txtPseudo );
	}
}

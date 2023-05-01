import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class Frame extends JFrame
{
	private Controleur ctrl;
	
	public Frame( Controleur ctrl )
	{
		this.ctrl = ctrl;
		
		PanelAccueil accueil;
		
		this.setTitle   ("Page Accueil");
		this.setSize    (400, 200);
		this.setLocation(20, 20);
		
		accueil = new PanelAccueil( this.ctrl );
		
		this.add(accueil);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

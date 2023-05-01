import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Color;

public class Frame extends JFrame
{
	public Frame()
	{
		PanelAccueil accueil;
		
		this.setTitle   ("Page Accueil");
		this.setSize    (400, 300);
		this.setLocation(20, 20);
		
		accueil = new PanelAccueil();
		
		this.add(accueil);
		
		this.setVisible(true);

	}
	
	
}

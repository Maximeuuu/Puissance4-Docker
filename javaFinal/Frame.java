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
		this.setSize    (400, 200);
		this.setLocation(20, 20);

		accueil = new PanelAccueil( this );

		this.add(accueil);

		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	public void cacherFrame()
	{
		this.setVisible(false);
	}

	public static void main(String[] args) {
        new Frame();
    }
}

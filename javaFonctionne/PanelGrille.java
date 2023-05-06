import javax.swing.*;
import java.awt.GridLayout;
import javax.swing.ImageIcon;

public class PanelGrille extends JPanel
{
	private JLabel[][]    tabLblCase;
	
	private static final int NB_LIG = 6;
	private static final int NB_COL = 7;

	public PanelGrille()
	{
		this.setLayout( new GridLayout( PanelGrille.NB_LIG, PanelGrille.NB_COL ) );
		
		this.tabLblCase = new JLabel[ PanelGrille.NB_LIG ][ PanelGrille.NB_COL ];

		for( int cptLig = 0; cptLig < PanelGrille.NB_LIG; cptLig++ )
		{
			for( int cptCol = 0; cptCol < PanelGrille.NB_COL; cptCol++ )
			{
				this.tabLblCase[cptLig][cptCol] = new JLabel( new ImageIcon("./image/vide.gif") );
				//this.tabLblCase[cptLig][cptCol].setOpaque(true);
				this.add( this.tabLblCase[cptLig][cptCol] );
			}
		}
		
		this.setVisible(true);
	}

	public void majGrille(int[][] board)
	{
		for( int cptLig = 0; cptLig < PanelGrille.NB_LIG; cptLig++ )
		{
			for( int cptCol = 0; cptCol < PanelGrille.NB_COL; cptCol++ )
			{
				String image;
				switch( board[cptLig][cptCol] )
				{
					case 1  -> image = "./image/rouge.gif";
					case 2  -> image = "./image/jaune.gif";
					case 0  -> image = "./image/vide.gif";
					default -> image = "./image/vide.gif" ;
				}
				
				this.tabLblCase[cptLig][cptCol].setIcon( new ImageIcon(image) );
			}
		}
	}
}

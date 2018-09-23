package UI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class UIMain extends JFrame{
	public static void startupUI() {
		
		System.out.println("startupUI");
		JFrame fenetre = new JFrame();
	    fenetre.setTitle("roBOTcop");
	    fenetre.setSize(400, 400);
	    fenetre.setLocationRelativeTo(null);
	    fenetre.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    JPanel pan = new JPanel();
		
	    pan.setBackground(Color.RED);        
	   
	    fenetre.setContentPane(pan);               

	    fenetre.setVisible(true);
	     
	}
	
	

	
}


package UI;

import java.awt.Color;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class fenetre extends JFrame{
	  public fenetre(){             
	    this.setTitle("Ma première fenêtre Java");
	    this.setSize(400, 100);
	    this.setLocationRelativeTo(null);               
	 
	    JPanel pan = new JPanel();
	
	    pan.setBackground(Color.ORANGE);        
	   
	    this.setContentPane(pan);               
	    this.setVisible(true);
	  }
}       

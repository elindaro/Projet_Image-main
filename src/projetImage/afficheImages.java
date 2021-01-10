package projetImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class afficheImages extends JPanel{
private BufferedImage Bb;

public BufferedImage getBi() {
	return Bb;
}

public void setBi(BufferedImage bb) {
	Bb = bb;
}
protected void paintComponent(Graphics g) {
	
	  g.drawImage(Bb,0,0,250,250,this);
}
}

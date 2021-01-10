package projetImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;
import javax.swing.text.IconView;


public class imageInterface extends JFrame implements ActionListener,MouseListener{
	private afficheImages afficherImage1=new  afficheImages();
	private afficherImage afficherImage2=new afficherImage();
	private JLabel l1;
	private JLabel l2;
   // private JLabel imageFond = new JLabel(new ImageIcon( "imageFond.jpg"));
	private File f=new File("mpeg7");
	private String[] images=f.list();
	private String f1=null;
    private ArrayList <BufferedImage> Images=new ArrayList<BufferedImage>();
    private JLabel label;
    private String s;
    private ArrayList <String> dist=new ArrayList <String>();


    public static void main(String[] args) {
    /* init + affichage de la fenêtre */
       new imageInterface().setVisible(true);
    }
  
 
    public imageInterface() {
    /* init de la fenêtre */	  
	 setTitle("interface image");
    setSize(300, 300);
    setLocationRelativeTo(null);
    //super();
    this.setBackground(Color.blue);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
     
    /* init du bouton qui va afficher le FileChooser */
    JButton button = new JButton("Sélectionner une Image");
    JPanel p = new JPanel();
    JPanel panel=new JPanel();
    JPanel panelG=new JPanel();
    JPanel p1=new JPanel();
    JPanel p2=new JPanel();
    label=new JLabel();
    l1=new JLabel();
    button.setFont(new java.awt.Font("Tahoma", 1, 15));
    /* ajout du listener qui prend en charge l'action sur le click */
    button.addActionListener(this); 
    panel.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Images", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16)));
    p.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Images Sélectionnées", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16)));
    l1.setFont(new java.awt.Font("Tahoma", 1, 14));
    p1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "  ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 16)));
    Container c=new Container();
    p.setPreferredSize (new Dimension(300, 600));
    p1.setPreferredSize (new Dimension(290, 300));
    p2.setPreferredSize (new Dimension(300, 300));
    panel.setPreferredSize (new Dimension(850, 600));
    BoxLayout box1=new BoxLayout(p,BoxLayout.Y_AXIS);
    BoxLayout box3=new BoxLayout(p2,BoxLayout.Y_AXIS);
    BoxLayout box2=new BoxLayout(panel,BoxLayout.Y_AXIS);
    p.setLayout(new BorderLayout());
    p1.setLayout(new BorderLayout());
    panel.setLayout(new BorderLayout());
    p.setLayout(box1);
    p2.setLayout(box3);
    panel.setLayout(box2);
    p.setBackground(new Color(255,213,200));
    p1.setBackground(new Color(255,213,200));
    p2.setBackground(new Color(255,213,200));
    panel.setBackground(new Color(200,200,200));
    GridLayout g=new GridLayout();
    p2.add(button);
    afficherImage1.addMouseListener(this);
    p2.add(afficherImage1);
    p1.add(l1,BorderLayout.NORTH);
    p.add(p2);
    p.add(p1);
    panel.add(afficherImage2);
    panelG.add(p);
    panelG.add(panel);
    panelG.setLayout(new BorderLayout());
    this.add(p,BorderLayout.WEST);
    this.getContentPane().add(panel,BorderLayout.EAST);
    this.add(panelG,BorderLayout.CENTER);
    this.setLayout(new FlowLayout());
    this.pack();
	this.setVisible(true);
  }
    
    
  
  public void actionPerformed(ActionEvent e) {
    /* init du filechooser */
	  JFileChooser fc = new JFileChooser("C:/Users/asmae el arrassi/eclipse-workspace/projetImage/mpeg7");
    /* affichage du dialog et test si le bouton ok est pressé */
    if (fc.showOpenDialog(this) == JFileChooser.APPROVE_OPTION)
      try {
        /* demande au système d'ouvrir le fichier précédemment séléctionné */
    	  
			    l1.setText("Chemin D'image :mpeg7/"+fc.getSelectedFile().getName());
			    BufferedImage Bb = null;
			    System.out.println(fc.getSelectedFile());
			    f1="mpeg7/"+fc.getSelectedFile().getName();
				Bb = ImageIO.read(new File("mpeg7/"+fc.getSelectedFile().getName()));
				afficherImage1.setBi(Bb);
				afficherImage1.repaint();
      //  Desktop.getDesktop().open(fc.getSelectedFile());
      } catch (IOException e1) {
        e1.printStackTrace();
      }
   
	
}

@Override
public void mouseClicked(MouseEvent arg0) {
	 ArrayList<ArrayList <String>> info=new ArrayList<ArrayList<String>>();
	    ComparerImages c= new ComparerImages();
	    info=c.ComparerImages(f1);
	    ArrayList <String> b=new ArrayList <String>();
		for(int s=0;s<info.size();s++) {
		  
		    BufferedImage Bb = null;
		    dist.add(info.get(s).get(1));
			try {
				Bb = ImageIO.read(new File(info.get(s).get(0)));
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		    Images.add(Bb);
		    b.add(info.get(s).get(1));
		  //  Float d=Float.parseFloat(info.get(s).get(1));
		    label.setText(info.get(s).get(1));
	}
		afficherImage2.setDist(dist);
		afficherImage2.setBi(Images);
		afficherImage2.repaint();
	
}

@Override
public void mouseEntered(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseReleased(MouseEvent arg0) {
	// TODO Auto-generated method stub
	
}
  }



 


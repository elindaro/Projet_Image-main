package projetImage;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class afficherImage extends JPanel{
	
	private ArrayList<BufferedImage> images=new ArrayList<BufferedImage>();
	public final static int width = 50, height = 50;
	private JLabel label ;
	ArrayList <String> dist=new ArrayList <String>();
	public afficherImage (ArrayList<BufferedImage> i){
		this.images=i;

		
	}
	public ArrayList<String> getDist() {
		return dist;
	}
	public void setDist(ArrayList<String> dist) {
		this.dist = dist;
	}
	public afficherImage (){

		
	}
	public ArrayList<BufferedImage>  getBi() {
		return images;
	}

	public void setBi(ArrayList<BufferedImage> i) {
		this.images = i;
	}
       @Override
       public void paintComponent(Graphics g){
           g.setColor(new Color(200,200,200));
           g.fillRect(0, 0, this.getWidth(), this.getHeight());
           int i = 0, j =0,cp=0;
           int avg=253;
           Font fonte = new Font("TimesRoman ",Font.BOLD,15);
           g.setFont(fonte);
          // String d=dist.get(0);
           for(int l=0;l<images.size();l++){
        	   /*BoxLayout box1=new BoxLayout(this,BoxLayout.Y_AXIS);
        	   label=new JLabel("hdlhjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjjj");
               this.setLayout(box1);*/
        	   System.out.println(images.size());
               if(i > (int)this.getWidth()/2-width){
                    i = 0;
                    cp=0;
                    j += height + 22;
                  //  label=new JLabel("")
                  //  this.add(label);
               }
               g.drawImage(images.get(l), i*2, j*2, width*2, height*2,null);
               g.drawString("d="+dist.get(l),cp,j*2+2*height+18);
               g.setColor(new Color(184, 32, 16)); 
               i += width + 5;
        	   cp+=2*width+10;
        	   g.drawString("d="+dist.get(0),0,2*height+18);
              // g.drawString(Integer.toString(avg), i+2*width, j+2*height);
               g.setColor(new Color(184, 32, 16));
             //  label=new JLabel("hdlhkkkkkkkkkkkkkkkkkkkkkkkkk");
             //  label.setLocation( i+2*width, j+2*height);
             //  this.setLayout(box1);
             //  this.add(label);
              // label=new JLabel("hdlhkkkkkkkkkkkkkkkkkkkkkkkkk");
              // label.setLocation( i+2*width, j+2*height);
             //  this.setLayout(box1);
               //this.add(label);
               
           } 
       //    g.drawString(d,0,2*height+22);
       }
	

}

package projetImage;

import java.awt.image.BufferedImage;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import java.lang.Math.*;
import java.lang.Math; 

public class testImage {
	File f=new File("mpeg7");
	String[] images=f.list();
    File file = new File("infoImage.txt");
	int cpt2;
    int [][] indexPointContour;
    String content;
	public testImage() {
		
	try {
		int [] indexPoint;
		int [][] pointContour;
		int n=18;
		int a=1;
	     int[] R;
		 int[] G;
		 int[] B;
	     R=new int[2];
		 G=new int[2];
		 B=new int[2];
		 R[0]=-1;
		 G[0]=-1;
		 B[0]=-1;
		 file.createNewFile();
		 FileWriter fw = new FileWriter(file.getAbsoluteFile());
		 BufferedWriter bw = new BufferedWriter(fw);
		 for(int u=0;u<3;u++) {
		   int cpt=1;	   
	       BufferedImage image=ImageIO.read(new File("mpeg7/"+images[u]));
	       pointContour=new int[(image.getHeight())*n][2];
			for(int j=0;j<(((image.getHeight())*n));j++) {
					pointContour[j][0]=-1;
				    pointContour[j][1]=-1;
				}	
			pointContour[0][0]=-1;
		    pointContour[0][1]=-1;
			 for(int i=0;i<image.getHeight();i++) {
				 for(int l=0;l<image.getWidth();l++) {
					 
			         int clr=  image.getRGB(l,i); 
			     
			         R[1]   = (clr & 0x00ff0000) >> 16;
			         G[1]   = (clr & 0x0000ff00) >> 8;
			         B[1]   =  clr & 0x000000ff;
			        
			        if((R[0]!=R[1])&&(G[0]!=G[1])&&(B[0]!=B[1])) {
			        	if(R[0]==255){
			        	    pointContour[a][0]=i;
			        	    pointContour[a][1]=l;
			        	}
			        	if(R[0]==0){
			        		pointContour[a][0]=i;
			        	    pointContour[a][1]=l-1;
			        	}
			        	if((pointContour[a][1]!=pointContour[a-1][1])||(pointContour[a][0]!=pointContour[a-1][0])) 
			        	        a++;
			        	
			        }
			        
			        R[0]=R[1];
			        G[0]=G[1];
			        B[0]=B[1];
            }               
           }
			 

			 indexPoint=new int[26];
			 int d=(int)((pointContour.length-1)/24);
			 Random rand = new Random() ;
			 int choix = 1+rand.nextInt(d-1) ;
			 int t=choix;
			 int q=1;
			 indexPoint[0]=0;
			 indexPoint[25]=pointContour.length-1;
			 while((t<pointContour.length-1)&&(q<25)) {
			      indexPoint[q]=pointContour[t][0];
			      t=t+d;
			      q++;
			      
			 }
			 
			 content ="mpeg7/"+images[u]+"=";
			 bw.write(content);
			 cpt2=0;
			 for(int g=0;g<pointContour.length;g++) {
				 for(int h=0;h<26;h++) {
					if(pointContour[g][0]==indexPoint[h]) {
						cpt2++;
					}
				 }
			 }
			
			 indexPointContour=new int [cpt2+1][2];
			
			 int z=0;
			 for(int g=0;g<pointContour.length;g++) {
				 for(int h=0;h<26;h++) {
					if(pointContour[g][0]==indexPoint[h]) {
						indexPointContour[z][0]=pointContour[g][0];
						indexPointContour[z][1]=pointContour[g][1];
						z++;
					}
				 }
			 }
			 if(image.getWidth()>image.getHeight())
			    definirCercle(image, bw,image.getWidth());
			 else
				definirCercle(image, bw,image.getHeight()); 
			
			
		}
		 bw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
}
	public int[][] definirContour(BufferedImage image){
		 int a=1;
		 int n=18;
		 int [][] pointContour;
	     int[] R;
		 int[] G;
		 int[] B;
	     R=new int[2];
		 G=new int[2];
		 B=new int[2];
		 R[0]=-1;
		 G[0]=-1;
		 B[0]=-1;
		 pointContour=new int[(image.getHeight())*n][2];
			for(int j=0;j<(((image.getHeight())*n));j++) {
				pointContour[j][0]=-1;
			    pointContour[j][1]=-1;
			}	
		pointContour[0][0]=-1;
	    pointContour[0][1]=-1;
		 for(int i=0;i<image.getHeight();i++) {
			 for(int l=0;l<image.getWidth();l++) {
				 
		         int clr=  image.getRGB(l,i); 
		     
		         R[1]   = (clr & 0x00ff0000) >> 16;
		         G[1]   = (clr & 0x0000ff00) >> 8;
		         B[1]   =  clr & 0x000000ff;
		        
		        if((R[0]!=R[1])&&(G[0]!=G[1])&&(B[0]!=B[1])) {
		        	if(R[0]==255){
		        	    pointContour[a][0]=i;
		        	    pointContour[a][1]=l;
		        	//a++;
		        	}
		        	if(R[0]==0){
		        		pointContour[a][0]=i;
		        	    pointContour[a][1]=l-1;
		        	//a++;
		        	}
		        	if((pointContour[a][1]!=pointContour[a-1][1])||(pointContour[a][0]!=pointContour[a-1][0])) 
		        	        a++;
		        	
		        }
		        
		        R[0]=R[1];
		        G[0]=G[1];
		        B[0]=B[1];
        }               
       }
		return pointContour ;
	}
	public void definirCercle(BufferedImage image,BufferedWriter bw,int R) {
		 try {
			 double height=image.getHeight();
				double width=image.getWidth();
				double[] Cercles;
				double r;
				int s=0;
				Cercles=new double[8];
				double Ci=width/2;
				double Cj=height/2;
				Cercles[0]=Ci;
				Cercles[1]=Cj;
			    r=R/5;
		    	Cercles[2]=0;
				Cercles[3]=r;
				Cercles[4]=2*r;
				Cercles[5]=3*r;
				Cercles[6]=4*r;
				Cercles[7]=5*r;
				double r1;
				double pi=Math.PI;
				double teta;
				r1=Cercles[2];
				double b;
				int arc=1;
				    
				for(int w=2;w<7;w++) {
					teta=pi/6;
					 content="/"+ arc +":";
					 bw.write(content);
				      int x=(int) (Cercles[0]+Cercles[w+1]);
				      for(int i=(int)(Cercles[0]+Cercles[w]);i<x;i++)	{
					      b=Cercles[1]-(teta*(i-Cercles[0]));
					      for(int j=(int)b;j<Cercles[1];j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+indexPointContour[k][0]+","+indexPointContour[k][1]+"";						      
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							    }
						  }	
					 }
				 }
				      arc++;
				      
			}
				
				
				for(int w=2;w<9;w++) {
					teta=pi/3;
				    content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]+Cercles[w+1]);
				      for(int i=(int)(Cercles[0]+Cercles[w]);i<x;i++)	{
					      b=Cercles[1]-teta*(i-Cercles[0]);
					      for(int j=(int)b;j<(Cercles[1]-((pi/6)*(i-Cercles[0])));j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/2;
				    content= "/"+ arc+":";
					bw.write(content);
				     int y=(int) (Cercles[1]-Cercles[w+1]);
				   	for(int j=(int)y;j<(Cercles[1]-Cercles[w]);j++){
					      b=Cercles[0]+(pi/6)*(Cercles[1]-j);
					      for(int i=(int)(Cercles[0]);i<b;i++)  {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							      System.out.println(content);
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/6;
				    content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]-Cercles[w]);
				      for(int i=(int)(Cercles[0]-Cercles[w+1]);i<x;i++)	{
					      b=Cercles[1]-teta*(Cercles[0]-i);
					      for(int j=(int)b;j<Cercles[1];j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							     
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/3;
				    content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]-Cercles[w]);
				      for(int i=(int)(Cercles[0]-Cercles[w+1]);i<x;i++)	{
					      b=Cercles[1]-(teta*(Cercles[0])-i);
					      for(int j=(int)b;j<(Cercles[1]-((pi/6)*(Cercles[0]-i)));j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/2;
				    content= "/"+ arc+":";
					bw.write(content);
				     int y=(int) (Cercles[1]-Cercles[w+1]);
				   	for(int j=(int)y;j<(Cercles[1]-Cercles[w]);j++){
				   		      
					      b=Cercles[0]-((pi/6)*(Cercles[1]-j));
					      for(int i=(int)b;i<(Cercles[0]);i++)  {
					    	 
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;

							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/6;
				    content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]+Cercles[w+1]);
				      for(int i=(int)(Cercles[0]+Cercles[w]);i<x;i++)	{
					      b=Cercles[1]+(teta*(i-Cercles[0]));
					      for(int j=(int)(Cercles[1]);j<b;j++) {
					    	  //System.out.println(i+":"+j);
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";						      
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							    }
						  }	
					 }
				 }
				      arc++;
			}
		    	 // System.out.println("----------------");
				for(int w=2;w<9;w++) {
					teta=pi/3;
					content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]+Cercles[w+1]);
				      for(int i=(int)(Cercles[0]+Cercles[w]);i<x;i++)	{
					      b=Cercles[1]+teta*(i-Cercles[0]);
					      for(int j=(int)(Cercles[1]+((pi/6)*(i-Cercles[0])));j<b;j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/2;
					content= "/"+ arc+":";
					bw.write(content);
				     int y=(int) (Cercles[1]+Cercles[w+1]);
				   	for(int j=(int)(Cercles[1]+Cercles[w]);j<y;j++){
					      b=Cercles[0]+(pi/6)*(j-Cercles[1]);
					      for(int i=(int)(Cercles[0]);i<b;i++)  {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							      System.out.println(content);
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/6;
					content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]-Cercles[w]);
				      for(int i=(int)(Cercles[0]-Cercles[w+1]);i<=x;i++)	{
					      b=Cercles[1]+teta*(Cercles[0]-i);
					      for(int j=(int)Cercles[1];j<b;j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							     
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/3;
					content= "/"+ arc+":";
					bw.write(content);
				      int x=(int) (Cercles[0]-Cercles[w]);
				      for(int i=(int)(Cercles[0]-Cercles[w+1]);i<x;i++)	{
					      b=Cercles[1]+teta*(Cercles[0]-i);
					      for(int j=(int)(Cercles[1]+((pi/6)*(Cercles[0]-i)));j<b;j++) {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;
							    }
						  }	
					 }
				 }
				      arc++;
			}
				for(int w=2;w<9;w++) {
					teta=pi/2;
					content= "/"+ arc+":";
					bw.write(content);
				     int y=(int) (Cercles[1]+Cercles[w+1]);
				   	for(int j=(int)((Cercles[1]+Cercles[w]));j<y;j++){
				   		      
					      b=Cercles[0]-(pi/6)*(j-Cercles[1]);
					      for(int i=(int)b;i<(Cercles[0]);i++)  {
						     for(int k=0;k<cpt2+1;k++) {
							   if((indexPointContour[k][0]==i)&&(indexPointContour[k][1]==j)) {
							      content=""+	indexPointContour[k][0]+","+indexPointContour[k][1]+"";
							      bw.write(content);
							      content="!";
							      bw.write(content);
							      s++;

							    }
						  }	
					 }
				 }
				      arc++;
			}
				
				System.out.println(arc+"arc"+s+"ld"+cpt2);  
				content ="="+s;
				bw.write(content);
				content="\n";
				 bw.write(content);
				 cpt2=0;
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void main(String[]args) {
		new testImage();
	}
}

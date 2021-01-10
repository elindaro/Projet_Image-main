package projetImage;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

public class ComparerImages {
    ArrayList<ArrayList<Integer>> point=new  ArrayList<ArrayList<Integer>>();
   	File f=new File("mpeg7");
	String[] images=f.list();
    String imagepath1="mpeg7/"+images[0];
    int[][] point1;
    String[] mots =null;
    
    ArrayList<ArrayList<String>> ComparerImages(String imagepath1) {
	
		    ArrayList<Float> dt=new  ArrayList<Float>();
		    ArrayList<String> dt1=new  ArrayList<String>();
		    ArrayList<ArrayList<String>> dt2=new ArrayList<ArrayList<String>>();
		      int cpp1=0;
			try {
			  String imagepath2="mpeg7/"+images[2];
			  File fichier =new File("C:\\Users\\asmae el arrassi\\eclipse-workspace\\projetImage\\infoImage.txt"); 
		      //Création du FileReader 
		      FileReader fileR = new FileReader(fichier);
		      //Création du BufferedReader (voir doc)
		      BufferedReader br = new BufferedReader(fileR);
		      //Initilisation de l'objet String utilisé pour la lecture
		      String s;
		      //Initilisation du tableau des mots
		      String[]chaine=null;
		      int length=0;
		    
		      while((s=br.readLine())!=null)
		      {
		         //Split des mots
		    	 
		         mots=s.split("=",3);
		         length = Integer.parseInt(mots[2]);
		         if(mots[0].contains(imagepath1)) {
		        	chaine=mots[1].split("/") ;
		            point=trouverPImage(chaine);  
		         }
		        
		      }
		      br.close();
		      FileReader fileR1 = new FileReader(fichier);
		      BufferedReader bb = new BufferedReader(fileR1);
		      while((s=bb.readLine())!=null)
		      {
		         //Split des mots par espace
		    	 
		         mots=s.split("=",3);
		         length = Integer.parseInt(mots[2]);
		         if(!mots[0].contentEquals(imagepath1)) {
			        	chaine=mots[1].split("/") ;
			        	dt1.add(mots[0]);
			            dt.add(trouverDistance(chaine,length));
			         }
		      }
		      
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			cpp1=0;
		     for(int h=0;h<dt.size();h++) {
		    	  if(dt.get(h)<20) {
		    		  dt2.add(new ArrayList<String>());
		    		  String s=Float.toString(dt.get(h));
		    		  dt2.get(cpp1).add(dt1.get(h));
		    		  dt2.get(cpp1).add(s);
		    		 System.out.println(dt2.get(cpp1)+"  "+dt.get(h));
		    		  cpp1++;
		    	  }
		      }
		  return(dt2);    
		
	}
	public ArrayList<ArrayList<Integer>> trouverPImage(String[] chaine) {
		 String[] liste;
	      String[]chaine2=null;
	      String[]chaine1=null;
	      int cpt=0;
	         //Parcours des mots un à un
	        for(int i=1;i<chaine.length;i++) {
	           liste=chaine[i].split(":") ;
	           if(liste.length==2 ) {
	        	   chaine2=liste[1].split("!");
	        	   for(int h=0;h<chaine2.length;h++) {
	        		   chaine1=chaine2[h].split(",");
	        		   int result1 = Integer.parseInt(chaine1[0]);
	        		   int result2 = Integer.parseInt(chaine1[1]);
	        		   int result3 = Integer.parseInt(liste[0]);
	        		   point.add(new ArrayList<Integer>());
		        	   point.get(cpt).add(result3);
	        		   point.get(cpt).add(result1);
	        		   point.get(cpt).add(result2);
		        	   cpt++;
	        	   }
	           }
	        }
	        return(point);
	}
	public float trouverDistance (String[] chaine,int length1) {
	      int cpt1=0;
	      String[] liste;
	      String[]chaine2=null;
	      String[]chaine1=null;
	      int cpp=0;
	      int cpt2=0;
	      int cpt3=0;
	      int cpt=0;
	      int compteur=0;
	      float dist=0;
	      ArrayList<ArrayList<Float>> distance=new  ArrayList<ArrayList<Float>>();
	      ArrayList<ArrayList<Float>> distanceTotal=new  ArrayList<ArrayList<Float>>();
		
        	point1=new int[length1][3];
        	cpt1=0;
	         //Parcours des mots un à un
	        for(int i=1;i<chaine.length;i++) {
	           liste=chaine[i].split(":") ;
	           if(liste.length==2) {
	        	   chaine2=liste[1].split("!");
	        	   for(int h=0;h<chaine2.length;h++) {
	        		   chaine1=chaine2[h].split(",");
	        		   int result1 = Integer.parseInt(chaine1[0]);
	        		   int result2 = Integer.parseInt(chaine1[1]);
	        		   int result3 = Integer.parseInt(liste[0]);
	        		   point1[cpt1][0]=result3;
		        	   point1[cpt1][1]=result1;
		        	   point1[cpt1][2]=result2;
		        	   cpt1++;
	        	   }
	           }
	        }           
	        	        		
	        			int k=0;	
	        			cpt2=0;
	        			int cpt5=0;
	        			int cpt4=0;
	        		    ArrayList<ArrayList<Float>> pointComp=new  ArrayList<ArrayList<Float>>();
	        			for(int j=0;j<point.size();j++) {	
	        			      ArrayList<ArrayList<Float>> dis=new  ArrayList<ArrayList<Float>>();
	        			      cpt3=0;
	        			for(int l=0;l<cpt1;l++) {
	        				if ((cpt3>0) && (point.get(j).get(0)==point1[l][0])) {
	        					for(int z=0;z<pointComp.size();z++) {
	        						if( (pointComp.get(z).get(0)!=point1[l][1]) && (pointComp.get(z).get(1)!=point1[l][2])) {
	       	        				dis.add(new ArrayList<Float>());
	      	        	            dis.get(cpt3).add((float)Math.sqrt((point1[l][1]-point.get(j).get(1))*(point1[l][1]-point.get(j).get(1)) + (point1[l][2]-point.get(j).get(2))*(point1[l][2]-point.get(j).get(2))));
	      	        	            dis.get(cpt3).add((float)point1[l][1]);
	      	        	            dis.get(cpt3).add((float)point1[l][2]);
	      	        		          k++;
	      	        		          cpt3++;
	      	        			    }
	        					}
	        				}
	        			    if(point.get(j).get(0)==point1[l][0]&& cpt4==0) {
	        				
	        			    	dis.add(new ArrayList<Float>());
      	        	            dis.get(cpt3).add((float)Math.sqrt((point1[l][1]-point.get(j).get(1))*(point1[l][1]-point.get(j).get(1)) + (point1[l][2]-point.get(j).get(2))*(point1[l][2]-point.get(j).get(2))));
      	        	            dis.get(cpt3).add((float)point1[l][1]);
      	        	            dis.get(cpt3).add((float)point1[l][2]);	   
	        		            cpt4++;
	        		            k++;
	        		            cpt3++;
	        			    }  
	        			}
	        			if(k==0) {
	        				dis.add(new ArrayList<Float>());
	        				dis.get(0).add((float)Math.sqrt((point.get(j).get(1))*(point.get(j).get(1)) + (point.get(j).get(2))*(point.get(j).get(2))));
	        				dis.get(0).add(-1f);
	        				dis.get(0).add(-1f);
	        			}
	        			float min=2000;
	        			int t=0;
	        			int i=-1;
	        			while(t<dis.size()) {
	        				
	        				if(dis.get(t).get(0)< min) {
	        					min=dis.get(t).get(0);
	        					i=t;
	        				}	
	        				t++;
	        			}
	        			  if(i!=-1) {
	        			  pointComp.add(new ArrayList<Float>());
        				  pointComp.get(cpt5).add(dis.get(i).get(1));
        				  pointComp.get(cpt5).add(dis.get(i).get(2));
        				  cpt5++;
	        			  }
	        			  
	        			distance.add(new ArrayList<Float>());
	        			distance.get(cpt2).add((float)point.get(j).get(0));
	        			distance.get(cpt2).add(min);
        				cpt2++;
        				k=0;
        				cpt4=0;
	        			}
	        			cpp=0;
	        			compteur=0;
	        			for(int r=0;r<distance.size();r++) {
	        				for(int o=0;o<distance.size();o++) {
	        					if(distance.get(r).get(0)==distance.get(o).get(0)&&distance.get(r).get(1)!=2000) {
	        						dist+=distance.get(o).get(1);
	        						compteur++;
	        					}
		        				
		        			}
	        				if (compteur!=0) {
	        				distanceTotal.add(new ArrayList<Float>());
	        				distanceTotal.get(cpp).add(distance.get(r).get(0));
	        				distanceTotal.get(cpp).add(dist/compteur);
	        				cpp++;
	        				}
	        				dist=0;
	        				compteur=0;
	        			}

	       float d=0;
	       for(int h=0;h<distanceTotal.size();h++) {
	        	d+=distanceTotal.get(h).get(1);	
		      }
	        d=d/distanceTotal.size();
	        return(d);
	      }
	

}

package Ecran;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import javax.imageio.ImageIO;


public class Image {
	
	
	/********************************************/
	
	/* Attributs de la classe Image */
	
	/********************************************/
 
	
	// Tableau qui contient les pixels de l'image
	private int [] pixels;
	
	// Attributs qui contiennent la hauteur et la largeur de l'image
	private int width, height;
	
	
	/********************************************/
	
	/* Constructeur de la classe Image */
	
	/********************************************/
	public Image (BufferedImage im){
		this.pixels = im.getRGB(0, 0, im.getWidth(), im.getHeight(), null, 0,im.getWidth());
		this.width = im.getWidth();
		this.height= im.getHeight();
	}
	


	/** 
	 * Méthode qui renvoie un pixel de l'image
	 * @param numéro du pixel à renvoyer
	 * @return le pixel i du tableau
	 */
	public int getPixel (int i){
		return this.pixels[i];
	}
	
	/**
	 * Méthode qui renvoie le tableau des pixels d'une image
	 * @return tableau de pixels représentant l'image
	 */
	public  int[] getPixels (){
		return this.pixels;
	}

	
	/**
	 * Méthode qui renvoie vrai si l'image à partir du pixel n est égale à l'image passée en paramètre
	 * @param Image à trouver dans l'image this
	 * @param pixel à partir du quel on compare les images
	 * @return vrai si l'image est la même, faux sinon
	 */
	public  boolean testepixel (Image toID, int n ){
		int cpt = 0;
		int init = n ;
		for (int i = 0 ; i< toID.pixels.length ; i++){
			if (cpt == toID.width && n+this.width< this.pixels.length){
				cpt = 0;
				n = init + this.width;
				init = init + this.width;
			}
			if (toID.getPixel(i)!=this.getPixel(n)) return false;
			n++;
			cpt ++;
		}
		return true;
	}

	/**
	 * Méthode qui cherche une image dans une image
	 * @param Image à trouver dans l'image this
	 * @return le premier pixel de l'image trouvé et si on ne trouve pas l'image, renvoie -1
	 */
	public int trouveDansImage(Image toID){
		for (int i = 0 ; i < this.pixels.length; i++){
			if (this.pixels[i]==toID.getPixel(0)){
				boolean res = this.testepixel(toID, i);
				if (res) return i;
			}
		}

		return -1;
	}
	
	/**
	 * Méthode qui renvoie vrai si deux images sont égales
	 * @param Image à comparer à l'image this
	 * @return vrai si les deux images sont égales, faux sinon
	 */
	public boolean equals (Image im){
		return (Arrays.equals(this.getPixels(), im.getPixels()));
	}

	/**
	 * Calcule la position du coin gauche supérier de la fenêtre de jeu
	 * @param pixel à partir du quel on a trouvé le coin droit supérieur de la fenêtre de jeu
	 * @param image que l'on a cherché dans le screenshot
	 * @param screenshot 
	 * @return un tableau représentant les coordonées (x,y) du coin supérieur gauche de la fenêtre de jeu
	 */
	public static int[]  computeImageCorner (int px, Image toID, Image screenshot, int taille){
		int [] coin = new int [2];
		coin[1] = px/screenshot.width;
		coin[0] = px%screenshot.width;
		if (coin[0]-1!=0) coin[1]=coin[1]+1;
		coin[0] += toID.width - taille;
		return coin;
	}
		
	/** 
	 * M�thode qui cr�e une Image � partir d'un fichier enregistr�.
	 * @param chemin pour acc�der � l'image
	 * @return une Image cr�e � partir du fichier.
	 */
	public static Image readIm (String path) {
		try {
			return new Image (ImageIO.read(new File(path)));
		}
		catch (IOException e){
			return null;
		}

	}


}
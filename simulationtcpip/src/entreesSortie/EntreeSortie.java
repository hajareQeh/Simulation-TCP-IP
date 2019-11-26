package entreesSortie;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import interconnexion.Graph;


public class EntreeSortie {


	private ObjectOutputStream ofW;
	// Définit un Objet fW de type ObjectOutpustream Utilisé pour écrire
	private ObjectInputStream ofR;
	// Définit un Objet fW de type ObjectInputstream Utilisé pour lire
	private char mode;
 
	// De façon générale, les traitements sur fichier se déroulent en 3 temps :
	//				1-Ouverture du flux
	//				2-Traitement des données
	//				3-Fermeture du flux.
 
 
	// 1 - Ouverture Du flux.
 
 
	public void ouvrir(String s, String filename) throws IOException {
 
		mode = (s.toUpperCase()).charAt(0);
		if((mode == 'R' || mode == 'L') && (filename != null)){
			ofR = new ObjectInputStream (new FileInputStream(filename));
		}
		else if((mode == 'W' || mode == 'E')&& (filename != null)){
			ofW = new ObjectOutputStream (new FileOutputStream(filename));
		}
 
	}
 
	// 2 - Traitement des données (du fichier).
	// Pour l'écriture
	public void ecrire (Graph tmp) throws IOException{
		if (tmp != null) {
			
			ofW.writeObject(tmp);
			ofW.flush(); //Vide le tempon
		}
	}
	// Pour la lecture
	public Graph lire() throws IOException, ClassNotFoundException {
		Graph tmp = (Graph) ofR.readObject();
		return tmp;
	}
 
	// 3 - Fermeture du flux.
	public void fermer() throws IOException{
		if(mode == 'R' || mode =='L') ofR.close();
		else if(mode == 'W' || mode == 'E') ofW.close();
	}

}

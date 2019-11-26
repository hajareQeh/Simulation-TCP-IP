package tcpip;


//Packages à importer afin d'utiliser les objets
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.util.*;

import interconnexion.Node;

public class Application implements Serializable {
	
	private String url;
	private List<Byte> messageB=null;
	private File fichierRecu;
	private Node dis;
	private Transport tcp;
	Byte[] ytes;
	byte[] l;
	//private Interconnexion interconnexion;
	
	public Application(String url,Node dis) {
		this.dis=dis;
		this.url = url;
		convertir();

		//System.out.println("coté sender : "+getMessageB());
		envoyerTcp();
		
	}

	public void clean() {
		this.messageB.clear();
	}
	public void convertir() {
		// Nous déclarons nos objets en dehors du bloc try/catch
		FileInputStream fis = null;
		messageB=new ArrayList<Byte>();
		try {
			// On instancie nos objets :
			// fis va lire le fichier
			fis = new FileInputStream(new File(this.url));

			// On crée un tableau de byte pour indiquer le nombre de bytes lus à
			// chaque tour de boucle
			byte[] buf = new byte[13];

			// On crée une variable de type int pour y affecter le résultat de
			// la lecture
			// Vaut -1 quand c'est fini
			int n = 0;

			// Tant que l'affectation dans la variable est possible, on boucle
			// Lorsque la lecture du fichier est terminée l'affectation n'est
			// plus possible !
			// On sort donc de la boucle
			
			while ((n = fis.read(buf)) >= 0) {
				// On écrit dans notre deuxième fichier avec l'objet adéquat
				// On affiche ce qu'a lu notre boucle au format byte et au
				for (byte bit : buf) {
					
					 messageB.add(bit);
				}
				//Nous réinitialisons le buffer à vide
	            //au cas où les derniers byte lus ne soient pas un multiple de 8
	            //Ceci permet d'avoir un buffer vierge à chaque lecture et ne pas avoir de doublon en fin de fichier
	            buf = new byte[13];
	         
			}
		}
		catch (FileNotFoundException e) {
	         // Cette exception est levée si l'objet FileInputStream ne trouve
	         // aucun fichier
	         e.printStackTrace();
	      } catch (IOException e) {
	         // Celle-ci se produit lors d'une erreur d'écriture ou de lecture
	         e.printStackTrace();
	      } finally {
	         // On ferme nos flux de données dans un bloc finally pour s'assurer
	         // que ces instructions seront exécutées dans tous les cas même si
	         // une exception est levée !
	         try {
	            if (fis != null)
	               fis.close();
	         } catch (IOException e) {
	            e.printStackTrace();
	         }}
		}

	public void envoyerTcp(){
		tcp=new Transport(messageB,dis);
		
	}
	
	public Transport getTcp() {
		return tcp;
	}

	public void setTcp(Transport tcp) {
		this.tcp = tcp;
	}

	
	public Application(List<Byte>messageB) {

		
		this.messageB.addAll(messageB);
	}

	public Application() {
		this.messageB=new ArrayList<Byte>();
		// TODO Auto-generated constructor stub
	}

	

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<Byte> getMessageB() {
		return messageB;
	}

	public void setMessageB(List<Byte> messageB) {
		this.messageB = messageB;
	}
	
	public String convertioninverse() {
		ytes =messageB.toArray(new Byte[messageB.size()]);
		int j=0;
		l = new byte[ ytes.length];
		for(Byte b: ytes) {
			if(b!=null) {
		 l[j++] = b.byteValue(); }}
		String str = new String(l, StandardCharsets.UTF_8);
		return str;
	}
	
}
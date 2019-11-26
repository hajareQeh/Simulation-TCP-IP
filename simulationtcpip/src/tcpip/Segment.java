package tcpip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Segment implements Serializable {
	private int numPortSource; // numéro de port source 
	private int numPortDestination;//numéro de port destination
	private int numSeq; //numero de séquence de chaque segment 
	private List<Byte> Donnee; //donnée on octets
	//private boollean cnx; //variable utiliser on cas de demande de ouverture ou fermeture de connexion
	public Segment(int numPortSource, int numPortDestination, int numSeq, List<Byte> donnee) {
		Donnee = new ArrayList<Byte>();
		this.numPortSource = numPortSource;
		this.numPortDestination = numPortDestination;
		this.numSeq = numSeq;
		Donnee = donnee;
	}
	
	public Segment(int syn) {
		this.numSeq=syn; 
	}

	public int getNumPortSource() {
		return numPortSource;
	}
	public void setNumPortSource(int numPortSource) {
		this.numPortSource = numPortSource;
	}
	public int getNumPortDestination() {
		return numPortDestination;
	}
	public void setNumPortDestination(int numPortDestination) {
		this.numPortDestination = numPortDestination;
	}
	public int getNumSeq() {
		return numSeq;
	}
	public void setNumSeq(int numSeq) {
		this.numSeq = numSeq;
	}
	public List<Byte> getDonnee() {
		return Donnee;
	}
	public void setDonnee(List<Byte> donnee) {
		Donnee = donnee;
	}

	//boolean ack=false ; acquittement de la réception 
	//public Segment(boolean cnx); //constructeur  pour changer la valeur de cnx(cas de la demande de connexion ouverture/fermetur)
	 

}
package tcpip;

import java.io.Serializable;
import java.util.*;

import interconnexion.Node;

public class Liaison implements Serializable{
	private List<Paquet> paquets=new ArrayList<Paquet>();//paquets reÃ§us de la classe Internet avec la mÃ©thode envoyerLiaison()
	private ArrayList<Trame> trames;
	private Internet internet;//objet utilisé pour envoyer les paquets à  la couche superieur lors de la reception
	private Node interconnexion;//objet utilisÃ© pour envoyer les trames à  la couche liaison de la machine destination
	private Node dis;

	Liaison(List<Paquet> paquets,Node dis){
		this.dis=dis;
		this.paquets = paquets;
		TransformerPaquet();
		envoyerLiaison();

	}
	public Liaison(ArrayList<Trame> trames){
		this.trames = trames;

		transformerTrame();
		envoyerIP();
	}
	public Liaison() {
		// TODO Auto-generated constructor stub
	}
	public void transformerTrame() {
		for (Trame trame : trames) {
			paquets.add(trame.getPaquet());
		}

	}
	public void TransformerPaquet() {
		trames=new ArrayList<Trame>();
		for (Paquet paquet : paquets) {

			trames.add(new Trame("155", "564", paquet));
		}
	}
	public void envoyerIP() {
		this.internet = new Internet(paquets);
	}
	public List<Paquet> getPaquets() {
		return paquets;
	}
	public void setPaquets(List<Paquet> paquets) {
		this.paquets = paquets;
	}
	public ArrayList<Trame> getTrames() {
		return trames;
	}
	public void setTrames(ArrayList<Trame> trames) {
		this.trames = trames;
	}
	public Internet getInternet() {
		return internet;
	}
	public void setInternet(Internet internet) {
		this.internet = internet;
	}
	public void envoyerLiaison() {
		dis.liaison(trames);
	}
	private Node getObjetInterconnexion(String adresse_mac) {
		// TODO Auto-generated method stub
		return null;
	}
 }

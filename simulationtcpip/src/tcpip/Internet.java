package tcpip;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import interconnexion.Node;

public class Internet implements Serializable {
	private ArrayList<Segment> segments=new ArrayList<Segment>();// segments reçus de la classe Transport avec la méthode envoyerTp()
	private List<Paquet> paquets; /*
	 								 * paquets extraits des segments ou envoyés de la couche inferieure dans le cas
									 * d'une reception
									 */
	private Liaison liaison; // objet utilisé pour envoyer les paquets à la couche inférieure lors de l'envoi
	private Transport transport; // objet utilisé pour envoyer segments à la couche superieure lors de la
									// reception
	/*
	 * constructeur qui initialise segments (utilisé dans la méthode envoyerIp() de
	 * la classe Transport)
	 */
	private static Node dis;

	public Internet(ArrayList<Segment> segments) {
		this.segments = segments;
	}

	public Internet(Segment segment,Node dis) {
		this.dis=dis;
		this.segments.add(segment);
		paquetage();
		envoyerLiaison();
		}
	/*
	 * constructeur qui initialise paquets (utilisé dans la méthode envoyerIp() de
	 * la classe Liaison)
	 */
	public Internet(List<Paquet> paquets) {
		this.paquets = paquets;
		paquetageInverse();
		envoyerTcp();
	}



	public Internet() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * extraire la liste des paquets des segments envoyés de la couche Transport
	 * pour les envoyer à Liaison et met à jour la liste des paquets
	 */
	public void paquetage() {

		paquets = new ArrayList<Paquet>();
		for (Segment segment : segments) {
			paquets.add(new Paquet(dis.getName(), segment));
		}
	}

	public ArrayList<Segment> getSegments() {
		return segments;
	}

	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}

	public List<Paquet> getPaquets() {
		return paquets;
	}

	public void setPaquets(List<Paquet> paquets) {
		this.paquets = paquets;
	}

	public Transport getTransport() {
		return transport;
	}

	public void setTransport(Transport transport) {
		this.transport = transport;
	}

	// fonction inverse de paquetage (transformer les paquets en segments)
	public void paquetageInverse() {
		segments = new ArrayList<Segment>();
		for (Paquet paquet : paquets) {
			segments.add(paquet.getSegment());
		}
	}

	// crée une instance de Liaison (liaison) en lui passant en paramètres la liste
	// paquets

	public void envoyerLiaison() {
	liaison = new Liaison(paquets,dis);
	}
	/*
	 * dans le cas de la reception } crée une instance de Transport (transport) en
	 * lui passant en paramètres segments
	 */

	public Liaison getLiaison() {
		return liaison;
	}

	public void setLiaison(Liaison liaison) {
		this.liaison = liaison;
	}

	public void envoyerTcp() {
		dis.t.recevoir(segments.get(0));

	}
}

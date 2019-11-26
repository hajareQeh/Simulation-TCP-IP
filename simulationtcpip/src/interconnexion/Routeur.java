package interconnexion;

import java.awt.geom.RectangularShape;
import java.util.List;

import tcpip.Liaison;

public class Routeur extends Node{
	
	private String passerelle;
	public String getPasserelle() {
		return passerelle;
	}


	public void setPasserelle(String passerelle) {
		this.passerelle = passerelle;
	}


	public Routeur(String nom,RectangularShape rs) {
		super(nom,rs);

	}


	public Routeur() {
		super();
	}
	
}

package interconnexion;

import java.awt.geom.RectangularShape;

public class Switch extends Node {
	private String adresse_mac;
	public Switch(String nom,RectangularShape rs) {
		super(nom,rs);

	}

	public Switch() {
		super();
	}
	public String getAdresse_mac() {
		return adresse_mac;
	}

	public void setAdresse_mac(String adresse_mac) {
		this.adresse_mac = adresse_mac;
	}

}

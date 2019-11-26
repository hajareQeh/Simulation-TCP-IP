package tcpip;

import java.io.Serializable;

public class Trame implements Serializable {
	
	private String Adresse_Source ;
	private String Adresse_Destination;
	private Paquet paquet;
	
	public Trame(String adresse_Source, String adresse_Destination, Paquet paquet) {
		Adresse_Source = adresse_Source;
		Adresse_Destination = adresse_Destination;
		this.paquet = paquet;
	}

	public String getAdresse_Source() {
		return Adresse_Source;
	}

	public void setAdresse_Source(String adresse_Source) {
		Adresse_Source = adresse_Source;
	}

	public String getAdresse_Destination() {
		return Adresse_Destination;
	}

	public void setAdresse_Destination(String adresse_Destination) {
		Adresse_Destination = adresse_Destination;
	}

	public Paquet getPaquet() {
		return paquet;
	}

	public void setPaquet(Paquet paquet) {
		this.paquet = paquet;
	}
	
}
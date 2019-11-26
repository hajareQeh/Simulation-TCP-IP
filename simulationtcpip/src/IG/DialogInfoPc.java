package IG;


public class DialogInfoPc {
	  private String nom, addressIP, masque, passerelle, addressMac;
	  private boolean configured=false;

	  public DialogInfoPc(){}
	  public DialogInfoPc(String nom, String addressIP, String masque, String passerelle, String addressMac,boolean b){
	    this.nom = nom;
	    this.addressIP = addressIP;
	    this.masque = masque;
	    this.passerelle = passerelle;
	    this.addressMac = addressMac;
	    this.configured=b;
	  }

	  public boolean isConfigured() {
		return configured;
	}
	public String toString(){
	    String str;
	    if(this.nom != null && this.addressIP != null && this.addressMac != null && this.masque != null && this.passerelle != null){
	      str = "Description d'equipement";
	      str += "\nNom : " + this.nom + "\n";
	      str += "addressIP : " + this.addressIP + "\n";
	      str += "masque : " + this.masque + "\n";
	      str += "passerelle : " + this.passerelle + "\n";
	      str += "addressMac : " + this.addressMac + "\n";
	    }
	    else{
	      str = "Aucune information !";
	    }
	    return str;
	  }
	public String getAddressIP() {
		return addressIP;
	}
	public String getMasque() {
		return masque;
	}
	public String getPasserelle() {
		return passerelle;
	}
	public String getAddressMac() {
		return addressMac;
	}
	public String getNom() {
		return nom;
	}
	

	}
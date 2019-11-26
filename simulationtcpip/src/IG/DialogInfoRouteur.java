package IG;


public class DialogInfoRouteur {
	  private String nom, addressIP,Masque, addressMac;
	  private String passerelle;
	  public String getPasserelle() {
		return passerelle;
	}
	public void setPasserelle(String passerelle) {
		this.passerelle = passerelle;
	}
	private boolean configured=false;

	  public DialogInfoRouteur(){}
	  public DialogInfoRouteur(String nom, String addressIP, String Masque ,String addressMac,boolean b){
	    this.nom = nom;
	    this.addressIP = addressIP;
	    this.Masque= Masque;
	    this.addressMac = addressMac;
	    this.configured=b;
	  }

	  public boolean isConfigured() {
		return configured;
	}
	public String toString(){
	    String str;
	    if(this.nom != null && this.addressIP != null && this.Masque != null && this.addressMac != null  ){
	      str = "Description de l'objet ";
	      str += "Nom : " + this.nom + "\n";
	      str += "addressIP : " + this.addressIP + "\n";
	      str += "Masque : " + this.Masque + "\n";
	      str += "addressMac : " + this.addressMac + "\n";
	    }
	    else{
	      str = "Aucune information n'est enregistrée !";
	    }
	    return str;
	  }
	public String getNom() {
		return nom;
	}
	

	}
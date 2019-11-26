package IG;


public class DialogInfoSwitch {
	  public String getAddressMac() {
		return addressMac;
	}
	public void setAddressMac(String addressMac) {
		this.addressMac = addressMac;
	}
	private String nom, Nbrport, addressIP, passerelle, addressMac;
	  private boolean configured=false;

	  public DialogInfoSwitch(){}
	  public DialogInfoSwitch(String nom, String addressMac,boolean b){
	    this.nom = nom;
	    this.addressMac = addressMac;
	    this.configured=b;
	  }

	  public boolean isConfigured() {
		return configured;
	}
	public String toString(){
	    String str;
	    if(this.nom != null ){
	      str = "Description de l'objet InfoZDialog";
	      str += "Nom : " + this.nom + "\n";
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
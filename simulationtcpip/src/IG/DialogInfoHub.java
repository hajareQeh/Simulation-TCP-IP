package IG;


public class DialogInfoHub {
	  private String nom, addressIP, nbrport, passerelle, addressMac;
	  private boolean configured=false;

	  public DialogInfoHub(){}
	  public DialogInfoHub(String nom,String addressMac,boolean b){
	    this.nom = nom;
	    this.addressMac = addressMac;
	    this.configured=b;
	   
	  }

	  public String getAddressMac() {
		return addressMac;
	}
	public void setAddressMac(String addressMac) {
		this.addressMac = addressMac;
	}
	public boolean isConfigured() {
		return configured;
	}
	public String toString(){
	    String str;
	    if(this.nom != null && this.addressMac != null){
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
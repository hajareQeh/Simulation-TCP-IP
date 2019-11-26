package interconnexion;

import java.io.Serializable;

public class Arc implements Serializable{


		/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		private String id;
		private Node origine;//Node origine
	    private Node destination;// Node destination
	    private int taille=10;// taille de l'arc
	    
		
		
		
		
	    public Arc(Node origine, Node destination,int taille) {
		this.origine = origine;
		this.destination = destination;
		this.taille=taille;
		this.id=""+origine.getName()+"-->"+destination.getName();
	}
	    public String getId() {
			return id;
		}
	    //getters setters

	    public Node getSource() {
	        return origine;
	    }

	    public Node getDestination() {
	        return destination;
	    }

	    public int getWeight() {
	        return taille;
	    }

	    public String toString() {
	        return origine+"----> " + destination+"avec tailles = "+taille;
	    }
}

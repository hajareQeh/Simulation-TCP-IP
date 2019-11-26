package interconnexion;


import java.awt.geom.RectangularShape;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import tcpip.Application;
import tcpip.Liaison;
import tcpip.Trame;
import tcpip.Transport;

public class Node implements Serializable{
	
    private String name;//identificacion du node
    private RectangularShape rs;
	private boolean selected;
	private String id;
	public Transport t=new Transport();
	public Application a=new Application();
	public static String adresse_mac;
	

	protected void envoyer(String url) throws IOException {
		
	}
	
	public void recevoir(ArrayList<Trame> trames) {
		
		
	}
	public Node() {
		this.adresse_mac = generateAdresseMac();
	}
	public String generateAdresseMac()
	{
		    String chars = "0123456789ABCDEF"; 
		    String pass = "";
		    for(int x=0; x<12; x++)
		    {
		       int i = (int)Math.floor(Math.random() * 16); 
		       pass += chars.charAt(i);
		    }
		    System.out.println(pass);
		    return pass;
	}


	public String getId() {
		return id;
	}
	
	public boolean isSelected() {
		return selected;
	}

 
    public RectangularShape getRs() {
		return rs;
	}

	public Node(String nom,RectangularShape rs) {
		this.rs = rs;
		this.name=nom;
		this.id=nom;
	}
	public void setNom(String nom) {
		this.name = nom;
		this.id=nom;
	}


	public void moveBy(double dx, double dy) {
		double x = this.rs.getX();
		double y = this.rs.getY();
		double w = this.rs.getWidth();
		double h = this.rs.getHeight();
		this.rs.setFrame(x + dx, y + dy, w, h);
	}
    
    
    @Override
    public String toString() {
        return name;
    }
    public String getName() {
		return name;
	}


	@Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Node other = (Node) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }
	
	public void liaison(ArrayList<Trame> trames) {
		// TODO Auto-generated method stub
		Liaison ret=new Liaison(trames);
		
	}

}


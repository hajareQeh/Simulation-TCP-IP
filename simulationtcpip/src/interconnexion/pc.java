package interconnexion;

import java.awt.geom.RectangularShape;


import java.util.List;

import java.io.File;
import java.io.PrintWriter;
import java.io.FileWriter;
import java.io.IOException;

import javax.sound.sampled.Port;
import javax.swing.JFileChooser;

import org.omg.CosNaming.NamingContextExtPackage.AddressHelper;

import tcpip.Application;
import tcpip.Liaison;



public class pc extends Node{

	private String ip;
	private String ip_destination;
	private String passerelle;

	private String masque;
	
	private String AddressMac;
	
	public String getAddressMac() {
		return AddressMac;
	}

	public void setAddressMac(String addressMac) {
		AddressMac = addressMac;
	}


	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getIp_destination() {
		return ip_destination;
	}

	public void setIp_destination(String ip_destination) {
		this.ip_destination = ip_destination;
	}

	public String getAdresse_mac() {
		return adresse_mac;
	}

	public void setAdresse_mac(String adresse_mac) {
		this.adresse_mac = adresse_mac;
	}

	public String getPasserelle() {
		return passerelle;
	}

	public void setPasserelle(String passerelle) {
		this.passerelle = passerelle;
	}

	public String getMasque() {
		return masque;
	}

	public void setMasque(String masque) {
		this.masque = masque;
	}

	
	  
	
	



public pc(String nom,RectangularShape rs) {
	super(nom,rs);
	
}

public pc() {
	super();

}

}

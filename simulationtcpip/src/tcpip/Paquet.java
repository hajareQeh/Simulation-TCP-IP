package tcpip;

import java.io.Serializable;
import java.util.List;

public class Paquet implements Serializable{
	private String ipSource;
	private String ipDestination;
	Segment segment;
	
	
	public Paquet( String aipDestination, Segment segment) {
		
		this.ipDestination = ipDestination;
		this.segment = segment;
	}

	public String getIpSource() {
		return ipSource;
	}

	public void setIpSource(String ipSource) {
		this.ipSource = ipSource;
	}

	public String getIpDestination() {
		return ipDestination;
	}

	public void setIpDestination(String ipDestination) {
		this.ipDestination = ipDestination;
	}

	public Segment getSegment() {
		return segment;
	}

	public void setSegment(Segment segment) {
		this.segment = segment;
	}


}

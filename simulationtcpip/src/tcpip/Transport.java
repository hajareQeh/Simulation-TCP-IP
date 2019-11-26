package tcpip;



import java.util.ArrayList;
import java.util.List;

import interconnexion.Node;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Transport implements Serializable {
	final int RTT=1; //pour attendre deux minute
	private List<Byte> messageT; //message dans une liste de byte envoyer de la couche supérieur(couche application)
	private ArrayList<Segment> segments=new ArrayList<Segment>(); //liste pour stocker les segments 
	private static int  syn=0; //pour vérifie la connextion tant que syn=false y a pas de connexion 
	private final int MaxSizeSeg=25; //taille maximal de segment
	public static int nombreDeSegments=0; //nbr de segment
	private Internet ip; //pour  avoir un lien a la couche inferieur (ip) ;
	private Application app;//pour avoir un lien a la couche supérieur (Application) ;
	static int NumAck=0;
	public static int slo=0;
	private static Node dis;
	List<Byte> d=new ArrayList();
	public Transport(List<Byte> messageT,Node dis) {
		this.dis=dis;
		this.messageT = new ArrayList<Byte>();
		this.messageT = messageT;
		demandeCnx();
		segmentation();
		envoyerIP();
		fermerCnx();
		
	}
	
	public void demandeCnx(){
		if(segments.isEmpty()){
		Segment demande = new Segment(syn);
		segments.add(demande);//contenu de la list mtn [demande-]
		
		envoyerIP();//ENVOI UNE DEMANDE DE CONNEXION 
		
		//System.out.println("fin de demande de connexion");
			}
		}
	
	public void segmentation() {
		if(syn==1) {
		//segments.remove(1);//supprimer les deux segment de demande de cnx
		segments.remove(0);//supprimer les deux segment de demande de cnx
		List<Byte> donnee;
		int seq =1;
		for(int i=0;i<messageT.size();i+=MaxSizeSeg) {
			donnee = new ArrayList<Byte>();
			for (int j=0;j<MaxSizeSeg;j++) {
				donnee.add(messageT.get(i+j));
				
				if((i+j)==(messageT.size()-1)) {
					break;
				}
				
			}
			seq++;
			
			segments.add(new Segment(3, 4, seq,donnee));//premier segment a le numero de seq =2
			
		}
		
		}else
			System.out.println("y a pas de connexion");
		nombreDeSegments=segments.size();
		slo=nombreDeSegments;
	}
	
	public void envoyerIP() {
		boolean c=false;
		SimpleDateFormat formater = new SimpleDateFormat("m");
		int compteur=NumAck+1;
		for(int k=0;k<segments.size();k+=1) {
			
			ip=new Internet(segments.get(k),dis);
			int temp=Integer.parseInt(formater.format(new Date()));
			
			
			
			
			while(NumAck!=compteur && (c= Integer.parseInt(formater.format(new Date())) < (temp+RTT))){}
			if(!c && NumAck!=compteur) {

				//system.out.println("renvoyer : temps écouler ");
				//renvoyer
				k--;
			}else {
					//system.out.println("ack arriver avancer");
					compteur++;
			
			}
			
		}
		
	} 
	
	public void fermerCnx(){
		//system.out.println("\nfin de connexion");
		//on vide tout la list puisque tout les segment sont bien envoyer
					segments.clear();
				Segment fermeture = new Segment(-1);
					segments.add(fermeture);//contenu de la liste [fermeture]
					envoyerIP();
			}
	
	
	public void recevoir(Segment segment) {
		this.segments.add(segment);
		if(segment.getNumSeq()==0 || segment.getNumSeq()==-1 ) {
			VerifieCnx();
		}else {
			NumAck++;
		if(--nombreDeSegments==0) {
			//System.out.println("start");
		deSegmentation();
		}
		}
		//System.out.println(dis.a.convertioninverse());
	}     
	
	public void VerifieCnx(){
		NumAck++;
	Segment reponse=segments.get(0);
	
			if(reponse.getNumSeq()==0){//RECOI LA SEGMENT DE DEMANDE DE CONNEXION [DONNÉE VIDE AVEC NUM SEQ =0]
				
				syn=1;//change le variable de cette objet a 1 pour dire la connexion est etablie
				segments.remove(0);
			}
			else { 
				 if(reponse.getNumSeq()==-1){
					 syn=0;
					 nombreDeSegments=0;
					 NumAck=0;
					 dis=null;
				 }
			}
	}
	
	public void deSegmentation() {
		
		d=dis.a.getMessageB();
		
		for(int i=0;i<segments.size();i++) {
			if(segments.get(i).getDonnee()!=null)
			d.addAll(segments.get(i).getDonnee());
			
			}
		dis.a.setMessageB(d);

		}
	
	public Internet getIp() {
		return ip;
	}
	public void setIp(Internet ip) {
		this.ip = ip;
	}
	public Application getApp() {
		return app;
	}
	public void setApp(Application app) {
		this.app = app;
	}
	public static int getNumAck() {
		return NumAck;
	}
	public static void setNumAck(int numAck) {
		NumAck = numAck;
	}
	
	
	 public int getsyn() {
		 return syn;
	 }
	 public void setsyn(int syn) {
		 this.syn=syn;
	 }
	public List<Segment> getSegments() {
		return segments;
	}
	public void setSegments(ArrayList<Segment> segments) {
		this.segments = segments;
	}
	public List<Byte> getMessageT() {
		return messageT;
	}
	public void setMessageT(List<Byte> messageT) {
		this.messageT = messageT;
		
	}
	
	// constructeur  pour initialiser la liste messageTransport(cas de l’expéditeur)
	
	// constructeur  initialise la liste de segments (cas de destinataire)
	    
	public Transport() {
		// TODO Auto-generated constructor stub
	}
	//public void  demandeCnx(); //cree un segment qui contien syn pour demander la connection 
	//devise la liste messageTransport en des petites liste et crée des Segment pour remplir la liste segments 
	 
	
		
		
		
		
		
		

		//contenu de la liste [segments] donc le nombre d'element de la liste c'est nombreDeSegments
		//extrait la liste données apartire de la liste de segments
	//public void fermerCnx(); //crée un segment qui contien syn pour fermer la connection 
	// utilise ip pour créer une instance de la classe Internet on transmettant la liste de Segment
	public void envoyerApp() {
		
		app= new Application(messageT);
	}
	//utilise app pour créer une instance de la classe Application on transmettant la liste de Byte
	//public void verifierCnx(Segment segment); // reçoit la segment de demande d'ouverture ou fermeture de la connexion et change la valeur de cnx ou elle traite le segment puis elle l'on voit a la couche supérieur
	}




package IG;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import interconnexion.Arc;
import interconnexion.Node;
import interconnexion.Routeur;
import interconnexion.pc;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTable;
import javax.swing.JScrollPane;


public class RouteurConfigurationDialog extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private DialogInfoRouteur zInfo = new DialogInfoRouteur();
  private boolean sendData;
  private JLabel nomLabel, ipLabel, ageLabel, tailleLabel,taille2Label, icon;
  private JTextField nom, adresseMac;
  private JTextField ip;
  private Node node;
  private JTextField mask;
  private JTable table;
  private List<Arc> arcs=new ArrayList<>();

  public RouteurConfigurationDialog(JFrame parent, String title, boolean modal,Routeur node,ArrayList<Arc> Arcs){
    super(parent, title, modal);
    this.node=node;
	this.arcs.addAll(Arcs);
    this.setSize(550, 551);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.initComponent();
  }

  public DialogInfoRouteur showZDialog(){
    this.sendData = false;
    this.setVisible(true);      
    return this.zInfo;      
  }

  private void initComponent(){
   


    JPanel panNom = new JPanel();
    panNom.setBounds(50, 5, 456, 60);
    panNom.setBackground(Color.white);
    panNom.setPreferredSize(new Dimension(220, 60));
    nom = new JTextField();
    nom.setBounds(225, 19, 123, 28);
    nom.setText(node.getName());
    nom.setPreferredSize(new Dimension(100, 25));
    panNom.setBorder(BorderFactory.createTitledBorder("Nom du routeur"));
    panNom.setLayout(null);
    nomLabel = new JLabel("Saisir un nom :");
    nomLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
    nomLabel.setBounds(125, 25, 90, 14);
    panNom.add(nomLabel);
    panNom.add(nom);


    JPanel panIP = new JPanel();
    panIP.setBounds(50, 76, 456, 60);
    panIP.setBackground(Color.white);
    panIP.setPreferredSize(new Dimension(220, 60));
    panIP.setBorder(BorderFactory.createTitledBorder("Adresse IPV4"));
    panIP.setLayout(null);
    

    
    ipLabel = new JLabel("   IP  adresse  : ");
    ipLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
    ipLabel.setBounds(122, 25, 85, 14);
    ipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    panIP.add(ipLabel);
    ButtonGroup bg = new ButtonGroup();

    JPanel panAdresseMac = new JPanel();
    panAdresseMac.setBounds(50, 213, 456, 60);
    panAdresseMac.setBackground(Color.white);
    panAdresseMac.setPreferredSize(new Dimension(220, 60));
    panAdresseMac.setBorder(BorderFactory.createTitledBorder("Adresse Mac"));
    panAdresseMac.setLayout(null);
    taille2Label = new JLabel("Adresse Mac :");
    taille2Label.setFont(new Font("Century Gothic", Font.BOLD, 11));
    taille2Label.setBounds(127, 25, 87, 15);
    panAdresseMac.add(taille2Label);
    adresseMac = new JTextField(generateAdresseMac());
    adresseMac.setBounds(224, 19, 124, 28);
    adresseMac.setEnabled(false);
    adresseMac.setPreferredSize(new Dimension(90, 25));
    panAdresseMac.add(adresseMac);

    
    JPanel panel = new JPanel();
    panel.setBounds(50, 142, 456, 60);
    panel.setLayout(null);
    panel.setPreferredSize(new Dimension(220, 60));
    panel.setBorder(BorderFactory.createTitledBorder("Masque"));
    panel.setBackground(Color.WHITE);
    
    JLabel lblMasque_1 = new JLabel("Masque : ");
    lblMasque_1.setHorizontalAlignment(SwingConstants.RIGHT);
    lblMasque_1.setFont(new Font("Century Gothic", Font.BOLD, 11));
    lblMasque_1.setBounds(122, 25, 85, 14);
    panel.add(lblMasque_1);
    
    mask = new JTextField();
    mask.setPreferredSize(new Dimension(100, 25));
    mask.setBounds(223, 19, 126, 28);
    panel.add(mask);

    JPanel content = new JPanel();
    content.setBackground(Color.white);
    tailleLabel = new JLabel("Taille : ");
    tailleLabel.setBounds(728, 92, 34, 14);

    
    this.getContentPane().add(content, BorderLayout.CENTER);
    
    JScrollPane scrollPane = new JScrollPane();
    scrollPane.setBounds(54, 284, 452, 180);
    
        JPanel control = new JPanel();
        control.setBounds(0, 488, 544, 33);
        JButton okBouton = new JButton("OK");
        
        okBouton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent arg0) {        
            zInfo = new DialogInfoRouteur(nom.getText(),ip.getText(),mask.getText(),adresseMac.getText(),true);
            setVisible(false);
          }
         
        });
        
            JButton cancelBouton = new JButton("Annuler");
            cancelBouton.addActionListener(new ActionListener(){
              public void actionPerformed(ActionEvent arg0) {
            	
                setVisible(false);
              }      
            });
            
                control.add(okBouton);
                control.add(cancelBouton);
    String[] columnNames = {"Name",
            				"Adresse IP",
            				"Masque",
    						"Debit"};
    ArrayList<Arc> voisin=new ArrayList<>();
    for (Arc arc 	: arcs) {
		if(arc.getSource()==node) {
			voisin.add(arc);
		}
	}
	Object[][] tableau = new Object[voisin.size()][4];
	 
	for(int i=0; i<tableau.length; i++)
	{
		tableau[i][0] = voisin.get(i).getDestination().getName();
		if(voisin.get(i).getDestination() instanceof pc ) {
			tableau[i][1] = ((pc)voisin.get(i).getDestination()).getIp();
			tableau[i][2] = ((pc)voisin.get(i).getDestination()).getMasque();	
		}else {
			tableau[i][1] = "####";
			tableau[i][2] = "####";
		}
		
		tableau[i][3] = voisin.get(i).getWeight()+" Mbps";

	}




    table = new JTable(tableau,columnNames);
    scrollPane.setViewportView(table);
    
    ip = new JTextField();
    ip.setBounds(223, 19, 126, 28);
    panIP.add(ip);
    ip.setPreferredSize(new Dimension(100, 25));
    content.setLayout(null);
    content.add(control);
    content.add(panel);
    content.add(panNom);
    content.add(panIP);
    content.add(tailleLabel);
    content.add(scrollPane);
    content.add(panAdresseMac);
  }  
  public String generateAdresseMac(){
	    String chars = "0123456789ABCDEF"; 
	    String pass = "";
	    int cnt=0;
	    for(int x=0; x<17; x++)
	    {
	       int i = (int)Math.floor(Math.random() * 16); 
	       if(cnt==2) {
	    	   pass+="-";
	    	   cnt=0;
	       }else {
	    	   pass += chars.charAt(i); 
	    	   cnt++;
	       }
	    }
	   
	    return pass;
	} 
}
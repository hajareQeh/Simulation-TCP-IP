package IG;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import interconnexion.Arc;
import interconnexion.Node;

import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;


public class HubConfigurationDialog extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private DialogInfoHub zInfo = new DialogInfoHub();
  private boolean sendData;
  private JLabel nomLabel, ageLabel,taille2Label, icon;
  private JTextField nom, adresseMac;
  private Node node;
  private JTable table;
  private List<Arc> arcs;
  public HubConfigurationDialog(JFrame parent, String title, boolean modal,Node node,ArrayList<Arc> arcs){
    super(parent, title, modal);
    this.node=node;
    this.arcs=arcs;
    this.setSize(550, 395);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
    this.initComponent();
  }

  public DialogInfoHub showZDialog(){
    this.sendData = false;
    this.setVisible(true);      
    return this.zInfo;      
  }

  private void initComponent(){
   

    //Le nom
    JPanel panNom = new JPanel();
    panNom.setBackground(Color.white);
    panNom.setPreferredSize(new Dimension(220, 60));
    nom = new JTextField();
    nom.setBounds(219, 21, 118, 28);
    nom.setText(node.getName());
    nom.setPreferredSize(new Dimension(100, 25));
    panNom.setBorder(BorderFactory.createTitledBorder("Nom du Hub"));
    panNom.setLayout(null);
    nomLabel = new JLabel("Saisir un nom :");
    nomLabel.setFont(new Font("Century Gothic", Font.BOLD, 11));
    nomLabel.setBounds(119, 26, 95, 14);
    panNom.add(nomLabel);
    panNom.add(nom);
    ButtonGroup bg = new ButtonGroup();

    //La taille
    JPanel panAdresseMac = new JPanel();
    panAdresseMac.setBackground(Color.white);
    panAdresseMac.setPreferredSize(new Dimension(220, 60));
    panAdresseMac.setBorder(BorderFactory.createTitledBorder("Adresse Mac"));
    panAdresseMac.setLayout(null);
    taille2Label = new JLabel("Adresse Mac :");
    taille2Label.setFont(new Font("Century Gothic", Font.BOLD, 11));
    taille2Label.setBounds(128, 27, 87, 14);
    panAdresseMac.add(taille2Label);
    adresseMac = new JTextField(generateAdresseMac());
    adresseMac.setBounds(218, 21, 115, 28);
    adresseMac.setEnabled(false);
    adresseMac.setPreferredSize(new Dimension(90, 25));
    panAdresseMac.add(adresseMac);

    JPanel content = new JPanel();
    content.setBackground(Color.white);

    
    this.getContentPane().add(content, BorderLayout.NORTH);
    
        JPanel control = new JPanel();
        JButton okBouton = new JButton("OK");
        
        okBouton.addActionListener(new ActionListener(){
          public void actionPerformed(ActionEvent arg0) {        
            zInfo = new DialogInfoHub(nom.getText(),adresseMac.getText(),true);
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
    
    JScrollPane scrollPane = new JScrollPane();
    GroupLayout gl_content = new GroupLayout(content);
    gl_content.setHorizontalGroup(
    	gl_content.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_content.createSequentialGroup()
    			.addGroup(gl_content.createParallelGroup(Alignment.LEADING)
    				.addComponent(control, GroupLayout.PREFERRED_SIZE, 544, GroupLayout.PREFERRED_SIZE)
    				.addGroup(gl_content.createSequentialGroup()
    					.addGap(42)
    					.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
    						.addComponent(panAdresseMac, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    						.addComponent(panNom, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, 465, Short.MAX_VALUE)
    						.addComponent(scrollPane, GroupLayout.PREFERRED_SIZE, 461, GroupLayout.PREFERRED_SIZE))))
    			.addContainerGap())
    );
    gl_content.setVerticalGroup(
    	gl_content.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_content.createSequentialGroup()
    			.addGap(5)
    			.addComponent(panNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.UNRELATED)
    			.addComponent(panAdresseMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.UNRELATED)
    			.addComponent(scrollPane, GroupLayout.DEFAULT_SIZE, 165, Short.MAX_VALUE)
    			.addGap(18)
    			.addComponent(control, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addGap(38))
    );
    String[] columnNames = {"Name",
	"Debit"};
ArrayList<Arc> voisin=new ArrayList<>();
for (Arc arc 	: arcs) {

if(arc.getSource()==node) {
voisin.add(arc);
}
}
Object[][] tableau = new Object[voisin.size()][2];

for(int i=0; i<tableau.length; i++)
{
tableau[i][0] = voisin.get(i).getDestination().getName();

tableau[i][1] = voisin.get(i).getWeight()+" Mbps";

} 
    table = new JTable(tableau,columnNames);
    scrollPane.setViewportView(table);
    content.setLayout(gl_content);
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
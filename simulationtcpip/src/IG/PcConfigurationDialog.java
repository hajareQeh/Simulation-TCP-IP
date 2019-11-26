package IG;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

import interconnexion.Node;
import interconnexion.pc;

import javax.swing.SwingConstants;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class PcConfigurationDialog extends JDialog {
  /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
private DialogInfoPc zInfo = new DialogInfoPc();
  private boolean sendData;
  private JLabel nomLabel, ipLabel, lblPasserelle,lblAdresseMac, icon;
  private JTextField nom, adresseMac;
  private JTextField ip;
  private JTextField masque;
  private JTextField passerelle;
  private pc node;
  public PcConfigurationDialog(JFrame parent, String title, boolean modal,pc node){
    super(parent, title, modal);
    this.node=node;
    this.setSize(550, 450);
    this.setLocationRelativeTo(null);
    this.setResizable(false);
    this.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
    this.initComponent();
  }

  public DialogInfoPc showZDialog(){
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
    nom.setText(node.getName());
    nom.setPreferredSize(new Dimension(125, 25));
    panNom.setBorder(BorderFactory.createTitledBorder("Nom de l'ordinateur"));
    nomLabel = new JLabel("Saisir un nom :");
    panNom.add(nomLabel);
    panNom.add(nom);

    
    JPanel panIP = new JPanel();
    panIP.setBackground(Color.white);
    panIP.setPreferredSize(new Dimension(220, 60));
    panIP.setBorder(BorderFactory.createTitledBorder("Adresse IPV4"));
    

    
    ipLabel = new JLabel("   IP  adresse  : ");
    ipLabel.setHorizontalAlignment(SwingConstants.RIGHT);
    panIP.add(ipLabel);
    

    JPanel panMasque = new JPanel();
    panMasque.setBackground(Color.white);
    panMasque.setBorder(BorderFactory.createTitledBorder("Masque "));
    panMasque.setPreferredSize(new Dimension(440, 60));
    ButtonGroup bg = new ButtonGroup();

    JPanel panAdresseMac = new JPanel();
    panAdresseMac.setBackground(Color.white);
    panAdresseMac.setPreferredSize(new Dimension(220, 60));
    panAdresseMac.setBorder(BorderFactory.createTitledBorder("Adresse Mac"));
    
    lblAdresseMac = new JLabel("Adresse Mac :");
    panAdresseMac.add(lblAdresseMac);
    if(node.getAddressMac()==null) {
    	adresseMac = new JTextField(generateAdresseMac());
    }else {
    	adresseMac = new JTextField(node.getAddressMac());
    }
    
    
    adresseMac.setEnabled(false);
    adresseMac.setPreferredSize(new Dimension(120, 25));
    panAdresseMac.add(adresseMac);

    JPanel panPasserelle = new JPanel();
    panPasserelle.setBackground(Color.white);
    panPasserelle.setPreferredSize(new Dimension(220, 60));
    panPasserelle.setBorder(BorderFactory.createTitledBorder("Passerelle"));
    lblPasserelle = new JLabel("Passerelle :");
    panPasserelle.add(lblPasserelle);

    JPanel content = new JPanel();
    content.setBackground(Color.white);

    JPanel control = new JPanel();
    JButton okBouton = new JButton("OK");
    
    okBouton.addActionListener(new ActionListener(){
      public void actionPerformed(ActionEvent arg0) {      
    		
  		String IP_pattern="^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                  + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                  + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\."
                  + "([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";
  				Pattern pattern=Pattern.compile(IP_pattern);
  				Matcher regexMatcherip=pattern.matcher(ip.getText());
  				Matcher regexMatcherpasserelle=pattern.matcher(passerelle.getText());
  				Matcher regexMatchermasque=pattern.matcher(masque.getText());
  				
  				if(regexMatcherip.matches()&&regexMatcherpasserelle.matches()&&regexMatchermasque.matches()) {
  			        zInfo = new DialogInfoPc(nom.getText(),ip.getText(), masque.getText(), passerelle.getText() ,adresseMac.getText(),true);
  			        setVisible(false);
  				}else{
  						 JOptionPane jop = new JOptionPane();
  				        jop.showMessageDialog(null, "Votre adressage est incorrect ","warning", JOptionPane.WARNING_MESSAGE);

  				}

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

    
    this.getContentPane().add(content, BorderLayout.CENTER);
    GroupLayout gl_content = new GroupLayout(content);
    gl_content.setHorizontalGroup(
    	gl_content.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_content.createSequentialGroup()
    			.addGap(50)
    			.addGroup(gl_content.createParallelGroup(Alignment.TRAILING, false)
    				.addComponent(panAdresseMac, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(panNom, GroupLayout.DEFAULT_SIZE, 456, Short.MAX_VALUE)
    				.addComponent(panIP, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(panMasque, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
    				.addComponent(panPasserelle, Alignment.LEADING, GroupLayout.DEFAULT_SIZE, GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    			.addContainerGap(38, Short.MAX_VALUE))
    );
    gl_content.setVerticalGroup(
    	gl_content.createParallelGroup(Alignment.LEADING)
    		.addGroup(gl_content.createSequentialGroup()
    			.addGap(5)
    			.addComponent(panNom, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.UNRELATED)
    			.addComponent(panIP, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(panMasque, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(panPasserelle, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addPreferredGap(ComponentPlacement.RELATED)
    			.addComponent(panAdresseMac, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
    			.addContainerGap(55, Short.MAX_VALUE))
    );
    
    passerelle = new JTextField();
    passerelle.setText(node.getPasserelle());
    passerelle.setPreferredSize(new Dimension(125, 25));
    panPasserelle.add(passerelle);
    passerelle.setColumns(10);
    
    JLabel lblMasque = new JLabel("         Masque   : ");
    lblMasque.setHorizontalAlignment(SwingConstants.RIGHT);
    panMasque.add(lblMasque);
    
    masque = new JTextField();
    masque.setText(node.getMasque());
    masque.setPreferredSize(new Dimension(125, 25));
    panMasque.add(masque);
    masque.setColumns(10);
    
    ip = new JTextField();
    ip.setText(node.getIp());
    

    panIP.add(ip);
    ip.setPreferredSize(new Dimension(125, 25));
    content.setLayout(gl_content);
    this.getContentPane().add(control, BorderLayout.SOUTH);
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
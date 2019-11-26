package IG;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;

import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JPanel;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.LineBorder;

import java.awt.Color;


import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.Date;

import java.util.LinkedList;

import java.awt.event.ActionEvent;
import javax.swing.JLabel;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Cursor;
import java.awt.Desktop;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

import javax.swing.JScrollPane;
import javax.swing.event.ChangeListener;
import javax.swing.filechooser.FileSystemView;

import entreesSortie.EntreeSortie;
import interconnexion.Arc;

import interconnexion.Graph;
import interconnexion.Hub;
import interconnexion.Node;
import interconnexion.Routage;
import interconnexion.Routeur;
import interconnexion.Switch;
import interconnexion.pc;
import tcpip.Application;
import tcpip.Transport;

import javax.swing.event.ChangeEvent;
import java.awt.SystemColor;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import java.awt.Panel;
import java.awt.Point;

import java.awt.BorderLayout;
import java.awt.event.MouseMotionAdapter;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

import java.awt.Font;

import java.awt.Toolkit;
import javax.swing.JSeparator;
import javax.swing.JButton;
import java.awt.GridLayout;
import javax.swing.JTextArea;


public class ZoneTravail  extends JFrame{

	/**
	 * 
	 */
	
	
	private static final long serialVersionUID = 1L;
	public JFrame frmSimulationTcpip;
	private JLabel lblHub;
	private JLabel lblSwitch;
	private JLabel lblRouteur;
	private JLabel lblCable;
	private JLabel lblOrdinateur;
	private JRadioButton rdbtnCable;
	private JRadioButton rdbtnHub ;
	private JRadioButton rdbtnPc;
	private ButtonGroup outils = new ButtonGroup();
	private JRadioButton rdbtnSwitch;
	private JRadioButton rdbtnRouter;
	private JRadioButton rdbtnSelect;
	private JRadioButton rdbtnDelete;
	private ButtonGroup function = new ButtonGroup();
	private JRadioButton rdbtnDataTest;
	private JRadioButton rdbtnTest;
	private JScrollPane zonetravail;
	private Panel panel_Dessin;
	private JPanel panel_Outils;
	private JPanel panel_Function;
	private JPanel panel_ZoneDeTravaile;
	private int xStart;
	private int yStart;
	private int xFin;
	private int yFin;
	private Graphics2D g2;
	private ArrayList<Arc> arcs=new ArrayList<Arc>();
	private ArrayList<Node> nodes=new ArrayList<Node>();
	
	///////////////////
	private Node courant=null;
	private Point2D lastPointPress;

	private BufferedImage image;
	private String imagefolder=System.getProperty("user.dir") + File.separator
	+ "images" + File.separator;
	////////////
	private Graph graph2 ;
	private Routage dijkstra;
	private Node nodeDepart;
	private Node testdejic;
	private LinkedList<Node> path;
	private String url;
	Image img= new ImageIcon(imagefolder+"if_message_172503.png").getImage();
			//new ImageIcon("/home/user/Bureau/a.png").
	public int right,down;
	 Point debut,fin;
	 double x,y,a,c;
	 String tempf,tempd,date;
	 private JMenuItem mntmOuvrir;
	 private JMenuItem mntmSauvegarder;
	 private JSeparator separator;
	 private JMenuItem mntmNewMenuItem;
	 private JMenuItem mntmEnregister;
	 
	 private String url1=null;
	 private JMenuItem mntmManuelDutilisation;
	 private JButton btnNewButton;
	 private JButton btnEnregistrer;
	 private JButton btnOuvrir;
	 private JLabel lblNewLabel;
	 private JLabel label;
	 private JLabel label_1;
	 private JPanel panel;
	 private JScrollPane scrollPane;
	 private JTextArea textAreaResultat;
	
	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					java.util.Timer vgTimer = new java.util.Timer(); 
					ZoneTravail window = new ZoneTravail();
					window.drawAll();
					window.frmSimulationTcpip.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
	}

	/**
	 * Create the application.
	 */
		public ZoneTravail() {
		initialize();
	}
		public ZoneTravail(Graph g) {
			this.graph2=g;
			this.nodes=(ArrayList<Node>) g.getVertexes();
			this.arcs=(ArrayList<Arc>) g.getEdges();
			
		initialize();
	}
		 public String printTime() {
		       SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");
		       Date aujourdhui = new Date();
		       return dateFormat.format(aujourdhui);
		   }
	/**
	 * Initialize the contents of the frame.
	 */
		 
	private void initialize() {
		frmSimulationTcpip = new JFrame();
		frmSimulationTcpip.setTitle("SIMULATION TCP/IP");
		frmSimulationTcpip.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\ilyass\\Desktop\\healthcare-icons-01.png"));
		frmSimulationTcpip.setBounds(90, 20,1100, 700);
		frmSimulationTcpip.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JMenuBar menuBar = new JMenuBar();
		frmSimulationTcpip.setJMenuBar(menuBar);
		JMenu mnFichier = new JMenu("Fichier");
		menuBar.add(mnFichier);
		
		mntmOuvrir = new JMenuItem("Ouvrir");
		mntmOuvrir.setIcon(new ImageIcon(ZoneTravail.class.getResource("/javax/swing/plaf/metal/icons/ocean/file.gif")));
		mntmOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
							JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
							int returnValue = jfc.showOpenDialog(null);
							// int returnValue = jfc.showSaveDialog(null);

							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = jfc.getSelectedFile();
								//System.out.println(selectedFile.getAbsolutePath());
								url=selectedFile.getAbsolutePath();
								url1=selectedFile.getAbsolutePath();
								
							}
							
							try {
								EntreeSortie es =new EntreeSortie();
								if( url1 != null) {
								es.ouvrir("Lire", url);
								graph2=es.lire();
								
								
									nodes.clear();
									arcs.clear();
									
									nodes=(ArrayList<Node>) graph2.getVertexes();
									arcs=(ArrayList<Arc>) graph2.getEdges();
									panel_Dessin.update(g2);
									es.fermer();
									drawAll();	
								
								}else {
									JOptionPane.showMessageDialog(null, "Overture annulée !");
								}
							} catch (Exception e) {
								// TODO Auto-generated catch block
								JOptionPane.showMessageDialog(null, "votre fichier est incompatible !");
							
							}
				/*
				 * 
				 * choix du enregistrement 
				 * 
				 */
			}
		});
		mnFichier.add(mntmOuvrir);
		
		mntmSauvegarder = new JMenuItem("Enregistrer sous ...");
		mntmSauvegarder.setIcon(new ImageIcon(ZoneTravail.class.getResource("/javax/swing/plaf/metal/icons/ocean/hardDrive.gif")));
		mntmSauvegarder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				//int returnValue = jfc.showOpenDialog(null);
				int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					//System.out.println(selectedFile.getAbsolutePath());
					url=selectedFile.getAbsolutePath();
				}

				try {
					EntreeSortie es =new EntreeSortie();
					graph2 = new Graph(nodes, arcs);
					if(url != null) {
					es.ouvrir("ECRIRE", url);
					es.ecrire(graph2);
					JOptionPane.showMessageDialog(null, "Sauvegarde réussie");
					}else {
						JOptionPane.showMessageDialog(null, "Enregistrement annulé");
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		mnFichier.add(mntmSauvegarder);
		
		mntmEnregister = new JMenuItem("Enregister");
		mntmEnregister.setIcon(new ImageIcon(ZoneTravail.class.getResource("/javax/swing/plaf/metal/icons/ocean/floppy.gif")));
		mntmEnregister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (url1!=null) {
					
					try {
						EntreeSortie es =new EntreeSortie();
						graph2 = new Graph(nodes, arcs);
						if(url != null) {
						es.ouvrir("ECRIRE", url1);
						es.ecrire(graph2);
						JOptionPane.showMessageDialog(null, "Sauvegarde réussie");
						}else {
							JOptionPane.showMessageDialog(null, "Enregistrement annulé");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//
				} else {
					
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					//int returnValue = jfc.showOpenDialog(null);
					int returnValue = jfc.showSaveDialog(null);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						//System.out.println(selectedFile.getAbsolutePath());
						url=selectedFile.getAbsolutePath();
					}

					try {
						EntreeSortie es =new EntreeSortie();
						graph2 = new Graph(nodes, arcs);
						if(url != null) {
						es.ouvrir("ECRIRE", url);
						es.ecrire(graph2);
						if(g2==null) {
						g2=(Graphics2D) panel_Dessin.getGraphics();
						JOptionPane.showMessageDialog(null, "sauvegarde réussie");
						}
						}else {
							JOptionPane.showMessageDialog(null, "Enregistrement annulé");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		mnFichier.add(mntmEnregister);
		
		separator = new JSeparator();
		mnFichier.add(separator);
		
		mntmNewMenuItem = new JMenuItem("Sortir");
		mntmNewMenuItem.setIcon(new ImageIcon(ZoneTravail.class.getResource("/javax/swing/plaf/metal/icons/ocean/close.gif")));
		mntmNewMenuItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);		
				}
		});
		mnFichier.add(mntmNewMenuItem);
		
		JMenu mnHelp = new JMenu("Aide");
		menuBar.add(mnHelp);
		
		mntmManuelDutilisation = new JMenuItem("manuel d'utilisation");
		mntmManuelDutilisation.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (Desktop.isDesktopSupported()) {
				    try {
				        File myFile = new File(System.getProperty("user.dir") + File.separator
				        		+ "fichier" + File.separator+"manuel.pdf");
				        Desktop.getDesktop().open(myFile);
				    } catch (IOException ex) {
				        // no application registered for PDFs
				    }
				}
			}
		});
		mntmManuelDutilisation.setIcon(new ImageIcon(ZoneTravail.class.getResource("/javax/swing/plaf/metal/icons/ocean/question.png")));
		mnHelp.add(mntmManuelDutilisation);
		
		panel_Function = new JPanel();
		panel_Function.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		JPanel panel_OutilsPrincipale = new JPanel();
		panel_OutilsPrincipale.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel_ZoneDeTravaile = new JPanel();
		panel_ZoneDeTravaile.setBackground(Color.WHITE);
		panel_ZoneDeTravaile.setBorder(new LineBorder(new Color(0, 0, 0)));
		panel_ZoneDeTravaile.add(zonetravail=new JScrollPane(panel_Dessin));
		
		JPanel panel_ChoixAndResultat = new JPanel();
		panel_ChoixAndResultat.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout groupLayout = new GroupLayout(frmSimulationTcpip.getContentPane());
		groupLayout.setHorizontalGroup(
			groupLayout.createParallelGroup(Alignment.TRAILING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_ZoneDeTravaile, GroupLayout.DEFAULT_SIZE, 411, Short.MAX_VALUE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_Function, GroupLayout.PREFERRED_SIZE, 53, GroupLayout.PREFERRED_SIZE))
				.addComponent(panel_OutilsPrincipale, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
				.addComponent(panel_ChoixAndResultat, GroupLayout.DEFAULT_SIZE, 470, Short.MAX_VALUE)
		);
		groupLayout.setVerticalGroup(
			groupLayout.createParallelGroup(Alignment.LEADING)
				.addGroup(groupLayout.createSequentialGroup()
					.addComponent(panel_OutilsPrincipale, GroupLayout.PREFERRED_SIZE, 34, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addGroup(groupLayout.createParallelGroup(Alignment.LEADING)
						.addComponent(panel_ZoneDeTravaile, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE)
						.addComponent(panel_Function, GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel_ChoixAndResultat, GroupLayout.PREFERRED_SIZE, 76, GroupLayout.PREFERRED_SIZE)
					.addGap(4))
		);
		panel_OutilsPrincipale.setLayout(null);
		
		btnNewButton = new JButton("Effacer tout");
		btnNewButton.setBackground(SystemColor.inactiveCaption);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int input =JOptionPane.showConfirmDialog (null, "Etes-vous sûr de vouloir tout effacer ?","Avertissement",JOptionPane.YES_NO_OPTION);
				    

				    if (input==0) { 
				    	nodes.clear();
						arcs.clear();
						panel_Dessin.update(g2);
						drawAll();
				    }

			}
		});
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(imagefolder+"eff.png"));
		lblNewLabel.setBounds(10, 6, 23, 23);
		panel_OutilsPrincipale.add(lblNewLabel);
		btnNewButton.setIcon(new ImageIcon("C:\\Users\\Marouane\\Desktop\\Sans titre-1.jpg"));
		btnNewButton.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnNewButton.setBounds(34, 6, 118, 23);
		panel_OutilsPrincipale.add(btnNewButton);
		
		label = new JLabel("");
		label.setIcon(new ImageIcon(imagefolder+"sauv.png"));
		label.setBounds(162, 6, 23, 23);
		panel_OutilsPrincipale.add(label);
		
		btnEnregistrer = new JButton("Enregistrer");
		btnEnregistrer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
if (url1!=null) {
					
					try {
						EntreeSortie es =new EntreeSortie();
						graph2 = new Graph(nodes, arcs);
						if(url != null) {
						es.ouvrir("ECRIRE", url1);
						es.ecrire(graph2);
						JOptionPane.showMessageDialog(null, "sauvegarde réussie");
						}else {
							JOptionPane.showMessageDialog(null, "Enregistrement annulé");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//
				} else {
					
					JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
					//int returnValue = jfc.showOpenDialog(null);
					int returnValue = jfc.showSaveDialog(null);

					if (returnValue == JFileChooser.APPROVE_OPTION) {
						File selectedFile = jfc.getSelectedFile();
						//System.out.println(selectedFile.getAbsolutePath());
						url=selectedFile.getAbsolutePath();
					}

					try {
						EntreeSortie es =new EntreeSortie();
						graph2 = new Graph(nodes, arcs);
						if(url != null) {
						es.ouvrir("ECRIRE", url);
						es.ecrire(graph2);
						if(g2==null) {
						g2=(Graphics2D) panel_Dessin.getGraphics();
						JOptionPane.showMessageDialog(null, "sauvegarde réussie");
						}
						}else {
							JOptionPane.showMessageDialog(null, "Enregistrement annulé");
						}
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
			}
		});
		btnEnregistrer.setBackground(SystemColor.inactiveCaption);
		btnEnregistrer.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnEnregistrer.setBounds(186, 6, 100, 23);
		panel_OutilsPrincipale.add(btnEnregistrer);
		
		label_1 = new JLabel("");
		label_1.setIcon(new ImageIcon(imagefolder+"ouvrir.png"));
		label_1.setBounds(296, 6, 23, 23);
		panel_OutilsPrincipale.add(label_1);
		
		btnOuvrir = new JButton("Ouvrir");
		btnOuvrir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				// int returnValue = jfc.showSaveDialog(null);

				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();
					//System.out.println(selectedFile.getAbsolutePath());
					url=selectedFile.getAbsolutePath();
					url1=selectedFile.getAbsolutePath();
				}
				
				try {
					EntreeSortie es =new EntreeSortie();
					if( url != null) {
					es.ouvrir("Lire", url);
					graph2=es.lire();
					
					
						nodes.clear();
						arcs.clear();
						
						nodes=(ArrayList<Node>) graph2.getVertexes();
						arcs=(ArrayList<Arc>) graph2.getEdges();
						es.fermer();
						drawAll();	
					
					}else {
						JOptionPane.showMessageDialog(null, "Overture annulée !");
					}
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null, "votre fichier est incompatible !");
				
				}
			}
		});
		btnOuvrir.setBackground(SystemColor.inactiveCaption);
		btnOuvrir.setFont(new Font("Century Gothic", Font.BOLD, 12));
		btnOuvrir.setBounds(329, 6, 89, 23);
		panel_OutilsPrincipale.add(btnOuvrir);
		
		panel_Dessin = new Panel();
		panel_Dessin.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseDragged(MouseEvent arg0) {
				Point p = arg0.getPoint();
				if(courant==null) {
					//System.out.println("mouse dragged sans avoire un courant " );
				if(rdbtnCable.isSelected()) {
					
					panel_Dessin.update(g2);
					drawAll();
				}
				if(rdbtnPc.isSelected()) {
					
					//System.out.println("mouse dragged sans  courant =pc");
					panel_Dessin.update(g2);
					xFin=arg0.getX();
					yFin=arg0.getY();
					lastPointPress=arg0.getPoint();
					drawAll();try {
						image = ImageIO.read(getClass().getResource("/receptor.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 g2.drawImage(image,arg0.getPoint().x,arg0.getPoint().y, null);	
				}
				if(rdbtnHub.isSelected()) {
					//System.out.println("mouse dragged sans courant =Hub");
					panel_Dessin.update(g2);
					xFin=arg0.getX();
					yFin=arg0.getY();
					lastPointPress=arg0.getPoint();
					drawAll();

					drawAll();try {
						image = ImageIO.read(getClass().getResource("/lsr.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 g2.drawImage(image,arg0.getPoint().x,arg0.getPoint().y, null);	
				}
				if(rdbtnSwitch.isSelected()) {
					//System.out.println("mouse dragged sans courant =switch");
					panel_Dessin.update(g2);
					xFin=arg0.getX();
					yFin=arg0.getY();
					lastPointPress=arg0.getPoint();
					drawAll();
					 try {
						image = ImageIO.read(getClass().getResource("/switch.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 g2.drawImage(image,arg0.getPoint().x,arg0.getPoint().y, null);	
				}
				if(rdbtnRouter.isSelected()) {
				//	System.out.println("mouse dragged sans courant = roouter");
					panel_Dessin.update(g2);
					xFin=arg0.getX();
					yFin=arg0.getY();
					lastPointPress=arg0.getPoint();
					drawAll();
					try {
						image = ImageIO.read(getClass().getResource("/ler.png"));
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
            		 g2.drawImage(image,arg0.getPoint().x,arg0.getPoint().y, null);	
				}
				if(rdbtnDataTest.isSelected()||rdbtnDelete.isSelected()||rdbtnTest.isSelected()||rdbtnSelect.isSelected()){
					panel_Dessin.update(g2);
					drawAll();
				}
				
				}else {
					//System.out.println("courant == "+courant.getName());
					if(rdbtnPc.isSelected()||rdbtnHub.isSelected()||rdbtnRouter.isSelected()||rdbtnSelect.isSelected()
							||rdbtnSwitch.isSelected()) {
				//		System.out.println("mouse dragged avec courant ");
					double dx = p.getX() - lastPointPress.getX();
					double dy = p.getY() - lastPointPress.getY();			
				
				//	if (!selectedNode.contains(courant)) {
						
						panel_Dessin.update(g2);
						drawAll();
						courant.moveBy(dx, dy);
						lastPointPress = p;
				}else {
					if(rdbtnCable.isSelected()) {
						//System.out.println("mouse dragged avec courant et radio button cable ");
						panel_Dessin.update(g2);
						xFin=arg0.getX();
						yFin=arg0.getY();
						lastPointPress=arg0.getPoint();
						Line2D lin=new Line2D.Float((float)courant.getRs().getCenterX(),(float)courant.getRs().getCenterY(),xFin,yFin);
						g2.draw(lin);
						drawAll();
					}else {
						if(rdbtnDelete.isSelected()) {
							
						//	System.out.println("mouse dragged avec courant"+courant.getName()+" et radio button Delete ");
							panel_Dessin.update(g2);
							xFin=arg0.getX();
							yFin=arg0.getY();
							lastPointPress=arg0.getPoint();
							Line2D lin=new Line2D.Float((float)courant.getRs().getCenterX(),(float)courant.getRs().getCenterY(),xFin,yFin);
							g2.draw(lin);
							drawAll();
						}else {
						//System.out.println("mouse dragged avec courant mais radio button= delet=test=datatest ");
						panel_Dessin.update(g2);
						drawAll();
						}
						

					}
				}
					}
				
			
			}

			@Override
			public void mouseMoved(MouseEvent arg0) {
				
				if (find(arg0.getPoint())==null)
					panel_Dessin.setCursor(Cursor.getDefaultCursor());
					else
						panel_Dessin.setCursor(Cursor.getPredefinedCursor(Cursor.CROSSHAIR_CURSOR));
				
			}
		});
		panel_Dessin.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				//System.out.println("mouse pressed ");
				if(g2==null) {
					g2=(Graphics2D) panel_Dessin.getGraphics();
				}
				panel_Dessin.update(g2);
				xStart=e.getX();
				yStart=e.getY();
				xFin=e.getX();
				yFin=e.getY();
				Point p = e.getPoint();
				
				courant = find(p);
				if(courant==null) {
				//System.out.println("mouse pressed initialisation du courant avec null");
		        if(nodes.isEmpty()) {
		        	
		        }
		        }
		        else {
					//System.out.println("mouse pressed initialisation du courant avec find p  ("+courant.getName()+" )");
					
					if(rdbtnTest.isSelected()) {
					if(testdejic==null) {
						panel_Dessin.update(g2);
						testdejic=courant;
						drawAll();
					}else {
						panel_Dessin.update(g2);
						
						drawAll();
						
							graph2 = new Graph(nodes, arcs);
							dijkstra = new Routage(graph2);
							int i=0;
							for (Node node : nodes) {
								if(node==testdejic) {
									break;
								}
								i++;
							}
							dijkstra.execute(nodes.get(i));
							
							 i=0;
							for (Node node : nodes) {
								if(node==courant) {
									break;
								}
								i++;
							}
							path = dijkstra.getPath(nodes.get(i));
							
							
							try {
								String s="";
							for (Node vertex : path) {
								s+=vertex+" .-> ";
							}
						   tempd = printTime();
							for(int k=0;k<path.size()-1;k++) {
								debut=new Point((int)path.get(k).getRs().getCenterX()-20,(int)path.get(k).getRs().getCenterY()-20);
								fin=new Point((int)path.get(k+1).getRs().getCenterX()-20,(int)path.get(k+1).getRs().getCenterY()-20);
								x=debut.getX();
								y=debut.getY();
								if(fin.getX()+5< x || fin.getX()-5> x) {
									a=(fin.getY()-debut.getY())/(fin.getX()-debut.getX());
									c=debut.getY()-a*debut.getX();
									//y=xa+c;
									int z=(int)Math.sqrt(Math.pow((fin.getX()-x),2)+ Math.pow((fin.getY()-y),2));
									int d=z/5;
									int h=d;
										while(h<z-1) {
											h+=d;
											g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
									if(x> fin.getX() ) {
													x-=d;
													y=a*x+c;
												
												}else {
													if(x<fin.getX()) {
														x+=d;
														y=a*x+c;
													}
												
											}
												
											Thread.sleep(800);
											panel_Dessin.update(g2);
											drawAll();
										
										}
										panel_Dessin.update(g2);
										drawAll();	
								}else {
									int h=0,f=0,t=1;
									int w=(int) Math.abs(fin.getY()-y);
									if(w<4) {
										h=1;
									}
									else
										h=w/4;
									
									while((f<4 && t<2 ) ) {
										g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
											if(h==1) t++;
										if(y> fin.getY()) {
											y-=h;
											
										}else {
												if(y<fin.getY()) {
														y+=h;
												}
										}
										Thread.sleep(800);
										panel_Dessin.update(g2);
										drawAll();
										f++;
									}
									g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
									Thread.sleep(800);
									panel_Dessin.update(g2);
									drawAll();
								}
							
										
										
						}
							
						    tempf = printTime();
													
							
							//label_1.setText("debut :"+debut.getX()+"et"+debut.getY()+"\nfin :"+fin.getX()+" et "+fin.getY());
						    String resultat="";
						  //  s = s.replace(s.substring(s.length()-1), "");
						    s=s.substring(0, s.length() - 1);
						    resultat=" Ping :  "+s.substring(0, s.length() - 1);
						    textAreaResultat.setText(resultat+"\n Temps de début : "+tempd+"\n Temps de fin : "+tempf+"\n Débit totale : "+ 
							dijkstra.getShortestDistance(nodes.get(i)));
						    

						    
							}
							catch(Exception e1) {
								textAreaResultat.setText("Pas de connexion entre les périphérique ");
							}
							

							
							testdejic=null;
					}
						
						
						
						
						
					}
					if(rdbtnDataTest.isSelected()) {
						if(testdejic==null||url=="") {
							panel_Dessin.update(g2);
							testdejic=courant;
							JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());

							int returnValue = jfc.showOpenDialog(null);
							// int returnValue = jfc.showSaveDialog(null);

							if (returnValue == JFileChooser.APPROVE_OPTION) {
								File selectedFile = jfc.getSelectedFile();
								//System.out.println(selectedFile.getAbsolutePath());
								url=selectedFile.getAbsolutePath();
								
							}
							drawAll();
						}else {
						
							panel_Dessin.update(g2);
							
							drawAll();
							
								graph2 = new Graph(nodes, arcs);
									
								
								dijkstra = new Routage(graph2);
								int i=0;
								for (Node node : nodes) {
									if(node==testdejic) {
										break;
									}
									i++;
								}
								
								dijkstra.execute(nodes.get(i));
								
								 i=0;
								for (Node node : nodes) {
									if(node==courant) {
										break;
									}
									i++;
								}
														
								path = dijkstra.getPath(nodes.get(i));
								
								if(path!=null) {
									
								
								tempd = printTime();
							
								Application envoi1=new Application(url,path.getLast());
								//System.out.println(path.get(path.size()-1).getName());
								
								}
								try {
									String s="";
								for (Node vertex : path) {
									s+=vertex+" ->";
								}
								
									for(int k=0;k<path.size()-1;k++) {
										debut=new Point((int)path.get(k).getRs().getCenterX()-20,(int)path.get(k).getRs().getCenterY()-20);
										fin=new Point((int)path.get(k+1).getRs().getCenterX()-20,(int)path.get(k+1).getRs().getCenterY()-20);
										x=debut.getX();
										y=debut.getY();
										if(fin.getX()+5< x || fin.getX()-5> x) {
											a=(fin.getY()-debut.getY())/(fin.getX()-debut.getX());
											c=debut.getY()-a*debut.getX();
											//y=xa+c;
											int z=(int)Math.sqrt(Math.pow((fin.getX()-x),2)+ Math.pow((fin.getY()-y),2));
											int d=z/5;
											int h=d;
												while(h<z-1) {
													h+=d;
													g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
											if(x> fin.getX() ) {
															x-=d;
															y=a*x+c;
														
														}else {
															if(x<fin.getX()) {
																x+=d;
																y=a*x+c;
															}
														
													}
														
													Thread.sleep(800);
													panel_Dessin.update(g2);
													drawAll();
												
												}
												panel_Dessin.update(g2);
												drawAll();	
										}else {
											int h=0,f=0,t=1;
											int w=(int) Math.abs(fin.getY()-y);
											if(w<4) {
												h=1;
											}
											else
												h=w/4;
											
											while((f<4 && t<2 ) ) {
												g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
													if(h==1) t++;
												if(y> fin.getY()) {
													y-=h;
													
												}else {
														if(y<fin.getY()) {
																y+=h;
														}
												}
												Thread.sleep(800);
												panel_Dessin.update(g2);
												drawAll();
												f++;
											}
											
											g2.drawImage(img,(int)x-20,(int)y-20,40,40,null);
											Thread.sleep(800);
											panel_Dessin.update(g2);
											drawAll();
										}
									
												
												
								}
									
								    tempf = printTime();

									//label_1.setText("debut :"+debut.getX()+"et"+debut.getY()+"\nfin :"+fin.getX()+" et "+fin.getY());
								    String resultat="";
								  //  s = s.replace(s.substring(s.length()-1), "");
								    s=s.substring(0, s.length() - 1);
								    resultat=" Ping :  "+s.substring(0, s.length() - 1);
								    textAreaResultat.setText(resultat+"\n nombre de segment : "+Transport.slo+"\n Temps de debut : "+tempd+"\n Temps de fin : "+tempf+"\n Debit totale : "+ 
									dijkstra.getShortestDistance(nodes.get(i))+"\n message reçue :"+path.get(path.size()-1).a.convertioninverse());
								    path.get(path.size()-1).a.clean();
									}
									catch(Exception e1) {
										textAreaResultat.setText("Pas de connexion entre les p�riph�rique ");
									}
									
								
								testdejic=null;
						}
					}
					
					
				}
					
					
					
					
					
					
				
				
				lastPointPress = p;
			//	System.out.println("mouse pressed initialisation du last poinPoin pressed  avec  p ( "+lastPointPress.getX()+"  "+
			//			lastPointPress.getY()+"  )");
			
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				//System.out.print("mouse released ");
				lastPointPress=e.getPoint();
				if(courant==null) {
					//System.out.print(" avec courant =null ");
				if(rdbtnCable.isSelected()) {
					//System.out.println(" et radiobutton = cable pour rien  ");
					panel_Dessin.update(g2);
					drawAll();
				}
				if(rdbtnPc.isSelected()) {
					//System.out.println(" et radiobutton = pc dessin pc");
					panel_Dessin.update(g2);
					xFin=e.getX();
					yFin=e.getY();		
					nodes.add(new pc("PC"+(nombreInstance(new pc())+1),new Rectangle2D.Float((float)xFin-20,(float)yFin-20,40,40)));
					drawAll();
	
				}
				
				if(rdbtnHub.isSelected()) {
					//System.out.println(" et radiobutton = pc dessin hub");
					panel_Dessin.update(g2);
					xFin=e.getX();
					yFin=e.getY();					
					nodes.add(new Hub("Hub"+(nombreInstance(new Hub())+1),new Rectangle2D.Float((float)xFin-20,(float)yFin-20,50,50)));
					drawAll();
				}
				if(rdbtnSwitch.isSelected()) {
					//System.out.println(" et radiobutton = pc dessin switch");
					panel_Dessin.update(g2);
					xFin=e.getX();
					yFin=e.getY();					
					nodes.add(new Switch("Switch"+(nombreInstance(new Switch())+1),new Rectangle2D.Float((float)xFin-20,(float)yFin-20,40,50)));
					drawAll();
	
				}
				if(rdbtnRouter.isSelected()) {
					//System.out.println(" et radiobutton = pc dessin  router");
					panel_Dessin.update(g2);
					xFin=e.getX();
					yFin=e.getY();					
					nodes.add(new Routeur("Routeur"+(nombreInstance(new Routeur())+1),new Rectangle2D.Float((float)xFin-20,(float)yFin-20,60,30)));
					drawAll();
					
				}
				drawAll();
				}else {
					//System.out.println("courant = "+courant.getName());
					
					if (rdbtnDelete.isSelected()) {	
						

						if(find(e.getPoint())!=null){
							ArrayList<Arc> arcdelete=new ArrayList<>();
						for (Arc arc : arcs) {
							if(arc.getSource()==courant&&arc.getDestination()==find(e.getPoint())) {
								arcdelete.add(arc);
							}
						}
						for (Arc arc : arcs) {
							if(arc.getSource()==find(e.getPoint())&&arc.getDestination()==courant) {
								arcdelete.add(arc);
							}
						}
						arcs.removeAll(arcdelete);
						panel_Dessin.update(g2);
						drawAll();
						}
						
					}
					if(rdbtnCable.isSelected()) {
						//System.out.print(" et radio button = cable");
						if(courant==null) {
							panel_Dessin.update(g2);
							drawAll();
							//System.out.println("     et courant =null");
						}else {
							//System.out.print("            et courant existe");
							panel_Dessin.update(g2);
							xFin=e.getX();
							yFin=e.getY();
							boolean b=false;
							for (Arc arc : arcs) {
								if((arc.getSource()==courant&&arc.getDestination()==find(e.getPoint()))) {	
									//System.out.println("             ce cable existe deja il existe deja frome origine  ");
									b=true;
									
								}
								if((arc.getSource()==find(e.getPoint())&&arc.getDestination()==courant)) {	
								//	System.out.println("             ce cable existe deja  frome destination ");
									b=true;
								}
							}
							
							if (!b && find(e.getPoint())!=null ) {
								boolean i=false;
								for (Arc arc : arcs) {
									if ((courant instanceof pc) && (arc.getSource()==courant||arc.getDestination()==courant)) {
										i=true;
									}
									if ((find(e.getPoint()) instanceof pc) && (arc.getSource()==find(e.getPoint())||arc.getDestination()==find(e.getPoint()))) {
										i=true;
									}
									
								}
							if((courant instanceof pc && find(e.getPoint())instanceof pc)||i||(courant==find(e.getPoint()))) {
								panel_Dessin.update(g2);
								drawAll();
								}else {
									drawAll();
									try {
									 int taille= Integer.parseInt(JOptionPane.showInputDialog("Donner la taille (Max 100Mbps)"));										
									if(taille<=100 && taille>0) {
									 //	System.out.println("la taille est "+taille );
										arcs.add(new Arc(courant,find(e.getPoint()),taille));	
										arcs.add(new Arc(find(e.getPoint()),courant,taille));
										//System.out.println("   ajouter    un cable");
										drawAll();}else {
											drawAll();
										}
									}catch(Exception ehh) {
										JOptionPane.showMessageDialog(frmSimulationTcpip,
											    "veuillez saisir un entier",
											    "Avertissement",
											    JOptionPane.WARNING_MESSAGE);
									}

								}
							
							}else {
								panel_Dessin.update(g2);
								drawAll();
							}
							
					}
					}
				}
				
			
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				//System.out.print("mouse clicked ");
				
				if(courant==null) {
					
					//System.out.println("et courant =null de mause clicked");
					drawAll();
				}else {
					//System.out.println("et courant =="+courant.getName());
					if(rdbtnDelete.isSelected()) {
						ArrayList<Arc> arcdelete=new ArrayList<>();
					for (Arc arc : arcs) {
						
						if(arc.getSource()==courant) {
							arcdelete.add(arc);
						}
						if (arc.getDestination()==courant) {
							arcdelete.add(arc);
						}
					}
					arcs.removeAll(arcdelete);
					int i=0;
					for (Node node : nodes) {
						
						if(node==courant)
						{//System.out.println("  "+nodes.indexOf(courant)+"        "+node.getName()+"><<<<<<<<" );
						break;
						}
						i++;
					}	
				
					nodes.remove(i);
					panel_Dessin.update(g2);
					drawAll();
					}
					if(rdbtnPc.isSelected()||rdbtnHub.isSelected()||rdbtnRouter.isSelected()||rdbtnSelect.isSelected()
							||rdbtnSwitch.isSelected()) {
					if(courant instanceof pc) {
						for(Node node:nodes) {
							if(node==courant) {
								drawAll();
								//System.out.print(" nom du  lpc "+node.getName());	
								PcConfigurationDialog pcconfiguration = new PcConfigurationDialog(null, "configuration de "+node.getName(), true,(pc)node);
						        DialogInfoPc zInfo = pcconfiguration.showZDialog(); 
						        if(zInfo.isConfigured()) {
						        	//System.out.println("pc bien configurer");
						        	node.setNom(zInfo.getNom());
						        	((pc) node).setIp(zInfo.getAddressIP());
						        	((pc) node).setAddressMac(zInfo.getAddressMac());
						        	((pc) node).setPasserelle(zInfo.getPasserelle());
						        	((pc) node).setMasque(zInfo.getMasque());
						        	drawAll();
						        }else {
						        	drawAll();
						        //	System.out.println("configuration annuler");

						        }
						        //JOptionPane jop = new JOptionPane();
						       // jop.showMessageDialog(null,zInfo.toString(), "Informations de "+node.getName(), JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					if(courant instanceof Hub) {
						for(Node node:nodes) {
							if(node==courant) {
								drawAll();
								//System.out.print("nom du  Hub "+node.getName());
								HubConfigurationDialog zd = new HubConfigurationDialog(null, "configuration de "+node.getName(), true,node,arcs);
						        DialogInfoHub zInfo = zd.showZDialog(); 
						        if(zInfo.isConfigured()) {
						        //	System.out.println("hub bien configurer");
						        	node.setNom(zInfo.getNom());
						        	((Hub) node).setAdresse_mac(zInfo.getAddressMac());
						        }else {
						        	drawAll();
						        	System.out.println("configuration annuler");

						        }
						       // JOptionPane jop = new JOptionPane();
						       // jop.showMessageDialog(null,zInfo.toString(), "Informations "+node.getName(), JOptionPane.INFORMATION_MESSAGE);
							}
						}}
					if(courant instanceof Switch) {
						for(Node node:nodes) {
							if(node==courant) {
								drawAll();
								//System.out.println("nom du  switch  "+node.getName());
								SwitchConfigurationDialog zd = new SwitchConfigurationDialog(null, "configuration de "+node.getName(), true,node,arcs);
						        DialogInfoSwitch zInfo = zd.showZDialog(); 
						        if(zInfo.isConfigured()) {
						        	//System.out.println(" Switch bien configurer");
						        	node.setNom(zInfo.getNom());
						        	((Switch) node).setAdresse_mac(zInfo.getAddressMac());
						        }else {
						        	drawAll();
						        	//System.out.println("configuration annuler ");
						        }
						     //  JOptionPane jop = new JOptionPane();
						     //   jop.showMessageDialog(null,zInfo.toString(), "Informations "+node.getName(), JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					if(courant instanceof Routeur) {
						for(Node node:nodes) {
							if(node==courant) {
								drawAll();
							//	System.out.println("nom du routeur "+node.getName());
								RouteurConfigurationDialog zd = new RouteurConfigurationDialog(null, "configuration de "+node.getName(), true,(Routeur)node,arcs);
						        DialogInfoRouteur zInfo = zd.showZDialog(); 
						        if(zInfo.isConfigured()) {
						        //	System.out.println("router bien configurate");
						        	node.setNom(zInfo.getNom());
						        }else {
						        	drawAll();
						        	//System.out.println("configuration annuler");

						        }
						     //   JOptionPane jop = new JOptionPane();
						     //   jop.showMessageDialog(null,zInfo.toString(), "Informations "+node.getName(), JOptionPane.INFORMATION_MESSAGE);
							}
						}
					}
					
					}

					
				}
			}
		});

		panel_Dessin.setBackground(Color.WHITE);
		panel_ZoneDeTravaile.setLayout(new BorderLayout(0, 0));
		panel_ZoneDeTravaile.add(zonetravail);
		panel_ZoneDeTravaile.add(panel_Dessin);
		
		
		
		panel_Outils = new JPanel();
		panel_Outils.setBorder(new LineBorder(new Color(0, 0, 0)));
		
		panel = new JPanel();
		panel.setBorder(new LineBorder(new Color(0, 0, 0)));
		GroupLayout gl_panel_ChoixAndResultat = new GroupLayout(panel_ChoixAndResultat);
		gl_panel_ChoixAndResultat.setHorizontalGroup(
			gl_panel_ChoixAndResultat.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_panel_ChoixAndResultat.createSequentialGroup()
					.addComponent(panel_Outils, GroupLayout.PREFERRED_SIZE, 308, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(panel, GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE))
		);
		gl_panel_ChoixAndResultat.setVerticalGroup(
			gl_panel_ChoixAndResultat.createParallelGroup(Alignment.LEADING)
				.addComponent(panel_Outils, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
				.addComponent(panel, GroupLayout.DEFAULT_SIZE, 74, Short.MAX_VALUE)
		);
		panel.setLayout(new GridLayout(1, 0, 0, 0));
		
		scrollPane = new JScrollPane();
		panel.add(scrollPane);
		
		 textAreaResultat = new JTextArea();
		 textAreaResultat.setFont(new Font("Monospaced", Font.BOLD, 14));
		 textAreaResultat.setText("R\u00E9sultat");
		textAreaResultat.setEditable(false);
		scrollPane.setViewportView(textAreaResultat);
		
		panel_Outils.setLayout(null);
		
		lblOrdinateur = new JLabel("ORDINATEUR");
		lblOrdinateur.setFont(new Font("Century Gothic", Font.BOLD, 10));
		lblOrdinateur.setBounds(60, 52, 70, 14);
		panel_Outils.add(lblOrdinateur);
		
		lblHub = new JLabel("HUB");
		lblHub.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblHub.setBounds(145, 52, 28, 14);
		panel_Outils.add(lblHub);
		
		lblSwitch = new JLabel("SWITCH");
		lblSwitch.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblSwitch.setBounds(199, 52, 46, 14);
		panel_Outils.add(lblSwitch);
		
		lblRouteur = new JLabel("ROUTEUR");
		lblRouteur.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblRouteur.setBounds(251, 52, 50, 12);
		panel_Outils.add(lblRouteur);
		
		lblCable = new JLabel();
		lblCable.setFont(new Font("Century Gothic", Font.BOLD, 11));
		lblCable.setText("CABLE");
		lblCable.setBounds(12, 52, 46, 14);
		panel_Outils.add(lblCable);
		
		rdbtnCable = new JRadioButton(new ImageIcon(imagefolder+"cable.png"));
		lblCable.setLabelFor(rdbtnCable);
		rdbtnCable.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnCable.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent arg0) {
				rdbtnCable.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnCable.setToolTipText("c�ble");
		rdbtnCable.setBackground(SystemColor.scrollbar);
		rdbtnCable.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnCable.isSelected()) {
					rdbtnCable.setBackground(Color.GREEN);
				}else {
					rdbtnCable.setBackground(SystemColor.scrollbar);
				}
			}
		});
		rdbtnCable.setBounds(3, 5, 50, 50);
		panel_Outils.add(rdbtnCable);
		
		rdbtnHub = new JRadioButton(new ImageIcon(imagefolder+"lsr.png"));
		lblHub.setLabelFor(rdbtnHub);
		rdbtnHub.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnHub.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnHub.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnHub.setToolTipText("hub");
		rdbtnHub.setBackground(SystemColor.scrollbar);
		rdbtnHub.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnHub.isSelected()) {
					rdbtnHub.setBackground(Color.GREEN);
				}else {
					rdbtnHub.setBackground(SystemColor.scrollbar);
				}
			}
		});
		rdbtnHub.setBounds(129, 5, 50, 50);
		panel_Outils.add(rdbtnHub);
		
		rdbtnPc = new JRadioButton(new ImageIcon(imagefolder+"receptor.png"));
		lblOrdinateur.setLabelFor(rdbtnPc);
		rdbtnPc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnPc.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnPc.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnPc.setToolTipText("ordinateur");
		rdbtnPc.setBackground(SystemColor.scrollbar);
		rdbtnPc.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnPc.isSelected()) {
					rdbtnPc.setBackground(Color.GREEN);
				}else {
					rdbtnPc.setBackground(SystemColor.scrollbar);
				}
				
			}
		});
		rdbtnPc.setBounds(65, 5, 50, 50);
		panel_Outils.add(rdbtnPc);
		
		outils.add(rdbtnCable);
		outils.add(rdbtnHub);
		outils.add(rdbtnPc);
		
		rdbtnSwitch = new JRadioButton(new ImageIcon(imagefolder+"switch.png"));
		lblSwitch.setLabelFor(rdbtnSwitch);
		rdbtnSwitch.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnSwitch.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnSwitch.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnSwitch.setToolTipText("switch");
		rdbtnSwitch.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnSwitch.isSelected()) {
					rdbtnSwitch.setBackground(Color.GREEN);
				}else {
					rdbtnSwitch.setBackground(SystemColor.scrollbar);
				}
				
			}
			
		});
		rdbtnSwitch.setBackground(SystemColor.scrollbar);
		rdbtnSwitch.setBounds(191, 5, 50, 50);
		panel_Outils.add(rdbtnSwitch);
		outils.add(rdbtnSwitch);
		
		rdbtnRouter = new JRadioButton(new ImageIcon(imagefolder+"ler.png"));
		lblRouteur.setLabelFor(rdbtnRouter);
		rdbtnRouter.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				function.clearSelection();
			}
		});
		rdbtnRouter.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnRouter.setBackground(SystemColor.controlShadow);
			}
		});
		rdbtnRouter.setToolTipText("routeur");
		rdbtnRouter.setBackground(SystemColor.scrollbar);
		rdbtnRouter.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnRouter.isSelected()) {
					rdbtnRouter.setBackground(Color.GREEN);
				}else {
					rdbtnRouter.setBackground(SystemColor.scrollbar);
				}
				
			}
		});
		rdbtnRouter.setBounds(251, 5, 50, 50);
		panel_Outils.add(rdbtnRouter);
		outils.add(rdbtnRouter);
		panel_ChoixAndResultat.setLayout(gl_panel_ChoixAndResultat);
		panel_Function.setLayout(null);
		
		rdbtnSelect = new JRadioButton(new ImageIcon(imagefolder+"if_hand_cursor_2639827 (3).png"));
		rdbtnSelect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				outils.clearSelection();
			}
		});
		rdbtnSelect.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent arg0) {
				if(rdbtnSelect.isSelected()) {
					rdbtnSelect.setBackground(Color.GREEN);
				}else {
					rdbtnSelect.setBackground(SystemColor.inactiveCaption);
				}
			}
		});
		rdbtnSelect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnSelect.setBackground(SystemColor.activeCaption);
			}
		});
		rdbtnSelect.setBounds(6, 6, 40, 40);
		rdbtnSelect.setToolTipText("s�lectionn�");
		
		rdbtnSelect.setBackground(SystemColor.inactiveCaption);
		panel_Function.add(rdbtnSelect);
		
		rdbtnDelete = new JRadioButton(new ImageIcon(imagefolder+"if_meanicons_24_197210.png"));
		rdbtnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outils.clearSelection();
			}
		});
		rdbtnDelete.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnDelete.setBackground(SystemColor.activeCaption);
			}
		});
		rdbtnDelete.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnDelete.isSelected()) {
					rdbtnDelete.setBackground(Color.GREEN);
				}else {
					rdbtnDelete.setBackground(SystemColor.inactiveCaption);
				}
			}
		});
		rdbtnDelete.setBounds(6, 56, 40, 40);
		rdbtnDelete.setToolTipText("effacer");
		rdbtnDelete.setBackground(SystemColor.inactiveCaption);
		panel_Function.add(rdbtnDelete);
		
		rdbtnTest = new JRadioButton(new ImageIcon(imagefolder+"if_message_172503.png"));
		rdbtnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				outils.clearSelection();
			}
		});
		rdbtnTest.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				rdbtnTest.setBackground(SystemColor.activeCaption);
			}
		});
		rdbtnTest.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				if(rdbtnTest.isSelected()) {
					rdbtnTest.setBackground(Color.GREEN);
				}else {
					rdbtnTest.setBackground(SystemColor.inactiveCaption);
				}
			}
		});
		rdbtnTest.setBackground(SystemColor.inactiveCaption);
		rdbtnTest.setToolTipText("verifier la connection");
		rdbtnTest.setBounds(6, 106, 40, 40);
		panel_Function.add(rdbtnTest);
		
		 rdbtnDataTest = new JRadioButton(new ImageIcon(imagefolder+"if_ic_attach_file_48px_352032.png"));
		 rdbtnDataTest.addActionListener(new ActionListener() {
		 	public void actionPerformed(ActionEvent e) {
		 		outils.clearSelection();
		 	}
		 });
		 rdbtnDataTest.addMouseListener(new MouseAdapter() {
		 	@Override
		 	public void mouseEntered(MouseEvent e) {
		 		rdbtnDataTest.setBackground(SystemColor.activeCaption);
		 	}
		 	
		 });
		 rdbtnDataTest.addChangeListener(new ChangeListener() {
		 	public void stateChanged(ChangeEvent e) {
		 		if(rdbtnDataTest.isSelected()) {
					rdbtnDataTest.setBackground(Color.GREEN);
				}else {
					rdbtnDataTest.setBackground(SystemColor.inactiveCaption);
				}
		 	}
		 });
		rdbtnDataTest.setBackground(SystemColor.inactiveCaption);
		rdbtnDataTest.setToolTipText("envoyer un fichier");
		rdbtnDataTest.setBounds(6, 156, 40, 40);
		panel_Function.add(rdbtnDataTest);
		frmSimulationTcpip.getContentPane().setLayout(groupLayout);
		function.add(rdbtnDataTest);
		function.add(rdbtnTest);
		function.add(rdbtnDelete);
		function.add(rdbtnSelect);
	}
	 private void drawAll() {
		// System.out.println("taille d'arc est  "+arcs.size());
			if(g2==null) {
				g2=(Graphics2D) panel_Dessin.getGraphics();
			}
			graph2=new Graph(nodes,arcs);
		 for (Arc arc : arcs) {
			 Point ps=new Point();
			 ps.setLocation(arc.getSource().getRs().getCenterX(), 
					 arc.getSource().getRs().getCenterY());
			 Point pd=new Point();
			 pd.setLocation(arc.getDestination().getRs().getCenterX(), 
					 arc.getDestination().getRs().getCenterY());
			 //if( arc.getDestino()!=arc.getOrigen()) {
				g2.draw(new Line2D.Float(ps,pd));
				g2.drawString(String.valueOf(arc.getWeight())+" Mbps", (int) (ps.x+pd.x)/2,(int) (ps.y+pd.y)/2);
				 //System.out.print("lorigine ="+arc.getSource().getName()+"  destination  "+arc.getDestination().getName()+"  "+arc.getWeight()+"\n");
			//g2.draw(new Line2D.Float(arc.getDepart(),arc.getArrive()));
		}
		 try {
            // image = ImageIO.read(getClass().getResource("/icons8-hub-32.png"));
             for (Node node : nodes) {
            	 if (node instanceof pc){
            		 image = ImageIO.read(getClass().getResource("/receptor.png"));
            		 g2.drawImage(image,(int) node.getRs().getCenterX()-20, (int) node.getRs().getCenterY()-20, null);	
            		 g2.drawString(node.getName(), (int) node.getRs().getCenterX()+5,(int) node.getRs().getCenterY()-20);
            	 }
            	 if (node instanceof Hub){
            		 image = ImageIO.read(getClass().getResource("/lsr.png"));
            		 g2.drawImage(image,(int) node.getRs().getCenterX()-20, (int) node.getRs().getCenterY()-20, null);
            		 g2.drawString(node.getName(), (int) node.getRs().getCenterX()+5,(int) node.getRs().getCenterY()-20);
            	 }
            	 if (node instanceof Switch){
            		 image = ImageIO.read(getClass().getResource("/switch.png"));
            		 g2.drawImage(image,(int) node.getRs().getCenterX()-20, (int) node.getRs().getCenterY()-20, null);
            		 g2.drawString(node.getName(), (int) node.getRs().getCenterX()+5,(int) node.getRs().getCenterY()-20);
            	 }
            	 if (node instanceof Routeur){
            		 image = ImageIO.read(getClass().getResource("/ler.png"));
            		 g2.drawImage(image,(int) node.getRs().getCenterX()-20, (int) node.getRs().getCenterY()-20, null);	
            		 g2.drawString(node.getName(), (int) node.getRs().getCenterX()+5,(int) node.getRs().getCenterY()-20);
            	 }
    			// System.out.print("node "+node.getRs().getCenterX()+node.getClass().getSimpleName());
    			 //g2.fill(node.getRs());
            	 //  g2.drawImage(image,(int) node.getRs().getCenterX(), (int) node.getRs().getCenterY(), null);	
    				
    			}
         } catch (IOException ex) {
             ex.printStackTrace();
         }
		 
		 
		/* 
		 for (Node node : nodes) {
			 System.out.print("node "+node.getRs().getCenterX()+node.getClass().getSimpleName());
			 g2.fill(node.getRs());
				
			}
		 */
	 }
	 public Node find(Point2D p) {

			for (Node node : nodes) {
				if (node.getRs().contains(p)) {
					return node;
				}
			}
			return null;
		}
	private  int nombreInstance(Node n){
		int i=0;
		if(n instanceof pc) {
			for (Node node : nodes) {
				if(node instanceof pc) {
				i++;	
				}
			}
		}
		if(n instanceof Routeur) {
			for (Node node : nodes) {
				if(node instanceof Routeur) {
				i++;	
				}
			}
		}
		if(n instanceof Hub) {
			for (Node node : nodes) {
				if(node instanceof Hub) {
				i++;	
				}
			}
		}
		if(n instanceof Switch) {
			for (Node node : nodes) {
				if(node instanceof Switch) {
				i++;	
				}
			}
		}
		return i;
	}
}


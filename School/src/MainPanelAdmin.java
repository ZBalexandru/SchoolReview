import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.sql.*;
import java.text.DecimalFormat;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

import net.proteanit.sql.DbUtils;

import java.awt.CardLayout;
import java.awt.event.MouseMotionAdapter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.AbstractListModel;
import javax.swing.JSeparator;
import javax.swing.JTextPane;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.TextArea;

public class MainPanelAdmin extends JFrame{
	private JTextField txtNume;
	private JTextField txtPrenume;
	
	private JTable tableElevi;
	private JScrollPane scrollPane;
	private JTextField txtCifra;
	private JTextField txtLitera;
	private JTextField txtIdClasa;
	private JTextField textFieldFilter;
	private JComboBox comboBoxCauta;
	
	private int mouseX;
	private int mouseY;
	private int idProf;
	
	private final JLabel schoolimage = new JLabel("");
	private final JPanel MainPanelElev = new JPanel();
	private final JPanel MainPanelProfesori = new JPanel();
	private final JPanel MainPanelAcasa = new JPanel();
	private final JPanel MainPanelRecenzii = new JPanel();
	private JTextField txtNumeProf;
	private JTextField txtPrenumeProf;
	private JTextField txtMaterie;
	private JTable tableProfesori;
	private JTable tabelProf2;
	private JTable tabelRecenzii;
	private JTextArea textArea = new JTextArea();
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
	
	public MainPanelAdmin() throws SQLException, ClassNotFoundException{
		setTitle("Administrator");
		
		setUndecorated(true);
		initVeziRecenzii();
		initAdaugaProfesori();
		initAdaugaElevi();
		initAcasa();
		MainPanelRecenzii.setVisible(false);
		MainPanelElev.setVisible(false);
		MainPanelProfesori.setVisible(false);
		MainPanelAcasa.setVisible(true);
		
		MainPanelElev.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelElev.setCursor(cursor.getDefaultCursor());
			}
		});
		MainPanelProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor.getDefaultCursor());
			}
		});	
		MainPanelRecenzii.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelRecenzii.setCursor(cursor.getDefaultCursor());
			}
		});
		
		
        
		Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
		//Connect
		String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "root";
		String pass = "root";
		
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
	
		
		
		getContentPane().setLayout(null);
		getContentPane().setBackground(new Color(35,152,171));
		
		JPanel topPanel = new JPanel();
		topPanel.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				int corX = evt.getXOnScreen();
				int corY = evt.getYOnScreen();
				
				setLocation(corX-mouseX, corY-mouseY);
			}
		});
		topPanel.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		topPanel.setBounds(0, 0, 878, 35);
		getContentPane().add(topPanel);
		topPanel.setBackground(new Color(153, 153, 204));
		topPanel.setLayout(null);
		
		JLabel exit_1 = new JLabel("X");
		exit_1.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int exit = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa iesi ?");
				if(exit == 0) {
					System.exit(0);
				}
				
			}
		});
		exit_1.setBounds(848, 4, 20, 24);
		topPanel.add(exit_1);
		exit_1.setHorizontalAlignment(SwingConstants.CENTER);
		exit_1.setFont(new Font("Bahnschrift", Font.BOLD, 32));
		
		JLabel minimize = new JLabel("-");
		minimize.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				setState(JFrame.ICONIFIED);		
			}
		});
		minimize.setFont(new Font("Bahnschrift", Font.PLAIN, 46));
		minimize.setBounds(804, 4, 34, 25);
		topPanel.add(minimize);
		
		JPanel sideRecenzii = new JPanel();
		JPanel sidePanel = new JPanel();
		sidePanel.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				sidePanel.setCursor(cursor.getDefaultCursor());
			}
		});
		sidePanel.setBounds(0, -25, 280, 676);
		sidePanel.setBackground(new Color(51, 51, 102));
		getContentPane().add(sidePanel);
		sidePanel.setLayout(null);
		schoolimage.setBounds(10, 74, 259, 179);
		sidePanel.add(schoolimage);
		schoolimage.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/school png.png")));
		
		JPanel panelHome = new JPanel();
		panelHome.setBackground(new Color(204, 204, 255));
		panelHome.setBounds(0, 264, 280, 43);
		sidePanel.add(panelHome);
		panelHome.setLayout(new CardLayout(0, 0));
		
		JLabel lblAcasa = new JLabel("                          Acasa");
		lblAcasa.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent arg0) {
				sidePanel.setCursor(cursor);
			}
		});
		lblAcasa.setForeground(new Color(255, 255, 255));
		lblAcasa.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblAcasa.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_home_page_35px_3.png")));
		panelHome.add(lblAcasa, "name_171157380477300");
		
		JPanel sideElev = new JPanel();
		sideElev.setBackground(new Color(102, 102, 153));
		sideElev.setBounds(0, 307, 280, 43);
		sidePanel.add(sideElev);
		sideElev.setLayout(new CardLayout(0, 0));
		
		JLabel lblAdaugaElevi = new JLabel("                  Adauga Elevi");
		lblAdaugaElevi.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				sidePanel.setCursor(cursor);
			}
		});
		lblAdaugaElevi.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_student_male_35px_1.png")));
		lblAdaugaElevi.setForeground(Color.WHITE);
		lblAdaugaElevi.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		sideElev.add(lblAdaugaElevi, "name_171806096750100");
		
		JPanel sideProfesori = new JPanel();
		sideProfesori.setBackground(new Color(102, 102, 153));
		sideProfesori.setBounds(0, 350, 280, 43);
		sidePanel.add(sideProfesori);
		sideProfesori.setLayout(new CardLayout(0, 0));
		
		JLabel lblAdaugaProfesori = new JLabel("              Adauga Profesori");
		lblAdaugaProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				sidePanel.setCursor(cursor);
			}
		});
		lblAdaugaProfesori.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				MainPanelRecenzii.setVisible(false);
				MainPanelElev.setVisible(false);
				MainPanelProfesori.setVisible(true);
				setColorSide(sideProfesori);
				resetColorSide(sideElev);
				resetColorSide(panelHome);
				resetColorSide(sideRecenzii);
				
				
			}
		});
		lblAdaugaProfesori.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_add_user_male_35px_1.png")));
		lblAdaugaProfesori.setForeground(Color.WHITE);
		lblAdaugaProfesori.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		sideProfesori.add(lblAdaugaProfesori, "name_172082674263100");
		
		sideRecenzii.setBackground(new Color(102, 102, 153));
		sideRecenzii.setBounds(0, 392, 280, 43);
		sidePanel.add(sideRecenzii);
		sideRecenzii.setLayout(new CardLayout(0, 0));
		
		JLabel lblVeziRecenzii = new JLabel("                     Vezi Recenzii");
		lblVeziRecenzii.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				sidePanel.setCursor(cursor);
			}
		});
		lblVeziRecenzii.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				
				MainPanelElev.setVisible(false);
				MainPanelProfesori.setVisible(false);
				MainPanelRecenzii.setVisible(true);
				setColorSide(sideRecenzii);
				resetColorSide(sideElev);
				resetColorSide(sideProfesori);
				resetColorSide(panelHome);
			}
		});
		lblVeziRecenzii.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_file_preview_30px.png")));
		lblVeziRecenzii.setForeground(Color.WHITE);
		lblVeziRecenzii.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		sideRecenzii.add(lblVeziRecenzii, "name_420491200485000");
		
		JButton btnNewButton_1 = new JButton("EMAIL ME");
		btnNewButton_1.setBounds(70, 495, 155, 41);
		sidePanel.add(btnNewButton_1);
		btnNewButton_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Desktop desktop;
				if (Desktop.isDesktopSupported() 
				    && (desktop = Desktop.getDesktop()).isSupported(Desktop.Action.MAIL)) {
				  URI mailto;
				try {
					mailto = new URI("mailto:zbalexandru96@gmail.com");
					desktop.mail(mailto);
				} catch (URISyntaxException | IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				} else {
				  throw new RuntimeException("Desktop doesn't have a default mail app");
				}
			}
		});
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		
							
		// Side Panel Events...	
		lblAcasa.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				MainPanelElev.setVisible(false);
				MainPanelProfesori.setVisible(false);
				MainPanelAcasa.setVisible(true);
				setColorSide(panelHome);
				resetColorSide(sideElev);
				resetColorSide(sideProfesori);
				resetColorSide(sideRecenzii);

			}
		});
		
		lblAdaugaElevi.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
			
				MainPanelElev.setVisible(true);
				setColorSide(sideElev);
				resetColorSide(panelHome);
				resetColorSide(sideProfesori);
				resetColorSide(sideRecenzii);
				MainPanelRecenzii.setVisible(false);
				MainPanelProfesori.setVisible(false);
				
			}
		});
		
	}
	
	public void connectSQL() {
		String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "root";
		String pass = "root";		
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, user, pass);
			Statement st = con.createStatement();
			System.out.println("Connected to the Database");
		} catch (ClassNotFoundException | SQLException e) {
			System.out.println("Error! Not able to connect to the Database !");
			e.printStackTrace();
		}	
		
	}
	
	private void setColorSide(JPanel panel) {
		panel.setBackground(new Color(204,204,255));
	}
	
	private void resetColorSide(JPanel panel) {
		panel.setBackground(new Color(102, 102, 153));		
	}
	
	public void initAdaugaElevi() throws ClassNotFoundException, SQLException{
		///Connect to DB
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String user = "root";
				String pass = "root";	
				
				
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pass);
					Statement st = con.createStatement();
				getContentPane().setLayout(null);
				MainPanelElev.setLayout(null);
				
				JPanel panelCreeazaElev = new JPanel();
				panelCreeazaElev.setBounds(29, 296, 249, 160);
				MainPanelElev.add(panelCreeazaElev);
				panelCreeazaElev.setBackground(new Color(255, 204, 0));
				panelCreeazaElev.setForeground(new Color(255, 255, 255));
				panelCreeazaElev.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, null, null, null));
				panelCreeazaElev.setLayout(null);
				
				JLabel lblCifra = new JLabel("CIFRA");
				lblCifra.setForeground(Color.BLACK);
				lblCifra.setBounds(28, 44, 63, 20);
				panelCreeazaElev.add(lblCifra);
				lblCifra.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				
				JLabel label = new JLabel("ADAUGA");
				label.setToolTipText("Adauga");
				label.setForeground(Color.BLACK);
				label.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				label.setBounds(38, 94, 62, 20);
				panelCreeazaElev.add(label);
				
				JButton btnAdaugaClasa = new JButton("");
				btnAdaugaClasa.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				btnAdaugaClasa.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						//Creeaza Tabelul cu clasa in SQL
						try {
							Statement st = con.createStatement();
							String cifra = txtCifra.getText();
						    String litera = txtLitera.getText();
						  		    
							String createTable = "create table " + cifra + litera.toUpperCase() + "(id_elev int references elevi(id), Nume varchar(40), Prenume varchar(40))";				
							if(TxtCheckforInt(cifra) == false || TxtCheckforInt(litera) == true) {
							JOptionPane.showMessageDialog(btnAdaugaClasa, "Va rugam introduceti informatii valide !");}
							else {
							st.executeUpdate(createTable);
							JOptionPane.showMessageDialog(btnAdaugaClasa, "Clasa " + cifra + litera + " a fost adaugata !");
							}
								
							
						} catch (SQLException e1) {			
							JOptionPane.showMessageDialog(btnAdaugaClasa, "Clasa deja exista !");
						}
					}
				});
				btnAdaugaClasa.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_add_file_35px.png")));
				btnAdaugaClasa.setForeground(Color.BLACK);
				btnAdaugaClasa.setBackground(new Color(255, 255, 204));
				btnAdaugaClasa.setBounds(33, 114, 67, 35);
				panelCreeazaElev.add(btnAdaugaClasa);
				
				JLabel lblAdaugamodificaClasele = new JLabel("Creeaza o clasa de Elevi");
				lblAdaugamodificaClasele.setForeground(Color.BLACK);
				lblAdaugamodificaClasele.setBounds(10, 11, 235, 22);
				panelCreeazaElev.add(lblAdaugamodificaClasele);
				lblAdaugamodificaClasele.setHorizontalAlignment(SwingConstants.CENTER);
				lblAdaugamodificaClasele.setFont(new Font("Bahnschrift", Font.BOLD, 18));
				
				JLabel lblLitera = new JLabel("LITERA");
				lblLitera.setForeground(Color.BLACK);
				lblLitera.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				lblLitera.setBounds(28, 75, 89, 14);
				panelCreeazaElev.add(lblLitera);
				
				txtCifra = new JTextField();
				txtCifra.setText("9");
				txtCifra.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				txtCifra.setColumns(10);
				txtCifra.setBounds(115, 44, 86, 20);
				panelCreeazaElev.add(txtCifra);
				
				txtLitera = new JTextField();
				txtLitera.setText("A");
				txtLitera.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				txtLitera.setColumns(10);
				txtLitera.setBounds(115, 72, 86, 20);
				panelCreeazaElev.add(txtLitera);
				
				JLabel lblSterge_1 = new JLabel("STERGE");
				lblSterge_1.setToolTipText("Adauga");
				lblSterge_1.setForeground(Color.BLACK);
				lblSterge_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				lblSterge_1.setBounds(165, 94, 67, 20);
				panelCreeazaElev.add(lblSterge_1);
				
				JButton btnStergeClasa = new JButton("");
				btnStergeClasa.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						String cifra = txtCifra.getText();
					    String litera = txtLitera.getText();;
						String del = "DROP TABLE "+cifra+litera;
						int delete = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa stergi clasa "+cifra+litera.toUpperCase()+" ?");
						if(delete == 0) {	
						Statement st;	
							try {
								st = con.createStatement();							
								st.executeUpdate(del);
								st.close();
								JOptionPane.showMessageDialog(null, "Clasa "+cifra+litera.toUpperCase()+" a fost stearsa !");
							} catch (SQLException e) {
								e.printStackTrace();
							}
						}	
					}
				});
				btnStergeClasa.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				btnStergeClasa.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_delete_document_35px_2.png")));
				btnStergeClasa.setForeground(Color.BLACK);
				btnStergeClasa.setBackground(new Color(255, 255, 204));
				btnStergeClasa.setBounds(157, 114, 67, 35);
				panelCreeazaElev.add(btnStergeClasa);
				
				JPanel panelAdaugaElevClasa = new JPanel();
				panelAdaugaElevClasa.setBounds(29, 467, 249, 139);
				MainPanelElev.add(panelAdaugaElevClasa);
				panelAdaugaElevClasa.setToolTipText("Va rugam introduceti un ID de elev existent! \r\nDaca acest ID nu exista, niciun elev nu va fi introdus in clasa.");
				panelAdaugaElevClasa.setBackground(new Color(102, 102, 153));
				panelAdaugaElevClasa.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(0, 0, 0), null, null, null));
				panelAdaugaElevClasa.setLayout(null);
				
				JLabel lblClasa_1 = new JLabel("Clasa :");
				lblClasa_1.setForeground(new Color(255, 255, 255));
				lblClasa_1.setBounds(43, 54, 63, 20);
				panelAdaugaElevClasa.add(lblClasa_1);
				lblClasa_1.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				
				JComboBox comboBoxClasa = new JComboBox();
				comboBoxClasa.setBounds(116, 56, 86, 20);
				panelAdaugaElevClasa.add(comboBoxClasa);
				comboBoxClasa.setBackground(new Color(255, 255, 255));
				
				txtIdClasa = new JTextField();
				txtIdClasa.setBounds(116, 32, 86, 20);
				panelAdaugaElevClasa.add(txtIdClasa);
				txtIdClasa.setText("ID");
				txtIdClasa.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				txtIdClasa.setColumns(10);
				JButton btnAdauga = new JButton("");
				btnAdauga.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				btnAdauga.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_add_file_35px.png")));
				btnAdauga.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						//Adauga Elev intr-o clasa
						String valueBox = (String) comboBoxClasa.getSelectedItem();
						String valueTxt = txtIdClasa.getText();						
						String query2= "update elevi set clasa_elevului='"+valueBox+"' where id="+valueTxt;
						
						if(valueTxt.isEmpty() || TxtCheckforInt(valueTxt) == false) {
							JOptionPane.showMessageDialog(btnAdauga, "Va rugam introduceti ID-ul elevului");
						}
						else {try {						
							st.executeUpdate(query2);
							JOptionPane.showMessageDialog(btnAdauga, "Elevul a fost adaugat in clasa " + valueBox);
							showElevi();
						} catch (SQLException | ClassNotFoundException e) {
							JOptionPane.showMessageDialog(btnAdauga, "Va rugam selectati o clasa");
							e.printStackTrace();
						}}
					}
				});
				
				
			    
				
								btnAdauga.setBounds(108, 91, 45, 37);
								panelAdaugaElevClasa.add(btnAdauga);
								btnAdauga.setFont(new Font("Bahnschrift", Font.BOLD, 17));
								
								JLabel lblIdElev = new JLabel("ID Elev");
								lblIdElev.setForeground(new Color(255, 255, 255));
								lblIdElev.setBounds(43, 32, 63, 20);
								panelAdaugaElevClasa.add(lblIdElev);
								lblIdElev.setFont(new Font("Bahnschrift", Font.BOLD, 16));
								
								JLabel lblAdaugaElevIntro = new JLabel("Adauga Elev intr-o clasa");
								lblAdaugaElevIntro.setForeground(new Color(255, 255, 255));
								lblAdaugaElevIntro.setBounds(0, 0, 246, 22);
								panelAdaugaElevClasa.add(lblAdaugaElevIntro);
								lblAdaugaElevIntro.setHorizontalAlignment(SwingConstants.CENTER);
								lblAdaugaElevIntro.setFont(new Font("Bahnschrift", Font.BOLD, 18));
								
								JLabel lblAdauga_1 = new JLabel("ADAUGA :");
								lblAdauga_1.setToolTipText("Adauga");
								lblAdauga_1.setForeground(Color.WHITE);
								lblAdauga_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
								lblAdauga_1.setBounds(22, 97, 84, 20);
								panelAdaugaElevClasa.add(lblAdauga_1);
				
				JPanel panelAdaugaElev = new JPanel();
				panelAdaugaElev.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor.getDefaultCursor());
					}
				});
				panelAdaugaElev.setBounds(29, 63, 249, 222);
				MainPanelElev.add(panelAdaugaElev);
				panelAdaugaElev.setBackground(new Color(51, 51, 102));
				panelAdaugaElev.setBorder(new BevelBorder(BevelBorder.RAISED, Color.DARK_GRAY, null, null, null));
				panelAdaugaElev.setLayout(null);
				
				
				JComboBox comboBox = new JComboBox();
				comboBox.setBounds(124, 133, 91, 20);
				panelAdaugaElev.add(comboBox);
				comboBox.setBackground(Color.WHITE);
				
				JLabel lblAdauga = new JLabel("Adauga/Modifica Elev");
				lblAdauga.setForeground(new Color(255, 255, 255));
				lblAdauga.setBounds(35, 11, 196, 22);
				panelAdaugaElev.add(lblAdauga);
				lblAdauga.setFont(new Font("Bahnschrift", Font.BOLD, 18));
				lblAdauga.setHorizontalAlignment(SwingConstants.CENTER);
				
				JLabel lblNume = new JLabel("NUME");
				lblNume.setForeground(new Color(255, 255, 255));
				lblNume.setBounds(35, 80, 57, 14);
				panelAdaugaElev.add(lblNume);
				lblNume.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				
				JLabel lblPrenume = new JLabel("PRENUME");
				lblPrenume.setForeground(new Color(255, 255, 255));
				lblPrenume.setBounds(35, 105, 89, 14);
				panelAdaugaElev.add(lblPrenume);
				lblPrenume.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				
				JLabel lblClasa = new JLabel("CLASA");
				lblClasa.setForeground(new Color(255, 255, 255));
				lblClasa.setBounds(35, 134, 57, 14);
				panelAdaugaElev.add(lblClasa);
				lblClasa.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				
				txtNume = new JTextField();
				txtNume.setBounds(124, 77, 86, 20);
				panelAdaugaElev.add(txtNume);
				txtNume.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				txtNume.setColumns(10);
				
				txtPrenume = new JTextField();
				txtPrenume.setBounds(124, 102, 86, 20);
				panelAdaugaElev.add(txtPrenume);
				txtPrenume.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				txtPrenume.setColumns(10);
				
				JLabel lblNewLabel_2 = new JLabel("ADAUGA");
				lblNewLabel_2.setForeground(new Color(255, 255, 255));
				lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				lblNewLabel_2.setToolTipText("Adauga");
				lblNewLabel_2.setBounds(21, 159, 62, 20);
				panelAdaugaElev.add(lblNewLabel_2);
				
				JLabel lblModifica = new JLabel("MODIFICA");
				lblModifica.setToolTipText("Adauga");
				lblModifica.setForeground(new Color(255, 255, 255));
				lblModifica.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				lblModifica.setBounds(93, 159, 75, 20);
				panelAdaugaElev.add(lblModifica);
				
				JLabel lblSterge = new JLabel("STERGE");
				lblSterge.setToolTipText("Adauga");
				lblSterge.setForeground(new Color(255, 255, 255));
				lblSterge.setFont(new Font("Bahnschrift", Font.BOLD, 13));
				lblSterge.setBounds(170, 159, 57, 20);
				panelAdaugaElev.add(lblSterge);
				
				JLabel txtID = new JLabel("ID");
				txtID.setForeground(new Color(255, 255, 255));
				txtID.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				txtID.setBounds(122, 52, 46, 14);
				panelAdaugaElev.add(txtID);
				
				
				
				JButton btnAdaugaElev = new JButton("");
				btnAdaugaElev.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				btnAdaugaElev.setForeground(new Color(0, 0, 0));
				btnAdaugaElev.setBackground(new Color(153, 153, 204));
				btnAdaugaElev.setBounds(31, 179, 38, 35);
				panelAdaugaElev.add(btnAdaugaElev);
				btnAdaugaElev.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						boolean emptyNume = txtNume.getText().isEmpty();
						boolean emptyPrenume = txtPrenume.getText().isEmpty();
						
						if(emptyNume == false && emptyPrenume == false && TxtCheckforInt(txtNume.getText()) == false &&
								TxtCheckforInt(txtPrenume.getText()) == false) {
						try {
							Statement st = con.createStatement();
							String nume = txtNume.getText();
							String prenume = txtPrenume.getText();
							String clasa = (String) comboBox.getSelectedItem();
							String insert = "insert into elevi" + "(numele_elevului , prenumele_elevului , clasa_elevului)" +
							                 "values('"+ nume + "','" + prenume + "','" + clasa + "')";
							st.executeUpdate(insert);
							JOptionPane.showMessageDialog(null, "Elevul "+nume.toUpperCase() +" "+ prenume.toUpperCase()+" a fost adaugat");
							txtNume.setText("");
							txtPrenume.setText("");
							txtID.setText("");
							showElevi();
						} catch (SQLException | ClassNotFoundException e1) {
							JOptionPane.showMessageDialog(null, "Va rugam introduceti informatii valide !");
							e1.printStackTrace();
						}
					  }
						else {JOptionPane.showMessageDialog(null, "Va rugam completati toate campurile sau introduceti informatii valide !");}
					}
				});
				btnAdaugaElev.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_add_user_group_woman_man_35px.png")));
				
				JButton btnModificaElev = new JButton("");
				btnModificaElev.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				btnModificaElev.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						String query = "update elevi set numele_elevului='"+txtNume.getText()+"' , prenumele_elevului='"+txtPrenume.getText()+
								"', clasa_elevului='"+comboBox.getSelectedItem().toString() + "' where id="+txtID.getText();
						try {
							Statement st = con.createStatement();
							st.executeUpdate(query);
							showElevi();
							JOptionPane.showMessageDialog(null, "Modificarea a fost facuta cu succes !");
							txtID.setText("");
							txtNume.setText("");
							txtPrenume.setText("");
						} catch (SQLException | ClassNotFoundException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				});
				btnModificaElev.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_edit_property_35px.png")));
				btnModificaElev.setForeground(Color.BLACK);
				btnModificaElev.setBackground(new Color(153, 153, 204));
				btnModificaElev.setBounds(108, 179, 38, 35);
				panelAdaugaElev.add(btnModificaElev);
				
				
				JButton btnStergeElev = new JButton("");
				btnStergeElev.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				btnStergeElev.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
						int row = tableElevi.getSelectedRow();
						String numeElev = tableElevi.getModel().getValueAt(row, 1).toString();
						String prenumeElev = tableElevi.getModel().getValueAt(row, 2).toString();
						String cell = tableElevi.getModel().getValueAt(row, 0).toString();
						String query = "delete from elevi where id="+cell;
						int exit = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa stergi elevul "+numeElev+" "+prenumeElev+" ?");
						if(exit == 0) {
						try {
							Statement st2 = con.createStatement();
							st2.executeUpdate(query);
							JOptionPane.showMessageDialog(null, "Informatia a fost stearsa !");
							txtID.setText("");
							txtNume.setText("");
							txtPrenume.setText("");
							showElevi();
							st2.close();
						}catch (ArrayIndexOutOfBoundsException | SQLException | ClassNotFoundException e) {JOptionPane.showMessageDialog(null, "Nu ai selectat nici un elev din tabel!");}
					   }
					}
				});
				btnStergeElev.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_delete_document_35px_2.png")));
				btnStergeElev.setForeground(Color.BLACK);
				btnStergeElev.setBackground(new Color(153, 153, 204));
				btnStergeElev.setBounds(177, 179, 38, 35);
				panelAdaugaElev.add(btnStergeElev);
				
				JLabel lblId = new JLabel("ID =");
				lblId.setForeground(Color.WHITE);
				lblId.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				lblId.setBounds(45, 52, 46, 14);
				panelAdaugaElev.add(lblId);
				
				
				scrollPane = new JScrollPane();
				scrollPane.setBounds(293, 77, 296, 529);
				MainPanelElev.add(scrollPane);
				
				tableElevi = new JTable();
				tableElevi.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent e) {
						MainPanelElev.setCursor(cursor);
					}
				});
				tableElevi.setName("");
				tableElevi.setSelectionForeground(new Color(51, 51, 102));
				tableElevi.setGridColor(Color.BLACK);
				tableElevi.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
				tableElevi.setShowVerticalLines(false);
				tableElevi.setRowHeight(25);
				tableElevi.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent e) {
						int row = tableElevi.getSelectedRow();
						String numeElev = tableElevi.getModel().getValueAt(row, 1).toString();
						String prenumeElev = tableElevi.getModel().getValueAt(row, 2).toString();
						String id = tableElevi.getModel().getValueAt(row, 0).toString();
						
						txtID.setText(id);
						txtNume.setText(numeElev);
						txtPrenume.setText(prenumeElev);
					}
				});
				tableElevi.setForeground(Color.BLACK);
				scrollPane.setViewportView(tableElevi);
				tableElevi.setBackground(new Color(153, 153, 204));
				
				JToggleButton tglbtnVeziElevi = new JToggleButton("Vezi toti Elevii");
				tglbtnVeziElevi.addMouseMotionListener(new MouseMotionAdapter() {
					@Override
					public void mouseMoved(MouseEvent arg0) {
						MainPanelElev.setCursor(cursor);
					}
				});
				tglbtnVeziElevi.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
				tglbtnVeziElevi.setBounds(469, 11, 109, 26);
				MainPanelElev.add(tglbtnVeziElevi);
				
				JLabel lblFilter = new JLabel("Cauta dupa :");
				lblFilter.setBounds(302, 46, 109, 20);
				MainPanelElev.add(lblFilter);
				lblFilter.setFont(new Font("Bahnschrift", Font.BOLD, 16));
				
				JComboBox comboBoxCauta_1 = new JComboBox();
				comboBoxCauta_1.setBounds(407, 48, 86, 20);
				MainPanelElev.add(comboBoxCauta_1);
				comboBoxCauta_1.setModel(new DefaultComboBoxModel(new String[] {"numele_elevului", "prenumele_elevului", "clasa_elevului", "id"}));
				
				textFieldFilter = new JTextField();
				textFieldFilter.setBounds(503, 48, 75, 20);
				MainPanelElev.add(textFieldFilter);
				textFieldFilter.addKeyListener(new KeyAdapter() {
					public void keyReleased(KeyEvent arg0) {
						//Cauta !!
						try {///Connect to DB							
							String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
							String user = "root";
							String pass = "root";	
								Class.forName("com.mysql.cj.jdbc.Driver");
								Connection con = DriverManager.getConnection(url, user, pass);			
								//Initialize search							
								String selection = (String)comboBoxCauta_1.getSelectedItem();							
								String query = "select * from elevi where "+selection+"=?";
								PreparedStatement st = con.prepareStatement(query);
								st.setString(1,textFieldFilter.getText());
							    ResultSet rs = st.executeQuery();						    
							    tableElevi.setModel(DbUtils.resultSetToTableModel(rs));
							    st.close();
						} catch (Exception e) {
							e.printStackTrace();}
						}
					}
				);
				textFieldFilter.setColumns(10);
				
				JButton btnNewButton_2 = new JButton("<html> Reimprospatare <br/> Informatii </html>");
				btnNewButton_2.addMouseListener(new MouseAdapter() {
					public void mouseClicked(MouseEvent arg0) {
						ResultSet rs;
						comboBox.removeAllItems();
						comboBoxClasa.removeAllItems();
						try {
							rs = st.executeQuery("show tables in scoala");
							while(rs.next()) {
						    	comboBox.addItem(rs.getString("Tables_in_scoala").toUpperCase());
						    	comboBoxClasa.addItem(rs.getString("Tables_in_scoala").toUpperCase());
						    }
						    rs.close();
						} catch (SQLException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
						
					}
				});
				btnNewButton_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
				btnNewButton_2.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_refresh_40px_4.png")));
				btnNewButton_2.setBounds(29, 11, 185, 43);
				MainPanelElev.add(btnNewButton_2);
				tglbtnVeziElevi.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						try {
							showElevi();
						} catch (ClassNotFoundException e1) {				
							e1.printStackTrace();
						} catch (SQLException e1) {
							e1.printStackTrace();
						}
						
					}
				});
				
				ResultSet rs = st.executeQuery("show tables in scoala");
			    
			    while(rs.next()) {
			    	comboBox.addItem(rs.getString("Tables_in_scoala").toUpperCase());
			    	comboBoxClasa.addItem(rs.getString("Tables_in_scoala").toUpperCase());
			    }
			    rs.close();
				
	}
	
	public void showElevi() throws ClassNotFoundException, SQLException {
		//Connect to DB
		String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "root";
		String pass = "root";	
		String query = "select * from elevi";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    tableElevi.setModel(DbUtils.resultSetToTableModel(rs));
	    rs.close();
		}
	
	public void showProfesori() throws ClassNotFoundException, SQLException {
		//Connect to DB
		String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "root";
		String pass = "root";	
		String query = "select * from profesori";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    tableProfesori.setModel(DbUtils.resultSetToTableModel(rs));
	    con.close();
	    st.close();
	    rs.close();
		}
	
	public void initAdaugaProfesori() throws ClassNotFoundException, SQLException {
		
		//Connect to DB
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String user = "root";
				String pass = "root";	
				String query = "select * from profesori";
				
				Class.forName("com.mysql.cj.jdbc.Driver");
				Connection con = DriverManager.getConnection(url, user, pass);
		
		JLabel lblNewLabel_3 = new JLabel("dsadasdsa");
		lblNewLabel_3.setBounds(118, 201, 46, 14);
		MainPanelAcasa.add(lblNewLabel_3);
				
		MainPanelProfesori.setBackground(new Color(255, 204, 0));
		MainPanelProfesori.setBounds(278, 35, 600, 616);
		getContentPane().add(MainPanelProfesori);
		MainPanelProfesori.setLayout(null);
		
		JPanel panelAdaugaProfesori = new JPanel();
		panelAdaugaProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor.getDefaultCursor());
			}
		});
		panelAdaugaProfesori.setBackground(new Color(51, 51, 102));
		panelAdaugaProfesori.setBorder(new BevelBorder(BevelBorder.RAISED, new Color(255, 255, 255), null, null, null));
		panelAdaugaProfesori.setBounds(20, 66, 234, 218);
		MainPanelProfesori.add(panelAdaugaProfesori);
		panelAdaugaProfesori.setLayout(null);
		
		JLabel lblAdaugaProfesori_1 = new JLabel("Adauga Profesori");
		lblAdaugaProfesori_1.setForeground(new Color(255, 255, 255));
		lblAdaugaProfesori_1.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		lblAdaugaProfesori_1.setBounds(46, 11, 178, 25);
		panelAdaugaProfesori.add(lblAdaugaProfesori_1);
		
		JLabel lblNume = new JLabel("NUME");
		lblNume.setForeground(new Color(255, 255, 255));
		lblNume.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblNume.setBounds(33, 71, 60, 14);
		panelAdaugaProfesori.add(lblNume);
		
		JLabel lblMaterie = new JLabel("MATERIE");
		lblMaterie.setForeground(Color.WHITE);
		lblMaterie.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblMaterie.setBounds(33, 132, 86, 14);
		panelAdaugaProfesori.add(lblMaterie);
		
		JLabel lblPrenume = new JLabel("PRENUME");
		lblPrenume.setForeground(Color.WHITE);
		lblPrenume.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblPrenume.setBounds(33, 103, 95, 14);
		panelAdaugaProfesori.add(lblPrenume);
		
		txtNumeProf = new JTextField();
		txtNumeProf.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		txtNumeProf.setBounds(126, 70, 86, 20);
		panelAdaugaProfesori.add(txtNumeProf);
		txtNumeProf.setColumns(10);
		
		txtPrenumeProf = new JTextField();
		txtPrenumeProf.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		txtPrenumeProf.setColumns(10);
		txtPrenumeProf.setBounds(126, 101, 86, 20);
		panelAdaugaProfesori.add(txtPrenumeProf);
		
		txtMaterie = new JTextField();
		txtMaterie.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		txtMaterie.setColumns(10);
		txtMaterie.setBounds(126, 130, 86, 20);
		panelAdaugaProfesori.add(txtMaterie);
		
		JButton btnAdaugaProfesori = new JButton("");
		btnAdaugaProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor);
			}
		});
		btnAdaugaProfesori.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				boolean emptyNume = txtNumeProf.getText().isEmpty();
				boolean emptyPrenume = txtPrenumeProf.getText().isEmpty();
				
				if(emptyNume == false && emptyPrenume == false && TxtCheckforInt(txtNumeProf.getText()) == false &&
						TxtCheckforInt(txtPrenumeProf.getText()) == false) {
				try {
					Statement st = con.createStatement();
					String nume = txtNumeProf.getText();
					String prenume = txtPrenumeProf.getText();
					String materie = txtMaterie.getText();
					String insert = "insert into profesori" + "(Nume , Prenume , Materie)" +
					                 "values('"+ nume + "','" + prenume + "','" + materie + "')";
					st.executeUpdate(insert);
					JOptionPane.showMessageDialog(null, "Profesorul "+nume.toUpperCase() +" "+ prenume.toUpperCase()+" a fost adaugat");
					txtNumeProf.setText("");
					txtPrenumeProf.setText("");
					
					showProfesori();
				} catch (SQLException | ClassNotFoundException e1) {
					JOptionPane.showMessageDialog(null, "Va rugam introduceti informatii valide !");
					e1.printStackTrace();
				}
			  }
				else {JOptionPane.showMessageDialog(null, "Va rugam completati toate campurile sau introduceti informatii valide !");}
			}
		});
		btnAdaugaProfesori.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_teacher_35px.png")));
		btnAdaugaProfesori.setBounds(22, 172, 53, 35);
		panelAdaugaProfesori.add(btnAdaugaProfesori);
		
		JButton btnModificaProfesori = new JButton("");
		btnModificaProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor);
			}
		});
		btnModificaProfesori.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_edit_property_35px.png")));
		btnModificaProfesori.setBounds(96, 172, 53, 35);
		panelAdaugaProfesori.add(btnModificaProfesori);
		
		JButton btnStergeProfesori = new JButton("");
		btnStergeProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor);
			}
		});
		btnStergeProfesori.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableProfesori.getSelectedRow();
				String nume = tableProfesori.getModel().getValueAt(row, 1).toString();
				String prenume = tableProfesori.getModel().getValueAt(row, 2).toString();
				String cell = tableProfesori.getModel().getValueAt(row, 0).toString();
				String query = "delete from profesori where id="+cell;
				int exit = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa stergi profesorul "+nume+" "+prenume+" ?");
				if(exit == 0) {
				try {
					Statement st2 = con.createStatement();
					st2.executeUpdate(query);
					JOptionPane.showMessageDialog(null, "Informatia a fost stearsa !");
					txtNumeProf.setText("");
					txtPrenumeProf.setText("");
					txtMaterie.setText("");
					showProfesori();
					st2.close();
				}catch (ArrayIndexOutOfBoundsException | SQLException | ClassNotFoundException e2) {JOptionPane.showMessageDialog(null, "Nu ai selectat nici un profesor din tabel!");}
			}
		   }
		});
		btnStergeProfesori.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_delete_document_35px_2.png")));
		btnStergeProfesori.setBounds(171, 172, 53, 35);
		panelAdaugaProfesori.add(btnStergeProfesori);
		
		JLabel lblNewLabel = new JLabel("ADAUGA");
		lblNewLabel.setForeground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblNewLabel.setBounds(22, 156, 71, 14);
		panelAdaugaProfesori.add(lblNewLabel);
		
		JLabel lblModifica_1 = new JLabel("MODIFICA");
		lblModifica_1.setForeground(Color.WHITE);
		lblModifica_1.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblModifica_1.setBounds(94, 157, 71, 14);
		panelAdaugaProfesori.add(lblModifica_1);
		
		JLabel lblSterge_2 = new JLabel("STERGE");
		lblSterge_2.setForeground(Color.WHITE);
		lblSterge_2.setFont(new Font("Bahnschrift", Font.BOLD, 12));
		lblSterge_2.setBounds(171, 157, 53, 14);
		panelAdaugaProfesori.add(lblSterge_2);
		
		JLabel lblIDProf = new JLabel("ID =");
		lblIDProf.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblIDProf.setForeground(new Color(255, 255, 255));
		lblIDProf.setBounds(53, 45, 46, 14);
		panelAdaugaProfesori.add(lblIDProf);
		
		JLabel txtIDProf = new JLabel("ID");
		txtIDProf.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		txtIDProf.setForeground(new Color(255, 255, 255));
		txtIDProf.setBounds(126, 45, 46, 14);
		panelAdaugaProfesori.add(txtIDProf);
		
		JLabel lblVeziProf = new JLabel("Vezi Profesorii");
		lblVeziProf.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblVeziProf.setBounds(143, 295, 128, 14);
		MainPanelProfesori.add(lblVeziProf);
		
		JButton btnTabelProf = new JButton("");
		btnTabelProf.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor);
			}
		});
		btnTabelProf.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				try {
					showProfesori();
				} catch (ClassNotFoundException e1) {				
					e1.printStackTrace();
				} catch (SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnTabelProf.setBackground(new Color(153, 153, 204));
		btnTabelProf.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		btnTabelProf.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/icons8_teacher_35px.png")));
		btnTabelProf.setBounds(166, 313, 74, 34);
		MainPanelProfesori.add(btnTabelProf);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(264, 66, 326, 539);
		MainPanelProfesori.add(scrollPane_1);
		
		tableProfesori = new JTable();
		tableProfesori.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelProfesori.setCursor(cursor);
			}
		});
		tableProfesori.setRowHeight(25);
		tableProfesori.setShowVerticalLines(false);
		tableProfesori.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		tableProfesori.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tableProfesori.getSelectedRow();
				String id = tableProfesori.getModel().getValueAt(row, 0).toString();
				String nume = tableProfesori.getModel().getValueAt(row, 1).toString();
				String prenume = tableProfesori.getModel().getValueAt(row, 2).toString();
				String materie = tableProfesori.getModel().getValueAt(row, 3).toString();
				
				txtIDProf.setText(id);
				txtNumeProf.setText(nume);
				txtPrenumeProf.setText(prenume);
				txtMaterie.setText(materie);
			}
		});
		scrollPane_1.setViewportView(tableProfesori);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/quill.png")));
		lblNewLabel_1.setBounds(10, 387, 128, 218);
		MainPanelProfesori.add(lblNewLabel_1);
		
		JLabel lblNewLabel_5 = new JLabel("");
		lblNewLabel_5.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/output-onlinepngtools.png")));
		lblNewLabel_5.setBounds(141, 501, 113, 104);
		MainPanelProfesori.add(lblNewLabel_5);
		MainPanelElev.setBackground(new Color(35,152,171));
		MainPanelElev.setBounds(278, 35, 589, 616);
		getContentPane().add(MainPanelElev);
		
		
		
		
	}
	
	public void initVeziRecenzii() {

		MainPanelRecenzii.setBackground(new Color(102, 51, 204));
		MainPanelRecenzii.setBounds(277, 35, 601, 616);
		getContentPane().add(MainPanelRecenzii);
		MainPanelRecenzii.setLayout(null);
		
		JLabel label = new JLabel("Selecteaza un profesor :");
		label.setForeground(Color.WHITE);
		label.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		label.setBounds(10, 69, 170, 14);
		MainPanelRecenzii.add(label);
		
		JPanel panelRecenzii = new JPanel();
		
		//stars
		JLabel star1 = new JLabel("");
		star1.setEnabled(false);
		star1.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/STAR SMALL.png")));
		star1.setBounds(10, 40, 31, 35);
		panelRecenzii.add(star1);
		
		JLabel star2 = new JLabel("");
		star2.setEnabled(false);
		star2.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/STAR SMALL.png")));
		star2.setBounds(42, 40, 31, 35);
		panelRecenzii.add(star2);
		
		JLabel star3 = new JLabel("");
		star3.setEnabled(false);
		star3.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/STAR SMALL.png")));
		star3.setBounds(73, 40, 31, 35);
		panelRecenzii.add(star3);
		
		JLabel star4 = new JLabel("");
		star4.setEnabled(false);
		star4.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/STAR SMALL.png")));
		star4.setBounds(106, 40, 31, 35);
		panelRecenzii.add(star4);
		
		JLabel star5 = new JLabel("");
		star5.setEnabled(false);
		star5.setIcon(new ImageIcon(MainPanelAdmin.class.getResource("/images/assets/STAR SMALL.png")));
		star5.setBounds(137, 40, 31, 35);
		panelRecenzii.add(star5);
		
		JTextArea txtAreaR = new JTextArea();
		txtAreaR.setEditable(false);
		
		txtAreaR.setText("Nici o recenzie scrisa.");
		JLabel lblNota = new JLabel("0");
		JLabel lblCalificativ = new JLabel("Calificativ");
		JLabel lblProfName = new JLabel("Profesor");
		
		JLabel lblUserName = new JLabel("User");
		JLabel lblData = new JLabel("");
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setBounds(10, 94, 570, 115);
		MainPanelRecenzii.add(scrollPane_1);
		
		tabelProf2 = new JTable();
		tabelProf2.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelRecenzii.setCursor(cursor);
			}
		});
		tabelProf2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				int row = tabelProf2.getSelectedRow();
				int row2= tabelRecenzii.getSelectedRow();
				String nume = tabelProf2.getModel().getValueAt(row, 1).toString();
				String prenume = tabelProf2.getModel().getValueAt(row, 2).toString();
				String id = tabelProf2.getModel().getValueAt(row, 0).toString();
				idProf = Integer.parseInt(id);
				
				lblProfName.setText(nume.toUpperCase() + " " + prenume.toUpperCase());
				
				//Connect to DB
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String user = "root";
				String pass = "root";	
				String query = "select Recenzie,Nota,User,Data from recenzii where ID_PROF="+idProf;
				double nota= 0;
				int noteN = 0;
				String query2 ="select Nota from recenzii where ID_PROF="+idProf;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pass);
					Statement st = con.createStatement();
				    ResultSet rs = st.executeQuery(query);
				    tabelRecenzii.setModel(DbUtils.resultSetToTableModel(rs));
				    rs.close();
				// Impartim cate note are la totalul de note obtinute ca sa afisam Nota Totala...
				    ResultSet rs2 = st.executeQuery(query2);
				    while(rs2.next()) {
				    	noteN++;
				    	nota += rs2.getInt(1);				    					    				    	
				    }			    
				    nota = nota/noteN;
				    DecimalFormat df = new DecimalFormat("#.#");
				    df.format(nota);
				    //Trebuie sa afisam un double cu doar un digit dupa punct...
				    nota = Math.round(nota * 10) / 10.0;
				    System.out.println(nota);
				    rs2.close();
				    //Afisam nota in tabel...
				    String query3="update profesori set Nota="+nota+" where id="+idProf;
				    String query4="select * from profesori";
				    st.executeUpdate(query3);
				    ResultSet rs4 = st.executeQuery(query4);
				    tabelProf2.setModel(DbUtils.resultSetToTableModel(rs4));
				    
				} catch (ClassNotFoundException | SQLException e1) {
					e1.printStackTrace();
				}
				
			  
				
			}
		});
		scrollPane_1.setViewportView(tabelProf2);
		tabelProf2.setShowVerticalLines(false);
		tabelProf2.setSelectionForeground(Color.WHITE);
		tabelProf2.setSelectionBackground(new Color(51, 51, 102));
		tabelProf2.setRowHeight(25);
		tabelProf2.setForeground(Color.BLACK);
		tabelProf2.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		tabelProf2.setBackground(new Color(102, 153, 255));
		
		JScrollPane scrollPane_2 = new JScrollPane();
		scrollPane_2.setBounds(389, 254, 194, 351);
		MainPanelRecenzii.add(scrollPane_2);
		
		tabelRecenzii = new JTable();
		tabelRecenzii.setRowHeight(25);
		tabelRecenzii.setBackground(new Color(255, 255, 102));
		tabelRecenzii.setShowGrid(false);
		tabelRecenzii.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		tabelRecenzii.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelRecenzii.setCursor(cursor);
			}
		});
		tabelRecenzii.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				int row = tabelRecenzii.getSelectedRow();
				String data = tabelRecenzii.getModel().getValueAt(row, 3).toString();
				String user = tabelRecenzii.getModel().getValueAt(row, 2).toString();
				int nota = (int) tabelRecenzii.getModel().getValueAt(row, 1);
				String recenzie = tabelRecenzii.getModel().getValueAt(row, 0).toString();
				
				if (recenzie.length() == 0) {
					txtAreaR.setText("Nici o recenzie scrisa.");
				}							
				txtAreaR.setText(recenzie);
				lblData.setText(data);
				lblUserName.setText(user.toUpperCase());
				//Daca are note x...
				if(nota == 1) {
					lblNota.setText("1");
					lblCalificativ.setText("Foarte rau !");
					lblCalificativ.setForeground(new Color(255,50,10));
					star1.setEnabled(true);
					star2.setEnabled(false);
					star3.setEnabled(false);
					star4.setEnabled(false);
					star5.setEnabled(false);
				}
				if(nota == 2) {
					lblNota.setText("2");
					lblCalificativ.setText("Rau !");
					lblCalificativ.setForeground(new Color(255,193,30));
					star1.setEnabled(true);
					star2.setEnabled(true);
					star3.setEnabled(false);
					star4.setEnabled(false);
					star5.setEnabled(false);
				}
				if(nota == 3) {
					lblNota.setText("3");
					lblCalificativ.setText("Bun !");
					lblCalificativ.setForeground(new Color(251,255,0));
					star1.setEnabled(true);
					star2.setEnabled(true);
					star3.setEnabled(true);
					star4.setEnabled(false);
					star5.setEnabled(false);
				}
				if(nota == 4) {
					lblNota.setText("4");
					lblCalificativ.setText("Foarte bun !");
					lblCalificativ.setForeground(new Color(91,255,98));	
					star1.setEnabled(true);
					star2.setEnabled(true);
					star3.setEnabled(true);
					star4.setEnabled(true);
					star5.setEnabled(false);
				}
				if(nota == 5) {
					lblNota.setText("5");
					lblCalificativ.setText("Extraordinar !");
					lblCalificativ.setForeground(new Color(225,0,255));	
					star1.setEnabled(true);
					star2.setEnabled(true);
					star3.setEnabled(true);
					star4.setEnabled(true);
					star5.setEnabled(true);
				}
				
				
			}
		});
		scrollPane_2.setViewportView(tabelRecenzii);
		
		panelRecenzii.setBackground(new Color(51, 51, 102));
		panelRecenzii.setBounds(10, 254, 351, 351);
		MainPanelRecenzii.add(panelRecenzii);
		panelRecenzii.setLayout(null);
		
		JLabel lblNewLabel_4 = new JLabel(" De la :");
		lblNewLabel_4.setBounds(0, 0, 72, 25);
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.BOLD, 20));
		panelRecenzii.add(lblNewLabel_4);
		
		lblUserName.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
		lblUserName.setForeground(new Color(255, 255, 51));
		lblUserName.setBounds(70, 5, 139, 19);
		panelRecenzii.add(lblUserName);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(10, 36, 331, 2);
		panelRecenzii.add(separator);
		
		JLabel lblNewLabel_6 = new JLabel("Nota :");
		lblNewLabel_6.setForeground(new Color(255, 255, 255));
		lblNewLabel_6.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblNewLabel_6.setBounds(170, 49, 46, 14);
		panelRecenzii.add(lblNewLabel_6);
		
		lblCalificativ.setForeground(new Color(255, 255, 255));
		lblCalificativ.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblCalificativ.setBounds(244, 49, 121, 14);
		panelRecenzii.add(lblCalificativ);
		
		lblNota.setForeground(new Color(255, 255, 255));
		lblNota.setFont(new Font("Bahnschrift", Font.BOLD, 22));
		lblNota.setBounds(220, 44, 23, 24);
		panelRecenzii.add(lblNota);
		
		lblData.setForeground(new Color(255, 255, 255));
		lblData.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
		lblData.setBounds(210, 4, 139, 14);
		panelRecenzii.add(lblData);
		
		JScrollPane scrollPane_3 = new JScrollPane();
		scrollPane_3.setBounds(10, 81, 331, 260);
		panelRecenzii.add(scrollPane_3);
		
		txtAreaR.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		txtAreaR.setForeground(new Color(255, 255, 255));
		txtAreaR.setBackground(new Color(51, 51, 102));
		scrollPane_3.setViewportView(txtAreaR);
		txtAreaR.setLineWrap(true);
		txtAreaR.setWrapStyleWord(true);
		
		JButton btnNewButton = new JButton("Vezi Profesorii");
		btnNewButton.addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				MainPanelRecenzii.setCursor(cursor);
			}
		});
		btnNewButton.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				try {
					showProf();
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 13));
		btnNewButton.setBounds(10, 26, 153, 32);
		MainPanelRecenzii.add(btnNewButton);
		
		JLabel lblPentru = new JLabel("Pentru :");
		lblPentru.setForeground(new Color(255, 255, 255));
		lblPentru.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblPentru.setBounds(10, 220, 74, 14);
		MainPanelRecenzii.add(lblPentru);
		
		
		lblProfName.setForeground(Color.ORANGE);
		lblProfName.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		lblProfName.setBounds(82, 220, 194, 16);
		MainPanelRecenzii.add(lblProfName);
		MainPanelElev.setVisible(false);
		
		
	}

	public void initAcasa() {
		
		MainPanelAcasa.setBounds(280, 35, 600, 616);
		getContentPane().add(MainPanelAcasa);
		MainPanelAcasa.setLayout(null);
		MainPanelAcasa.setBackground(new Color(51, 51, 153));
		
		JLabel lblMainInfo = new JLabel("<html> Hello, <br/><br/>This is my personal project, I am using JAVA language, OOP techniques and  SQL  language knowledge in order to build a School Teacher Review System , where you can add, delete, modify students/ teachers, add classes and courses,search function and many more . I added a review based system for students where they can review any teacher by giving them a note(star) and optionally, a written review. You can log in as an user to add a review of a teacher, and logging in as an administrator gives you all the functions listed above including seeing all the reviews made by the users/students. <br/> Please be free to try it out now ! <br/> <br/> Thanks, Zubas Bogdan<html>");
		lblMainInfo.setFont(new Font("Bahnschrift", Font.BOLD, 17));
		lblMainInfo.setForeground(Color.WHITE);
		lblMainInfo.setVerticalAlignment(SwingConstants.TOP);
		lblMainInfo.setBounds(30, 57, 523, 322);
		MainPanelAcasa.add(lblMainInfo);
		
		JLabel lblDespre = new JLabel("Despre");
		lblDespre.setForeground(Color.ORANGE);
		lblDespre.setFont(new Font("Bahnschrift", Font.PLAIN, 29));
		lblDespre.setBounds(235, 11, 111, 35);
		MainPanelAcasa.add(lblDespre);
		
		
	}
	
	public void showProf() throws ClassNotFoundException, SQLException {
		//Connect to DB
		String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
		String user = "root";
		String pass = "root";	
		String query = "select * from profesori";
		
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();
	    ResultSet rs = st.executeQuery(query);
	    tabelProf2.setModel(DbUtils.resultSetToTableModel(rs));
	    rs.close();
		}
	
	private static boolean TxtCheckforInt(String strNum) {
	    if (strNum == null) {
	        return false;
	    }
	    try {
	        double d = Double.parseDouble(strNum);
	    } catch (NumberFormatException nfe) {
	        return false;
	    }
	    return true;
	}
}

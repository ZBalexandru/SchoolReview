import javax.swing.JFrame;

import javax.swing.JButton;
import javax.swing.JComboBox;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.ActionEvent;
import java.awt.Font;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Scanner;

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
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import javax.swing.border.LineBorder;
import javax.swing.border.EtchedBorder;
import java.awt.TextArea;
import javax.swing.JSeparator;
import javax.swing.JCheckBox;
import javax.swing.JProgressBar;

public class MainPanelUser extends JFrame{
	
	private JTextField txtNume;
	private JTextField txtPrenume;
	
	private JTable tableElevi;
	private JScrollPane scrollPane;
	private JTextField txtCifra;
	private JTextField txtLitera;
	private JTextField txtIdClasa;
	private JTextField textFieldFilter;
	private JComboBox comboBoxCauta;
	private JTextArea textArea = new JTextArea();
	
	private int mouseX;
	private int mouseY;

	
	private final JLabel schoolimage = new JLabel("");
	private final JPanel MainPanelElev = new JPanel();
	private final JPanel MainPanelProfesori = new JPanel();
	private final JPanel MainPanelAcasa = new JPanel();
	private JTextField txtNumeProf;
	private JTextField txtPrenumeProf;
	private JTextField txtMaterie;
	private JTable tableProfesori;
	
	private JLabel lblProfSelect = new JLabel("Selecteaza un profesor din tabel");
	
	public int idProf;
	private String userdb;
	private Cursor cursor = new Cursor(Cursor.HAND_CURSOR);
    boolean scrieRecenzie = false;
    public int nota = 0;
    private JTable tabelProf; 

public MainPanelUser() throws SQLException, ClassNotFoundException {
	setTitle("Adauga Recenzie");
	setUndecorated(true);	
	
	//Connect
	String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
	String user = "root";
	String pass = "root";
	
	
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection con = DriverManager.getConnection(url, user, pass);
		Statement st = con.createStatement();

	
	
	getContentPane().setLayout(null);
	getContentPane().setBackground(new Color(51, 51, 102));
	
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
	
	JPanel sidePanel = new JPanel();
	sidePanel.setBounds(0, -25, 280, 676);
	sidePanel.setBackground(new Color(0, 102, 153));
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
	
	JLabel lblRecenzie = new JLabel("                    Recenzie noua");
	lblRecenzie.setForeground(new Color(255, 255, 255));
	lblRecenzie.setFont(new Font("Bahnschrift", Font.PLAIN, 17));
	lblRecenzie.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_add_to_favorites_30px.png")));
	panelHome.add(lblRecenzie, "name_171157380477300");
	
	JLabel lblBineAiVenit = new JLabel("Bine ai venit ,");
	lblBineAiVenit.setForeground(Color.WHITE);
	lblBineAiVenit.setFont(new Font("Bahnschrift", Font.BOLD, 24));
	lblBineAiVenit.setBounds(67, 352, 152, 25);
	sidePanel.add(lblBineAiVenit);
	
	
	JPanel MainPanelUser = new JPanel();
	MainPanelUser.addMouseMotionListener(new MouseMotionAdapter() {
		@Override
		public void mouseMoved(MouseEvent e) {
			MainPanelUser.setCursor(cursor.getDefaultCursor());
		}
	});
	MainPanelUser.setBackground(new Color(51, 102, 153));
	MainPanelUser.setBounds(277, 33, 620, 573);
	getContentPane().add(MainPanelUser);
	MainPanelUser.setLayout(null);
	
	JPanel panelRecenzie = new JPanel();
	JLabel lblAdaugaBoolean = new JLabel("Adauga o recenzie scrisa (OPTIONAL) :");
	JLabel lblBooleanRecenzie = new JLabel("");
	JLabel lblNota = new JLabel("Selecteaza o stea !");
	
	JLabel star5 = new JLabel("");
	JLabel star4 = new JLabel("");
	JLabel star3 = new JLabel("");
	JLabel star2 = new JLabel("");
	JLabel star1 = new JLabel("");
	star1.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			MainPanelUser.setCursor(cursor);
			
		}
	});
	star2.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			star2.setEnabled(true);
			star3.setEnabled(false);
			star4.setEnabled(false);
			star5.setEnabled(false);
			nota = 2;
			
			lblNota.setText("Rau !");
			lblNota.setForeground(new Color(255,193,30));
			
			scrieRecenzie = true;
			lblBooleanRecenzie.setText("Nota acordata este mai mica de 3, te rog adauga o recenzie scrisa ! *");
			lblAdaugaBoolean.setText("Adauga o recenzie scrisa (OBLIGATORIU) *");
			lblAdaugaBoolean.setForeground(new Color(255,55,55));
		}
	});
	star2.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent arg0) {
			MainPanelUser.setCursor(cursor);
			star2.setEnabled(true);		
		}
	});
	
	JLabel lblProfesor = new JLabel("Profesor :");
	lblProfesor.setForeground(new Color(255, 255, 255));
	lblProfesor.setFont(new Font("Bahnschrift", Font.BOLD, 18));
	lblProfesor.setBounds(20, 11, 108, 19);
	panelRecenzie.add(lblProfesor);
	star2.setEnabled(false);
	star2.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_christmas_star_FILLED.png")));
	star2.setBounds(75, 131, 61, 55);
	panelRecenzie.add(star2);
	
	star3.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			star2.setEnabled(true);
			star3.setEnabled(true);
			star4.setEnabled(false);
			star5.setEnabled(false);
			nota = 3;
			
			lblNota.setText("Bun !");
			lblNota.setForeground(new Color(251,255,0));
			
			scrieRecenzie = false;
			lblBooleanRecenzie.setText("");
			lblAdaugaBoolean.setText("Adauga o recenzie scrisa (OPTIONAL) *");
			lblAdaugaBoolean.setForeground(new Color(255, 255, 255));
		}
	});
	star3.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			MainPanelUser.setCursor(cursor);
			star2.setEnabled(true);
			star3.setEnabled(true);
		}
	});
	star3.setEnabled(false);
	star3.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_christmas_star_FILLED.png")));
	star3.setBounds(138, 131, 61, 55);
	panelRecenzie.add(star3);
	
	star4.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			star2.setEnabled(true);
			star3.setEnabled(true);
			star4.setEnabled(true);
			star5.setEnabled(false);
			nota = 4;
			
			lblNota.setText("Foarte Bun !");
			lblNota.setForeground(new Color(91,255,98));
			
			scrieRecenzie = false;
			lblBooleanRecenzie.setText("");
			lblAdaugaBoolean.setText("Adauga o recenzie scrisa (OPTIONAL) *");
			lblAdaugaBoolean.setForeground(new Color(255, 255, 255));
		}
	});
	star4.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			MainPanelUser.setCursor(cursor);
			star2.setEnabled(true);
			star3.setEnabled(true);
			star4.setEnabled(true);
			
		}
	});
	star4.setEnabled(false);
	star4.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_christmas_star_FILLED.png")));
	star4.setBounds(203, 131, 61, 55);
	panelRecenzie.add(star4);
	star5.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent e) {
			star2.setEnabled(true);
			star3.setEnabled(true);
			star4.setEnabled(true);
			star5.setEnabled(true);
			nota = 5;
			
			lblNota.setText("Extraordinar !");
			lblNota.setForeground(new Color(225,0,255));
			
			scrieRecenzie = false;
			lblBooleanRecenzie.setText("");
			lblAdaugaBoolean.setText("Adauga o recenzie scrisa (OPTIONAL) *");
			lblAdaugaBoolean.setForeground(new Color(255, 255, 255));
		}
	});
	star5.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent e) {
			MainPanelUser.setCursor(cursor);
			star2.setEnabled(true);
			star3.setEnabled(true);
			star4.setEnabled(true);
			star5.setEnabled(true);
		}
	});
	star5.setEnabled(false);
	star5.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_christmas_star_FILLED.png")));
	star5.setBounds(268, 131, 61, 55);
	panelRecenzie.add(star5);
	
	
	star1.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			nota = 1;
			star2.setEnabled(false);
			star3.setEnabled(false);
			star4.setEnabled(false);
			star5.setEnabled(false);
			lblNota.setText("Foarte rau !");
			lblNota.setForeground(new Color(255, 22, 22));
			
			scrieRecenzie = true;
			lblBooleanRecenzie.setText("Nota acordata este mai mica de 2, te rog adauga o recenzie scrisa ! *");
			lblAdaugaBoolean.setText("Adauga o recenzie scrisa (OBLIGATORIU) *");
			lblAdaugaBoolean.setForeground(new Color(255,55,55));
		}
	});
	star1.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_christmas_star_FILLED.png")));
	star1.setBounds(10, 131, 61, 55);
	panelRecenzie.add(star1);

	if(nota == 1) {
		
		star2.setEnabled(false);
		star3.setEnabled(false);
		star4.setEnabled(false);
		star5.setEnabled(false);
	}
	if(nota == 2) {
		star3.setEnabled(false);
		star4.setEnabled(false);
		star5.setEnabled(false);
	}
	if(nota == 3) {
		star4.setEnabled(false);
		star5.setEnabled(false);
	}
	if(nota == 4) {
		star5.setEnabled(false);
	}
	if(nota == 5) {
		star5.setEnabled(true);
	}
	
	panelRecenzie.addMouseMotionListener(new MouseMotionAdapter() {
		@Override
		public void mouseMoved(MouseEvent e) {
			MainPanelUser.setCursor(cursor.getDefaultCursor());
			if(nota == 0) {
			star2.setEnabled(false);
			star3.setEnabled(false);
			star4.setEnabled(false);
			star5.setEnabled(false);}
		
			if(nota == 1) {
				
				star2.setEnabled(false);
				star3.setEnabled(false);
				star4.setEnabled(false);
				star5.setEnabled(false);
			}
			if(nota == 2) {
				star3.setEnabled(false);
				star4.setEnabled(false);
				star5.setEnabled(false);
			}
			if(nota == 3) {
				star4.setEnabled(false);
				star5.setEnabled(false);
			}
			if(nota == 4) {
				star5.setEnabled(false);
			}
			if(nota == 5) {
				star5.setEnabled(true);
			}
		}
	});
	panelRecenzie.setBackground(new Color(51, 51, 102));
	panelRecenzie.setBounds(10, 176, 418, 197);
	MainPanelUser.add(panelRecenzie);
	panelRecenzie.setLayout(null);
	
	lblNota.setForeground(new Color(255, 255, 255));
	lblNota.setFont(new Font("Bahnschrift", Font.BOLD, 15));
	lblNota.setBounds(75, 112, 170, 14);
	panelRecenzie.add(lblNota);
	
	JLabel lblVreauCaRecenzia = new JLabel("Vreau ca recenzia mea sa fie anonima :");
	lblVreauCaRecenzia.setForeground(new Color(255, 255, 255));
	lblVreauCaRecenzia.setFont(new Font("Bahnschrift", Font.BOLD, 16));
	lblVreauCaRecenzia.setBounds(20, 41, 330, 14);
	panelRecenzie.add(lblVreauCaRecenzia);
	
	JCheckBox checkBoxAnon = new JCheckBox("DA");
	checkBoxAnon.setForeground(new Color(0, 0, 0));
	checkBoxAnon.setFont(new Font("Tahoma", Font.BOLD, 14));
	checkBoxAnon.setBounds(344, 37, 54, 23);
	panelRecenzie.add(checkBoxAnon);
	
	JSeparator separator1 = new JSeparator();
	separator1.setForeground(new Color(0, 0, 0));
	separator1.setBounds(20, 68, 388, 2);
	panelRecenzie.add(separator1);
	
	
	
	JLabel lblNewLabel = new JLabel("Selecteaza o stea de la 1 pana la 5 !");
	lblNewLabel.setForeground(new Color(255, 255, 255));
	lblNewLabel.setFont(new Font("Bahnschrift", Font.BOLD, 15));
	lblNewLabel.setBounds(20, 87, 270, 14);
	panelRecenzie.add(lblNewLabel);
	
	JLabel lblNewLabel_1 = new JLabel("Nota* :");
	lblNewLabel_1.setForeground(new Color(255, 255, 255));
	lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
	lblNewLabel_1.setBounds(20, 112, 51, 14);
	panelRecenzie.add(lblNewLabel_1);
	
	lblProfSelect.setForeground(new Color(255, 255, 51));
	lblProfSelect.setFont(new Font("Bahnschrift", Font.BOLD, 15));
	lblProfSelect.setBounds(115, 14, 283, 14);
	panelRecenzie.add(lblProfSelect);
	
	JLabel titlu = new JLabel("Adauga Recenzie");
	titlu.setBounds(184, 5, 277, 33);
	MainPanelUser.add(titlu);
	titlu.setForeground(new Color(255, 255, 102));
	titlu.setFont(new Font("Bahnschrift", Font.BOLD, 31));
	
	JLabel lblMinimCaractere = new JLabel("");
	JButton btnAdaugaRecenzia = new JButton("Adauga Recenzia");
	btnAdaugaRecenzia.addMouseMotionListener(new MouseMotionAdapter() {
		public void mouseMoved(MouseEvent arg0) {
			MainPanelUser.setCursor(cursor);
		}
	});
	
	JScrollPane scrollPane_2 = new JScrollPane();
	scrollPane_2.setBounds(10, 440, 572, 123);
	MainPanelUser.add(scrollPane_2);
	
	JTextArea textArea_1 = new JTextArea();
	scrollPane_2.setViewportView(textArea_1);
	textArea_1.addKeyListener(new KeyAdapter() {
		public void keyReleased(KeyEvent arg0) {
			int txtL = textArea_1.getText().length();
			if(txtL < 100) {	
				lblMinimCaractere.setText("Minim 100 de caractere !*");
			}
			else lblMinimCaractere.setText(""); 
		}
	});
	textArea_1.setForeground(new Color(0, 0, 0));
	textArea_1.setFont(new Font("Bahnschrift", Font.BOLD, 13));
	textArea_1.setBackground(new Color(255, 255, 153));
	textArea_1.setLineWrap(true);
	textArea_1.setWrapStyleWord(true);
	
	lblBooleanRecenzie.setForeground(new Color(255, 255, 255));
	lblBooleanRecenzie.setFont(new Font("Bahnschrift", Font.BOLD, 14));
	lblBooleanRecenzie.setBounds(10, 372, 567, 25);
	MainPanelUser.add(lblBooleanRecenzie);
	
	lblAdaugaBoolean.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
	lblAdaugaBoolean.setForeground(Color.WHITE);
	lblAdaugaBoolean.setBounds(10, 420, 287, 14);
	MainPanelUser.add(lblAdaugaBoolean);
	
	btnAdaugaRecenzia.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			String NumeUserDB= "";
			//Luam userul din fisierul pe care l-am creat si il pasam variabilei userdb;	
			try {
				Scanner x = new Scanner(new File("userName"));	
					userdb = x.next();
					x.close();
			} catch(Exception e){e.printStackTrace();}
			try {
			//Connect to DB
			String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
			String usersql = "root";
			String pass = "root";	
			String querynume = "select nume from users where user like '"+userdb+"';";
			String queryprenume = "select prenume from users where user like '"+userdb+"';";
			String nume = "";
			String prenume = "";
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection con = DriverManager.getConnection(url, usersql, pass);	
			Statement st = con.createStatement();
			
			ResultSet rs = st.executeQuery(querynume);
			rs.next();
			nume = rs.getString("nume");
			rs.close();
			ResultSet rs2= st.executeQuery(queryprenume);
			rs2.next();
			prenume = rs2.getString("prenume");
			NumeUserDB = nume+ " " +prenume;
			
			}catch(Exception e) {e.printStackTrace();}
			//Get time and data from user
			String data = "";
			SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
			Date date = new Date(System.currentTimeMillis());
			data=(formatter.format(date));
			//Verificam daca checkBox Anonim este selectat sau nu...
			boolean checkBox = checkBoxAnon.isSelected();
			//Boolean pt select Prof
			boolean bProf = lblProfSelect.getText().equals("Selecteaza un profesor din tabel");
			int reviewContains = textArea_1.getText().length();
			int review = 0;
			if(checkBox == true) {
				NumeUserDB = "Anonim";			
			}	
			
			//Daca nota e mai mica decat 3 dar recenzia nu are 100 char
			if(nota <= 2 && textArea_1.getText().length() <= 100 && bProf == false) {
				JOptionPane.showMessageDialog(null, "Recenzia scrisa trebuie sa aiba minim 100 de caractere !");
				review = 0;
			}
			//Daca nota e mai mica decat 3 si recenzia ARE 100+ char !
			if(nota <= 2 && nota != 0 && textArea_1.getText().length() >= 100 && bProf == false) {
				review = 1;			
			}
			//Daca nici o steluta nu e selectata...
			if(nota == 0) {
				JOptionPane.showMessageDialog(null, "Te rugam selecteaza o steluta !");
				review = 0;
			}
			//Daca nota e mai mare decat 2 si recenzia NU ARE 100 CHAR
			if(nota > 2 && textArea_1.getText().length() <= 100 && reviewContains >= 1 && bProf == false) {
				JOptionPane.showMessageDialog(null, "Recenzia scrisa trebuie sa aiba minim 100 de caractere !");
				review = 0;}
			//Daca nota e mai mare decat 2 si recenzia ARE 100+ char !
			if(nota > 2 && textArea_1.getText().length() >= 100 && bProf == false) {
				review = 1;
			}
			//Daca nota e mai mare decat 2 si NU ARE RECENZIE SCRISA...
			if(nota > 2 && reviewContains == 0 && bProf == false) {
				review = 1;
			}
			//Daca totul este bine, in final...
			if(review == 1) {
				
				//Connect to DB
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String usersql = "root";
				String pass = "root";	
				String query = "insert into recenzii (ID_PROF, Recenzie, nota,User,Data) values ((select id from profesori where id="+idProf+")"
						+ ",'"+textArea_1.getText()+"',"+nota+",'"+NumeUserDB+"','"+data+"')";
				int output = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa adaugi aceasta recenzie profesorului "+
						lblProfSelect.getText()+" ?\n Nota : "+nota+"  "+lblNota.getText());
				if(output == 0) {
				try {	
					
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, usersql, pass);
					Statement st = con.createStatement();
				    st.executeUpdate(query);
				    
				    JOptionPane.showMessageDialog(null, "Recenzia a fost adaugata cu succes !");
				} catch (ClassNotFoundException | SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				}
				}
			
			//Daca userul nu a ales nici un profesor...
			if(bProf == true) { JOptionPane.showMessageDialog(null, "Selectati un profesor !");
			review = 0;}
				
		
		}
	});
	btnAdaugaRecenzia.setFont(new Font("Bahnschrift", Font.BOLD, 13));
	btnAdaugaRecenzia.setBounds(433, 401, 149, 33);
	MainPanelUser.add(btnAdaugaRecenzia);
	
	lblMinimCaractere.setForeground(Color.WHITE);
	lblMinimCaractere.setFont(new Font("Bahnschrift", Font.PLAIN, 11));
	lblMinimCaractere.setBounds(294, 420, 129, 14);
	MainPanelUser.add(lblMinimCaractere);
	
	JScrollPane scrollPane_1 = new JScrollPane();
	scrollPane_1.setBounds(10, 57, 572, 108);
	MainPanelUser.add(scrollPane_1);
	
	tabelProf = new JTable();
	tabelProf.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			int row = tabelProf.getSelectedRow();
			String nume = tabelProf.getModel().getValueAt(row, 1).toString();
			String prenume = tabelProf.getModel().getValueAt(row, 2).toString();
			String id = tabelProf.getModel().getValueAt(row, 0).toString();
			idProf = Integer.parseInt(id);
			
			lblProfSelect.setText(nume.toUpperCase() + " " + prenume.toUpperCase());
		}
	});
	tabelProf.setForeground(new Color(0, 0, 0));
	tabelProf.setSelectionBackground(new Color(51, 51, 102));
	tabelProf.setBackground(new Color(102, 153, 255));
	tabelProf.setSelectionForeground(new Color(255, 255, 255));
	tabelProf.setFont(new Font("Bahnschrift", Font.BOLD, 16));
	tabelProf.setShowVerticalLines(false);
	tabelProf.setRowHeight(25);
	scrollPane_1.setViewportView(tabelProf);
	
	JLabel lblUserDisplay = new JLabel("");
	lblUserDisplay.setForeground(Color.WHITE);
	lblUserDisplay.setFont(new Font("Bahnschrift", Font.BOLD, 19));
	lblUserDisplay.setBounds(77, 393, 120, 25);
	sidePanel.add(lblUserDisplay);
	
	JLabel lblNewLabel_2 = new JLabel("Selecteaza un profesor :");
	lblNewLabel_2.setForeground(Color.WHITE);
	lblNewLabel_2.setFont(new Font("Bahnschrift", Font.BOLD, 13));
	lblNewLabel_2.setBounds(10, 40, 170, 14);
	MainPanelUser.add(lblNewLabel_2);
	
	JButton btnNewButton = new JButton("");
	btnNewButton.setIcon(new ImageIcon(MainPanelUser.class.getResource("/images/assets/icons8_classroom_30px_1.png")));
	btnNewButton.addMouseListener(new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent arg0) {
			try {
				try {
					Scanner x = new Scanner(new File("userName"));
					if(lblUserDisplay.getText().isEmpty() == true) {
						lblUserDisplay.setText(x.next());
						}
						x.close();
				} catch(Exception e){e.printStackTrace();}
				showProf();
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	});
	btnNewButton.setBounds(460, 193, 106, 41);
	MainPanelUser.add(btnNewButton);
	
	JLabel lblNewLabel_3 = new JLabel("Afiseaza Profesorii");
	lblNewLabel_3.setForeground(new Color(255, 255, 255));
	lblNewLabel_3.setFont(new Font("Bahnschrift", Font.BOLD, 15));
	lblNewLabel_3.setBounds(438, 176, 177, 14);
	MainPanelUser.add(lblNewLabel_3);
	MainPanelElev.setVisible(false);
	
						
	// Side Panel Events...	
	lblRecenzie.addMouseListener(new MouseAdapter() {
		public void mouseClicked(MouseEvent arg0) {
			MainPanelElev.setVisible(false);
			MainPanelProfesori.setVisible(false);
			MainPanelAcasa.setVisible(true);
			setColorSide(panelHome);

		}
	});
	
	
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
    tabelProf.setModel(DbUtils.resultSetToTableModel(rs));
    rs.close();
	}

private void setColorSide(JPanel panel) {
	panel.setBackground(new Color(204,204,255));
}

private void resetColorSide(JPanel panel) {
	panel.setBackground(new Color(102, 102, 153));		
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

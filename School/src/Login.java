import javax.swing.JFrame;
import java.awt.Dialog.ModalExclusionType;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import com.mysql.cj.xdevapi.Result;

import java.awt.Color;
import java.awt.Frame;
import javax.swing.JTextField;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JPasswordField;
import java.awt.event.MouseMotionAdapter;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

public class Login extends JFrame{
	private JTextField textNume;
	private JTextField textPrenume;
	private JTextField textUser;
	private JTextField textParola;
	private JTextField textParola2;
	public JTextField txtUser;
	private JPasswordField txtPw;
	private String userdb;
	public String inputUser;
	private JFrame login;
	
	private int mouseX;
	private int mouseY;
	private int register = 0;
	
	File file = new File("userName");
	
	public Login() {
		setTitle("Login School");
		getContentPane().setBackground(new Color(35,152,171));
		setUndecorated(true);
		setVisible(true);
		setResizable(false);
		getContentPane().setLayout(null);
		
		JPanel mainpanel1 = new JPanel();
		mainpanel1.setLayout(null);
		mainpanel1.setBackground(new Color(35, 152, 171));
		mainpanel1.setBounds(334, 43, 490, 507);
		getContentPane().add(mainpanel1);
		
		
		JLabel label_1 = new JLabel("AUTENTIFICARE");
		label_1.setForeground(Color.BLACK);
		label_1.setFont(new Font("Bahnschrift", Font.BOLD, 30));
		label_1.setBounds(123, 11, 242, 31);
		mainpanel1.add(label_1);
		
		JLabel label_4 = new JLabel("User :");
		label_4.setForeground(Color.WHITE);
		label_4.setFont(new Font("Bahnschrift", Font.BOLD, 26));
		label_4.setBounds(64, 153, 124, 27);
		mainpanel1.add(label_4);
		
		txtUser = new JTextField();
		txtUser.setColumns(10);
		txtUser.setBackground(new Color(174, 232, 228));
		txtUser.setBounds(160, 154, 242, 26);
		mainpanel1.add(txtUser);
		
		txtPw = new JPasswordField();
		txtPw.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent evt) {
				if(evt.getKeyCode() == KeyEvent.VK_ENTER) {
					//LOGIN
					String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
					String user = "root";
					String pass = "root";	
					inputUser = txtUser.getText();
					String inputPw = txtPw.getText();
					boolean adminB = txtUser.getText().matches("administrator");
					int log = 0;
					try {
						Class.forName("com.mysql.cj.jdbc.Driver");
						Connection con = DriverManager.getConnection(url, user, pass);
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("select * from users");
						while(rs.next()) {
							if((rs.getString(2).equals(inputUser) && rs.getString(5).equals(inputPw))) {
								log =1;
								break;							
							}	
						}
						if(log == 1 && adminB == true) {
							JOptionPane.showMessageDialog(null, "Bine ai venit " + inputUser +" !\nTe-ai autentificat sub drepturi de administrator !");
							MainPanelAdmin admin = new MainPanelAdmin();
							admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
							admin.setSize(870, 650);
							admin.setVisible(true);
							
							Login login = new Login();
							login.setVisible(false);
							
							con.close();
							st.close();
							rs.close();
							dispose();
						}
						else if(log == 1 && adminB == false) {
							JOptionPane.showMessageDialog(null, "Bine ai venit " + inputUser +" !");							
							MainPanelUser userpanel = new MainPanelUser();
							userpanel.setSize(870,600);
							userpanel.setVisible(true);
								
							//Pasam numele userului in USER												
							try {
								file.createTempFile("temp" ,"userName");
								PrintWriter pw = new PrintWriter(file);
								pw.println(inputUser);
								pw.close();
							} catch (IOException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
											       
							con.close();
							st.close();
							rs.close();					
				           dispose();							
						}
						else if(log == 0) {
							JOptionPane.showMessageDialog(null, "User-ul " + inputUser +" nu exista sau parola introdusa nu a fost gasita !");
							txtPw.setText("");
							
							con.close();
							st.close();
							rs.close();
						}
					} catch (ClassNotFoundException | SQLException e) {
						
						e.printStackTrace();
					}											
				}
				
			}
		});
		txtPw.setBackground(new Color(174, 232, 228));
		txtPw.setBounds(160, 191, 242, 27);
		mainpanel1.add(txtPw);
		
		JLabel label_5 = new JLabel("Parola :");
		label_5.setForeground(Color.WHITE);
		label_5.setFont(new Font("Bahnschrift", Font.BOLD, 26));
		label_5.setBounds(43, 191, 124, 24);
		mainpanel1.add(label_5);
		
		JButton btnLogin = new JButton("Autentifica-te");
		btnLogin.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				//LOGIN
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String user = "root";
				String pass = "root";	
				String inputUser = txtUser.getText();
				String inputPw = txtPw.getText();
				boolean adminB = txtUser.getText().matches("administrator");
				int log = 0;
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pass);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery("select * from users");
					
					while(rs.next()) {
						if((rs.getString(2).equals(inputUser) && rs.getString(5).equals(inputPw))) {
							log =1;
							break;
						}	

					}
					if(log == 1 && adminB == true) {
						JOptionPane.showMessageDialog(btnLogin, "Bine ai venit " + inputUser +" !\nTe-ai autentificat sub drepturi de administrator !");
						MainPanelAdmin admin = new MainPanelAdmin();
						admin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
						admin.setSize(900, 570);
						admin.setVisible(true);
						
						Login login = new Login();
						login.setName(inputUser);
						
						con.close();
						st.close();
						rs.close();
						dispose();
					}
					
					else if(log == 1 && adminB == false) {
						JOptionPane.showMessageDialog(null, "Bine ai venit " + inputUser +" !");							
						MainPanelUser userpanel = new MainPanelUser();
						userpanel.setSize(870,600);
						userpanel.setVisible(true);
							
						//Pasam numele userului in USER						
						try {
							file.createTempFile("temp" ,"userName");
							PrintWriter pw = new PrintWriter(file);
							pw.println(inputUser);
							pw.close();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
											
						con.close();
						st.close();
						rs.close();
						dispose();
					}
					else if(log == 0) {
						JOptionPane.showMessageDialog(null, "User-ul " + inputUser +" nu exista sau parola introdusa nu a fost gasita !");
						txtPw.setText("");
						
						con.close();
						st.close();
						rs.close();
					}
				} catch (ClassNotFoundException | SQLException e) {
					
					e.printStackTrace();
				}									
				
			
				
			}
		});
		btnLogin.setForeground(Color.WHITE);
		btnLogin.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		btnLogin.setBackground(new Color(51, 51, 102));
		btnLogin.setBounds(177, 271, 150, 31);
		mainpanel1.add(btnLogin);
		
		JPanel mainpanel2 = new JPanel();
		mainpanel2.setBounds(334, 43, 490, 507);
		getContentPane().add(mainpanel2);
		mainpanel2.setBackground(new Color(35,152,171));
		mainpanel2.setLayout(null);
		mainpanel2.setVisible(false);
		
		JLabel lblClickAiciDaca = new JLabel("Nu ai cont ?");
		lblClickAiciDaca.setForeground(Color.WHITE);
		lblClickAiciDaca.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblClickAiciDaca.setBounds(205, 433, 92, 30);
		mainpanel1.add(lblClickAiciDaca);	
		
		JButton btnInregistreazate = new JButton("Inregistreaza-te");
		btnInregistreazate.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				mainpanel1.setVisible(false);
				mainpanel2.setVisible(true);
			}
		});
		btnInregistreazate.setForeground(Color.WHITE);
		btnInregistreazate.setBackground(new Color(0,223,200));
		btnInregistreazate.setFont(new Font("Bahnschrift", Font.PLAIN, 13));
		btnInregistreazate.setBounds(177, 469, 138, 27);
		mainpanel1.add(btnInregistreazate);		
		
		
		JLabel lblNewLabel_2 = new JLabel("INREGISTRARE");
		lblNewLabel_2.setBounds(123, 11, 242, 31);
		mainpanel2.add(lblNewLabel_2);
		lblNewLabel_2.setForeground(Color.BLACK);
		lblNewLabel_2.setFont(new Font("MS PGothic", Font.BOLD, 30));
		
		JLabel lblNewLabel_3 = new JLabel("Nume :");
		lblNewLabel_3.setFont(new Font("Arial", Font.BOLD, 26));
		lblNewLabel_3.setBounds(69, 85, 96, 18);
		mainpanel2.add(lblNewLabel_3);
		
		JLabel lblPrenume = new JLabel("Prenume :");
		lblPrenume.setFont(new Font("Arial", Font.BOLD, 26));
		lblPrenume.setBounds(31, 122, 141, 18);
		mainpanel2.add(lblPrenume);
		
		JLabel lblUser = new JLabel("User :");
		lblUser.setFont(new Font("Arial", Font.BOLD, 26));
		lblUser.setBounds(87, 165, 124, 18);
		mainpanel2.add(lblUser);
		
		JLabel lblUserWrong = new JLabel("User deja luat ! Va rog incercati altul !");
		lblUserWrong.setForeground(Color.RED);
		lblUserWrong.setFont(new Font("Arial", Font.BOLD, 12));
		lblUserWrong.setBounds(162, 199, 299, 14);
		mainpanel2.add(lblUserWrong);
		lblUserWrong.setVisible(false);
		
		textNume = new JTextField();
		textNume.setBackground(new Color(174,232,228));
		textNume.setBounds(177, 86, 188, 26);
		mainpanel2.add(textNume);
		textNume.setColumns(10);
		
		textPrenume = new JTextField();
		textPrenume.setBackground(new Color(174,232,228));
		textPrenume.setColumns(10);
		textPrenume.setBounds(177, 123, 188, 26);
		mainpanel2.add(textPrenume);
		
		textUser = new JTextField();
		textUser.addKeyListener(new KeyAdapter() {
			public void keyReleased(KeyEvent arg0) {
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String user = "root";
				String pass = "root";	
				String userdb = textUser.getText();
				String verificaUser = "select * from users where user='" + userdb+"'";
				String returnUser = "";
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pass);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(verificaUser);
					rs.next();
					rs.getString(1);
					if(rs != null) {
					lblUserWrong.setVisible(true);
					}else {lblUserWrong.setVisible(false);}
					
				} catch (ClassNotFoundException | SQLException e1) {
					lblUserWrong.setVisible(false);
				}								
				
			}
		});
		textUser.setBackground(new Color(174,232,228));
		textUser.setColumns(10);
		textUser.setBounds(177, 166, 188, 26);
		mainpanel2.add(textUser);
		
		JLabel lblParola = new JLabel("Parola :");
		lblParola.setFont(new Font("Arial", Font.BOLD, 26));
		lblParola.setBounds(31, 218, 124, 18);
		mainpanel2.add(lblParola);
		
		JLabel lblParoleGood = new JLabel("Parolele corespund !");
		lblParoleGood.setForeground(Color.GREEN);
		lblParoleGood.setFont(new Font("Arial", Font.BOLD, 17));
		lblParoleGood.setBounds(173, 355, 179, 18);
		mainpanel2.add(lblParoleGood);
		lblParoleGood.setVisible(false);
		
		JLabel lblParoleWrong = new JLabel("Parolele nu corespund !");
		lblParoleWrong.setForeground(Color.RED);
		lblParoleWrong.setFont(new Font("Arial", Font.BOLD, 17));
		lblParoleWrong.setBounds(173, 355, 229, 18);
		mainpanel2.add(lblParoleWrong);
		lblParoleWrong.setVisible(false);
		
		JLabel lblParoleMinim = new JLabel("Parola trebuie sa aiba minim 6 caractere ! *");
		lblParoleMinim.setFont(new Font("Bahnschrift", Font.BOLD, 11));
		lblParoleMinim.setForeground(Color.RED);
		lblParoleMinim.setBounds(139, 224, 254, 14);
		mainpanel2.add(lblParoleMinim);
		lblParoleMinim.setVisible(false);
		
		textParola = new JTextField();
		textParola.addKeyListener(new KeyAdapter() {
			boolean emptyParola = textParola.getText().isEmpty();
			public void keyReleased(KeyEvent e) {
				if(textParola.getText().equals(textParola2.getText()) && emptyParola == false) {
					lblParoleGood.setVisible(true);
					lblParoleWrong.setVisible(false);
				}else {lblParoleWrong.setVisible(true);
				lblParoleGood.setVisible(false);}
				
				String verifyChars = textParola.getText();
				int count = 0;
				for(int i =0; i <= verifyChars.length(); i++) {
					count++;
				}
				if(count <= 6) {
					lblParoleMinim.setVisible(true);
				}
				else {lblParoleMinim.setVisible(false);}
			}
		});
		textParola.setBackground(new Color(174,232,228));
		textParola.setColumns(10);
		textParola.setBounds(31, 247, 334, 26);
		mainpanel2.add(textParola);
		
		JLabel lblConfirmaParola = new JLabel("Confirma parola :");
		lblConfirmaParola.setFont(new Font("Arial", Font.BOLD, 23));
		lblConfirmaParola.setBounds(31, 278, 213, 31);
		mainpanel2.add(lblConfirmaParola);
		
		textParola2 = new JTextField();
		textParola2.addKeyListener(new KeyAdapter() {
		
			public void keyReleased(KeyEvent arg0) {
				if(textParola.getText().equals(textParola2.getText())) {
					lblParoleGood.setVisible(true);
					lblParoleWrong.setVisible(false);
				}else {lblParoleWrong.setVisible(true);
				lblParoleGood.setVisible(false);}
				
			}
		});
		textParola2.setBackground(new Color(174,232,228));
		textParola2.setColumns(10);
		textParola2.setBounds(31, 318, 334, 26);
		mainpanel2.add(textParola2);
		
		JButton btnRegister = new JButton("Inregistreaza-te");
		btnRegister.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				//Connect to DB
				String url = "jdbc:mysql://localhost:3306/scoala?useSSL=false&allowPublicKeyRetrieval=true";
				String user = "root";
				String pass = "root";	
				String userdb = textUser.getText();
				String verificaUser = "select * from users where user='" + userdb+"'";
				String query = "insert into users (user,nume,prenume,parola) values ('"+ userdb + "','" + textNume.getText().toUpperCase() + "','" + textPrenume.getText().toUpperCase() + "','" + textParola.getText() + "')";
				register = 0;
				if(textNume.getText().isEmpty() == true) {
					JOptionPane.showMessageDialog(btnRegister, "Va rugam introduceti-va numele !");
					register = 0;
				}
				else {register++;}
				if(textPrenume.getText().isEmpty() == true && register == 1) {
					JOptionPane.showMessageDialog(btnRegister, "Va rugam introduceti-va prenumele !");
					register = 0;
				}
				else {register++;}
				//Verificam daca Parola&ConfirmaParola sunt egale
				if(textParola.getText().equals(textParola2.getText())) {
					register++;
				
				try {
					Class.forName("com.mysql.cj.jdbc.Driver");
					Connection con = DriverManager.getConnection(url, user, pass);
					Statement st = con.createStatement();
					ResultSet rs = st.executeQuery(verificaUser);
					rs.next();
					rs.getString(1);
					rs.close();
					//Verificam daca userul este deja in baza de date
					if(rs != null) {
						JOptionPane.showMessageDialog(btnRegister, "User-ul " + userdb +" e deja luat ! Incercati altul ");
						register = 0;
					}				
					}
				catch (ClassNotFoundException | SQLException e1) {
					register++;;
				}	}
				else {
					JOptionPane.showMessageDialog(btnRegister, "Parolele nu corespund sau user-ul este deja luat !");
				register = 0;}	
				//O ultima verificare...
				if(textParola.getText().equals(textParola2.getText()) && register == 4){
					System.out.println(register);
						try {
							Class.forName("com.mysql.cj.jdbc.Driver");
							Connection con = DriverManager.getConnection(url, user, pass);
							Statement st = con.createStatement();
							st.executeUpdate(query);
							JOptionPane.showMessageDialog(btnRegister, "Felicitari "+userdb +" , acum te poti autentifica !");
                            mainpanel1.setVisible(true);
                            mainpanel2.setVisible(false);
						} catch (ClassNotFoundException | SQLException e1) {
							
						}						
				}
			}
		});
		btnRegister.setForeground(new Color(255, 255, 255));
		btnRegister.setFont(new Font("Arial", Font.BOLD, 15));
		btnRegister.setBackground(new Color(51, 51, 102));
		btnRegister.setBounds(162, 397, 150, 31);
		mainpanel2.add(btnRegister);
		
		JLabel lblNewLabel_4 = new JLabel("Ai deja cont ?");
		lblNewLabel_4.setForeground(new Color(255, 255, 255));
		lblNewLabel_4.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_4.setBounds(189, 439, 106, 30);
		mainpanel2.add(lblNewLabel_4);
		
		JButton btnLogin2 = new JButton("Autentifica-te");
		btnLogin2.addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent arg0) {
				mainpanel2.setVisible(false);
				mainpanel1.setVisible(true);
			}
		});
		btnLogin2.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		btnLogin2.setBounds(177, 470, 124, 26);
		btnLogin2.setBackground(new Color(0,223,200));
		mainpanel2.add(btnLogin2);
		
		
		JPanel panel_2 = new JPanel();
		panel_2.addMouseMotionListener(new MouseMotionAdapter() {
			public void mouseDragged(MouseEvent evt) {
				int corX = evt.getXOnScreen();
				int corY = evt.getYOnScreen();
				
				setLocation(corX-mouseX, corY-mouseY);
			}
		});
		panel_2.addMouseListener(new MouseAdapter() {
			public void mousePressed(MouseEvent e) {
				mouseX = e.getX();
				mouseY = e.getY();
			}
		});
		panel_2.setBackground(new Color(126,179,255));
		panel_2.setBounds(0, 0, 824, 42);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setBounds(800, 11, 14, 30);
		panel_2.add(lblNewLabel_1);
		lblNewLabel_1.addMouseListener(new MouseAdapter() {
			
			public void mouseClicked(MouseEvent arg0) {
				int exit = JOptionPane.showConfirmDialog(null, "Esti sigur ca vrei sa iesi ?");
				if(exit == 0) {
					System.exit(0);
				}
			}
		});
		lblNewLabel_1.setFont(new Font("Yu Gothic UI", Font.BOLD, 22));
		
		JLabel label = new JLabel("-");
		label.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			setState(JFrame.ICONIFIED);
			}
		});
		label.setBounds(768, 16, 22, 20);
		panel_2.add(label);
		label.setFont(new Font("Yu Gothic UI", Font.BOLD, 35));
		
		JPanel toppanel = new JPanel();
		toppanel.setBackground(new Color(51, 51, 102));
		toppanel.setBounds(0, 42, 334, 303);
		getContentPane().add(toppanel);
		toppanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(37, 42, 265, 291);
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/images/assets/school png.png")));
		toppanel.add(lblNewLabel);
		
		JLabel lblNewLabel_7 = new JLabel("School Teacher Review System");
		lblNewLabel_7.setForeground(Color.WHITE);
		lblNewLabel_7.setFont(new Font("Bahnschrift", Font.BOLD, 16));
		lblNewLabel_7.setBounds(37, 11, 256, 45);
		toppanel.add(lblNewLabel_7);
		
		JPanel bottompanel = new JPanel();
		bottompanel.setBackground(new Color(35,31,32));
		bottompanel.setBounds(0, 335, 334, 345);
		getContentPane().add(bottompanel);
		bottompanel.setLayout(null);
		
		JLabel lblBineAiVenit = new JLabel("Bine ai venit");
		lblBineAiVenit.setForeground(Color.WHITE);
		lblBineAiVenit.setBounds(93, 28, 166, 22);
		lblBineAiVenit.setFont(new Font("Bahnschrift", Font.BOLD, 24));
		bottompanel.add(lblBineAiVenit);
		
		JLabel lblNewLabel_5 = new JLabel("Intra in cont sau inregistreaza-te daca nu ai unul");
		lblNewLabel_5.setForeground(Color.WHITE);
		lblNewLabel_5.setFont(new Font("Bahnschrift", Font.PLAIN, 15));
		lblNewLabel_5.setBounds(10, 72, 314, 28);
		bottompanel.add(lblNewLabel_5);
		
		JLabel lblNewLabel_6 = new JLabel("");
		lblNewLabel_6.setIcon(new ImageIcon(Login.class.getResource("/images/assets/output-onlinepngtools.png")));
		lblNewLabel_6.setBounds(20, 121, 146, 85);
		bottompanel.add(lblNewLabel_6);
	}
	 public void setName (String uName){
	        inputUser = uName;
	    }
	    public String getName (){
	        return inputUser;
	    }
	    public void sayUname(){
	        getName();
	    }
}

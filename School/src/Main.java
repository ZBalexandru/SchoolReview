import java.io.IOException;
import java.sql.SQLException;

import javax.swing.JFrame;

public class Main extends JFrame{

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		MainPanelAdmin admin = new MainPanelAdmin();
		admin.setSize(870,650);
		admin.setVisible(false);
		
		Login login = new Login();
		login.setSize(820,560);
		login.setVisible(true);
	}

	

}

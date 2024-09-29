import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class LoginWindow extends JFrame {
	
	Connection conn=null;
	
	public LoginWindow () {
		setTitle("Login");
		setSize(300,200);
		AscoltatoreLogin a=new AscoltatoreLogin(this);
		
		Properties props = new Properties();
		try {
			FileInputStream in = new FileInputStream("credenziali_database.properties");
			try {
				props.load(in);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				in.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String url=props.getProperty("db.url");
		System.out.println(url);
		String username = props.getProperty("db.username");
		System.out.println(username);
		String password = props.getProperty("db.password");
		System.out.println(password);

		try {
			conn= DriverManager.getConnection(url, username, password);
			a.conn=conn;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JPanel loginPanel=new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.Y_AXIS));
		
		JLabel usernameLabel=new JLabel("Username");
		loginPanel.add(usernameLabel);
		
		JTextField userCampo=new JTextField();
		a.user=userCampo;
		loginPanel.add(userCampo);
		
		JLabel passLabel=new JLabel("Password");
		loginPanel.add(passLabel);
		
		JPasswordField passCampo=new JPasswordField();
		a.pass=passCampo;
		loginPanel.add(passCampo);
		
		JToolBar tool=new JToolBar("Tools");
		//JPanel bottoni=new JPanel();
		
		JButton login=new JButton(new ImageIcon(new ImageIcon("loginImage.png").getImage().getScaledInstance(64, 32, Image.SCALE_SMOOTH)));
		login.setToolTipText("Login");
		a.login=login;
		login.addActionListener(a);
		tool.add(login);
		
		JButton nuovoUtente=new JButton("Nuovo Utente");
		
		a.nuovoUtente=nuovoUtente;
		nuovoUtente.addActionListener(a);
		tool.add(nuovoUtente);
		
		add(tool,"North");
		add(loginPanel);
		
		setVisible(true);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		
		addWindowListener(new WindowAdapter(){
			@Override
            public void windowClosing(WindowEvent e) {
				if(conn!=null) {
					try {
						conn.close();
						System.out.println("conn close");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
			}
		});
	}
}

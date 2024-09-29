import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.Connection;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JToolBar;

public class EditUtente extends JFrame{
	
	public Connection conn;

	public EditUtente(Connection conn) {
		this.conn=conn;
		
		setSize(300,200);
		setTitle("Nuovo Utente");
		
		AscoltatoreEditUtente a=new AscoltatoreEditUtente(this);
		
		JPanel loginPanel=new JPanel();
		loginPanel.setLayout(new BoxLayout(loginPanel,BoxLayout.Y_AXIS));
		
		JLabel usernameLabel=new JLabel("Username");
		loginPanel.add(usernameLabel);
		
		JTextField userCampo=new JTextField();
		a.user=userCampo;
		loginPanel.add(userCampo);
		
		JLabel passLabel=new JLabel("Password");
		loginPanel.add(passLabel);
		
		JTextField passCampo=new JTextField();
		a.pass=passCampo;
		loginPanel.add(passCampo);
		
		//JPanel bottoni=new JPanel();
		JToolBar tool=new JToolBar("Tools");
		
		JButton salva=new JButton(new ImageIcon(new ImageIcon("saveImage.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		salva.setText("Salva");
		salva.setToolTipText("Salva");
		a.salva=salva;
		salva.addActionListener(a);
		tool.add(salva);
		
		JButton elimina=new JButton(new ImageIcon(new ImageIcon("deleteImage.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		elimina.setText("Elimina");
		elimina.setToolTipText("Elimina");
		a.elimina=elimina;
		elimina.addActionListener(a);
		tool.add(elimina);
		
		add(loginPanel);
		add(tool,"North");
		setVisible(true);
	}
}

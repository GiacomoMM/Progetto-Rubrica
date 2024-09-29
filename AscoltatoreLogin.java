import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AscoltatoreLogin implements ActionListener {

	public JTextField user;
	public JPasswordField pass;
	public JButton login;
	public LoginWindow loginWindow;
	public JButton nuovoUtente;
	public Connection conn;
	
	private boolean verificaDataBase(String u,char[] p) {
		String s="SELECT COUNT(*) FROM utenti WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps=loginWindow.conn.prepareStatement(s);
			ps.setString(1, u);
			ps.setString(2,new String(p));
			ResultSet r=ps.executeQuery();
			if(r.next()) {
				return r.getInt(1)>0;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}

	public AscoltatoreLogin(LoginWindow loginWindow) {
		// TODO Auto-generated constructor stub
		this.loginWindow=loginWindow;
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==login) {
			if(user.getText().isEmpty()||pass.getPassword().toString().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Riempire tutti i campi");
				return;
			}
			if(verificaDataBase(user.getText(),pass.getPassword())) {
				System.out.println("utente trovato");
				this.loginWindow.dispose();
				new FinestraPrincipale(this.conn);
			}
			else {
				JOptionPane.showMessageDialog(null, "Inserire username o password validi");
			}
		}
		
		if(e.getSource()==nuovoUtente) {
			new EditUtente(this.conn);
		}
	}

}

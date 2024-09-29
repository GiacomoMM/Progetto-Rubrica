import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class AscoltatoreEditUtente implements ActionListener {

	public JTextField user;
	public JTextField pass;
	public JButton salva;
	public JButton elimina;
	public EditUtente editUtente;
	
	private boolean verificaDataBase(String u,String p) {
		String s="SELECT COUNT(*) FROM utenti WHERE username = ? AND password = ?";
		try {
			PreparedStatement ps=editUtente.conn.prepareStatement(s);
			ps.setString(1, u);
			ps.setString(2, p);
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
	
	private void inserisciDataBase(String u,String p) {
		String s="INSERT INTO utenti(username,password) VALUES(?,?)";
		try {
			PreparedStatement ps=editUtente.conn.prepareStatement(s);
			ps.setString(1, u);
			ps.setString(2, p);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public AscoltatoreEditUtente(EditUtente editUtente) {
		// TODO Auto-generated constructor stub
		this.editUtente=editUtente;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==salva) {
			Utente u=new Utente(user.getText(),pass.getText());
			if(u.getUsername().isEmpty() || u.getPassword().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Riempire tutti i campi");
				return;
			}
			if(verificaDataBase(u.getUsername(),u.getPassword())) {
				JOptionPane.showMessageDialog(null, "Inserire un altra combinazione di valori");
				return;
			}
			inserisciDataBase(u.getUsername(),u.getPassword());
			editUtente.dispose();
		}
		if(e.getSource()==elimina) {
			editUtente.dispose();
		}
	}

}

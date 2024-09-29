import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class AscoltatoreEditPersona implements ActionListener {

	public JPanel pannello;
	public JButton salva;
	public JButton annulla;
	public JTextField valoreNome;
	public JTextField valoreCognome;
	public JTextField valoreIndirizzo;
	public JTextField valoreTelefono;
	public JTextField valoreEta;
	public EditPersona editPersona;

	public AscoltatoreEditPersona(EditPersona editPersona) {
		// TODO Auto-generated constructor stub
		this.editPersona=editPersona;
	}
	
	public void aggiornaDataBase(Persona p, String temp_telefono) {
		String s="UPDATE persone SET nome=?,cognome=?,indirizzo=?,eta=?,telefono=? WHERE telefono = ?";
		try {
			PreparedStatement ps = this.editPersona.conn.prepareStatement(s);
			ps.setString(1,p.getNome());
			ps.setString(2,p.getCognome());
			ps.setString(3,p.getIndirizzo());
			ps.setInt(4,p.getEta());
			ps.setString(5, p.getTelefono());
			ps.setString(6, temp_telefono);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	private boolean aggiungiPersonaDataBase(Persona p) {
		String s="INSERT INTO persone (nome,cognome,indirizzo,telefono,eta) VALUES (?,?,?,?,?)";
		try {
			PreparedStatement ps=editPersona.conn.prepareStatement(s);
			ps.setString(1, p.getNome());
			ps.setString(2, p.getCognome());
			ps.setString(3, p.getIndirizzo());
			ps.setString(4, p.getTelefono());
			ps.setInt(5, p.getEta());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==salva) {
			if(!this.editPersona.modificaElemento) {
				//nuova persona
				Persona p=new Persona(null, null, null, null, null);
				p.setNome(valoreNome.getText());
				p.setCognome(valoreCognome.getText());
				p.setIndirizzo(valoreIndirizzo.getText());
				p.setTelefono(valoreTelefono.getText());
				
				try {
		            Integer.parseInt(valoreEta.getText());
		        } 
				catch (Exception er) {
					JOptionPane.showMessageDialog(null, "Inserire un intero in eta");
					return;
		        }
				
				p.setEta(Integer.valueOf(valoreEta.getText()));
				
				if (valoreNome.getText().isEmpty() || valoreCognome.getText().isEmpty() || valoreIndirizzo.getText().isEmpty() || valoreTelefono.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi devono essere riempiti");
					return;
			    }
				else {
					if(aggiungiPersonaDataBase(p)) {
						this.editPersona.lista_persone.add(p);
						this.editPersona.modelloTabella.addRow(new Object[]{p.getNome(), p.getCognome(), p.getTelefono()});
					}
					else {
						JOptionPane.showMessageDialog(null, "Inserire un numero di telefono diverso");
						return;
					}
				}
			}
			else {
				//modifica persona
				if (valoreNome.getText().isEmpty() || valoreCognome.getText().isEmpty() || valoreIndirizzo.getText().isEmpty() || valoreTelefono.getText().isEmpty()) {
					JOptionPane.showMessageDialog(null, "Tutti i campi devono essere riempiti");
					return;
			    }
				this.editPersona.lista_persone.remove(this.editPersona.p);
				String temp_telefono=this.editPersona.p.getTelefono();
				
				this.editPersona.p.setNome(valoreNome.getText());
				this.editPersona.p.setCognome(valoreCognome.getText());
				this.editPersona.p.setIndirizzo(valoreIndirizzo.getText());
				try {
		            Integer.parseInt(valoreEta.getText());
		        } 
				catch (Exception er) {
					JOptionPane.showMessageDialog(null, "Inserire un intero in eta");
					return;
		        }
				this.editPersona.p.setEta(Integer.valueOf(valoreEta.getText()));
				this.editPersona.p.setTelefono(valoreTelefono.getText());
				aggiornaDataBase(this.editPersona.p,temp_telefono);

				
				this.editPersona.lista_persone.add(this.editPersona.p);
				System.out.println(this.editPersona.valoreListaModifica);
				this.editPersona.modelloTabella.setValueAt(this.editPersona.p.getNome(), this.editPersona.valoreListaModifica, 0);
				this.editPersona.modelloTabella.setValueAt(this.editPersona.p.getCognome(), this.editPersona.valoreListaModifica, 1);
				this.editPersona.modelloTabella.setValueAt(this.editPersona.p.getTelefono(), this.editPersona.valoreListaModifica, 2);
			}
			this.editPersona.sort.sort();
			this.editPersona.dispose();
		}
		
		if(e.getSource()==annulla) {
			this.editPersona.dispose();
			return ;
		}
	}

}

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AscoltatoreRubrica implements ActionListener {

	public JButton modifica;
	public JButton nuovo;
	public JButton elimina;
	public ArrayList<Persona> lista_persone;
	public TableRowSorter<DefaultTableModel> sort;
	public JTable tabella;
	public DefaultTableModel modelloTabella;
	FinestraPrincipale finestraPrincipale;
	
	public AscoltatoreRubrica(FinestraPrincipale finestraPrincipale) {
		// TODO Auto-generated constructor stub
		this.finestraPrincipale=finestraPrincipale;
	}


	private void EliminaDataBase(String t) {
		String s="DELETE FROM persone WHERE telefono=?";
		try {
			PreparedStatement ps=finestraPrincipale.conn.prepareStatement(s);
			ps.setString(1, t);
			ps.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Persona trovaPersona(String tel) {
		for(Persona p:lista_persone) {
			if(p.getTelefono()==tel)
				return p;
		}
		return null;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==nuovo) {
			new EditPersona(this.finestraPrincipale.conn,false,null,lista_persone,tabella,modelloTabella,sort,-1);
		}
		if(e.getSource()==modifica) {
			if(tabella.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null,"selezionare una riga nella tabella");
				return;
			}
			String valoreTel = (String) tabella.getValueAt(tabella.getSelectedRow(), 2);
			Persona p=trovaPersona(valoreTel);
			new EditPersona(this.finestraPrincipale.conn,true,p,lista_persone,tabella,modelloTabella,sort,tabella.convertRowIndexToModel(tabella.getSelectedRow()));
			
		}
		if(e.getSource()==elimina) {
			if(tabella.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null,"selezionare una riga nella tabella");
				return;
			}
			else {
				String valoreTel = (String) tabella.getValueAt(tabella.getSelectedRow(), 2);
				Persona p=trovaPersona(valoreTel);
				int conf=JOptionPane.showConfirmDialog(null, "Eliminare la persona "+p.getNome()+" "+p.getCognome()+" ?", "Eliminazione", JOptionPane.YES_NO_OPTION);
				if(conf==JOptionPane.YES_OPTION) {
					EliminaDataBase(p.getTelefono());
					lista_persone.remove(p);
					//elimina dalla tabella
					modelloTabella.removeRow(tabella.convertRowIndexToModel(tabella.getSelectedRow()));
				}
			}
		}
	}

}

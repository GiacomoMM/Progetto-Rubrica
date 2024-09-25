import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Ascoltatore implements ActionListener {
	public JButton nuovo;
	public JButton modifica;
	public JButton elimina;
	public JFrame finestra;
	public ArrayList<Persona> lista_persone;
	public DefaultTableModel modelloTabella;
	public JTable tabella;
	public File dir;
	public TableRowSorter<DefaultTableModel> sort;
	
	public Persona trovaPersona(String tel) {
		for(Persona p:lista_persone) {
			if(p.getTelefono()==tel)
				return p;
		}
		return null;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		//pulsante nuovo
		if(e.getSource()==this.nuovo) {
			new editor(lista_persone,modelloTabella,false,-1,dir,tabella,sort);
		}
		//pulsante modifica
		if(e.getSource()==this.modifica) {
			if(tabella.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null,"selezionare una riga nella tabella");
			}
			else {
				new editor(lista_persone,modelloTabella,true,tabella.convertRowIndexToModel(tabella.getSelectedRow()),dir,tabella,sort);
			}
		}
		//pulsante elimina
		if(e.getSource()==this.elimina) {
			if(tabella.getSelectedRow()==-1) {
				JOptionPane.showMessageDialog(null,"selezionare una riga nella tabella");
			}
			else {
				String valoreTel = (String) tabella.getValueAt(tabella.getSelectedRow(), 2);
				Persona p=trovaPersona(valoreTel);
				int conf=JOptionPane.showConfirmDialog(null, "Eliminare la persona "+p.getNome()+" "+p.getCognome()+" ?", "Eliminazione", JOptionPane.YES_NO_OPTION);
				if(conf==JOptionPane.YES_OPTION) {
					
					File f=new File(dir,p.getStringHashCode()+".txt");
					if(f.exists()) {
						if(f.delete()) {
							System.out.println("file eliminato");
						}
					}
					lista_persone.remove(p);
					//elimina dalla tabella
					modelloTabella.removeRow(tabella.convertRowIndexToModel(tabella.getSelectedRow()));
				}
			}
		}
	}

}

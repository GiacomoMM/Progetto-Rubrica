import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class AscoltatoreEditor implements ActionListener {
	public JPanel pannello;
	public JTextField valoreNome;
	public JTextField valoreCognome;
	public JTextField valoreIndirizzo;
	public JTextField valoreTelefono;
	public JTextField valoreEta;
	public JButton salva;
	public JButton annulla;
	public JFrame frame;
	public ArrayList<Persona> lista_persone;
	public DefaultTableModel modelloTabella;
	public boolean modificaElemento;
	public int valoreListaModifica;
	public File dir;
	public JTable tabella;
	public TableRowSorter<DefaultTableModel> sort;
	
	public Persona trovaPersona(String tel) {
		for(Persona p:lista_persone) {
			if(p.getTelefono()==tel)
				return p;
		}
		return null;
	}
	
	public AscoltatoreEditor(JFrame f) {
		this.frame=f;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==this.salva) {
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
	        }
			
			p.setEta(Integer.valueOf(valoreEta.getText()));
			
			if (valoreNome.getText().isEmpty() || valoreCognome.getText().isEmpty() || valoreIndirizzo.getText().isEmpty() || valoreTelefono.getText().isEmpty()) {
				JOptionPane.showMessageDialog(null, "Tutti i campi devono essere riempiti.");
		    }
			else {
				if(modificaElemento) {
					String valoreTel = (String) tabella.getValueAt(tabella.getSelectedRow(), 2);
					Persona temp=trovaPersona(valoreTel);
					File f=new File(dir,temp.getStringHashCode()+".txt");
					lista_persone.remove(temp);
					if(f.exists()) {
						f.delete();
					}
					f=new File(dir,p.getStringHashCode()+".txt");
					try {
						f.createNewFile();
						PrintStream scrittura=new PrintStream(f);
						scrittura.println(p.getNome()+";"+p.getCognome()+";"+p.getIndirizzo()+";"+p.getTelefono()+";"+p.getEta());
						scrittura.close();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					
					lista_persone.add(p);
					modelloTabella.setValueAt(p.getNome(), valoreListaModifica, 0);
                    modelloTabella.setValueAt(p.getCognome(), valoreListaModifica, 1);
                    modelloTabella.setValueAt(p.getTelefono(), valoreListaModifica, 2);
				}
				else {
					File f=new File(dir,p.getStringHashCode()+".txt");
					if(f.exists()) {
						System.out.println("file esiste gia");
						this.frame.dispose();
					}
					else {
						try {
							f.createNewFile();
							PrintStream scrittura=new PrintStream(f);
							scrittura.println(p.getNome()+";"+p.getCognome()+";"+p.getIndirizzo()+";"+p.getTelefono()+";"+p.getEta());
							scrittura.close();
							
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						
					}
					lista_persone.add(p);
					modelloTabella.addRow(new Object[]{p.getNome(), p.getCognome(), p.getTelefono()});
				}
				sort.sort();
				this.frame.dispose();
			}
		}
		
		if(e.getSource()==this.annulla) {
			this.frame.dispose();
		}
	}

}

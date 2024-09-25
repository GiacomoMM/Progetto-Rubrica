import java.awt.BorderLayout;
import java.io.File;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class editor extends JFrame{
	public ArrayList<Persona> lista_persone;
	public DefaultTableModel modelloTabella;
	public boolean modificaElemento=false;
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
	
	public editor(ArrayList<Persona> lista_persone, DefaultTableModel modelloTabella,boolean c, int i, File dir, JTable tabella, TableRowSorter<DefaultTableModel> sort){
		this.lista_persone=lista_persone;
		this.modelloTabella=modelloTabella;
		this.modificaElemento=c;
		this.valoreListaModifica=i;
		this.dir=dir;
		this.tabella=tabella;
		this.sort=sort;
		
		setTitle("editor-persona");
		setSize(400,400);
		
		AscoltatoreEditor a=new AscoltatoreEditor(this);
		a.lista_persone=this.lista_persone;
		a.modelloTabella=this.modelloTabella;
		a.modificaElemento=this.modificaElemento;
		a.valoreListaModifica=this.valoreListaModifica;
		a.dir=dir;
		a.tabella=tabella;
		a.sort=sort;
		
		JPanel pannello=new JPanel();
		pannello.setLayout(new BoxLayout(pannello,BoxLayout.Y_AXIS));
		a.pannello=pannello;
		
		//riferimento persona
		
		Persona temp=null;
		if(modificaElemento) {
			String valoreTel = (String) tabella.getValueAt(tabella.getSelectedRow(), 2);
			temp=trovaPersona(valoreTel);
		}
		
		
		//Nome
		JLabel campoNome=new JLabel("Inserisci Nome: ");
		pannello.add(campoNome);
		
		JTextField valoreNome=new JTextField("");
		if(this.modificaElemento==true) {
			valoreNome.setText(temp.getNome());
		}
		a.valoreNome=valoreNome;
		pannello.add(valoreNome);
		
		//Cognome
		JLabel campoCognome=new JLabel("Inserisci Cognome: ");
		pannello.add(campoCognome);
		
		JTextField valoreCognome=new JTextField("");
		if(this.modificaElemento==true) {
			valoreCognome.setText(temp.getCognome());
		}
		a.valoreCognome=valoreCognome;
		pannello.add(valoreCognome);
		
		//indirizzo
		JLabel campoIndirizzo=new JLabel("Inserisci Indirizzo: ");
		pannello.add(campoIndirizzo);
		
		JTextField valoreIndirizzo=new JTextField("");
		if(this.modificaElemento==true) {
			valoreIndirizzo.setText(temp.getIndirizzo());
		}
		a.valoreIndirizzo=valoreIndirizzo;
		pannello.add(valoreIndirizzo);
		
		//telefono
		JLabel campoTelefono=new JLabel("Inserisci Telefono: ");
		pannello.add(campoTelefono);
		
		JTextField valoreTelefono=new JTextField("");
		if(this.modificaElemento==true) {
			valoreTelefono.setText(temp.getTelefono());
		}
		a.valoreTelefono=valoreTelefono;
		pannello.add(valoreTelefono);
		
		//eta
		JLabel campoEta=new JLabel("Inserisci Eta: ");
		pannello.add(campoEta);
		
		JTextField valoreEta=new JTextField("");
		if(this.modificaElemento==true) {
			valoreEta.setText(temp.getEta().toString());
		}
		a.valoreEta=valoreEta;
		pannello.add(valoreEta);
		
		//bottoni
		JPanel pannello_bottoni=new JPanel();
		JButton salva=new JButton("Salva");
		salva.addActionListener(a);
		a.salva=salva;
		pannello_bottoni.add(salva);
		
		JButton annulla=new JButton("Annulla");
		annulla.addActionListener(a);
		a.annulla=annulla;
		pannello_bottoni.add(annulla);
		
		add(pannello,BorderLayout.NORTH);
		add(pannello_bottoni,BorderLayout.SOUTH);
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

import java.awt.BorderLayout;
import java.awt.Image;
import java.sql.Connection;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class EditPersona extends JFrame{
	public Connection conn;
	public Boolean modificaElemento;
	public Persona p=null;
	public ArrayList<Persona> lista_persone;
	public JTable tabella;
	public DefaultTableModel modelloTabella;
	public TableRowSorter<DefaultTableModel> sort;
	public int valoreListaModifica;
	
	public EditPersona(Connection conn, boolean m, Persona p, ArrayList<Persona> lista_persone, JTable tabella, DefaultTableModel modelloTabella, TableRowSorter<DefaultTableModel> sort, int i) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
		this.modificaElemento=m;
		this.lista_persone=lista_persone;
		this.tabella=tabella;
		this.modelloTabella=modelloTabella;
		this.sort=sort;
		this.valoreListaModifica=i;
		this.p=p;
		
		setTitle("Editor-persona");
		setSize(400,400);
		
		AscoltatoreEditPersona a=new AscoltatoreEditPersona(this);
		JPanel pannello=new JPanel();
		pannello.setLayout(new BoxLayout(pannello,BoxLayout.Y_AXIS));
		a.pannello=pannello;
		
		JLabel campoNome=new JLabel("Inserisci Nome: ");
		pannello.add(campoNome);
		
		JTextField valoreNome=new JTextField("");
		if(this.modificaElemento==true) {
			valoreNome.setText(p.getNome());
		}
		a.valoreNome=valoreNome;
		pannello.add(valoreNome);
		
		//Cognome
		JLabel campoCognome=new JLabel("Inserisci Cognome: ");
		pannello.add(campoCognome);
		
		JTextField valoreCognome=new JTextField("");
		if(this.modificaElemento==true) {
			valoreCognome.setText(p.getCognome());
		}
		a.valoreCognome=valoreCognome;
		pannello.add(valoreCognome);
		
		//indirizzo
		JLabel campoIndirizzo=new JLabel("Inserisci Indirizzo: ");
		pannello.add(campoIndirizzo);
		
		JTextField valoreIndirizzo=new JTextField("");
		if(this.modificaElemento==true) {
			valoreIndirizzo.setText(p.getIndirizzo());
		}
		a.valoreIndirizzo=valoreIndirizzo;
		pannello.add(valoreIndirizzo);
		
		//telefono
		JLabel campoTelefono=new JLabel("Inserisci Telefono: ");
		pannello.add(campoTelefono);
		
		JTextField valoreTelefono=new JTextField("");
		if(this.modificaElemento==true) {
			valoreTelefono.setText(p.getTelefono());
		}
		a.valoreTelefono=valoreTelefono;
		pannello.add(valoreTelefono);
		
		//eta
		JLabel campoEta=new JLabel("Inserisci Eta: ");
		pannello.add(campoEta);
		
		JTextField valoreEta=new JTextField("");
		if(this.modificaElemento==true) {
			valoreEta.setText(p.getEta().toString());
		}
		a.valoreEta=valoreEta;
		pannello.add(valoreEta);
		
		//bottoni
		//JPanel pannello_bottoni=new JPanel();
		JToolBar tool=new JToolBar();
		JButton salva=new JButton(new ImageIcon(new ImageIcon("saveImage.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		salva.setText("Salva");
		salva.setToolTipText("Salva");
		salva.addActionListener(a);
		a.salva=salva;
		tool.add(salva);
			
		JButton annulla=new JButton("Annulla");
		annulla.addActionListener(a);
		a.annulla=annulla;
		tool.add(annulla);
				
		add(pannello);
		add(tool,"North");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}

}

import java.awt.BorderLayout;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class Main {
	public static void main(String[] args) {
		Ascoltatore a =new Ascoltatore();
		JFrame finestra=new JFrame("Rubrica");
		a.finestra=finestra;
		finestra.setSize(500,550);
		finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//creazione arrayList per persone
		ArrayList<Persona> lista_persone=new ArrayList<Persona>();
		
		//file
		File dir =new File("informazioni");
		if(dir.exists()) {
			System.out.println("directory esiste");
		}
		else {
			if(dir.mkdir()) {
				System.out.println("directory creata");
			}
			else {
				System.out.println("directory errore creazione");
			}
		}
		
		if(dir.listFiles()!=null && dir.listFiles().length>0) {
			File[] lista_file=dir.listFiles();
			for(File f: lista_file) {
				try {
					Scanner s=new Scanner(f);
					String riga=s.nextLine();
					String[]valori_file=riga.split(";");
					lista_persone.add(new Persona(valori_file[0],valori_file[1],valori_file[2],valori_file[3],Integer.parseInt(valori_file[4])));
					s.close();
					System.out.println("ho aggiunto persona");
				} catch (FileNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		a.dir=dir;
		Collections.sort(lista_persone, new Comparator<Persona>() {
            @Override
            public int compare(Persona p1, Persona p2) {
            	int confNome=p1.getNome().compareTo(p2.getNome());
            	if(confNome==0) {
            		return p1.getCognome().compareTo(p2.getCognome());
            	}
                return confNome;
            }
        });
		
		//gestione lista
		a.lista_persone=lista_persone;
		
		//tabella
		JPanel pannello_tabella=new JPanel();
		String[] nomiCol = {"Nome", "Cognome", "Telefono"};
		
		DefaultTableModel modelloTabella = new DefaultTableModel(nomiCol, 0);
		JTable tabella=new JTable(modelloTabella) {
			@Override
			public boolean isCellEditable(int row, int column) {
                return false;
            }
		};
		tabella.getTableHeader().setReorderingAllowed(false);
		
		//ordinamento tabella
		TableRowSorter<DefaultTableModel> sort = new TableRowSorter<DefaultTableModel>(modelloTabella);
		sort.setSortKeys(java.util.List.of(new RowSorter.SortKey(0, SortOrder.ASCENDING)));
		tabella.setRowSorter(sort);
		a.sort=sort;
		
		for (Persona persona : lista_persone) {
	           modelloTabella.addRow(new Object[]{persona.getNome(), persona.getCognome(), persona.getTelefono()});
	    }
		
		a.tabella=tabella;
		a.modelloTabella=modelloTabella;
		
		//tabella.setPreferredScrollableViewportSize(new java.awt.Dimension(400, 400));
		
		JScrollPane scrollPane = new JScrollPane(tabella);
		//scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        //scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		pannello_tabella.add(scrollPane);
		
		//bottoni
		JPanel pannello_bottoni=new JPanel();
		JButton nuovo =new JButton();
		nuovo.setText("Nuovo");
		nuovo.addActionListener(a);
		a.nuovo=nuovo;
		pannello_bottoni.add(nuovo);
		
		JButton modifica =new JButton();
		modifica.setText("Modifica");
		modifica.addActionListener(a);
		a.modifica=modifica;
		pannello_bottoni.add(modifica);
		
		JButton elimina =new JButton();
		elimina.setText("Elimina");
		elimina.addActionListener(a);
		a.elimina=elimina;
		pannello_bottoni.add(elimina);
		
		//add finestra principale
		finestra.setLayout(new BorderLayout());
		finestra.add(pannello_tabella,BorderLayout.CENTER);
		finestra.add(pannello_bottoni,BorderLayout.SOUTH);
		finestra.setResizable(false);
		finestra.setVisible(true);
	}
}

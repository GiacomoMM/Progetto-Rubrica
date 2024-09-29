import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.RowSorter;
import javax.swing.SortOrder;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

public class FinestraPrincipale extends JFrame{
	public Connection conn;

	public FinestraPrincipale(Connection conn) {
		// TODO Auto-generated constructor stub
		this.conn=conn;
		AscoltatoreRubrica a=new AscoltatoreRubrica(this);
		setTitle("Rubrica");
		setSize(600,600);
		addWindowListener(new WindowAdapter(){
			@Override
            public void windowClosing(WindowEvent e) {
				if(conn!=null) {
					try {
						conn.close();
						System.out.println("conn close");
					} catch (SQLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				dispose();
			}
		});
		
		
		ArrayList<Persona> lista_persone=new ArrayList<Persona>();
		String s="SELECT * FROM persone";
		try {
			Statement st=conn.createStatement();
			ResultSet r=st.executeQuery(s);
			while(r.next()) {
				lista_persone.add(new Persona(r.getString("nome"),r.getString("cognome"),r.getString("indirizzo"),r.getString("telefono"),r.getInt("eta")));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
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
		JToolBar tool=new JToolBar("Tools");
		//JPanel pannello_bottoni=new JPanel();
		JButton nuovo =new JButton();
		nuovo.setText("Nuovo");
		nuovo.addActionListener(a);
		a.nuovo=nuovo;
		tool.add(nuovo);
				
		JButton modifica =new JButton(new ImageIcon(new ImageIcon("editImage.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		modifica.setToolTipText("Modificia");
		modifica.setText("Modifica");
		modifica.addActionListener(a);
		a.modifica=modifica;
		tool.add(modifica);
				
		JButton elimina =new JButton(new ImageIcon(new ImageIcon("deleteImage.png").getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH)));
		elimina.setToolTipText("Elimina");
		elimina.setText("Elimina");
		elimina.addActionListener(a);
		a.elimina=elimina;
		tool.add(elimina);
		
		setVisible(true);
		add(tool,"North");
		add(pannello_tabella);
		
	}
	

}

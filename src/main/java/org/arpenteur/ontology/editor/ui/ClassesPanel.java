package org.arpenteur.ontology.editor.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

import org.arpenteur.ontology.editor.common.GetOntologyIndividuals;
import org.arpenteur.ontology.editor.model.Search;

public class ClassesPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7110941882562446314L;

	public static String classSelectedName = "";
    
	public static JTable classesTable = new JTable(){
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8524139886408158979L;

		@Override
		public boolean isCellEditable(int row, int column) {                
            return false;               
    	}; 
    };
		
	
	/**
	 * Create the Panel and add a JTable and a toolbar
	 */
	public ClassesPanel() {
		setLayout(new BorderLayout());
				
		addListener();
		
		this.add(new FramePanelsToolBars(2), BorderLayout.NORTH);
		this.add(createJTable(), BorderLayout.CENTER);
	}
	
	/**
	 * add the JTable inside a scroll pane
	 * @return a scrollPane with a JTables inside it
	 */
	private JScrollPane createJTable() {
		JScrollPane tableContainer = new JScrollPane(classesTable);
		return tableContainer;
	}
	
	private void addListener() {
		classesTable.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) { //Get mouse click count ( 1 click in our case )
					
					//Get the number of row selected
				    int row = classesTable.convertRowIndexToModel(classesTable.getSelectedRow());
				    
				    //Get the selected class as a String
				    classSelectedName = classesTable.getModel().getValueAt(row, 0).toString();
				    
				    if (classSelectedName == "") {
				    	classSelectedName = classesTable.getModel().getValueAt(row, 1).toString();
				    }
				   
				    //JOptionPane.showConfirmDialog(null, " test " + classSelectedName);
				    //Set instance Tab name to default
				    //MyFrame.myTabbedPane.setTitleAt(1, "Instance Name");
				    				    
				    //Create a data Property table (right side top) model and reset it to 0
				    DefaultTableModel dataTable = (DefaultTableModel) DataPropertyPanel.dataPropertyTable.getModel();
				    dataTable.setRowCount(0);
				    
				    //Create a object Property table (right side bot) model and reset it to 0
				    DefaultTableModel objectTable = (DefaultTableModel) ObjectPropertyPanel.objectPropertyTable.getModel();
				    objectTable.setRowCount(0);
				    
				    //Fill the individuals table (left side bot) 
				    IndividualsPanel.individualsTable.setModel(new GetOntologyIndividuals(ClassesPanel.classSelectedName));
				    //Add row sorter to the individual table
				    Search.rowSorterIndividual = new TableRowSorter<TableModel>(IndividualsPanel.individualsTable.getModel());
				    IndividualsPanel.individualsTable.setRowSorter(Search.rowSorterIndividual);
				}
		    }
		});
	}
}

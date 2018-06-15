package org.arpenteur.ontology.editor.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class DataPropertyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6498594005868647340L;

	public static JTable dataPropertyTable = new JTable(){
		/**
		 * 
		 */
		private static final long serialVersionUID = -4115010963121288399L;

		@Override
		public boolean isCellEditable(int row, int column) {                
            return false;               
    	}; 
    };
	
	public static String selectedDataProperty = "";
	
	public static String selectedDataPropertyValue = "";
	
	/**
	 * Add table and toolbar to the Panel
	 */
	
	public DataPropertyPanel() {
		setLayout(new BorderLayout());
		
		addListener();
		
		this.add(new FramePanelsToolBars(0), BorderLayout.NORTH);
		this.add(createTable(), BorderLayout.CENTER);
	}
	
	/**
	 * Create Data property table
	 * @return JScrollPane with JTable in it
	 */
	private JScrollPane createTable() {
		JScrollPane tableContainer = new JScrollPane(dataPropertyTable);
		return tableContainer;
	}
	
	/**
	 * Add listener to the data property table
	 */
	private void addListener() {
		dataPropertyTable.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
				    int row = target.getSelectedRow();
				    
				    //Get selected property name
				    selectedDataProperty = dataPropertyTable.getModel().getValueAt(row, 0).toString();
				    
				    //Get selectes property value
				    selectedDataPropertyValue = dataPropertyTable.getModel().getValueAt(row, 1).toString();
				}
		    }
		});
	}
}

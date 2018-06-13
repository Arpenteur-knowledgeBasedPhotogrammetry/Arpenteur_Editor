package org.arpenteur.ontology.editor.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.arpenteur.editor.model.GetOWLClassName;
import org.arpenteur.editor.ui.FramePanelsToolBars;

public class ObjectPropertyPanel extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2726881569443265890L;

	public static JTable objectPropertyTable = new JTable(){

		/**
		 * 
		 */
		private static final long serialVersionUID = 2735301792224899656L;

		@Override
		public boolean isCellEditable(int row, int column) {                
            return false;               
    	}; 
    };
	
	public static String selectedObjectProperty = "";
	
	public static String selectedObjectPropertyValue = "";
	
	/**
	 * Add table and toolbar to the Panel
	 */
	public ObjectPropertyPanel() {
		setLayout(new BorderLayout());
		
		addListener();
		
		this.add(new FramePanelsToolBars(1), BorderLayout.NORTH);
		this.add(createTable());
	}
	
	/**
	 * Create Object property table
	 * @return JScrollPane
	 */
	private JScrollPane createTable() {
		JScrollPane tableContainer = new JScrollPane(objectPropertyTable);
		return tableContainer;
	}
	
	/**
	 * Add listener to the Object property table
	 */
	private void addListener() {
		objectPropertyTable.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					JTable target = (JTable) e.getSource();
				    int row = target.getSelectedRow();
				    
				    //Get selected property name
				    selectedObjectProperty = objectPropertyTable.getModel().getValueAt(row, 0).toString();
				    
				    //Get selectes property value
				    selectedObjectPropertyValue = objectPropertyTable.getModel().getValueAt(row, 1).toString();
				    
				    //Get the class name of the object property relation
				    new GetOWLClassName(selectedObjectPropertyValue);
				}
		    }
		});
	}
}

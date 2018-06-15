package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTable;

import org.arpenteur.ontology.editor.common.GetOntologyIndividuals;
import org.protege.editor.core.ui.util.JOptionPaneEx;

public class ClassesIndividualsDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3597119637966004152L;

	public static String tableClassesDialogSelectedClass = null;
	
	public static String tableIndividualsDialogSelectedClass = null;
	
	public static JTable classesTableDialog = new JTable(){
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -8524139886408158979L;

		@Override
		public boolean isCellEditable(int row, int column) {                
            return false;               
    	}; 
    };
    
    public static JTable individualsTableDialog = new JTable(){
		
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
	 * Constract a JPanel with a split and Tables in it
	 * and show them as JOptionPane
	 */
	@SuppressWarnings("static-access")
	public ClassesIndividualsDialog() {
		/*AddObjectPropertyDialog.isAddObject = true;
		EditObjectPropertyDialog.isEditObject = true;*/
		
		setLayout(new BorderLayout());
		
		JSplitPane splitPane1 = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				createClassesTable(), createIndividualsTable());
		
		/*splitPane1.setOneTouchExpandable(true);
		splitPane2.setDividerLocation(500);*/
		splitPane1.setResizeWeight(0.5);
		
		this.add(splitPane1);
		
		addListener();
		
		int clickedButton = JOptionPaneEx.showValidatingConfirmDialog(this,
                "Classes & Indviduals",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.classesTableDialog);
		
		if (clickedButton == 0) {
			//AddObjectPropertyDialog.isAddObject = false;
			//EditObjectPropertyDialog.isEditObject = false;
		}
	}
	
	/**
	 * add the class table inside a scroll pane
	 * @return a scrollPane with a JTable inside it
	 */
	private JScrollPane createClassesTable() {
		JScrollPane tableContainer = new JScrollPane(classesTableDialog);
		return tableContainer;
	}
	
	/**
	 * add the individuals table inside a scroll pane
	 * @return a scrollPane with a JTable inside it
	 */
	private JScrollPane createIndividualsTable() {
		JScrollPane tableContainer = new JScrollPane(individualsTableDialog);
		return tableContainer;
	}
	
	/**
	 * add listener to the tables
	 */
	private void addListener() {
		classesTableDialog.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) { //Get mouse click count ( 1 click in our case )
					
					//Get the number of row selected
				    int row = classesTableDialog.convertRowIndexToModel(classesTableDialog.getSelectedRow());
				    
				    //Get the selected class as a String
				    tableClassesDialogSelectedClass = classesTableDialog.getModel().getValueAt(row, 0).toString();
				    
				    //Fill the dialog individuals table
				    individualsTableDialog.setModel(new GetOntologyIndividuals(tableClassesDialogSelectedClass));
				}
		    }
		});
		
		individualsTableDialog.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) { //Get mouse click count ( 1 click in our case )
					
					//Get the number of row selected
				    int row = individualsTableDialog.convertRowIndexToModel(individualsTableDialog.getSelectedRow());
				    
				    //Get the selected class as a String
				    tableIndividualsDialogSelectedClass = individualsTableDialog.getModel().getValueAt(row, 0).toString();
				    
				    if (AddObjectPropertyDialog.isAddObject) {
					    AddObjectPropertyDialog.valueLabelAddObjectProperty.setText(tableIndividualsDialogSelectedClass);
				    }
				    
				    if (EditObjectPropertyDialog.isEditObject) {
				    	EditObjectPropertyDialog.valueLabelEditObjectproperty.setText(tableIndividualsDialogSelectedClass);
				    }
				}
		    }
		});
	}
}

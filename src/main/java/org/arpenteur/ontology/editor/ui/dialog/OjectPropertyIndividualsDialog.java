package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.arpenteur.ontology.editor.common.GetOntologyIndividuals;
import org.arpenteur.ontology.editor.model.GetOWLClassName;
import org.protege.editor.core.ui.util.JOptionPaneEx;

public class OjectPropertyIndividualsDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7384535254140470285L;

	public static JTable objectPropertyindividualsTableDialog = new JTable(){
		/**
		 * 
		 */
		private static final long serialVersionUID = 2846134532865173766L;

		@Override
		public boolean isCellEditable(int row, int column) {                
            return false;               
    	}; 
    };
    
    public static String individualSelectedForObjectProperty = "";
    
    
    /**
	 * Constract a JPanel with an individual Table in it
	 * and show it as JOptionPane
	 */
    public OjectPropertyIndividualsDialog() {
    	objectPropertyindividualsTableDialog.setModel(new GetOntologyIndividuals(GetOWLClassName.classNameForObjectProperty));
    	setLayout(new BorderLayout());
    	
    	addListener();
    	
    	this.add(createClassesTable());
    	
    	@SuppressWarnings("static-access")
		int clickedButton = JOptionPaneEx.showValidatingConfirmDialog(this,
                "Classes & Indviduals",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.objectPropertyindividualsTableDialog);
    	
    	if (clickedButton == 0) {
    		
    	}
    }
    
    /**
	 * add the class table inside a scroll pane
	 * @return a scrollPane with a JTable inside it
	 */
	private JScrollPane createClassesTable() {
		JScrollPane tableContainer = new JScrollPane(objectPropertyindividualsTableDialog);
		return tableContainer;
	}
	
	/**
	 * add listener to the table
	 */
	private void addListener() {
		objectPropertyindividualsTableDialog.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) { //Get mouse click count ( 1 click in our case )
					
					//Get the number of row selected
				    int row = objectPropertyindividualsTableDialog.convertRowIndexToModel(objectPropertyindividualsTableDialog.getSelectedRow());
				    
				    //Get the selected class as a String
				    individualSelectedForObjectProperty = objectPropertyindividualsTableDialog.getModel().getValueAt(row, 0).toString();
				    
				    if (EditObjectPropertyDialog.isEditObject) {
				    	EditObjectPropertyDialog.valueLabelEditObjectproperty.setText(individualSelectedForObjectProperty);
				    }
				}
		    }
		});
	}
}

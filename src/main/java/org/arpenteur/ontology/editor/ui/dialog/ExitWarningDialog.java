package org.arpenteur.ontology.editor.ui.dialog;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.arpenteur.editor.model.ontology.SaveOntology;

public class ExitWarningDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7869424943268259433L;
	private Object[] options = {"Yes", "No", "Cancel"};
	
	
	/**
	 * Constract the exit dialog
	 */
	public ExitWarningDialog() {
		int clickedButton = JOptionPane.showOptionDialog(this, 
				"Do you want to save changes that you made to the ontology in this workspace?"
				+ "\nYour changes will be lost if you don't save them.",
				"Save Ontology?",
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null, options, options[2]);
		
		if (clickedButton == 0) {
			new SaveOntology(false);
			System.exit(0);
		} else if (clickedButton == 1) {
			System.exit(0);
		} 
	}
}

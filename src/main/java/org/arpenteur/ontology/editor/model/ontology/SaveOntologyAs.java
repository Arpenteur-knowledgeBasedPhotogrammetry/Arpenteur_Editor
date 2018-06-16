package org.arpenteur.ontology.editor.model.ontology;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.protege.editor.core.ui.error.ErrorLogPanel;
import org.semanticweb.owlapi.io.OWLFunctionalSyntaxOntologyFormat;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLOntologyStorageException;

public class SaveOntologyAs extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6721489700216825916L;
	
	private JFileChooser fileChooser = new JFileChooser();
	
	private File ontologyFile = null;
	
	private String savePath = "";
	
	
	public SaveOntologyAs() {
		fileChooser.setSelectedFile(new File("myFile.owl"));
		int rVal = fileChooser.showSaveDialog(this);
		
		if (rVal == JFileChooser.APPROVE_OPTION) {
			savePath = fileChooser.getSelectedFile().getAbsolutePath();
			
			try {
				ontologyFile = new File(savePath);
				GlobalVariables.ontologyManager.saveOntology(GlobalVariables.ontology, 
															new OWLFunctionalSyntaxOntologyFormat(), 
															IRI.create(ontologyFile));
				
				JOptionPane.showMessageDialog(null,
				        "Your Ontology has been Saved Successfully",
				        "Save Successfully",
				        JOptionPane.INFORMATION_MESSAGE);
			} catch (OWLOntologyStorageException e) {
				ErrorLogPanel.showErrorDialog(e);
			}
		}
	}
}

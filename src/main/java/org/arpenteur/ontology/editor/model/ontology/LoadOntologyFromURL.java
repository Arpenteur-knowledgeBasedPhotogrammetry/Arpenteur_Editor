package org.arpenteur.ontology.editor.model.ontology;

import java.net.URI;

import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.model.GetOntologyClasseForTable;
import org.arpenteur.ontology.editor.model.Search;
import org.arpenteur.ontology.editor.ui.ClassesPanel;
import org.arpenteur.ontology.editor.ui.dialog.ClassesIndividualsDialog;
import org.semanticweb.owlapi.model.IRI;

public class LoadOntologyFromURL {
	
	/**
	 * 
	 * @param ontologyURL: Ontology URL
	 */
	public LoadOntologyFromURL(URI ontologyURI) {
		URI physicalURI = ontologyURI;
		
		try {
			GlobalVariables.ontology = GlobalVariables.ontologyManager.loadOntologyFromOntologyDocument(
																		IRI.create(physicalURI));
			JOptionPane.showMessageDialog(null,
				    "Ontology loaded successfully.",
				    "Ontology loaded",
				    JOptionPane.INFORMATION_MESSAGE);
		} catch (Exception e) {
			//ErrorLogPanel.showErrorDialog(e);
		}
		
		/**
		 * if ontology is loaded assign some variables and fill Class table,
		 * and create a rowSorter for class table
		 */
		if (GlobalVariables.ontology != null) {
			//Initialize resoner and dataFactory variables
			GlobalVariables.reasoner = GlobalVariables.reasonerFactory.createNonBufferingReasoner(GlobalVariables.ontology);
			GlobalVariables.dataFactory = GlobalVariables.ontologyManager.getOWLDataFactory();
			
			//Fill the classTable with data ( the main table )
			ClassesPanel.classesTable.setModel(new GetOntologyClasseForTable());
			
			//Fill the classTable with data ( the dialog table)
			ClassesIndividualsDialog.classesTableDialog.setModel(new GetOntologyClasseForTable());
			
			//Create and assign a Row Sorter to the table
			Search.rowSorterClass = new TableRowSorter<>(ClassesPanel.classesTable.getModel());
			ClassesPanel.classesTable.setRowSorter(Search.rowSorterClass);
			
			//Declare that there is changes made to the ontology
			GlobalVariables.isOntologyChanged = true;
		}
	}
}

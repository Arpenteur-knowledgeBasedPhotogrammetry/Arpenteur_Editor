package org.arpenteur.ontology.editor.model.ontology;

import java.io.File;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.TableRowSorter;

import org.arpenteur.editor.model.GetOntologyClasseForTable;
import org.arpenteur.editor.model.Search;
import org.arpenteur.editor.ui.ClassesPanel;
import org.arpenteur.editor.ui.dialog.ClassesIndividualsDialog;
import org.arpenteur.variables.GlobalVariables;
import org.protege.editor.core.ui.error.ErrorLogPanel;
import org.semanticweb.owlapi.model.OWLOntology;

public class LoadOntologyFromFile {
	
private JFileChooser openDialog;
				
	
	public LoadOntologyFromFile() {
		openDialog = new JFileChooser();
		
		int result = openDialog.showOpenDialog(null);
		
		if (result == JFileChooser.APPROVE_OPTION) {
			File file = openDialog.getSelectedFile();
			load(file.getAbsolutePath());
		}
	}
	
	private OWLOntology load(String fileName) {
		File file = new File(fileName);
		try {
			System.out.println("======== {Start Loading Ontology} ========");
			GlobalVariables.ontology = GlobalVariables.ontologyManager.loadOntologyFromOntologyDocument(file);
			JOptionPane.showMessageDialog(null,
				    "Ontology loaded successfully.",
				    "Ontology loaded",
				    JOptionPane.INFORMATION_MESSAGE);
			System.out.println("======== {End Loading Ontology} ========");
		} catch (Exception e) {
			ErrorLogPanel.showErrorDialog(e);
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
			
			//Create and assign a Row Sorter to the tables
			Search.rowSorterClass = new TableRowSorter<>(ClassesPanel.classesTable.getModel());
			ClassesPanel.classesTable.setRowSorter(Search.rowSorterClass);
			
			//Declare that there is changes made to the ontology
			GlobalVariables.isOntologyChanged = true;
		}
		
		return GlobalVariables.ontology;
	}
}

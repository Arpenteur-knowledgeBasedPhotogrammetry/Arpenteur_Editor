package org.arpenteur.ontology.editor.global;

import javax.swing.table.TableRowSorter;

import org.arpenteur.common.GetDataProperty;
import org.arpenteur.common.GetObjectProperty;
import org.arpenteur.common.GetOntologyIndividuals;
import org.arpenteur.editor.model.GetOntologyClasseForTable;
import org.arpenteur.editor.model.Search;
import org.arpenteur.editor.ui.ClassesPanel;
import org.arpenteur.editor.ui.DataPropertyPanel;
import org.arpenteur.editor.ui.IndividualsPanel;
import org.arpenteur.editor.ui.ObjectPropertyPanel;
import org.arpenteur.variables.GlobalVariables;
import org.protege.editor.owl.model.OWLModelManager;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLDataFactory;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.semanticweb.owlapi.reasoner.OWLReasoner;
import org.semanticweb.owlapi.reasoner.OWLReasonerFactory;
import org.semanticweb.owlapi.reasoner.structural.StructuralReasonerFactory;

public class GlobalVariables {
	/**
	 * Editor & Plugin
	 */
	public static OWLOntologyManager ontologyManager = OWLManager.createOWLOntologyManager();
	
	public static OWLReasonerFactory reasonerFactory = new StructuralReasonerFactory();
	
	public static OWLReasoner reasoner = null;
	
	public static OWLOntology ontology = null;
	
	public static OWLDataFactory dataFactory = null;
		
	
	/**
	 * Plugin only
	 */
	public static OWLModelManager modelManager = null;
	
	
	/**
	 * Editor only
	 */
	public static String editorName = "NYM Editor";
	
	public static boolean isOntologyChanged = false;
	
	
	
	//For plugin .. We need to call this every time an ontology loaded to the Protege .
	public static void toLoadAfterOntology() {
		GlobalVariables.ontology = GlobalVariables.modelManager.getActiveOntology();
    	GlobalVariables.reasoner = GlobalVariables.reasonerFactory.createNonBufferingReasoner(GlobalVariables.ontology);
    	GlobalVariables.dataFactory = GlobalVariables.ontologyManager.getOWLDataFactory();
	}
	
	//For editor to refresh all tables if change made.
	public static void refreshTables() {
		//Data property table (right side top)
		DataPropertyPanel.dataPropertyTable.setModel(new GetDataProperty(IndividualsPanel.individualName));
		
		//Object property table (right side bot)
		ObjectPropertyPanel.objectPropertyTable.setModel(new GetObjectProperty(IndividualsPanel.individualName));
		
		//Individuals table (left side bot)
		IndividualsPanel.individualsTable.setModel(new GetOntologyIndividuals(ClassesPanel.classSelectedName));
		Search.rowSorterIndividual = new TableRowSorter<>(IndividualsPanel.individualsTable.getModel());
	    IndividualsPanel.individualsTable.setRowSorter(Search.rowSorterIndividual);
		
		//Classes JTable (left side top)
		ClassesPanel.classesTable.setModel(new GetOntologyClasseForTable());
		Search.rowSorterClass = new TableRowSorter<>(ClassesPanel.classesTable.getModel());
		ClassesPanel.classesTable.setRowSorter(Search.rowSorterClass);
	}
	
	//For plugin and editor; get the classes prefix
	public static String prefix() {
		return GlobalVariables.ontology.getClassesInSignature().iterator().next().getIRI().getNamespace();
	}
}

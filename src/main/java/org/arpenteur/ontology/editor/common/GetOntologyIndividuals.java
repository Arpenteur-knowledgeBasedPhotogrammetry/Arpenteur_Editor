package org.arpenteur.ontology.editor.common;

import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.protege.editor.core.ui.error.ErrorLogPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;

public class GetOntologyIndividuals extends DefaultTableModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2953252825398438191L;

	/**
	 * Constructs a GetOntologyIndividuals to get individuals of selected class from ontology.
	 * I use Vector to store data because the size of Vector can grow or shrink as needed.
	 * 
	 * @param className		selected class
	 */
	public GetOntologyIndividuals(String className) {
		try {
			System.out.println("======== {Start loading individuals for " + className + "} ========");
			//Add three column to Table model
			this.addColumn("Individual");
			this.addColumn("IDn");
			this.addColumn("Name");
			
			//Create a vector to store ontology individuals.
			Vector<String> rows = null;
			
			/*GraphLauncher.nodeStr.removeAllElements();
			//Add the first node to the Graph with the selected class name
			GraphLauncher.nodeStr.add(className);*/
			
			//The first Loop is to get ontology classes so we can create instances of each class 
			//and see if it is the right one and so on.
			for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			    if (c.getIRI().getShortForm().equals(className)){
			        NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			        
			        for (OWLNamedIndividual i : instances.getFlattened()) {
			        	OWLDataProperty hasIDnProperty = GlobalVariables.dataFactory.
			        										getOWLDataProperty(IRI.create(GlobalVariables.prefix() + "hasIdn"));
			        	OWLDataProperty hasNameProperty = GlobalVariables.dataFactory.
															getOWLDataProperty(IRI.create(GlobalVariables.prefix() + "hasName"));
			        	
			        	Set<OWLLiteral> nameV = GlobalVariables.reasoner.getDataPropertyValues(i, hasNameProperty);
			    		Set<OWLLiteral> idnV = GlobalVariables.reasoner.getDataPropertyValues(i, hasIDnProperty);
			    		
			    		/**
			    		 * Graph test
			    		 */
		    			//Set isIndividualsLoaded to true to tell the Graph class that the individuals is ready to display
			    		//GraphLauncher.isIndividualsLoaded = true;
			    		
			    		//Add other nodes with individuals name
			    		//GraphLauncher.nodeStr.add(i.getIRI().getShortForm());
			    		
			    		//end graph test
			    		
			    		
			    		//Create a new Vector for each row we added
			        	rows = new Vector<String>();
			            rows.add(i.getIRI().getShortForm());
			            
			            //JOptionPane.showConfirmDialog(null, "idn: " + idnV + " Name: " + nameV);
			        	for (OWLLiteral idnValue : GlobalVariables.reasoner.getDataPropertyValues(i, hasIDnProperty)) {
			    			rows.add(idnValue.getLiteral());
			    		}
			        	
			        	//if there is no data for this cell we add a Null value to it
			        	if (idnV.size() == 0) {
			    			rows.add(null);
			    		}
			        	
			        	for (OWLLiteral NameValue : GlobalVariables.reasoner.getDataPropertyValues(i, hasNameProperty)) {
			    			rows.add(NameValue.getLiteral());
			    		}
			        	
			        	//if there is no data for this cell we add a Null value to it
			        	if (nameV.size() == 0) {
			    			rows.add(null);
			    		}
			        	
			        	//Add the created rows to Table model
			            this.addRow(rows);
			        }
			    }
			}
			
			System.out.println("======== {End loading individuals} ========");
		} catch(Exception e) {
			ErrorLogPanel.showErrorDialog(e);
		}	
	}
}

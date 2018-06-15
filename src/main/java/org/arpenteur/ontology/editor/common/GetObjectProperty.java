package org.arpenteur.ontology.editor.common;

import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLIndividual;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.search.EntitySearcher;

public class GetObjectProperty extends DefaultTableModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2693192259668744448L;

	/**
	 *  Constructs a GetObjectProperty who can get object property as a DefaultTableModel
	 *  I use Vector to store data because the size of Vector can grow or shrink as needed.
	 *  
	 * @param individualName	selected individual
	 */
	public GetObjectProperty(String individualName) {
		System.out.println("======== {Start loading Object Property for " + individualName + "} ========");
		
		//Add two column to Table model
		this.addColumn("Object Property");
		this.addColumn("Value");
		
		//Create a vector to store ontology object property
		Vector<Object> rows = null;
		
		//The first Loop is to get ontology classes so we can create instances of each class 
		//and see if it is the right one and so on.
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			
			for (OWLNamedIndividual i : instances.getFlattened()) {
				if(i.getIRI().getShortForm().equals(individualName)) {
					/**
					 * Graph
					 */
					/*GraphLauncher.nodeStr.removeAllElements();
					GraphLauncher.nodeStr.add(individualName);*/
					//End graph
					
					OWLNamedIndividual input = GlobalVariables.dataFactory.getOWLNamedIndividual(IRI.create(i.getIRI().toString()));
					Set<OWLObjectPropertyAssertionAxiom> properties = GlobalVariables.ontology.getObjectPropertyAssertionAxioms(input);
					
					for (OWLObjectPropertyAssertionAxiom ax: properties) {
						for (OWLIndividual propertyLit :  EntitySearcher.getObjectPropertyValues(i, ax.getProperty(), GlobalVariables.ontology)) {
							//Graph
							//GraphLauncher.nodeStr.add(propertyLit.getSignature().iterator().next().getIRI().getShortForm());
							
							//Create a new Vector for each row we added
							rows = new Vector<Object>();
							rows.add(ax.getObjectPropertiesInSignature().iterator().next().getIRI().getShortForm());
			    			rows.add(propertyLit.getSignature().iterator().next().getIRI().getShortForm());
						}
						//Add the created rows to Table model
						this.addRow(rows);
					}
				}
			}
		}
		System.out.println("======== {End loading Object Properties} ========");
	}
}

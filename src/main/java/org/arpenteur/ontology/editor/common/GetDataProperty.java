package org.arpenteur.ontology.editor.common;

import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.arpenteur.variables.GlobalVariables;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLLiteral;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;
import org.semanticweb.owlapi.search.EntitySearcher;

public class GetDataProperty extends DefaultTableModel {
	
	/**
	 * Constructs a GetDataProperty who can get data property as a DefaultTableModel
	 * I use Vector to store data because the size of Vector can grow or shrink as needed.
	 * 
	 * @param individualName	selected individual
	 */
	public GetDataProperty(String individualName) {
		System.out.println("======== {Start loading Data Property for " + individualName + "} ========");
		
		//Add two column to Table model
		this.addColumn("Data Property");
		this.addColumn("Value");
		
		//Create a vector to store ontology data property.
		Vector<Object> rows = null;
		
		//The first Loop is to get ontology classes so we can create instances of each class 
		//and see if it is the right one and so on.
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			
			for (OWLNamedIndividual i : instances.getFlattened()) {
				if(i.getIRI().getShortForm().equals(individualName)) {
					
					OWLNamedIndividual input = GlobalVariables.dataFactory.getOWLNamedIndividual(IRI.create(i.getIRI().toString()));
					Set<OWLDataPropertyAssertionAxiom> properties = GlobalVariables.ontology.getDataPropertyAssertionAxioms(input);
					
					for (OWLDataPropertyAssertionAxiom ax: properties) {
						for (OWLLiteral propertyLit :  EntitySearcher.getDataPropertyValues(i, ax.getProperty(), GlobalVariables.ontology)) {
							//Create a new Vector for each row we added
							rows = new Vector<Object>();
							rows.add(ax.getDataPropertiesInSignature().iterator().next().getIRI().getShortForm());
							rows.add(propertyLit.getLiteral());
						}
						//Add the created rows to Table model
						this.addRow(rows);
					}
				}
			}
		}
		System.out.println("======== {End loading Data Properties} ========");
	}
}

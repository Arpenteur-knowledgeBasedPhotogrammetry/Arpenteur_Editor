package org.arpenteur.ontology.editor.model;

import javax.swing.table.DefaultTableModel;

import org.arpenteur.variables.GlobalVariables;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;

public class GetOWLClassName extends DefaultTableModel {
	
public static String classNameForObjectProperty = "";

	
	/**
	 * Get the class name by the individual name
	 * @param individualName	individual selected name
	 */
	public GetOWLClassName(String individualName) {
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			
			for (OWLNamedIndividual i : instances.getFlattened()) {
				if (i.getIRI().getShortForm().equals(individualName)) {
					classNameForObjectProperty = c.getIRI().getShortForm();
				}
			}
		}
	}
}

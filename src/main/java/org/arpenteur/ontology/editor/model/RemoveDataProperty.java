package org.arpenteur.ontology.editor.model;

import java.util.Set;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.ui.DataPropertyPanel;
import org.arpenteur.ontology.editor.ui.IndividualsPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.reasoner.NodeSet;

public class RemoveDataProperty {
	
	/**
	 * Get classes and instances 
 	 * Get the selected individual
 	 * Get the selected property then delete it
	 */
	public RemoveDataProperty() {
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			for (OWLNamedIndividual i : instances.getFlattened()) {
				if(i.getIRI().getShortForm().equals(IndividualsPanel.individualName)) {
					OWLNamedIndividual input = GlobalVariables.dataFactory.getOWLNamedIndividual(IRI.create(i.getIRI().toString()));
					Set<OWLDataPropertyAssertionAxiom> properties = GlobalVariables.ontology.getDataPropertyAssertionAxioms(input);
					
					for (OWLDataPropertyAssertionAxiom ax: properties) {
						if(getDataName(ax).equals(DataPropertyPanel.selectedDataProperty)) {
							GlobalVariables.ontologyManager.removeAxiom(GlobalVariables.ontology, ax);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Filter IRI of the property to delete it.
	 * @param ax : Take a data property axiom
	 * @return name of the data property as a String
	 */
	private String getDataName(OWLDataPropertyAssertionAxiom ax) {
		return ax.getDataPropertiesInSignature().iterator().next().getIRI().getShortForm();
	}
}

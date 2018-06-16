package org.arpenteur.ontology.editor.model;

import java.util.Set;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.ui.IndividualsPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectPropertyAssertionAxiom;
import org.semanticweb.owlapi.reasoner.NodeSet;

public class RemoveObjectProperty {
	
	/**
	 * Remove the selected object property
	 * 
	 * @param objectPropertyName	Object property name
	 */
	public RemoveObjectProperty(String objectPropertyName) {
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			
			for (OWLNamedIndividual i : instances.getFlattened()) {
				if(i.getIRI().getShortForm().equals(IndividualsPanel.individualName)) {
					OWLNamedIndividual input = GlobalVariables.dataFactory.
										getOWLNamedIndividual(IRI.create(i.getIRI().toString()));
					Set<OWLObjectPropertyAssertionAxiom> properties = GlobalVariables.ontology.
										getObjectPropertyAssertionAxioms(input);
					
					for (OWLObjectPropertyAssertionAxiom ax: properties) {
						if(getObjectName(ax).equals(objectPropertyName)) {
							GlobalVariables.ontologyManager.removeAxiom(GlobalVariables.ontology, ax);
						}
					}
				}
			}
		}
	}
	
	/**
	 * Filter IRI of the property to delete it.
	 * @param ax : Take a Object property axiom
	 * @return name of the Object property as a String
	 */
	private String getObjectName(OWLObjectPropertyAssertionAxiom ax) {
		return ax.getObjectPropertiesInSignature().iterator().next().getIRI().getShortForm();
	}
}

package org.arpenteur.ontology.editor.model;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.ui.IndividualsPanel;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.OWLObjectProperty;

public class AddObjectProperty {
	
	/**
	 * Constract a new Object property
	 * 
	 * @param objectPropertyName	The name of the object property
	 * @param objectPropertyValue	The value of the object property
	 */
	public AddObjectProperty(String objectPropertyName, String objectPropertyValue) {
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			String prefix = c.getIRI().getNamespace();
			
			OWLNamedIndividual classIndName = GlobalVariables.dataFactory.getOWLNamedIndividual(IRI.create(prefix + objectPropertyValue));
			OWLNamedIndividual originIndName = GlobalVariables.dataFactory.getOWLNamedIndividual(IRI.create(prefix + IndividualsPanel.individualName));
			OWLObjectProperty objectName = GlobalVariables.dataFactory.getOWLObjectProperty(IRI.create(prefix + objectPropertyName));
			
			OWLAxiom axiom = GlobalVariables.dataFactory.getOWLObjectPropertyAssertionAxiom(objectName, originIndName, classIndName);
			
			AddAxiom addAxiom = new AddAxiom(GlobalVariables.ontology, axiom);
			GlobalVariables.ontologyManager.applyChange(addAxiom);
		}
	}
}

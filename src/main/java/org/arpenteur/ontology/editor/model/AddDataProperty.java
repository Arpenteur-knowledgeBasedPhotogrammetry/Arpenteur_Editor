package org.arpenteur.ontology.editor.model;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.ui.IndividualsPanel;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class AddDataProperty {
	
	/**
	 * Adding data property to a selected individual
	 * 
	 * @param propertyName		Name of data Property
	 * @param propertyValue		Value of Data Property
	 * @param propertyType		Data Type of Data Property
	 */
	public AddDataProperty(String propertyName, String propertyValue, String propertyType) {
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			String prefix = c.getIRI().getNamespace();
			OWLAxiom axiom = null;
			
			OWLDataProperty hasSomethingProperty = GlobalVariables.dataFactory.
													getOWLDataProperty(IRI.create(prefix + propertyName));
			OWLNamedIndividual classIndName = GlobalVariables.dataFactory.
												getOWLNamedIndividual(IRI.create(prefix + IndividualsPanel.individualName));
			
			axiom = GlobalVariables.dataFactory.
						getOWLDataPropertyAssertionAxiom(hasSomethingProperty, classIndName, propertyValue);
			
			GlobalVariables.ontologyManager.applyChange(new AddAxiom(GlobalVariables.ontology, axiom));
		}
	}
}

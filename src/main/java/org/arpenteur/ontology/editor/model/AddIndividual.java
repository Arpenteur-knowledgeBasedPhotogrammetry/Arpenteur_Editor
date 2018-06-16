package org.arpenteur.ontology.editor.model;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.ui.ClassesPanel;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLClassAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;

public class AddIndividual {
	
	/**
	 * Adding new individual to a selected class
	 * 
	 * @param individualName		new individual Name
	 */
	public AddIndividual(String individualName) {
		// the Trick is in ":" .. if u want to add use it
		
		OWLClass clsPSS = GlobalVariables.dataFactory.
				getOWLClass(IRI.create(GlobalVariables.prefix() + ":" + ClassesPanel.classSelectedName));
		
		OWLNamedIndividual indivName = GlobalVariables.dataFactory.
				getOWLNamedIndividual(IRI.create(GlobalVariables.prefix() + ":" + individualName));
		
		OWLClassAssertionAxiom classAssertion = GlobalVariables.dataFactory.getOWLClassAssertionAxiom(clsPSS, indivName);
		
		GlobalVariables.ontologyManager.addAxiom(GlobalVariables.ontology, classAssertion);
	}
}

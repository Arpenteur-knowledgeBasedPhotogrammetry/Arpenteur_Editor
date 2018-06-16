package org.arpenteur.ontology.editor.model;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDeclarationAxiom;

public class AddOWLClass {
	
	private OWLClass clsPSS = null;
	
	OWLDeclarationAxiom classAssertion = null;
	
	/**
	 * Adding the new class
	 * 
	 * @param className		the new class name
	 */
	public AddOWLClass(String className) {
		//If we let the ':' it like add class, if we remove ':' it like just reading the class
		clsPSS = GlobalVariables.dataFactory.getOWLClass(IRI.create(GlobalVariables.prefix() + ":" + className));
				
		classAssertion = GlobalVariables.dataFactory.getOWLDeclarationAxiom(clsPSS);
							
		GlobalVariables.ontologyManager.addAxiom(GlobalVariables.ontology, classAssertion);
	}
}

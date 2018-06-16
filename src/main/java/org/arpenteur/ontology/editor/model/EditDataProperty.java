package org.arpenteur.ontology.editor.model;

import java.util.Set;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.ui.DataPropertyPanel;
import org.arpenteur.ontology.editor.ui.IndividualsPanel;
import org.semanticweb.owlapi.model.AddAxiom;
import org.semanticweb.owlapi.model.IRI;
import org.semanticweb.owlapi.model.OWLAxiom;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.model.OWLDataProperty;
import org.semanticweb.owlapi.model.OWLDataPropertyAssertionAxiom;
import org.semanticweb.owlapi.model.OWLNamedIndividual;
import org.semanticweb.owlapi.model.RemoveAxiom;
import org.semanticweb.owlapi.reasoner.NodeSet;

public class EditDataProperty {
	/**
	 * Edit the selected data property by removing it and adding it again with new value
	 * 
	 * @param dataName		Data Property name
	 * @param dataValue		Data property value
	 */
	public EditDataProperty(String dataName, String dataValue) {
		
		//The first loop get all ontology classes
		for (OWLClass c : GlobalVariables.ontology.getClassesInSignature()) {
			
			OWLDataProperty hasSomethingProperty = GlobalVariables.dataFactory.
													getOWLDataProperty(IRI.create(GlobalVariables.prefix() + dataName));
			OWLNamedIndividual classIndName = GlobalVariables.dataFactory.
												getOWLNamedIndividual(IRI.create(GlobalVariables.prefix() + IndividualsPanel.individualName));
			OWLAxiom axiom = GlobalVariables.dataFactory.
								getOWLDataPropertyAssertionAxiom(hasSomethingProperty, classIndName, dataValue);
			
			NodeSet<OWLNamedIndividual> instances = GlobalVariables.reasoner.getInstances(c, false);
			for (OWLNamedIndividual i : instances.getFlattened()) {
				if(i.getIRI().getShortForm().equals(IndividualsPanel.individualName)) {
					OWLNamedIndividual input = GlobalVariables.dataFactory.getOWLNamedIndividual(IRI.create(i.getIRI().toString()));
					Set<OWLDataPropertyAssertionAxiom> properties = GlobalVariables.ontology.getDataPropertyAssertionAxioms(input);
					
					for (OWLDataPropertyAssertionAxiom ax: properties) {
						if(getDataName(ax).equals(DataPropertyPanel.selectedDataProperty)) {
							//Remove the old data propertyS
							GlobalVariables.ontologyManager.applyChange(new RemoveAxiom(GlobalVariables.ontology, ax));
							//Add new Data property with the same value as the old one with editing
							GlobalVariables.ontologyManager.applyChange(new AddAxiom(GlobalVariables.ontology, axiom));
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

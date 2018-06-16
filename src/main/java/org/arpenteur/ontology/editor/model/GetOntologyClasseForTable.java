package org.arpenteur.ontology.editor.model;

import java.util.Set;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.semanticweb.owlapi.model.OWLClass;

public class GetOntologyClasseForTable extends DefaultTableModel {
	
private Set<OWLClass> ontologyClassesArray = null;
	
	/**
	 * Get ontology OWLClasses as a DefaultTableModel
	 * I use Vector to store data because the size of Vector can grow or shrink as needed.
	 */
	public GetOntologyClasseForTable() {
		System.out.println("======== {Start Loading classes} ========");
		//Create one table column
		this.addColumn("Classes Name");
		
		//Create a new Vector to store Classes
		Vector<String> rows = null;
		
		//if ontology is loaded
		if(GlobalVariables.ontology != null) {
			ontologyClassesArray = GlobalVariables.ontology.getClassesInSignature();
		}
		
		for (OWLClass clazz : ontologyClassesArray){
			//Create a new vector for each row
			rows = new Vector<String>();
			rows.add(clazz.getIRI().getShortForm());
			//Add the vector created to table model
			this.addRow(rows);
    	}
		System.out.println("======== {End Loading classes} ========");
	}
}

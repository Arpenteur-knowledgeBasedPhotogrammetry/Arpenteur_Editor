package org.arpenteur.ontology.editor.model;

import org.arpenteur.editor.model.AddObjectProperty;
import org.arpenteur.editor.model.RemoveObjectProperty;

public class EditObjectProperty {
	
	/**
	 * Remove the old Object property and add a new one with the same relation name
	 * and the edited value.
	 */
	public EditObjectProperty(String newObjectPropertyName, String newObjectPropertyValue) {
		new RemoveObjectProperty(newObjectPropertyName);
		new AddObjectProperty(newObjectPropertyName, newObjectPropertyValue);
	}
}

package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.arpenteur.ontology.editor.model.EditObjectProperty;
import org.arpenteur.ontology.editor.ui.ObjectPropertyPanel;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.JOptionPaneEx;

public class EditObjectPropertyDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4918068949354086622L;

	private JTextField objectPropertyName;
	
	private JButton chooseValue = new JButton("Choose Value");
	
	
	public static JLabel valueLabelEditObjectproperty;
	
	//To know if Edit object property dialog is active or no
	public static boolean isEditObject = false;
	

	/**
	 * Constract the editing dialog
	 */
	public EditObjectPropertyDialog() {
		isEditObject = true;
		
		setLayout(new BorderLayout());
		
		addListener();
		
		objectPropertyName = new JTextField(45);
		objectPropertyName.setEnabled(false);
		objectPropertyName.setText(ObjectPropertyPanel.selectedObjectProperty);
		JPanel individualNameFieldHolder = new JPanel(new BorderLayout());
		individualNameFieldHolder.setBorder(ComponentFactory.createTitledBorder("Class Name"));
		individualNameFieldHolder.add(objectPropertyName, BorderLayout.NORTH);
		add(individualNameFieldHolder, BorderLayout.NORTH);
		
		valueLabelEditObjectproperty = new JLabel(ObjectPropertyPanel.selectedObjectPropertyValue);
		JPanel ValueFieldHolder = new JPanel(new BorderLayout());
		ValueFieldHolder.setBorder(ComponentFactory.createTitledBorder("Value"));
		ValueFieldHolder.add(valueLabelEditObjectproperty, BorderLayout.NORTH);
		ValueFieldHolder.add(chooseValue, BorderLayout.CENTER);
		add(ValueFieldHolder, BorderLayout.CENTER);
		
		int dialogeInput = JOptionPaneEx.showValidatingConfirmDialog(this,
                "Create a new OWLClass",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.objectPropertyName);
		
		if (dialogeInput == 0) {
			new EditObjectProperty(objectPropertyName.getText(), valueLabelEditObjectproperty.getText());
		}
		
		objectPropertyName.setText("");
		valueLabelEditObjectproperty.setText("");
	}
	
	/**
	 * Add listener to the button
	 */
	private void addListener() {
		chooseValue.addActionListener(e -> new OjectPropertyIndividualsDialog());
	}
}

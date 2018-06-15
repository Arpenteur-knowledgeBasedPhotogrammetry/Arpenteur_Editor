package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.arpenteur.ontology.editor.model.AddObjectProperty;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.JOptionPaneEx;

public class AddObjectPropertyDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8241239863324566871L;

	private JTextField objectPropertyName;
	
	private JButton chooseValue = new JButton("Choose Value");
	
	
	public static JLabel valueLabelAddObjectProperty;
	
	//To know if the AddObjectProperty active or no
	public static boolean isAddObject = false;
	
	/**
	 * Constract a object property dialog to add the value and the property name
	 */
	public AddObjectPropertyDialog() {
		isAddObject = true;
		setLayout(new BorderLayout());
		
		addListener();
		
		objectPropertyName = new JTextField(45);
		JPanel individualNameFieldHolder = new JPanel(new BorderLayout());
		individualNameFieldHolder.setBorder(ComponentFactory.createTitledBorder("Class Name"));
		individualNameFieldHolder.add(objectPropertyName, BorderLayout.NORTH);
		add(individualNameFieldHolder, BorderLayout.NORTH);
		
		valueLabelAddObjectProperty = new JLabel(" ");
		JPanel ValueFieldHolder = new JPanel(new BorderLayout());
		ValueFieldHolder.setBorder(ComponentFactory.createTitledBorder("Value"));
		ValueFieldHolder.add(valueLabelAddObjectProperty, BorderLayout.NORTH);
		ValueFieldHolder.add(chooseValue, BorderLayout.CENTER);
		add(ValueFieldHolder, BorderLayout.CENTER);
		
		int dialogeInput = JOptionPaneEx.showValidatingConfirmDialog(this,
                "Create a new OWLClass",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.objectPropertyName);
		
		if (dialogeInput == 0) {
			new AddObjectProperty(objectPropertyName.getText(), valueLabelAddObjectProperty.getText());
		}
		
		objectPropertyName.setText("");
		valueLabelAddObjectProperty.setText("");
	}
	
	/**
	 * Add listener to the button
	 */
	private void addListener() {
		chooseValue.addActionListener(e -> new ClassesIndividualsDialog());
	}
}

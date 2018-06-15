package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.arpenteur.editor.model.AddDataProperty;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.InputVerificationStatusChangedListener;
import org.protege.editor.core.ui.util.JOptionPaneEx;
import org.protege.editor.core.ui.util.VerifiedInputEditor;

public class AddDataPropertyDialog extends JPanel implements VerifiedInputEditor {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6546331882434412882L;
	
	private JTextField propertyNameField;
	
	private JTextField propertyValueField;
	
	private JComboBox<String> dataTypeCombo;
	
	//Data property data type
	private String[] dataTypes = { "boolean", "byte", "decimal", "double",
							"float", "int", "integer", "long",
							"name", "short", "string"};
	
	/**
	 * Constract add data property dialog
	 */
	public AddDataPropertyDialog() {
		setLayout(new BorderLayout());
		
		propertyNameField = new JTextField(45);
		JPanel propertyNameFieldHolder = new JPanel(new BorderLayout());
		propertyNameFieldHolder.setBorder(ComponentFactory.createTitledBorder("Data Property Name"));
		propertyNameFieldHolder.add(propertyNameField, BorderLayout.NORTH);
		add(propertyNameFieldHolder, BorderLayout.NORTH);
		
		propertyValueField = new JTextField(45);
		JPanel propertyValueFieldHolder = new JPanel(new BorderLayout());
		propertyValueFieldHolder.setBorder(ComponentFactory.createTitledBorder("Value"));
		propertyValueFieldHolder.add(propertyValueField, BorderLayout.CENTER);
		add(propertyValueFieldHolder, BorderLayout.CENTER);
		
		dataTypeCombo = new JComboBox<String>(dataTypes);
		JPanel propertyTypeFieldHolder = new JPanel(new BorderLayout());
		propertyTypeFieldHolder.setBorder(ComponentFactory.createTitledBorder("Type"));
		propertyTypeFieldHolder.add(dataTypeCombo, BorderLayout.SOUTH);
		add(propertyTypeFieldHolder, BorderLayout.SOUTH);
		
		int dialogeInput = JOptionPaneEx.showValidatingConfirmDialog(null,
                "Add Data Property",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.propertyNameField);
		
		if (dialogeInput == 0) {
			new AddDataProperty(propertyNameField.getText(), propertyValueField.getText(), dataTypeCombo.getSelectedItem().toString());
		}
		else {
			return;
		}
	}


	@Override
	public void addStatusChangedListener(InputVerificationStatusChangedListener listener) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeStatusChangedListener(InputVerificationStatusChangedListener listener) {
		// TODO Auto-generated method stub
		
	}
}

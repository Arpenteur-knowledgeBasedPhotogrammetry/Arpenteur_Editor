package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import org.arpenteur.editor.model.EditDataProperty;
import org.arpenteur.editor.ui.DataPropertyPanel;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.InputVerificationStatusChangedListener;
import org.protege.editor.core.ui.util.JOptionPaneEx;
import org.protege.editor.core.ui.util.VerifiedInputEditor;

public class EditDataPropertyDialog extends JPanel implements VerifiedInputEditor {
	
private JTextField propertyNameField;
	
	private JTextField propertyValueField;
	
	private Object[] options = { "Apply changes", "Cancel" };
	
	/**
	 * Constract edit data property dialog
	 */
	public EditDataPropertyDialog() {
		setLayout(new BorderLayout());
		
		propertyNameField = new JTextField(45);
		propertyNameField.setEnabled(false);
		propertyNameField.setText(DataPropertyPanel.selectedDataProperty);
		JPanel propertyNameFieldHolder = new JPanel(new BorderLayout());
		propertyNameFieldHolder.setBorder(ComponentFactory.createTitledBorder("Data Property Name"));
		propertyNameFieldHolder.add(propertyNameField, BorderLayout.NORTH);
		add(propertyNameFieldHolder, BorderLayout.NORTH);
		
		propertyValueField = new JTextField(45);
		propertyValueField.setText(DataPropertyPanel.selectedDataPropertyValue);
		JPanel propertyValueFieldHolder = new JPanel(new BorderLayout());
		propertyValueFieldHolder.setBorder(ComponentFactory.createTitledBorder("Value"));
		propertyValueFieldHolder.add(propertyValueField, BorderLayout.CENTER);
		add(propertyValueFieldHolder, BorderLayout.CENTER);
		
		int clickedButton = JOptionPaneEx.showConfirmDialog(null,
                "Add Data Property",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.propertyNameField, options, options[1]);
		
		if (clickedButton == 0) {
			new EditDataProperty(propertyNameField.getText(), propertyValueField.getText());
			JOptionPane.showMessageDialog(this, "Change Applied Successfully");
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

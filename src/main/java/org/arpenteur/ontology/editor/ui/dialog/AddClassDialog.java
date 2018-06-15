package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.model.AddOWLClass;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.JOptionPaneEx;

public class AddClassDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 176199279323054184L;
	
	private JTextField classNameField;
	
	private JLabel iriLabel;
	
	/**
	 * Constract a dialog to add OWLClass
	 */
	public AddClassDialog() {
		setLayout(new BorderLayout());
		
		classNameField = new JTextField(45);
		JPanel individualNameFieldHolder = new JPanel(new BorderLayout());
		individualNameFieldHolder.setBorder(ComponentFactory.createTitledBorder("Class Name"));
		individualNameFieldHolder.add(classNameField, BorderLayout.NORTH);
		add(individualNameFieldHolder, BorderLayout.NORTH);
		
		iriLabel = new JLabel();
		iriLabel.setText(GlobalVariables.prefix());
		JPanel iriFieldHolder = new JPanel(new BorderLayout());
		iriFieldHolder.setBorder(ComponentFactory.createTitledBorder("IRI (auto-generated)"));
		iriFieldHolder.add(iriLabel, BorderLayout.CENTER);
		add(iriFieldHolder, BorderLayout.CENTER);
		
		classNameField.getDocument().addDocumentListener(new DocumentListener(){

			@Override
			public void changedUpdate(DocumentEvent arg0) {
				changeText();
			}

			@Override
			public void insertUpdate(DocumentEvent arg0) {
				changeText();
			}

			@Override
			public void removeUpdate(DocumentEvent arg0) {
				changeText();
			}
		});
		
		int dialogeInput = JOptionPaneEx.showValidatingConfirmDialog(this,
														                "Create a new OWLClass",
														                this,
														                JOptionPane.PLAIN_MESSAGE,
														                JOptionPane.OK_CANCEL_OPTION,
														                this.classNameField);
		
		if (dialogeInput == 0) {
			new AddOWLClass(classNameField.getText());
		}
		
		classNameField.setText("");
		iriLabel.setText(GlobalVariables.prefix());
	}
	
	private void changeText() {
		iriLabel.setText(GlobalVariables.prefix() + classNameField.getText());
	}
}

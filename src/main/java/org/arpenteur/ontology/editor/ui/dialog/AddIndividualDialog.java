package org.arpenteur.ontology.editor.ui.dialog;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.model.AddIndividual;
import org.protege.editor.core.ui.util.ComponentFactory;
import org.protege.editor.core.ui.util.JOptionPaneEx;

public class AddIndividualDialog extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4470617238229825111L;

	private JTextField individualNameField;
	
	private JLabel iriLabel;
	
	
	/**
	 * Constract the add individual dialog
	 */
	public AddIndividualDialog() {
		setLayout(new BorderLayout());
		
		individualNameField = new JTextField(45);
		JPanel individualNameFieldHolder = new JPanel(new BorderLayout());
		individualNameFieldHolder.setBorder(ComponentFactory.createTitledBorder("Name"));
		individualNameFieldHolder.add(individualNameField, BorderLayout.NORTH);
		add(individualNameFieldHolder, BorderLayout.NORTH);
		
		iriLabel = new JLabel();
		iriLabel.setText(GlobalVariables.prefix());
		JPanel iriFieldHolder = new JPanel(new BorderLayout());
		iriFieldHolder.setBorder(ComponentFactory.createTitledBorder("IRI (auto-generated)"));
		iriFieldHolder.add(iriLabel, BorderLayout.CENTER);
		add(iriFieldHolder, BorderLayout.CENTER);
		
		individualNameField.getDocument().addDocumentListener(new DocumentListener(){

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
			
			public void changeText() {
				iriLabel.setText(GlobalVariables.prefix() + individualNameField.getText());
			}
			
		});
		
		JOptionPaneEx.showValidatingConfirmDialog(this,
                "Create a new OWLNamedIndividual",
                this,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_CANCEL_OPTION,
                this.individualNameField);
		
		new AddIndividual(individualNameField.getText());
		
		individualNameField.setText("");
		iriLabel.setText(GlobalVariables.prefix());
	}
}

package org.arpenteur.ontology.editor.model;

import java.util.Set;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.protege.editor.core.ui.error.ErrorLogPanel;
import org.semanticweb.owlapi.model.OWLClass;
import org.semanticweb.owlapi.reasoner.ReasonerInterruptedException;
import org.semanticweb.owlapi.reasoner.TimeOutException;

public class InconsistencyTest extends JPanel {
	
	
	public InconsistencyTest() {
		if (GlobalVariables.ontology == null) {
			JOptionPane.showMessageDialog(this, "Validation  fail, Ontology is null !");
		} else {
			System.out.println("Validation start");
			if(GlobalVariables.reasoner.isConsistent()) {
				JOptionPane.showMessageDialog(this, "Ontology is consistent");
			}
			
			if (!GlobalVariables.reasoner.isConsistent()) {
				JOptionPane.showMessageDialog(null, "check consistency of Ontologie: " + GlobalVariables.reasoner.isConsistent());
				try {
					Set<OWLClass> problems = GlobalVariables.reasoner.getUnsatisfiableClasses().getEntities();
					
					for (OWLClass cl : problems) {
						System.out.println("Inconsistent class: " + cl.toStringID());
					}
				} catch (TimeOutException e) {
					ErrorLogPanel.showErrorDialog(e);
				} catch (ReasonerInterruptedException e) {
					ErrorLogPanel.showErrorDialog(e);
				}
			}
			System.out.println("Validation end");
		}
	}
}

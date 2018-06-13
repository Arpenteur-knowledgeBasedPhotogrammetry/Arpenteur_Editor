package org.arpenteur.ontology.editor.ui;

import javax.swing.JSplitPane;
import javax.swing.JTabbedPane;

import org.arpenteur.editor.ui.DataPropertyPanel;
import org.arpenteur.editor.ui.GraphPanel;
import org.arpenteur.editor.ui.ObjectPropertyPanel;

public class MainFrameTabbedPane extends JTabbedPane {
	
	//For tabed pane test
	//private String instanceNameTab = "Instance Name";

		
	/**
	 * 
	 */
	private static final long serialVersionUID = 9050314844836536431L;

	/**
	 * Constract the tabs
	 */
	public MainFrameTabbedPane() {
		//First tab , data and object property tables
		JSplitPane data_Object_Property_Split = new JSplitPane(JSplitPane.VERTICAL_SPLIT,
				new DataPropertyPanel(), new ObjectPropertyPanel());
        
        data_Object_Property_Split.setDividerLocation(300);
        
        //The data and object property tab
		this.addTab("Data & Object Property", data_Object_Property_Split);
		
		this.addTab("Ontology Graph", new GraphPanel());
		//the instance tab (this tabed pane was just a test)
		//this.addTab(instanceNameTab, instancePanel);
	}
}

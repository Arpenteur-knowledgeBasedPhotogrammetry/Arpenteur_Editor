package org.arpenteur.ontology.editor.ui;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;
import javax.swing.JToolBar;

import org.arpenteur.ontology.editor.global.GlobalVariables;
import org.arpenteur.ontology.editor.model.RemoveOWLClass;
import org.arpenteur.ontology.editor.model.Search;
import org.arpenteur.ontology.editor.model.graph.GraphAlgo;
import org.arpenteur.ontology.editor.model.graph.GraphLauncher;
import org.arpenteur.ontology.editor.model.graph.GraphPumpRunner;
import org.arpenteur.ontology.editor.ui.dialog.AddClassDialog;
import org.arpenteur.ontology.editor.ui.dialog.AddDataPropertyDialog;
import org.arpenteur.ontology.editor.ui.dialog.AddIndividualDialog;
import org.arpenteur.ontology.editor.ui.dialog.AddObjectPropertyDialog;
import org.arpenteur.ontology.editor.ui.dialog.EditDataPropertyDialog;
import org.arpenteur.ontology.editor.ui.dialog.EditObjectPropertyDialog;
import org.arpenteur.ontology.editor.ui.dialog.RemoveDataPropertyDialog;
import org.arpenteur.ontology.editor.ui.dialog.RemoveObjectPropertyDialog;

public class FramePanelsToolBars extends JToolBar {
	/**
	 * 
	 */
	private static final long serialVersionUID = 562388072550110321L;
	
	//Data Property
    private JButton add_Data = new JButton("Add");
    private JButton remove_Data = new JButton("Remove");
    private JButton edit_Data = new JButton("Edit");
    //private JButton Annotations_Data = new JButton("Annotations");
    
    //Object Property
    private JButton add_Object = new JButton("Add");
    private JButton remove_Object = new JButton("Remove");
    private JButton edit_Object = new JButton("Edit");
    //private JButton Annotations_Object = new JButton("Annotations");

    //Classes
    private JButton add_Class = new JButton("Add Class");
    private JButton remove_Class = new JButton("Remove Class");
    private JLabel  search_Class = new JLabel ("Search: ");
    private JTextField searchField_Class = new JTextField();
    
    //Individuals
    private JButton add_Individual = new JButton("Add Individual");
    private JButton remove_Individual = new JButton("Remove Individual");
    private JLabel  search_Individual = new JLabel ("Search: ");
    private JTextField searchField_Individual = new JTextField();
    
    //Ontology Graph Tab
    private JButton charge_Graph = new JButton("Charge Graph");
    private ImageIcon plusIcon_Graph = new ImageIcon("resources/plusR.png");
    private JButton zoomOut_Graph = new JButton(plusIcon_Graph);
    private JSlider zoomInOut_Graph = new JSlider();
    private ImageIcon minusIcon_Graph = new ImageIcon("resources/minusR.png");
    private JButton zoomIn_Graph = new JButton(minusIcon_Graph);
    private JButton resetSize_Graph = new JButton("Default Size");
    public static JCheckBox selectedClass_Graph = new JCheckBox("Selected Class");
    
    
	/**
	 * 
	 * @param type: 0: Data Property Table; 1: Object Property Table
	 * 				2: Classes Table;		3: Individuals Table
	 * 				4: Instance Tab
	 */
	public FramePanelsToolBars(int type) {
		switch (type) {
			case 0: {
				this.add(add_Data);
				this.add(remove_Data);
				this.add(edit_Data);
				//this.add(Annotations_Data);
				break;
			}
			case 1: {
				this.add(add_Object);
				this.add(remove_Object);
				this.add(edit_Object);
				//this.add(Annotations_Object);
				break;
			}
			case 2: {
				this.add(add_Class);
				this.add(remove_Class);
				this.addSeparator();
				this.add(search_Class);
				this.add(searchField_Class);
				break;
			}
			case 3: {
				this.add(add_Individual);
				this.add(remove_Individual);
				this.addSeparator();
				this.add(search_Individual);
				this.add(searchField_Individual);
				break;
			}
			case 4: {
				this.add(charge_Graph);
				this.addSeparator();
				this.addSeparator();
				this.add(zoomOut_Graph);
				this.add(zoomInOut_Graph);
				this.add(zoomIn_Graph);
				this.addSeparator();
				this.addSeparator();
				this.add(resetSize_Graph);
				this.addSeparator();
				this.add(selectedClass_Graph);
				selectedClass_Graph.setEnabled(false);
				break;
			}
			default: System.err.println("For dev: You have error in toolbar String put data or object");break;
		}
		
		//Add buttons listener
		addListener();
		
		//call search class and put the search field as a parameter
		new Search(searchField_Class, true);
		new Search(searchField_Individual, false);
	}
	
	/**
	 * Add a new data property and refresh tables.
	 */
	private void addDataProperty() {
		new AddDataPropertyDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Remove data property and refresh tables
	 */
	private void removeDataProperty() {
		new RemoveDataPropertyDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Editing selected data property
	 */
	private void editDataProperty() {
		new EditDataPropertyDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Add a new Object property
	 */
	private void addObjectProperty() {
		new AddObjectPropertyDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Editing selected Object property
	 */
	private void editObjectProperty() {
		new EditObjectPropertyDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Remove selected Object Property
	 */
	private void removeObjectProperty() {
		new RemoveObjectPropertyDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Add Class to the ontology
	 */
	private void addClassToOntology() {
		new AddClassDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Remove Class from the Ontology
	 */
	private void removeOWLClass() {
		new RemoveOWLClass();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Add Individual to a selected class
	 */
	private void addIndividual() {
		new AddIndividualDialog();
		GlobalVariables.refreshTables();
	}
	
	/**
	 * Charge Graph for the selected class
	 */
	private void chargeGraph() {
		if (IndividualsPanel.isInstanceSelected) {
			if(GraphLauncher.graph != null) {
				for (int i = 0; i < GraphLauncher.nodeStr.size(); i++) {
					GraphLauncher.graph.removeNode(GraphLauncher.nodeStr.get(i));
				}
			}
			
			GraphAlgo.graphEdgeID = 0;

			new GraphLauncher();
			MainFrame.tabbedPanes.remove(1);
			MainFrame.tabbedPanes.addTab("Ontology Graph", new GraphPanel());
			MainFrame.tabbedPanes.setSelectedIndex(1);
			
			GraphPumpRunner graphPumpRunner = new GraphPumpRunner();
			Thread thread = new Thread(graphPumpRunner);
			thread.start();
		}
	}
	
	/**
	 * Zoom In and Zoom Out the graph
	 */
	private void zoomInOut() {
		GraphPanel.viewPanel.getCamera().setViewPercent(zoomInOut_Graph.getValue() / 20.0);
	}
	
	/**
	 * Zoom out the graph
	 */
	private void zoomOut() {
		zoomInOut_Graph.setValue(zoomInOut_Graph.getValue() - 5);
	}
	
	/**
	 * Zoom in the graph
	 */
	private void zoomIn() {
		zoomInOut_Graph.setValue(zoomInOut_Graph.getValue() + 5);
	}
	
	/**
	 * Reset the default size of the graph
	 */
	private void resetDefaultSize() {
		GraphPanel.viewPanel.getCamera().resetView();
	}
	
	
	/**
	 * Add listener to buttons,, it require java 8 ..
	 */
	private void addListener() {
		//Data Property Table (right side top)
		add_Data.addActionListener(e -> addDataProperty());
		remove_Data.addActionListener(e -> removeDataProperty());
		edit_Data.addActionListener(e -> editDataProperty());
		
		//Class Table (left side top)
		add_Class.addActionListener(e -> addClassToOntology());
		remove_Class.addActionListener(e -> removeOWLClass());
		
		//Individuals Table (left side bot)
		add_Individual.addActionListener(e -> addIndividual());
		
		//Object Property Table (right side bot)
		add_Object.addActionListener(e -> addObjectProperty());
		edit_Object.addActionListener(e -> editObjectProperty());
		remove_Object.addActionListener(e -> removeObjectProperty());
		
		//Ontology Graph tab
		charge_Graph.addActionListener(e -> chargeGraph());
		zoomInOut_Graph.addChangeListener(e -> zoomInOut());
		zoomOut_Graph.addActionListener(e -> zoomOut());
		zoomIn_Graph.addActionListener(e -> zoomIn());
		resetSize_Graph.addActionListener(e -> resetDefaultSize());
	}
}

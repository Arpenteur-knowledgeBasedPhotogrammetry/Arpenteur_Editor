package org.arpenteur.ontology.editor.ui;

import java.awt.event.KeyEvent;
import java.net.URI;

import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import org.arpenteur.ontology.editor.model.InconsistencyTest;
import org.arpenteur.ontology.editor.model.ontology.LoadOntologyFromFile;
import org.arpenteur.ontology.editor.model.ontology.LoadOntologyFromURL;
import org.arpenteur.ontology.editor.model.ontology.SaveOntology;
import org.arpenteur.ontology.editor.model.ontology.SaveOntologyAs;
import org.protege.editor.core.ui.OpenFromURLPanel;

public class FrameTopMenu extends JMenuBar {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3042400502924824703L;

	//First menu
		private final JMenu file = new JMenu("File");
		
		//2nd menu
		private final JMenu tools = new JMenu("Tools");
		
		//3rd
		private final JMenu tabs = new JMenu("Tabs");
		
		//4th menu
		private final JMenu help = new JMenu("Help");
		
		
		//File menu items
		private final JMenuItem _new = new JMenuItem("New");
		
		private final JMenuItem _open = new JMenuItem("Open");
		
		private final JMenuItem _openFromURL = new JMenuItem("Open From URL...");
		
		private final JMenuItem _save = new JMenuItem("Save");
		
		private final JMenuItem _saveAs = new JMenuItem("Save As");
		
		private final JMenuItem _exit = new JMenuItem("Exit");
		
		//Tools menu items
		private final JMenuItem _inconsistencyTest = new JMenuItem("Inconsistency Test");
		
		//Tabs menu items
		private final JMenuItem _ontologyGraph = new JMenuItem("Ontology Graph");
		
		//Help menu items
		private final JMenuItem _about = new JMenuItem("About");
		

		/**
		 * Constracts the menu
		 */
		public FrameTopMenu() {
			//File menu
			file.setMnemonic(KeyEvent.VK_F);
			file.add(_new);
			file.add(_open);
			file.add(_openFromURL);
			file.addSeparator();
			file.add(_save);
			file.add(_saveAs);
			file.addSeparator();
			file.add(_exit);
			
			this.add(file);
			
			//Tools menu
			tools.setMnemonic(KeyEvent.VK_T);
			tools.add(_inconsistencyTest);
			
			this.add(tools);
			
			//Tabs menu
			tabs.setEnabled(false);	//I blocked the tabs Menu just for testing ..
			tabs.add(_ontologyGraph);
			
			this.add(tabs);
			
			//Help menu
			help.setMnemonic(KeyEvent.VK_H);
			help.add(_about);
			
			this.add(help);
			
			addListener();
		}
		
		/**
		 * Add listener to the menu buttons
		 */
		private void addListener() {
			_open.addActionListener(e -> new LoadOntologyFromFile());
			_openFromURL.addActionListener(e -> new LoadOntologyFromURL(getOntologyURI()));
			_save.addActionListener(e -> new SaveOntology(true));
			_saveAs.addActionListener(e -> new SaveOntologyAs());
			_inconsistencyTest.addActionListener(e -> new InconsistencyTest());
			_ontologyGraph.addActionListener(e -> graphLauncher());
		}
		
		/**
		 * Get ontology URI
		 * @return URI
		 */
		private URI getOntologyURI() {
			return OpenFromURLPanel.showDialog();
		}
		
		/**
		 * Open Graph launcher
		 */
		private void graphLauncher() {
			/*if(MyVariables.ontology != null && GraphLauncher.isIndividualsLoaded) {
				//new GraphLauncher();
				//JOptionPane.showMessageDialog(null, new GraphLauncher());
				//MainFrameTabbedPane.graphPanel = new GraphLauncher();
				
				MainFrame.tabbedPanes.addTab("Ontology Graph", new GraphLauncher());
			} else {
				JOptionPane.showMessageDialog(null, "Load the ontology first or/and Amphorae individuals !!");
			}*/
		}
}

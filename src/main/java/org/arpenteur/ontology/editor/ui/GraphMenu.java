package org.arpenteur.ontology.editor.ui;

import java.awt.FlowLayout;

import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

public class GraphMenu extends JPopupMenu {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8437357643197589574L;
	

	private JMenuItem rename = new JMenuItem("Rename");
	
	private JMenuItem copy = new JMenuItem("Copy");
	
	private JMenuItem details = new JMenuItem("Details");
	
	private JLabel title = null;
	
	public GraphMenu(String nodeID) {
		super("Menu");
		
		title = new JLabel(nodeID + " :");
		title.setEnabled(false);
		
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.add(title);
		this.add(rename);
		this.add(copy);
		this.add(details);
	}
}

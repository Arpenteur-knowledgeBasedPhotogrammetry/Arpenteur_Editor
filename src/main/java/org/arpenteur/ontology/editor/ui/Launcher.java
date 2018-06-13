package org.arpenteur.ontology.editor.ui;

import javax.swing.SwingUtilities;

public class Launcher {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
            public void run() {
            	MainFrame frame = new MainFrame();
            	frame.pack();
            	frame.setVisible(true);
            }
        });
	}

}

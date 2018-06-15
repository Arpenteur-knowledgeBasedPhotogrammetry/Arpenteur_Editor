package org.arpenteur.ontology.editor.model.graph;

import org.arpenteur.ontology.editor.ui.GraphPanel;

public class GraphPumpRunner implements Runnable {

	@Override
	public void run() {
		while (true) {
			GraphPanel.fromViewer.pump();
		}
	}
}

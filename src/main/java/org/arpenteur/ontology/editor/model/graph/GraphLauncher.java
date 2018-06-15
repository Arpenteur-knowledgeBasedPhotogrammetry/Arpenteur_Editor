package org.arpenteur.ontology.editor.model.graph;

import java.awt.BorderLayout;
import java.util.Vector;

import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.AdjacencyListGraph;
import org.graphstream.ui.spriteManager.Sprite;
import org.graphstream.ui.spriteManager.SpriteManager;

public class GraphLauncher extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -658271052866531728L;
	
	private GraphAlgo graphAlgo = new GraphAlgo();

	//Create a new graph
	public static Graph graph = null;
	
	//Create an ArrayList that can store the graph nodes
	public static Vector<String> nodeStr = new Vector<String>();
	
	//To know if the individuals is loaded or no
	public static boolean isIndividualsLoaded = false;
	
	//Object Property sprite
	public static Sprite sprite = null;
	
		
	public GraphLauncher() {
	    System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");

		setLayout(new BorderLayout());

		graph = new AdjacencyListGraph("Entity graph");
 		 
		graph.addAttribute("ui.stylesheet", "url('src/main/resources/GraphStyle.css')");
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");
   
		graphAlgo.addNodes(graph, nodeStr);

		graphAlgo.addRelations(graph, nodeStr);
		
		SpriteManager sman = new SpriteManager(graph);
		
		sprite = sman.addSprite("partOf");

        //display labels
		graphAlgo.displayLabels(graph);

        graph.setAutoCreate(true);
        graph.setStrict(false);
        
        //Test 
       /* for (int i = 0 ; i < GetDataAndObjectPropertyForGraph.objectPropertyTable.length ; i++) {
        	JOptionPane.showMessageDialog(null, "I am here" + GetDataAndObjectPropertyForGraph.objectPropertyTable[i].toString());
        	graph.getNode(GetDataAndObjectPropertyForGraph.objectPropertyTable[i].toString()).
        							addAttribute("ui.style", "fill-color: rgb(255, 0, 0);");
        }*/
        
        
	}
}

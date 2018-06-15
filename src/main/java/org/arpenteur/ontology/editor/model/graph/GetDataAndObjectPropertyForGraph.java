package org.arpenteur.ontology.editor.model.graph;

import java.util.Random;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import org.arpenteur.editor.model.graph.GraphLauncher;
import org.arpenteur.editor.ui.IndividualsPanel;

public class GetDataAndObjectPropertyForGraph {
	
public static long ID = 0;
	
	public static Object[] theObjectPropertyTable = null;
	
	private Random rand = new Random();
	
	private int  randomNumber = rand.nextInt(999) + 1;
	
	
	public GetDataAndObjectPropertyForGraph(JTable DataProperttable, JTable ObjectProperttable) {
		DefaultTableModel DataTableModel = (DefaultTableModel) DataProperttable.getModel();
		DefaultTableModel ObjectTableModel = (DefaultTableModel) ObjectProperttable.getModel();
		
		int nRowD = DataTableModel.getRowCount();
		int nRowO = ObjectTableModel.getRowCount();
		
		Object[] dataPropertyTable = new Object[nRowD];
		Object[] objectPropertyTable = new Object[nRowO];
		
		theObjectPropertyTable = new Object[nRowO];
				
	    for (int i = 0 ; i < nRowD ; i++) {
	    	dataPropertyTable[i] = DataTableModel.getValueAt(i,1);
	    }
	    
	    for (int i = 0 ; i < nRowO ; i++) {
	    	objectPropertyTable[i] = ObjectTableModel.getValueAt(i,1);
	    }
	    
	    GraphLauncher.nodeStr.removeAllElements();
		GraphLauncher.nodeStr.add(IndividualsPanel.individualName);
		
	    for (int i = 0 ; i < dataPropertyTable.length ; i++) {
	    	randomNumber = rand.nextInt(999) + 1;
	    	ID++;
	    	GraphLauncher.nodeStr.add("id: "+ID+" Value: "+dataPropertyTable[i].toString() );
	    }
	    
	    for (int i = 0 ; i < objectPropertyTable.length ; i++) {
	    	randomNumber = rand.nextInt(999) + 1;
	    	ID++;
	    	GraphLauncher.nodeStr.add("id: "+ID+" Object: "+objectPropertyTable[i].toString() );
	    	theObjectPropertyTable[i] = "id: "+ID+" Object: "+objectPropertyTable[i].toString() ;
	    }
	}
}

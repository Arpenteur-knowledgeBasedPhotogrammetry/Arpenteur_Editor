package org.arpenteur.ontology.editor.ui;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import org.arpenteur.ontology.editor.common.GetDataProperty;
import org.arpenteur.ontology.editor.common.GetObjectProperty;
import org.arpenteur.ontology.editor.model.graph.GetDataAndObjectPropertyForGraph;


public class IndividualsPanel extends JPanel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 2488151201286377412L;

	public static boolean isInstanceSelected = false;
	
	public static JTable individualsTable = new JTable(){
		
		
		/**
		 * 
		 */
		private static final long serialVersionUID = -6408052269537205277L;

		@Override
		public boolean isCellEditable(int row, int column) {                
            return false;               
    	}; 
    };
    
    public static String individualName = "";
	
	
	public IndividualsPanel() {
		setLayout(new BorderLayout());
		
		addListener();
		
		this.add(new FramePanelsToolBars(3), BorderLayout.NORTH);
		this.add(createTable(), BorderLayout.CENTER);
	}
	
	/**
	 * Add Jtable inside of a JScrollPane
	 * 
	 * @return JScrollPane
	 */
	private JScrollPane createTable() {
		JScrollPane tableContainer = new JScrollPane(individualsTable);
		return tableContainer;
	}
	
	/**
	 * Add Listener to the individualsTable 
	 */
	private void addListener() {
		individualsTable.addMouseListener(new MouseAdapter() {
			@Override
		    public void mousePressed(MouseEvent e) {
				if (e.getClickCount() == 1) {
					//Get seleted row number
				    int row = individualsTable.convertRowIndexToModel(individualsTable.getSelectedRow());
				    
				    //Get the selected row value
				    individualName = individualsTable.getModel().getValueAt(row, 0).toString();
				    System.out.println("IndividualPanel .... individualsTable.getModel().getValueAt(row, 0) "+individualsTable.getModel().getValueAt(row, 0).getClass().getName()  );				    
				    //Get Image location
				   /* new GetImageLocation(individualName, "hasImage");
				    try {
						Image image = ImageIO.read(new URL("http://www.redorbit.com/media/uploads/2015/09/test_2.png"));
						InstancePanel.image = new JLabel(new ImageIcon(image));
						JOptionPane.showConfirmDialog(null,InstancePanel.image);
					} catch (MalformedURLException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				    */
				    
				    //Change instance Tab name (this line was just a test for 
				    //instance name tab)
				    //MyFrame.myTabbedPane.setTitleAt(1, individualName);
				    
				    //Fill data property table
				    DataPropertyPanel.dataPropertyTable.setModel(new GetDataProperty(individualName));
				    //Fill object property table
				    ObjectPropertyPanel.objectPropertyTable.setModel(new GetObjectProperty(individualName));
				    
				    isInstanceSelected = true;
				    
				    new GetDataAndObjectPropertyForGraph(DataPropertyPanel.dataPropertyTable, ObjectPropertyPanel.objectPropertyTable);
				}
		    }
		});
	}
}

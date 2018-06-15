package org.arpenteur.ontology.editor.model;

import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class Search {
	
	/**
	 * Table sorting and filtering is managed by a sorter object
	 */
	public static TableRowSorter<TableModel> rowSorterClass = null;
	
	public static TableRowSorter<TableModel> rowSorterIndividual = null;
	
	
	/**
	 * Add listener to TextFields and manage the search
	 * 
	 * @param searchField		which searchField classe field or individual field
	 * @param isClass			Class field if true, individual field if false
	 */
	public Search(JTextField searchField, boolean isClass) {
		searchField.getDocument().addDocumentListener(new DocumentListener(){
            @Override
            public void insertUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if(isClass) {
                	if (text.trim().length() == 0) {
                    	rowSorterClass.setRowFilter(null);
                    } else {
                    	rowSorterClass.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                } else if(!isClass) {
                	if (text.trim().length() == 0) {
                		rowSorterIndividual.setRowFilter(null);
                    } else {
                    	rowSorterIndividual.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            }
            @Override
            public void removeUpdate(DocumentEvent e) {
                String text = searchField.getText();

                if(isClass) {
                	if (text.trim().length() == 0) {
                    	rowSorterClass.setRowFilter(null);
                    } else {
                    	rowSorterClass.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                } else if(!isClass) {
                	if (text.trim().length() == 0) {
                		rowSorterIndividual.setRowFilter(null);
                    } else {
                    	rowSorterIndividual.setRowFilter(RowFilter.regexFilter("(?i)" + text));
                    }
                }
            }
            @Override
            public void changedUpdate(DocumentEvent e) {
                throw new UnsupportedOperationException("Not supported yet.");
            }

        });
	}
}

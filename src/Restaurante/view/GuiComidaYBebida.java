/**
 * Gui de la tabla de ComidaYBebida
 */
package Restaurante.view;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import org.json.JSONArray;
import org.json.JSONObject;

import Restaurante.control.Restaurante;
import Restaurante.view.ComidaYBebida.CYBTableModel;

public class GuiComidaYBebida extends JPanel{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private String[] keys = {"Name","Amount","Food","Desc"};
	private int _status;
	private JTable _table;
	private CYBTableModel tableModel;
	Restaurante res;
	//-----------------------------
	//Metodos
	//-----------------------------
	/**
	 * Constructor
	 * @param frame
	 * @param res
	 */
	public GuiComidaYBebida(Restaurante res) {
		this.res = res;
		initGUI();
	}
	/**
	 * Inicia el JDialog de ComidaYbebida
	 */
	private void initGUI() {
		_status = 0;
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(LEFT_ALIGNMENT);
		mainPanel.add(toolBar);	
		//TABLE
		tableModel = new CYBTableModel(res);
		_table = new JTable(tableModel);
		//SCROLLPANE
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

		//BUTTONS
		JButton g = new JButton("Guardar");
		g.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {guardar(e,g);}});
		
		
		JButton Ac = new JButton("Añadir plato");
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.addCYB();}});
		
		JButton Ec = new JButton("Eliminar plato");
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {	tableModel.RemoveCYB();}});
		toolBar.add(g);
		toolBar.add(Ac);
		toolBar.add(Ec);
		add(mainPanel);
	    setVisible(false); 
	}
	/**
	 * Guarda los datos en el fichero elegido
	 * @param e
	 * @param g
	 */
	public void guardar(ActionEvent e,JButton g) {
    	if(e.getSource() == g) {
			try {
				res.setCYB(getCYB());
	            Restaurante.closeCYB();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
    	}
    	
	}
	/**
	 * Abre la tabla
	 * @return
	 */
	public int open() {
        tableModel.clear();
		setVisible(true);
		return _status;
	}
	/**
	 * Coge los datos de la tabla y los pone en un JSONArray
	 * @return
	 */
	public JSONArray getCYB() {
		JSONArray cl = new JSONArray();
		String data = "{}";
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			JSONObject x = new JSONObject();
			for(int j = 0; j < tableModel.getColumnCount();j++) {
				String key = keys[j];
				String value = (String) tableModel.getValueAt(i, j);
				if(j != 0 && !data.equals("{")) {
					data += ",";
				}
				else if (j == 0){
					data = "{";
				}
				if(!value.equals("")) {
					data += key+":"+value;	
				}
				else {
					data += key+":"+"-";	
				}
				if(j == tableModel.getColumnCount()-1) {
					data += "}";
				}
			}
			x.put("type", "Plato");
			x.put("data", new JSONObject(data));
			cl.put(x);
			data = "{}";
		}
		
		return cl;
	}
	public String toString(){
		return tableModel.toString();
	}
}

/**
 * Gui de la tabla de cliente
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
import Restaurante.view.Cliente.ClienteTableModel;

public class GuiCliente extends JPanel{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private String[] keys = {"Name","Bill","Pay"};
	private int _status;;
	private JTable _table;
	private ClienteTableModel tableModel;
	Restaurante res;
	//-----------------------------
	//Metodos
	//-----------------------------
	/**
	 * Constructor
	 * @param frame
	 * @param res
	 */
	public GuiCliente(Restaurante res) {
		this.res = res;
		initGUI();
	}
	/**
	 * Inicia el JDialog de cliente
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
		tableModel = new ClienteTableModel(res);
		_table = new JTable(tableModel);
		//SCROLLPANE
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		//BUTTONS
		JButton g = new JButton("Guardar");
		g.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {guardar(e, g);}});
		
		JButton Ac = new JButton("Añadir Cliente");
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.addCli();}});
		
		JButton Ec = new JButton("Eliminar Cliente");
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.RemoveCli();}});
		
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
				res.setClientes(getCliente());
	            Restaurante.closeCli();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
    	}
	}
	/**
	 * Coge los datos de la tabla y los pone en un JSONArray
	 * @return
	 */
	public JSONArray getCliente() {
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
				if(j == tableModel.getColumnCount()-1) {
					data += "}";
				}
			}
			x.put("type", "Cliente");
			x.put("data", new JSONObject(data));
			cl.put(x);
			data = "{}";
		}
		
		return cl;
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
	public String toString(){
		return tableModel.toString();
	}
}

/**
 * Gui de la tabla de ComidaYBebida
 */
package Restaurante.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import org.json.JSONArray;
import org.json.JSONObject;

import Restaurante.control.Restaurante;
import Restaurante.view.ComidaYBebida.CYBTableModel;

public class GuiComidaYBebida extends JDialog{
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
	public GuiComidaYBebida(Frame frame,Restaurante res) {
		super(frame, true);
		this.setModal(false);
		this.res = res;
		initGUI();
	}
	/**
	 * Inicia el JDialog de ComidaYbebida
	 */
	private void initGUI() {
		_status = 0;
		setTitle("Carta");
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
		
		JButton c = new JButton("Cargar");
		c.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {cargar(e,c);}});
		
		JButton Ac = new JButton("Añadir plato");
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.addCYB();}});
		
		JButton Ec = new JButton("Eliminar plato");
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {	tableModel.RemoveCYB();}});
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {ok();}});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {_status = 0;setVisible(false);}});
		
		
		JPanel opt = new JPanel(new FlowLayout());
		toolBar.add(g);
		toolBar.add(c);
		toolBar.add(Ac);
		toolBar.add(Ec);
		opt.add(ok);
		opt.add(cancel);
		mainPanel.add(opt);
		
		add(mainPanel);
	    setVisible(false); 
	    pack();
	}
	/**
	 * Caja de aviso cuando se sale de la gui
	 */
	public void ok() {
		String[] x = {"OK","Cancel"};
		int i = JOptionPane.showOptionDialog(getParent(), "Guarde antes de salir", "Aviso", JOptionPane.OK_CANCEL_OPTION, JOptionPane.INFORMATION_MESSAGE, null, x, x[1]);
		if(i == 0 || i == -1) {
			_status = 0;
		}
		else {
			_status = 1;
			setVisible(false);
		}
	}
	/**
	 * Carga los datos desde el fichero elegido
	 * @param e
	 * @param c
	 */
	public void cargar(ActionEvent e,JButton c) {
		JFileChooser fc = new JFileChooser();
    	if(e.getSource() == c) {
    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			InputStream w = null;
				try {
					w = new FileInputStream(file);
					res.resetCYB();
	            	res.loadCYB(w);
	    			System.out.println("loading " +file.getName());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
    		}
    		else System.out.println("load cancelled by user");
    	}
	}
	/**
	 * Guarda los datos en el fichero elegido
	 * @param e
	 * @param g
	 */
	public void guardar(ActionEvent e,JButton g) {
		JFileChooser fc = new JFileChooser();
    	if(e.getSource() == g) {
    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			FileOutputStream w = null;
				try {
					res.setCYB(getCYB());
					w = new FileOutputStream(file);
	            	Restaurante.closeCYB(w);
	    			System.out.println("loading " +file.getName());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
    		}
    		else System.out.println("load cancelled by user");
    	}
	}
	/**
	 * Abre la tabla
	 * @return
	 */
	public int open() {
        pack();
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

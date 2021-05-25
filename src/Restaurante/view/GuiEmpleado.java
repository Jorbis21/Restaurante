/**
 * Gui de la tabla de empleado
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
import Restaurante.view.Empleado.CocineroTableModel;
import Restaurante.view.Empleado.EmplTableModel;

public class GuiEmpleado extends JDialog{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private String[] keys = {"Name","Id","Salary","Date","IdE","ListE"};
	private String[] keys1 = {"Name","Id","Salary","Date","Type","Specialty"};
	private int _status;;
	private JTable _table;
	private EmplTableModel tableModel;
	private CocineroTableModel CociTableModel;
	private JPanel mainPanel;
	private Restaurante res;
	//-----------------------------
	//Metodos
	//-----------------------------
	/**
	 * Constructor
	 * @param frame
	 * @param res
	 */
	public GuiEmpleado(Frame frame, Restaurante res) {
		super(frame, true);
		this.res = res;
		this.setModal(false);
		initGUI();
	
	}
	/**
	 * Inicia el JDialog de Empleado
	 */
	private void initGUI() {
		_status = 0;
		setTitle("Empleado");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		tablaEmpl(false);
		
	}
	/**
	 * Carga la tabla de empleados
	 * @param a
	 */
	private void tablaEmpl(boolean a) {
		if(a) {
			mainPanel.removeAll();
		}
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		//TABLE
		tableModel = new EmplTableModel(res);
		_table = new JTable(tableModel);
		//SCROLLPANE
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		add(mainPanel);
		//BUTTONS
		JButton Ac = new JButton("Añadir empleado");
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.addEmpl();}});
		Ac.setEnabled(false);
		
		JButton Ec = new JButton("Eliminar empleado");
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.RemoveEmpl();}});
		Ec.setEnabled(false);

		JButton g = new JButton("Guardar");
		g.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {guardarEmpl(e,g);}});
		g.setEnabled(false);
		
		JButton c = new JButton("Cargar");
		c.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {cargarEmpl(e,c);}});
		
		JButton Ve = new JButton("Vista Encargado");
		Ve.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {VEncEmpl(g,Ac,Ec);}});
		
		JButton Vc = new JButton("Vista Cocina");
		Vc.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.setEdit(false);tablaCoci();}});
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {ok();}});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {_status = 0;setVisible(false);}});
		
		JPanel opt = new JPanel(new FlowLayout());
		toolBar.add(g);
		toolBar.add(c);
		toolBar.add(Ve);
		toolBar.add(Vc);
		toolBar.add(Ac);
		toolBar.add(Ec);
		opt.add(ok);
		opt.add(cancel);
		mainPanel.add(opt);
	    pack();
	}
	/**
	 * Permite alencargado modificar a los empleados
	 * @param g
	 * @param Ac
	 * @param Ec
	 */
	public void VEncEmpl(JButton g,JButton Ac,JButton Ec) {
		Object i = JOptionPane.showInputDialog(getParent(), "Inserte su Id de encargado", "Acceso vista encargado", JOptionPane.OK_CANCEL_OPTION);
		for(int j = 0; j < res.getListEncargado().size(); j++) {
			if(i.equals((String.valueOf(res.getListEncargado().get(j).getIdEncargado())))){
				g.setEnabled(true);
				Ac.setEnabled(true);
				Ec.setEnabled(true);
				res.tablaEnc(res.getListEncargado().get(j).getLista());
				tableModel.setEdit(true);
				_status = 0;
			}
		}
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
	public void cargarEmpl(ActionEvent e,JButton c) {
		JFileChooser fc = new JFileChooser();
    	if(e.getSource() == c) {
    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			InputStream w = null;
				try {
					w = new FileInputStream(file);
					res.resetEnc();
	            	res.loadEnc(w);
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
	public void guardarEmpl(ActionEvent e,JButton g) {
		JFileChooser fc = new JFileChooser();
    	if(e.getSource() == g) {
    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			FileOutputStream w = null;
				try {
					for(int j = 0; j < res.getListEncargado().size(); j++) {
						res.setEnc(getEmpl(),j);
					}
					w = new FileOutputStream(file);
	            	Restaurante.closeEnc(w);
	    			System.out.println("loading " +file.getName());
				} catch (Exception e1) {
					JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
				}
    		}
    		else System.out.println("load cancelled by user");
    	}
	}
	/**
	 * Carga la tabla de cocinero
	 */
	private void tablaCoci() {
		mainPanel.removeAll();
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		//TABLE
		CociTableModel = new CocineroTableModel(res);
		_table = new JTable(CociTableModel);
		//SCROLLPANE
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		add(mainPanel);
		//BUTTONS
		JButton Ac = new JButton("Añadir cocinero");
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {CociTableModel.addCoci();}});
		Ac.setEnabled(false);
		
		JButton Ec = new JButton("Eliminar cocinero");
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {CociTableModel.RemoveCoci();}});
		Ec.setEnabled(false);

		JButton g = new JButton("Guardar");
		g.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {guardarCoci(e,g);}});
		g.setEnabled(false);
		
		JButton c = new JButton("Cargar");
		c.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {cargarCoci(e,c);}});
		
		JButton Ve = new JButton("Vista Encargado");
		Ve.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {VEncCoci(g,Ac,Ec);}});
		
		JButton Vc = new JButton("Vista Empleados");
		Vc.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {VEmplCoci();}});
		
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {ok();}});
		
		JButton cancel = new JButton("Cancel");
		cancel.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {	_status = 0;setVisible(false);}});
		
		JPanel opt = new JPanel(new FlowLayout());
		toolBar.add(g);
		toolBar.add(c);
		toolBar.add(Ve);
		toolBar.add(Vc);
		toolBar.add(Ac);
		toolBar.add(Ec);
		
		opt.add(ok);
		opt.add(cancel);
		mainPanel.add(opt);
	    pack();
	}
	/**
	 * Caja de aviso cuando se sale de la gui
	 */
	public void okC() {
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
	 * Carga tabla empleados
	 */
	public void VEmplCoci() {
		res.tablaEnc(res.getListEmpleado());
		CociTableModel.setEdit(false);
		tablaEmpl(true);
	}
	/**
	 * Carga modo encargado
	 * @param g
	 * @param Ac
	 * @param Ec
	 */
	public void VEncCoci(JButton g,JButton Ac,JButton Ec) {
		Object i = JOptionPane.showInputDialog(getParent(), "Inserte su Id de encargado", "Acceso vista encargado", JOptionPane.OK_CANCEL_OPTION);
		for(int j = 0; j < res.getListEncargado().size(); j++) {
			if(i.equals((String.valueOf(res.getListEncargado().get(j).getIdEncargado())))){
				g.setEnabled(true);
				Ac.setEnabled(true);
				Ec.setEnabled(true);
				CociTableModel.setEdit(true);
				_status = 0;
			}
		}
	}
	/**
	 * Carga los datos desde el fichero elegido
	 * @param e
	 * @param c
	 */
	public void cargarCoci(ActionEvent e,JButton c) {
		JFileChooser fc = new JFileChooser();
    	if(e.getSource() == c) {
    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			InputStream w = null;
				try {
					w = new FileInputStream(file);
					res.resetCoci();
	            	res.loadCoci(w);
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
	public void guardarCoci(ActionEvent e,JButton g) {
		JFileChooser fc = new JFileChooser();
    	if(e.getSource() == g) {
    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
    			File file = fc.getSelectedFile();
    			FileOutputStream w = null;
				try {
					res.setCoci(getCoci());
					w = new FileOutputStream(file);
	            	Restaurante.closeCoci(w);
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
	public JSONArray getEmpl() {
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
			x.put("type", "Empleado");
			x.put("data", new JSONObject(data));
			cl.put(x);
			data = "{}";
		}
		
		return cl;
	}
	/**
	 * Coge los datos de la tabla y los pone en un JSONArray
	 * @return
	 */
	public JSONArray getCoci() {
		JSONArray cl = new JSONArray();
		String data = "{}";
		for(int i = 0; i < CociTableModel.getRowCount(); i++) {
			JSONObject x = new JSONObject();
			for(int j = 0; j < CociTableModel.getColumnCount();j++) {
				String key = keys1[j];
				String value = (String) CociTableModel.getValueAt(i, j);
				if(j != 0 && !data.equals("{")) {
					data += ",";
				}
				else if (j == 0){
					data = "{";
				}
				if(!value.equals("")) {
					data += key+":"+value;	
				}
				if(j == CociTableModel.getColumnCount()-1) {
					data += "}";
				}
			}
			x.put("type", "Cocinero");
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

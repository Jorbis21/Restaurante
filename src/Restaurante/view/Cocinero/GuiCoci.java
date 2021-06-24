package Restaurante.view.Cocinero;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

import org.json.JSONArray;
import org.json.JSONObject;

import Restaurante.control.Restaurante;
import Restaurante.view.Cocinero.control.CocineroTableModel;


public class GuiCoci extends JPanel{
	//-----------------------------
		//Atributos
		//-----------------------------
		private static final long serialVersionUID = 1L;
		private String[] keys1 = {"Name","Id","Salary","Date","Type","Specialty","Dni"};
		private int status = -1;
		private JTable _table;
		private CocineroTableModel tableModel;
		private Restaurante res;
		JTextField bus;
		@SuppressWarnings("rawtypes")
		private TableRowSorter trsfiltro;
		//-----------------------------
		//Metodos
		//-----------------------------
		/**
		 * Constructor
		 * @param frame
		 * @param res
		 */
		public GuiCoci(Restaurante res, int s) {
			this.res = res;
			status = s;
			initGUI();
		
		}
	/**
	 * Carga la tabla de cocinero
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(600,700));
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		//TABLE
		tableModel = new CocineroTableModel(res);
		_table = new JTable(tableModel);
		_table.setRowSelectionAllowed(true);
		//SCROLLPANE
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		add(mainPanel);
		//JTEXTFIELD
				bus = new JTextField("Buscar");
				bus.addMouseListener(new MouseListener() {
					@Override
					public void mouseClicked(MouseEvent e) {bus.setText("");}
					@Override
					public void mousePressed(MouseEvent e) {}
					@Override
					public void mouseReleased(MouseEvent e) {}
					@Override
					public void mouseEntered(MouseEvent e) {}
					@Override
					public void mouseExited(MouseEvent e) {bus.setText("Buscar");}	
				});
				
				bus.addKeyListener(new KeyAdapter() {
					public void keyReleased(final KeyEvent e) {
						String cadena = (bus.getText());
						bus.setText(cadena);
						repaint();
						filtro();
					}});
				trsfiltro = new TableRowSorter(_table.getModel());
				_table.setRowSorter(trsfiltro);
		//BUTTONS
		JButton Ac = new JButton();
		Ac.setIcon(new ImageIcon("resources/m.png"));
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.addCoci();}});
		Ac.setEnabled(false);
		
		JButton Ec = new JButton();
		Ec.setIcon(new ImageIcon("resources/s.png"));
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			int x[] = _table.getSelectedRows();
			for(int i = 0; i < x.length; i++) {
				tableModel.RemoveCoci(x[i]);
			}
			
			}});
		Ec.setEnabled(false);

		JButton g = new JButton();
		g.setIcon(new ImageIcon("resources/g.png"));
		g.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {guardarCoci(e,g);}});
		g.setEnabled(false);
		if(status == 0) {
			g.setEnabled(true);
			Ac.setEnabled(true);
			Ec.setEnabled(true);
			tableModel.setEdit(true);
		}
		else if(status == 1) {
			g.setEnabled(false);
			Ac.setEnabled(false);
			Ec.setEnabled(false);
			tableModel.setEdit(false);
		}
		
		
		
		toolBar.add(g);
		toolBar.add(Ac);
		toolBar.add(Ec);
		toolBar.add(bus);
		setVisible(false);

	}
	
	@SuppressWarnings("unchecked")
	public void filtro() {
		trsfiltro.setRowFilter(RowFilter.regexFilter(bus.getText()));
	}
	
	/**
	 * Guarda los datos en el fichero elegido
	 * @param e
	 * @param g
	 */
	public void guardarCoci(ActionEvent e,JButton g) {
		if(e.getSource() == g) {
			try {
				res.setCoci(getCoci());
	            Restaurante.closeCoci();
			} catch (Exception e1) {
				JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
    	}
	}
	
	/**
	 * Abre la tabla
	 * @return
	 */
	public void open() {
		tableModel.clear();
		setVisible(true);
	}
	/**
	 * Coge los datos de la tabla y los pone en un JSONArray
	 * @return
	 */
	public JSONArray getCoci() {
		JSONArray cl = new JSONArray();
		String data = "{}";
		for(int i = 0; i < tableModel.getRowCount(); i++) {
			JSONObject x = new JSONObject();
			for(int j = 0; j < tableModel.getColumnCount();j++) {
				String key = keys1[j];
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

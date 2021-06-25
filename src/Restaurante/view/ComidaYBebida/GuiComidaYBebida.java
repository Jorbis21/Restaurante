/**
 * Gui de la tabla de ComidaYBebida
 */
package restaurante.view.comidaybebida;

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

import restaurante.control.Restaurante;

public class GuiComidaYBebida extends JPanel{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private String[] keys = {"Name","Amount","Food","Desc","Price"};
	private int status;
	private JTable _table;
	private CYBTableModel tableModel;
	Restaurante res;
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
	public GuiComidaYBebida(Restaurante res, int s) {
		this.res = res;
		status = s;
		initGUI();
	}
	/**
	 * Inicia el JDialog de ComidaYbebida
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() {
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);	
		//TABLE
		tableModel = new CYBTableModel(res);
		_table = new JTable(tableModel);
		_table.setRowSelectionAllowed(true);
		//SCROLLPANE
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
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
		JButton g = new JButton();
		g.setIcon(new ImageIcon("resources/g.png"));
		g.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {guardar(e,g);}});
		g.setEnabled(false);
		
		JButton Ac = new JButton();
		Ac.setIcon(new ImageIcon("resources/m.png"));
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {tableModel.addCYB();}});
		Ac.setEnabled(false);
		JButton Ec = new JButton();
		Ec.setIcon(new ImageIcon("resources/s.png"));
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {	
			int x[] = _table.getSelectedRows();
			for(int i = 0; i < x.length; i++) {
				tableModel.RemoveCYB(x[i]);
			}
			
			}});
		Ec.setEnabled(false);
		if(status == 0 || status == 1) {
			g.setEnabled(true);
			Ac.setEnabled(true);
			Ec.setEnabled(true);
			tableModel.setEdit(true);
		}
		toolBar.add(g);
		toolBar.add(Ac);
		toolBar.add(Ec);
		toolBar.add(bus);
		add(mainPanel);
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
	public void open() {
        tableModel.clear();
		setVisible(true);
	
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

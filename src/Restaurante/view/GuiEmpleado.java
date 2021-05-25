package Restaurante.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;

import Restaurante.view.Empleado.CocineroTableModel;
import Restaurante.view.Empleado.EmplTableModel;
import Restaurante.view.Empleado.EncargadoTableModel;

public class GuiEmpleado extends JDialog{
	private static final long serialVersionUID = 1L;
	private int _status;;
	private JTable _table;
	private EmplTableModel tableModel;
	private CocineroTableModel CociTableModel;
	private EncargadoTableModel EncTableModel;
	private JPanel mainPanel;
	
	public GuiEmpleado(Frame frame) {
		super(frame, true);
		//_forceLawsInfo = forceLawsInfo;
		initGUI();
	}
	private void initGUI() {
		_status = 0;
		
		setTitle("Empleado");
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		tablaEmpl(false);
		
	}
	
	private void tablaEmpl(boolean a) {
		if(a) {
			mainPanel.removeAll();
		}
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		tableModel = new EmplTableModel();
		_table = new JTable(tableModel);
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		add(mainPanel);
		
		JButton Ve = new JButton("Vista Encargado");
		Ve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaEncargado();
			}
		});
		JButton Vc = new JButton("Vista Cocina");
		Vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				tablaCoci();
			}
		});
		toolBar.add(Ve);
		toolBar.add(Vc);
		
	    pack();
	}
	private void tablaCoci() {
		mainPanel.removeAll();
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		CociTableModel = new CocineroTableModel();
		_table = new JTable(CociTableModel);
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		add(mainPanel);
		
		JButton Ve = new JButton("Vista Encargado");
		Ve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaEncargado();
			}
		});
		JButton Vc = new JButton("Vista Empleados");
		Vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				tablaEmpl(true);
			}
		});
		toolBar.add(Ve);
		toolBar.add(Vc);
		
	    pack();
	}
	private void tablaEncargado() {
		mainPanel.removeAll();
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		JLabel help = new JLabel("");
		help.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(help);
		EncTableModel = new EncargadoTableModel();
		_table = new JTable(EncTableModel);
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));
		add(mainPanel);
		
		JButton Ve = new JButton("Vista Cocina");
		Ve.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tablaCoci();
			}
		});
		JButton Vc = new JButton("Vista Empleados");
		Vc.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				tablaEmpl(true);
			}
		});
		toolBar.add(Ve);
		toolBar.add(Vc);
		
	    pack();
	}
	public int open() {
        pack();
        tableModel.clear();
		setVisible(true);
		return _status;
	}
	/*public JSONObject getSelectedLaws() {
		JSONObject law = new JSONObject();
		JSONObject selLaw = _forceLawsInfo.get(_selectedLawsIndex);
		String data = "{}";
		for(int i = 0; i < _table.getRowCount(); i++) {
			String key = (String) _table.getValueAt(i, 0);
			String value = (String) _table.getValueAt(i, 1);
			if(i != 0 && !data.equals("{")) {
				data += ",";
			}
			else if (i == 0){
				data = "{";
			}
			if(!value.equals("")) {
				data += key+":"+value;	
			}
			if(i == _table.getRowCount()-1) {
				data += "}";
			}
		}
		law.put("type", selLaw.getString("type"));
		law.put("data", new JSONObject(data));
		return law;
	}*/
	public String toString(){
		return tableModel.toString();
	}
}

package Restaurante.view;

import java.awt.Dimension;
import java.awt.FlowLayout;
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

import Restaurante.view.Cliente.ClienteTableModel;

public class GuiCliente extends JDialog{
	private static final long serialVersionUID = 1L;
	private int _status;;
	private JTable _table;
	private ClienteTableModel tableModel;
	
	public GuiCliente(Frame frame) {
		super(frame, true);
		//_forceLawsInfo = forceLawsInfo;
		initGUI();
	}
	private void initGUI() {
		_status = 0;
		setTitle("Force Laws Selection");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//HELP
		JLabel help = new JLabel("<html><p>Select a force law and provide values for the parametes in the <b>Value column</b> (default values are used for parametes with no value).</p></html>");
		help.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(help);
		//TABLE
		tableModel = new ClienteTableModel();
		_table = new JTable(tableModel);
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

		//BUTTONS
		JPanel opt = new JPanel(new FlowLayout());
		JButton ok = new JButton("Vista Encargado");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				_status = 1;
				setVisible(false);
			}
		});
		JButton cancel = new JButton("Vista cocina");
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				_status = 0;
				setVisible(false);
			}
		});
		opt.add(ok);
		opt.add(cancel);
		mainPanel.add(opt);
		
		add(mainPanel);
	    setVisible(false); 
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

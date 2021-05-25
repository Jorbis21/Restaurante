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
import Restaurante.view.Almacen.AlmTableModel;

public class GuiAlmacen extends JDialog{
	private static final long serialVersionUID = 1L;
	private String keys[] = {"Name","Type","Amount"};
	private int _status;;
	private JTable _table;
	private AlmTableModel tableModel;
	
	public GuiAlmacen(Frame frame,Restaurante res) {
		super(frame, true);
		initGUI(res);
	}
	private void initGUI(Restaurante res) {
		_status = 0;
		setTitle("Almacen");
		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//HELP
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);	
		//TABLE
		tableModel = new AlmTableModel(res);
		_table = new JTable(tableModel);
		JScrollPane x = new JScrollPane(_table, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainPanel.add(x);
		mainPanel.add(Box.createRigidArea(new Dimension(0,20)));

		//BUTTONS
		JButton g = new JButton("Guardar");
		g.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
		    	if(e.getSource() == g) {
		    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		    			File file = fc.getSelectedFile();
		    			FileOutputStream w = null;
						try {
							res.setAlm(getAlm());
							w = new FileOutputStream(file);
			            	Restaurante.closeAlm(w);
			    			System.out.println("loading " +file.getName());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
		    		}
		    		else System.out.println("load cancelled by user");
		    	}
			}
		});
		JButton c = new JButton("Cargar");
		c.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fc = new JFileChooser();
		    	if(e.getSource() == c) {
		    		if(fc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
		    			File file = fc.getSelectedFile();
		    			InputStream w = null;
						try {
							w = new FileInputStream(file);
							res.resetAlm();
			            	res.loadAlmacen(w);
			    			System.out.println("loading " +file.getName());
						} catch (Exception e1) {
							JOptionPane.showMessageDialog(getParent(), "Somethings went wrong: "+e1.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
						}
		    		}
		    		else System.out.println("load cancelled by user");
		    	}
			}
		});
		JButton Ac = new JButton("Añadir Bibere");
		Ac.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tableModel.addAlm();
			}
		});
		JButton Ec = new JButton("Eliminar Bibere");
		Ec.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {	
				tableModel.RemoveAlm();
			}
		});
		toolBar.add(g);
		toolBar.add(c);
		toolBar.add(Ac);
		toolBar.add(Ec);
		JPanel opt = new JPanel(new FlowLayout());
		JButton ok = new JButton("OK");
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
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
		});
		JButton cancel = new JButton("Cancel");
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
	public JSONArray getAlm() {
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
			x.put("type", "Almacen");
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

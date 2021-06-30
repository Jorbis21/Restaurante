/**
 * Gui de la tabla de empleado
 */
package restaurante.view.empleado;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JToolBar;
import javax.swing.RowFilter;
import javax.swing.table.TableRowSorter;

public class GuiEmpleado extends JPanel{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private int status = -1;
	private JTable _table;
	private EmplTableModel tableModel;
	private JPanel mainPanel;
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
	 * @throws FileNotFoundException 
	 */
	public GuiEmpleado(int s) throws FileNotFoundException {
		status = s;
		initGUI();
	
	}
	/**
	 * Inicia el JPanel de Empleado
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() throws FileNotFoundException {
		
		mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);
		//TABLE
		tableModel = new EmplTableModel(status);
		_table = new JTable(tableModel);
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
		_table.setRowSelectionAllowed(true);
		//BUTTONS
		JButton Ac = new JButton();
		Ac.setIcon(new ImageIcon("resources/m.png"));
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			tableModel.addEmpl();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}});
		Ac.setEnabled(false);

		JButton Ec = new JButton();
		Ec.setIcon(new ImageIcon("resources/s.png"));
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			int x[] = new int[_table.getSelectedRowCount()];
			for(int i = 0; i < _table.getSelectedRowCount(); i++) {
				x[i] = _table.getSelectedRow();
			}
			for(int i = 0; i < x.length; i++) {
				try {
					tableModel.RemoveEmpl(x[i]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}});
		Ec.setEnabled(false);

		if(status == 0) {
			Ac.setEnabled(true);
			Ec.setEnabled(true);
			tableModel.setEdit(true);
		}
		else if(status == 1) {
			Ac.setEnabled(false);
			Ec.setEnabled(false);
			tableModel.setEdit(false);
		}
		toolBar.add(Ac);
		toolBar.add(Ec);
		toolBar.add(bus);
		setVisible(false);


		
	}
	/**
	 * Filtro para las filas
	 */
	@SuppressWarnings("unchecked")
	public void filtro() {
		trsfiltro.setRowFilter(RowFilter.regexFilter(bus.getText()));
	}
	/**
	 * Abre la tabla
	 * @return
	 */
	public void open() {
        tableModel.clear();
		setVisible(true);
	}
}

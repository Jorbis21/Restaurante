/**
 * Gui de la tabla de cliente
 */
package restaurante.view.cliente;

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

public class GuiCliente extends JPanel{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private int _status;;
	private JTable _table;
	private ClienteTableModel tableModel;
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
	public GuiCliente() throws FileNotFoundException {
		
		initGUI();
	}
	/**
	 * Inicia el JDialog de cliente
	 * @throws FileNotFoundException 
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initGUI() throws FileNotFoundException {
		_status = 0;

		JPanel mainPanel = new JPanel();
		mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
		//TOOLBAR
		JToolBar toolBar = new JToolBar();
		toolBar.setAlignmentX(CENTER_ALIGNMENT);
		mainPanel.add(toolBar);	
		//TABLE
		tableModel = new ClienteTableModel();
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
		JButton Ac = new JButton();
		Ac.setIcon(new ImageIcon("resources/m.png"));
		Ac.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			tableModel.addCli();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}});
		
		JButton Ec = new JButton();
		Ec.setIcon(new ImageIcon("resources/s.png"));
		Ec.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			int x[] = _table.getSelectedRows();
			for(int i = 0; i < x.length; i++) {
				try {
					tableModel.RemoveCli(x[i]);
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			
		}});
		
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

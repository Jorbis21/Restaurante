/**
 * MainGui
 */
package Restaurante.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;

import Restaurante.control.Restaurante;
import Restaurante.view.Almacen.GuiAlmacen;
import Restaurante.view.Cliente.GuiCliente;
import Restaurante.view.ComidaYBebida.GuiComidaYBebida;
import Restaurante.view.Empleado.GuiEmpleado;



public class MainGui extends JFrame{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private JButton Clientes, ComidaYBebida, Empleados, Almacen, Exit;
	private GuiEmpleado tablaEmpl;
	private GuiCliente tablaCli;
	private GuiComidaYBebida tablaCYB;
	private GuiAlmacen tablaAlm;
	private Restaurante res;
	private JPanel center;
	//-----------------------------
	//Metodos
	//-----------------------------
	/**
	 * Constructor
	 * @param res
	 */
	public MainGui(Restaurante res) {
		super("Restaurante");
		this.res = res;
		initGUI();
	}
	/**
	 * Inicia el JFrame de la aplicacion
	 */
	private void initGUI() {;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,600));
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
		JToolBar toolbar = new JToolBar();
		mainPanel.add(toolbar, BorderLayout.PAGE_START);
		center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		mainPanel.add(center, BorderLayout.CENTER);
		
		tablaEmpl = new GuiEmpleado(res);
		tablaCYB = new GuiComidaYBebida(res);
		tablaAlm = new GuiAlmacen(res);
		tablaCli = new GuiCliente(res);
		
		Clientes = new JButton("Clientes");
		Clientes.setActionCommand("Ver lista clientes");
		Clientes.setToolTipText("Gestion lista clientes");
		Clientes.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionClientes();}});
		toolbar.add(Clientes);
		ComidaYBebida = new JButton("Carta");
		ComidaYBebida.setActionCommand("Ver carta");
		ComidaYBebida.setToolTipText("Gestion carta");
		ComidaYBebida.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionComidaYBebida();}});
		toolbar.add(ComidaYBebida);
		Empleados = new JButton("Empleados");
		Empleados.setActionCommand("Ver lista empleados");
		Empleados.setToolTipText("Gestion lista empleados");
		Empleados.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionEmpleados();}});
		toolbar.add(Empleados);
		Almacen = new JButton("Almacen");
		Almacen.setActionCommand("Ver lista del almacen");
		Almacen.setToolTipText("Gestion almacen");
		Almacen.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionAlmacen();}});
		toolbar.add(Almacen);
		Exit = new JButton("Salir");
		Exit.setActionCommand("Cierra el programa");
		Exit.setToolTipText("Cierra el programa guardando todos los datos");
		Exit.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			guardar();
			System.exit(0);
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}});
		toolbar.add(Box.createGlue());
		toolbar.add(Exit);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	private void guardar() throws FileNotFoundException {
		res.setClientes(tablaCli.getCliente());
		res.setCYB(tablaCYB.getCYB());
		res.setAlm(tablaAlm.getAlm());
		/*for(int j = 0; j < res.getListEncargado().size(); j++) {
			res.setEnc(tablaEmpl.getEmpl(),j);
		}*/
		res.setCoci(tablaEmpl.getCoci());
    	Restaurante.closeAlm();
    	Restaurante.closeCli();
    	Restaurante.closeCoci();
    	Restaurante.closeCYB();
    	Restaurante.closeEnc();
	}
	/**
	 * Inicia la GUICliente
	 */
	private void gestionClientes() {
		center.removeAll();
		tablaCli = new GuiCliente(res);
		center.add(tablaCli);
		tablaCli.open();
		
	}
	/**
	 * Inicia la GUIComidaYBebida
	 */
	private void gestionComidaYBebida() {
		center.removeAll();
		tablaCYB = new GuiComidaYBebida(res);
		center.add(tablaCYB);
		tablaCYB.open();
	}
	/**
	 * Inicia la GUIEmpleados
	 */
	private void gestionEmpleados() {
		center.removeAll();
		tablaEmpl = new GuiEmpleado(res);
		center.add(tablaEmpl);
		tablaEmpl.open();
		
	}
	/**
	 * Inicia la GUIAlmacen
	 */
	private void gestionAlmacen() {
		center.removeAll();
		tablaAlm = new GuiAlmacen(res);
		center.add(tablaAlm);
		tablaAlm.open();
	}

}

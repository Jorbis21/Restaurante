/**
 * MainGui
 */
package restaurante.view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import restaurante.view.almacen.GuiAlmacen;
import restaurante.view.cliente.GuiCliente;
import restaurante.view.cocinero.GuiCoci;
import restaurante.view.comidaybebida.GuiComidaYBebida;
import restaurante.view.empleado.GuiEmpleado;

public class MainGui extends JFrame{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private JButton Clientes, ComidaYBebida, Empleados, Almacen,Coci, IniSesion, CerrarSesion;
	private GuiCoci tablaCoci;
	private GuiEmpleado tablaEmpl;
	private GuiCliente tablaCli;
	private GuiComidaYBebida tablaCYB;
	private GuiAlmacen tablaAlm;
	private JPanel center;
	private int status = -1;
	//-----------------------------
	//Metodos
	//-----------------------------
	/**
	 * Constructor
	 * @param res
	 * @throws FileNotFoundException 
	 */
	public MainGui() throws FileNotFoundException {
		super("Restaurante");
		initGUI();
	}
	/**
	 * Inicia el JFrame de la aplicacion
	 * @throws FileNotFoundException 
	 */
	private void initGUI() throws FileNotFoundException {;
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(600,600));
		
		JPanel mainPanel = new JPanel(new BorderLayout());
		this.add(mainPanel);
		JToolBar toolbar = new JToolBar();
		mainPanel.add(toolbar, BorderLayout.PAGE_START);
		center = new JPanel();
		center.setLayout(new BoxLayout(center, BoxLayout.Y_AXIS));
		mainPanel.add(center, BorderLayout.CENTER);
		
		tablaEmpl = new GuiEmpleado(status);
		tablaCYB = new GuiComidaYBebida(status);
		tablaAlm = new GuiAlmacen();
		tablaCli = new GuiCliente();
		tablaCoci = new GuiCoci(status);
		
		
		Clientes = new JButton("Clientes");
		Clientes.setActionCommand("Ver lista clientes");
		Clientes.setToolTipText("Gestion lista clientes");
		Clientes.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionClientes();}});
		Clientes.setVisible(false);
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
		Empleados.setVisible(false);
		toolbar.add(Empleados);
		
		Coci = new JButton("Cocineros");
		Coci.setActionCommand("Ver lista cocineres");
		Coci.setToolTipText("Gestion lista cocineros");
		Coci.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionCoci();}});
		Coci.setVisible(false);
		toolbar.add(Coci);
		
		Almacen = new JButton("Almacen");
		Almacen.setActionCommand("Ver lista del almacen");
		Almacen.setToolTipText("Gestion almacen");
		Almacen.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionAlmacen();}});
		Almacen.setVisible(false);
		toolbar.add(Almacen);
		
		toolbar.add(Box.createGlue());
		IniSesion = new JButton();
		IniSesion.setIcon(new ImageIcon("resources/i.png"));
		IniSesion.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {IniSes();}});
		toolbar.add(IniSesion);
		
		CerrarSesion = new JButton();
		CerrarSesion.setIcon(new ImageIcon("resources/o.png"));
		CerrarSesion.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {CerrarSes();}});
		toolbar.add(CerrarSesion);
		
		gestionComidaYBebida();
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	/**
	 * Cierra la sesion de la cuenta que este iniciada
	 */
	private void CerrarSes() {
		status = -1;
		gestionComidaYBebida();	
		Clientes.setVisible(false);
		Empleados.setVisible(false);
		Almacen.setVisible(false);
		Coci.setVisible(false);
	}
	/**
	 * Muestra la tabla de cocineros
	 */
	private void gestionCoci() {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(true);
		Almacen.setEnabled(true);
		Clientes.setEnabled(true);
		Coci.setEnabled(false);
		try {
			tablaCoci = new GuiCoci(status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		center.add(tablaCoci);
		tablaCoci.open();
	}
	/**
	 * Inicia sesion con los parametros obtenidos en el IniGui
	 */
	private void IniSes() {
		IniGui Ini = new IniGui((Frame) SwingUtilities.getWindowAncestor(this));
		status = Ini.open();
		if(status == 0 || status == 1) {
			Clientes.setVisible(true);
			Empleados.setVisible(true);
			Almacen.setVisible(true);
			Coci.setVisible(true);
		}
	}
	/**
	 * Muestra la tabla clientes
	 */
	private void gestionClientes() {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(true);
		Almacen.setEnabled(true);
		Coci.setEnabled(true);
		Clientes.setEnabled(false);
		try {
			tablaCli = new GuiCliente();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		center.add(tablaCli);
		tablaCli.open();
		
	}
	/**
	 * Muestra la tabla de ComidaYBebida
	 */
	private void gestionComidaYBebida() {
		center.removeAll();
		Empleados.setEnabled(true);
		Almacen.setEnabled(true);
		Clientes.setEnabled(true);
		Coci.setEnabled(true);
		ComidaYBebida.setEnabled(false);
		try {
			tablaCYB = new GuiComidaYBebida(status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		center.add(tablaCYB);
		tablaCYB.open();
	}
	/**
	 * Muestra la tabla de Empleados
	 */
	private void gestionEmpleados() {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(false);
		Almacen.setEnabled(true);
		Clientes.setEnabled(true);
		Coci.setEnabled(true);
		try {
			tablaEmpl = new GuiEmpleado(status);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		center.add(tablaEmpl);
		tablaEmpl.open();
		
	}
	/**
	 * Muestra la tabla de Almacen
	 */
	private void gestionAlmacen() {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(true);
		Almacen.setEnabled(false);
		Clientes.setEnabled(true);
		Coci.setEnabled(true);
		try {
			tablaAlm = new GuiAlmacen();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		center.add(tablaAlm);
		tablaAlm.open();
	}

}

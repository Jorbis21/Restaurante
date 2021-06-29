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
import restaurante.control.RestauranteCtrl;



public class MainGui extends JFrame{
	//-----------------------------
	//Atributos
	//-----------------------------
	private static final long serialVersionUID = 1L;
	private JButton Clientes, ComidaYBebida, Empleados, Almacen,Coci, Exit, IniSesion, CerrarSesion;
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
		Clientes.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			gestionClientes();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}});
		Clientes.setVisible(false);
		toolbar.add(Clientes);
		ComidaYBebida = new JButton("Carta");
		ComidaYBebida.setActionCommand("Ver carta");
		ComidaYBebida.setToolTipText("Gestion carta");
		ComidaYBebida.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			gestionComidaYBebida();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}});
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
		Coci.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			gestionCoci();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}}});
		Coci.setVisible(false);
		toolbar.add(Coci);
		Almacen = new JButton("Almacen");
		Almacen.setActionCommand("Ver lista del almacen");
		Almacen.setToolTipText("Gestion almacen");
		Almacen.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {try {
			gestionAlmacen();
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		}}});
		Almacen.setVisible(false);
		toolbar.add(Almacen);
		toolbar.add(Box.createGlue());
		IniSesion = new JButton();
		IniSesion.setIcon(new ImageIcon("resources/i.png"));
		IniSesion.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			IniSes();
		}});
		toolbar.add(IniSesion);
		CerrarSesion = new JButton();
		CerrarSesion.setIcon(new ImageIcon("resources/o.png"));
		CerrarSesion.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
			try {
				CerrarSes();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}});
		toolbar.add(CerrarSesion);
		Exit = new JButton();
		Exit.setIcon(new ImageIcon("resources/e.png"));
		Exit.setActionCommand("Cierra el programa");
		Exit.setToolTipText("Cierra el programa guardando todos los datos");
		Exit.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {
		//guardar();
		System.exit(0);}});
		
		toolbar.add(Exit);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	private void CerrarSes() throws FileNotFoundException {
		status = -1;
		gestionComidaYBebida();
		Clientes.setVisible(false);
		Empleados.setVisible(false);
		Almacen.setVisible(false);
		Coci.setVisible(false);
	}
	private void gestionCoci() throws FileNotFoundException {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(true);
		Almacen.setEnabled(true);
		Clientes.setEnabled(true);
		Coci.setEnabled(false);
		tablaCoci = new GuiCoci(status);
		center.add(tablaCoci);
		tablaCoci.open();
	}
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
	 * Inicia la GUICliente
	 * @throws FileNotFoundException 
	 */
	private void gestionClientes() throws FileNotFoundException {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(true);
		Almacen.setEnabled(true);
		Coci.setEnabled(true);
		Clientes.setEnabled(false);
		tablaCli = new GuiCliente();
		center.add(tablaCli);
		tablaCli.open();
		
	}
	/**
	 * Inicia la GUIComidaYBebida
	 * @throws FileNotFoundException 
	 */
	private void gestionComidaYBebida() throws FileNotFoundException {
		center.removeAll();
		
		Empleados.setEnabled(true);
		Almacen.setEnabled(true);
		Clientes.setEnabled(true);
		Coci.setEnabled(true);
		ComidaYBebida.setEnabled(false);
		
		tablaCYB = new GuiComidaYBebida(status);
		center.add(tablaCYB);
		tablaCYB.open();
	}
	/**
	 * Inicia la GUIEmpleados
	 */
	private void gestionEmpleados() {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(false);
		Almacen.setEnabled(true);
		Clientes.setEnabled(true);
		Coci.setEnabled(true);
		tablaEmpl = new GuiEmpleado(status);
		center.add(tablaEmpl);
		tablaEmpl.open();
		
	}
	/**
	 * Inicia la GUIAlmacen
	 * @throws FileNotFoundException 
	 */
	private void gestionAlmacen() throws FileNotFoundException {
		center.removeAll();
		ComidaYBebida.setEnabled(true);
		Empleados.setEnabled(true);
		Almacen.setEnabled(false);
		Clientes.setEnabled(true);
		Coci.setEnabled(true);
		tablaAlm = new GuiAlmacen();
		center.add(tablaAlm);
		tablaAlm.open();
	}

}

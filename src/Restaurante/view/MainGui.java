package Restaurante.view;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class MainGui extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton Clientes, ComidaYBebida, Empleados, Almacen;
	private GuiEmpleado tablaEmpl;
	public MainGui() {
		super("Restaurante");
		initGUI();
	}
	private void initGUI() {;
		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(500,350));
		Clientes = new JButton("Clientes");
		Clientes.setActionCommand("Ver lista clientes");
		Clientes.setToolTipText("Gestion lista clientes");
		Clientes.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionClientes();}});
		
		ComidaYBebida = new JButton("ComidaYBebida");
		ComidaYBebida.setActionCommand("Ver carta");
		ComidaYBebida.setToolTipText("Gestion carta");
		ComidaYBebida.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionComidaYBebida();}});
		
		Empleados = new JButton("Empleados");
		Empleados.setActionCommand("Ver lista empleados");
		Empleados.setToolTipText("Gestion lista empleados");
		Empleados.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionEmpleados();}});
		
		Almacen = new JButton("Almacen");
		Almacen.setActionCommand("Ver lista del almacen");
		Almacen.setToolTipText("Gestion almacen");
		Almacen.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionAlmacen();}});
		JButton[] botones= { Clientes, ComidaYBebida, Empleados, Almacen};
		for(JButton b : botones) {
			this.add(b);
		}
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	private void gestionClientes() {
		
	}
	private void gestionComidaYBebida() {
		
	}
	private void gestionEmpleados() {
		if(tablaEmpl == null) {
			tablaEmpl = new GuiEmpleado((Frame) SwingUtilities.getWindowAncestor(this));
		}
		int status = tablaEmpl.open();
		if(status == 1) {
			try {
				//JSONObject obj = _changeForceLawsDialog.getSelectedLaws();
				//_ctrl.setForceLaws(obj);
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this.getParent(), "Somethings went wrong: "+e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	private void gestionAlmacen() {
	
	}

}

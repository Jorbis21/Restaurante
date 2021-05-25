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

import Restaurante.control.Restaurante;


public class MainGui extends JFrame{
	private static final long serialVersionUID = 1L;
	private JButton Clientes, ComidaYBebida, Empleados, Almacen;
	private GuiEmpleado tablaEmpl;
	private GuiCliente tablaCli;
	private GuiComidaYBebida tablaCYB;
	private GuiAlmacen tablaAlm;
	private Restaurante res;
	public MainGui(Restaurante res) {
		super("Restaurante");
		this.res = res;
		initGUI();
	}
	private void initGUI() {;
		this.setLayout(new GridLayout(2,2));
		this.setPreferredSize(new Dimension(500,350));
		Clientes = new JButton("Clientes");
		Clientes.setActionCommand("Ver lista clientes");
		Clientes.setToolTipText("Gestion lista clientes");
		Clientes.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionClientes();}});
		this.add(Clientes);
		ComidaYBebida = new JButton("Carta");
		ComidaYBebida.setActionCommand("Ver carta");
		ComidaYBebida.setToolTipText("Gestion carta");
		ComidaYBebida.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionComidaYBebida();}});
		this.add(ComidaYBebida);
		Empleados = new JButton("Empleados");
		Empleados.setActionCommand("Ver lista empleados");
		Empleados.setToolTipText("Gestion lista empleados");
		Empleados.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionEmpleados();}});
		this.add(Empleados);
		Almacen = new JButton("Almacen");
		Almacen.setActionCommand("Ver lista del almacen");
		Almacen.setToolTipText("Gestion almacen");
		Almacen.addActionListener(new ActionListener() {public void actionPerformed(ActionEvent e) {gestionAlmacen();}});
		this.add(Almacen);
		
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.pack();
		this.setVisible(true);
		
	}
	private void gestionClientes() {
		tablaCli = new GuiCliente((Frame) SwingUtilities.getWindowAncestor(this), res);
		int status = tablaCli.open();
		if(status == 1) {
			try {
				res.setClientes(tablaCli.getCliente());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this.getParent(), "Somethings went wrong: "+e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	private void gestionComidaYBebida() {
		tablaCYB = new GuiComidaYBebida((Frame) SwingUtilities.getWindowAncestor(this),res);
		int status = tablaCYB.open();
		if(status == 1) {
			try {
				res.setCYB(tablaCYB.getCYB());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this.getParent(), "Somethings went wrong: "+e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}
	private void gestionEmpleados() {
		tablaEmpl = new GuiEmpleado((Frame) SwingUtilities.getWindowAncestor(this),res);
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
		tablaAlm = new GuiAlmacen((Frame) SwingUtilities.getWindowAncestor(this),res);
		int status = tablaAlm.open();
		if(status == 1) {
			try {
				res.setAlm(tablaAlm.getAlm());
			}
			catch(Exception e) {
				JOptionPane.showMessageDialog(this.getParent(), "Somethings went wrong: "+e.getLocalizedMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
			}
			
		}
	}

}

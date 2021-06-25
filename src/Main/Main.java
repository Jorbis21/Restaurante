/*Clase Main, en esta clase se inicializa el restaurante
 * y se lanza la interfaz
 */
package main;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import restaurante.view.MainGui;
import restaurante.control.Restaurante;



public class Main {
	
	private static Restaurante res = new Restaurante();
	
	public static void init() throws FileNotFoundException {
		res.loadAlmacen(new FileInputStream(new File("resources/Almacen.json")));
		res.loadClientes(new FileInputStream(new File("resources/Clientes.json")));
		res.loadCoci(new FileInputStream(new File("resources/Cocineros.json")));
		res.loadCYB(new FileInputStream(new File("resources/Carta.json")));
		res.loadEnc(new FileInputStream(new File("resources/Encargados.json")));
	}
	
	private static void start(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				new MainGui(res);
			}
		});
	}
	public static void main(String[] args) {
		try {
			init();
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}

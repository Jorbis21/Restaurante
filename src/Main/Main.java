/*Clase Main, en esta clase se inicializa el restaurante
 * y se lanza la interfaz
 */
package main;


import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import restaurante.view.MainGui;
import restaurante.control.RestauranteCtrl;



public class Main {
	
	private static RestauranteCtrl res;
	
	public static void init() throws FileNotFoundException {
		res = new RestauranteCtrl();
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

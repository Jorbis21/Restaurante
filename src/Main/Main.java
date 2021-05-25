/*Clase Main, en esta clasese inicializa el restaurante
 * y se lanza la interfaz
 */
package Main;


import javax.swing.SwingUtilities;

import Restaurante.control.Restaurante;
import Restaurante.view.MainGui;



public class Main {
	
	private static Restaurante res = new Restaurante();
	
	
	
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
			start(args);
		} catch (Exception e) {
			System.err.println("Something went wrong ...");
			System.err.println();
			e.printStackTrace();
		}
	}
}

/*Clase Main, en esta clase se inicializa el restaurante
 * y se lanza la interfaz
 */
package main;


import java.io.FileNotFoundException;

import javax.swing.SwingUtilities;

import restaurante.view.MainGui;



public class Main {

	private static void start(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				try {
					new MainGui();
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
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

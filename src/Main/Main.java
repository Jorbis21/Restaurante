package Main;

import javax.swing.SwingUtilities;

import Restaurante.view.MainGui;


public class Main {
	private static void start(String[] args) throws Exception {
		SwingUtilities.invokeAndWait(new Runnable() {
			@Override
			public void run() {
				new MainGui();
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

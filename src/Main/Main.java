package Main;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;

import javax.swing.SwingUtilities;

import Restaurante.control.Restaurante;
import Restaurante.view.MainGui;



public class Main {
	private static String _inFileCli = null;
	private static String _inFileCoci = null;
	private static String _inFileAlm = null;
	private static String _inFileEmp = null;
	private static String _inFileEnc = null;
	private static String _inFileCYB = null;
	private static String _inFileRes = null;
	private static String _outFileEmp = null;
	private static String _outFileEnc = null;
	private static Restaurante res;
	
	private static void init() throws FileNotFoundException {
		res = new Restaurante(10, 10,30);
		if(_inFileRes != null) {
			InputStream is = new FileInputStream(new File(_inFileRes));
			res.loadRes(is);
		}
		initCli();
		initAlm();
		initCoci();
		initEmp();
		initEnc();
		initCYB();
		
	}
	private static void initCli() throws FileNotFoundException {
		if(_inFileCli != null) {
			InputStream is = new FileInputStream(new File(_inFileCli));
			res.loadClientes(is);
		}
		
	}
	
	private static void initAlm() throws FileNotFoundException {
		if(_inFileAlm != null) {
			InputStream is = new FileInputStream(new File(_inFileAlm));
			res.loadClientes(is);
		}
		
	}
	
	public static void initCoci() throws FileNotFoundException {
		if(_inFileCoci != null) {
			InputStream is = new FileInputStream(new File(_inFileCoci));
			res.loadClientes(is);
		}
		
	}
	public static void closeCoci(FileOutputStream a) throws FileNotFoundException {
		OutputStream os = a;
		PrintStream p = new PrintStream(os);
		p.println(res.chargeCoci());
	}
	private static void initEmp() throws FileNotFoundException {
		if(_inFileEmp != null) {
			InputStream is = new FileInputStream(new File(_inFileEmp));
			res.loadClientes(is);
		}
		
	}
	private static void closeEmp() throws FileNotFoundException {
		OutputStream os = _outFileEmp == null ? System.out : new FileOutputStream(new File(_outFileEmp));
		PrintStream p = new PrintStream(os);
		p.println(res.chargeEmpl());
	}
	private static void initEnc() throws FileNotFoundException {
		if(_inFileEnc != null) {
			InputStream is = new FileInputStream(new File(_inFileEnc));
			res.loadClientes(is);
		}
		
	}
	private static void closeEnc() throws FileNotFoundException {
		OutputStream os = _outFileEnc == null ? System.out : new FileOutputStream(new File(_outFileEnc));
		PrintStream p = new PrintStream(os);
		p.println(res.chargeEnc());
	}
	private static void initCYB() throws FileNotFoundException {
		if(_inFileCYB != null) {
			InputStream is = new FileInputStream(new File(_inFileCYB));
			res.loadClientes(is);
		}
		
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

/**
 * Modelo de la tabla Cliente
 */
package restaurante.view.cliente;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import restaurante.model.Almacen;
import restaurante.model.Cliente;
import restaurante.model.Cocinero;
import restaurante.model.ComidaYBebida;
import restaurante.model.Empleado;
import restaurante.model.ResObserver;


public class ClienteTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Cuenta","MetodoPago"};
	private List<ClienteTable> row;
	private GuiCliCtrl cli;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 * @throws FileNotFoundException 
	 */
	public ClienteTableModel() throws FileNotFoundException{
		cli = new GuiCliCtrl();
		row = new ArrayList<ClienteTable>();
		cli.iniciarObs(this);
	}
	/**
	 * Aniade fila
	 * @throws FileNotFoundException 
	 */
	public void addCli() throws FileNotFoundException {
		row.add(new ClienteTable());
		cli.eventoCli(row.get(getRowCount() - 1).convert(), 0, -1);
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 * @throws FileNotFoundException 
	 */
	public void RemoveCli(int x) throws FileNotFoundException {
		Cliente c = row.get(x).convert();
		row.remove(x);
		cli.eventoCli(c, 1, x);
		fireTableStructureChanged();
	}
	/**
	 * Devuelve Num filas
	 */
	@Override
	public int getRowCount() {
		return row.size();
	}
	/**
	 * Devuelve Num Columnas
	 */
	@Override
	public int getColumnCount() {
		return col.length;
	}
	/**
	 * Devuelve nombre columna 
	 */
	@Override
	public String getColumnName(int column) {
		return col[column];
	}
	/**
	 * Pone un valor en la tabla
	 */
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		ClienteTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				if(Integer.parseInt(o.toString()) < 0) {
					JOptionPane.showMessageDialog(null, "La cuenta no puede ser menor que 0", "Cuenta negativa", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					ct.setCuenta(o.toString());
				}
			break;
			case 2:
				if(o.toString().equals("Metalico") || o.toString().equals("Tarjeta")) {
					ct.setMetodoPago(o.toString());
				}
				else {
					JOptionPane.showMessageDialog(null, "El metodo de pago solo puede ser Tarjeta o Metalico", "ERROR", JOptionPane.ERROR_MESSAGE, null);
				}
			break;
		}
		try {
			cli.eventoCli(ct.convert(), 2, rowIndex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * La tabla es editable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return true;
	}
	/**
	 * Devuelve el valor de una posicion indicada
	 */
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		ClienteTable ct = row.get(rowIndex);
		String s = "";
		switch(colIndex) {
		case 0:
			s = ct.getNombre();
			break;
		case 1:
			s = ct.getCuenta();
			break;
		case 2:
			s = ct.getMetodoPago();
			break;
		}
		return s;
	}
	/**
	 * Limpia la tabla
	 */
	public void clear() {
		row.clear();
	}
	/**
	 * Actualiza los datos atraves de los observadores
	 * @param cli
	 */
	private void act(List<Cliente> cli) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < cli.size(); i++) {
					row.add(new ClienteTable());
					row.get(i).setNombre(cli.get(i).getNombre());
					boolean x = cli.get(i).getMetodoPago();
					if(x)
						row.get(i).setMetodoPago("Tarjeta");
					else
						row.get(i).setMetodoPago("Metalico");
					row.get(i).setCuenta(String.valueOf(cli.get(i).getCuenta()));
				}
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void ObsAlm(List<Almacen> alm) {	}
	@Override
	public void ObsCli(List<Cliente> cli) {
		act(cli);
	}
	@Override
	public void ObsEmp(List<Empleado> emp) {}
	@Override
	public void ObsCyb(List<ComidaYBebida> cyb) {}
	@Override
	public void ObsCoci(List<Cocinero> coci) {}

}

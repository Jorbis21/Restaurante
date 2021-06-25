/**
 * Modelo de la tabla Empleado
 */
package restaurante.view.empleado;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import restaurante.model.Almacen;
import restaurante.model.Cliente;
import restaurante.model.Cocinero;
import restaurante.model.ComidaYBebida;
import restaurante.model.Empleado;
import restaurante.model.Encargado;
import restaurante.model.ResObserver;
import restaurante.control.RestauranteCtrl;


public class EmplTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private String[] col = {"Nombre","Id","Salario", "FechaPago","Dni"};
	private List<EmpleadosTable> row;
	RestauranteCtrl res;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 */
	public EmplTableModel(RestauranteCtrl res){
		row = new ArrayList<EmpleadosTable>();
		this.res = res;
		res.addObserver(this);
	}
	/**
	 * Aniade fila
	 */
	public void addEmpl() {
		row.add(new EmpleadosTable());
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 */
	public void RemoveEmpl(int x) {
		row.remove(x);
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
		EmpleadosTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				ct.setId(o.toString());
			break;
			case 2:
				ct.setSalario(o.toString());
			break;
			case 3:
				ct.setFechaPago(o.toString());
			break;
			case 4:	
				ct.setDni(o.toString());
			break;
		}
    }

	/**
	 * Indica si la tabla es editable
	 * @param x
	 */
	public void setEdit(boolean x) {
		edit = x;
	}
	/**
	 * La tabla es editable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return edit;
	}
	/**
	 * Devuelve el valor de una posicion indicada
	 */
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		EmpleadosTable et = row.get(rowIndex);
		String s = "";
		switch(colIndex) {
		case 0:
			s = et.getNombre();
			break;
		case 1:
			s = et.getId();
			break;
		case 2:
			s = et.getSalario();
			break;
		case 3:
			s = et.getFechaPago();
			break;
		case 4:
			s = et.getDni();
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
	 * @param emp
	 */
	private void act(List<Empleado> emp) {
		if(emp != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					row.clear();
					for(int i = 0; i < emp.size(); i++) {
						row.add(new EmpleadosTable(null, null, null, null, null));
						row.get(i).setNombre(emp.get(i).getNombre());
						row.get(i).setId(String.valueOf(emp.get(i).getid()));
						row.get(i).setSalario(String.valueOf(emp.get(i).getSalario()));
						row.get(i).setFechaPago(emp.get(i).getFechaPago());
						row.get(i).setDni(emp.get(i).getDni());
					}
					fireTableStructureChanged();
				}
			});
		}
		
		
	}
	@Override
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		act(emp);
	}


	@Override
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(emp);
	}

}

/**
 * Modelo de la tabla almacen
 */
package restaurante.view.almacen.control;

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
import restaurante.control.Restaurante;


public class AlmTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Tipo","Cantidad"};
	private List<AlmTable> row;
	
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 */
	public AlmTableModel(Restaurante res){
		row = new ArrayList<AlmTable>();
		res.addObserver(this);
	}
	/**
	 * Aniade fila
	 */
	public void addAlm() {
		row.add(new AlmTable());
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 */
	public void RemoveAlm(int x) {
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
	@Override
	/**
	 * Devuelve nombre columna 
	 */
	public String getColumnName(int column) {
		return col[column];
	}
	/**
	 * Pone un valor en la tabla
	 */
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		AlmTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				ct.setTipo(o.toString());
			break;
			case 2:
				ct.setCantidad(o.toString());
			break;
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
		AlmTable at = row.get(rowIndex);
		String s = "";
		switch(colIndex) {
		case 0:
			s = at.getNombre();
			break;
		case 1:
			s = at.getTipo();
			break;
		case 2:
			s = at.getCantidad();
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
	 * @param alm
	 */
	private void act(List<Almacen> alm) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < alm.size(); i++) {
					row.add(new AlmTable(null, null, null));
					row.get(i).setNombre(alm.get(i).getNombre());
					row.get(i).setTipo(alm.get(i).getTipo());
					row.get(i).setCantidad(String.valueOf(alm.get(i).getCantidad()));
				}
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(alm);
		
	}
	@Override
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(alm);
	}
}

/**
 * Modelo de la tabla ComidaYBebida
 */
package Restaurante.view.ComidaYBebida.control;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import Restaurante.control.Restaurante;
import Restaurante.model.Almacen;
import Restaurante.model.Cliente;
import Restaurante.model.Cocinero;
import Restaurante.model.ComidaYBebida;
import Restaurante.model.Empleado;
import Restaurante.model.Encargado;
import Restaurante.model.ResObserver;

public class CYBTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Cantidad","Comida", "Descripcion"};
	private List<CYBTable> row;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 */
	public CYBTableModel(Restaurante res){
		row = new ArrayList<CYBTable>();
		res.addObserver(this);
	}
	/**
	 * Aniade fila
	 */
	public void addCYB() {
		row.add(new CYBTable());
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 */
	public void RemoveCYB(int x) {
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
		CYBTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				ct.setCantidad(o.toString());
			break;
			case 2:
				ct.setComida(o.toString());
			break;
			case 3:
				ct.setDesc(o.toString());
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
		CYBTable ct = row.get(rowIndex);
		String s = "";
		switch(colIndex) {
		case 0:
			s = ct.getNombre();
			break;
		case 1:
			s = ct.getCantidad();
			break;
		case 2:
			s = ct.getComida();
			break;
		case 3: 
			s = ct.getDesc();
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
	 * @param cyb
	 */
	private void act(List<ComidaYBebida> cyb) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < cyb.size(); i++) {
					row.add(new CYBTable(null, null, null, null));
					row.get(i).setNombre(cyb.get(i).getNombre());
					boolean x = cyb.get(i).getComida();
					if(x)
						row.get(i).setComida("Bebida");
					else
						row.get(i).setComida("Comida");
					row.get(i).setCantidad(String.valueOf(cyb.get(i).getCantidad()));
					row.get(i).setDesc(cyb.get(i).getDesc());
				}
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(CYB);
	}

	@Override
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(CYB);
	}

}

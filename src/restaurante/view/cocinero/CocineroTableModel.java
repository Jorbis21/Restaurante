/**
 * Modelo de la tabla Cocinero
 */
package restaurante.view.cocinero;

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


public class CocineroTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private String[] col = {"Nombre","Id","Salario", "FechaPago", "Tipo", "Especialidad", "Dni"};
	private List<CocineroTable> row;
	private Restaurante res;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 */
	public CocineroTableModel(Restaurante res){
		row = new ArrayList<CocineroTable>();
		this.res = res;
		res.addObserver(this);
	}
	/**
	 * Aniade fila
	 */
	public void addCoci() {
		row.add(new CocineroTable());
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 */
	public void RemoveCoci(int x) {
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
		CocineroTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				if(!res.exiteId(Integer.parseInt(o.toString()))) 
					ct.setId(o.toString());
			break;
			case 2:
				ct.setSalario(o.toString());
			break;
			case 3:
				ct.setFechaPago(o.toString());
			break;
			case 4:
				ct.setTipo(o.toString());
			break;
			case 5:
				ct.setEspecialidad(o.toString());
			break;
			case 6:
				if(!res.exiteDni(o.toString())) 
					ct.setDni(o.toString());
			break;
		}
    }
	/**
	 * Indica si la tabla es editable
	 * @param b
	 */
	public void setEdit(boolean b) {
		edit = b;
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
		CocineroTable ct = row.get(rowIndex);
		String s = "";
		switch(colIndex) {
		case 0:
			s = ct.getNombre();
			break;
		case 1:
			s = ct.getId();
			break;
		case 2:
			s = ct.getSalario();
			break;
		case 3:
			s = ct.getFechaPago();
			break;
		case 4:
			s = ct.getTipo();
			break;
		case 5:
			s = ct.getEspecialidad();
			break;
		case 6:
			s = ct.getDni();
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
	private void act(List<Cocinero> coc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < coc.size(); i++) {
					row.add(new CocineroTable(null, null, null, null, null, null,null));
					row.get(i).setNombre(coc.get(i).getNombre());
					row.get(i).setId(String.valueOf(coc.get(i).getid()));
					row.get(i).setSalario(String.valueOf(coc.get(i).getSalario()));
					row.get(i).setFechaPago(coc.get(i).getFechaPago());
					row.get(i).setTipo(coc.get(i).getTipo());
					row.get(i).setEspecialidad(coc.get(i).getEspecialidad());
					row.get(i).setDni(coc.get(i).getDni());
				}
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(coci);
	}

	@Override
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(coci);
	}

	
}

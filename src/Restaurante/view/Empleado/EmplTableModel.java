package Restaurante.view.Empleado;

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


public class EmplTableModel extends AbstractTableModel implements ResObserver{

	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private String[] col = {"Nombre","Id","Salario", "FechaPago"};
	private List<EmpleadosTable> row;
	
	public EmplTableModel(Restaurante res){
		row = new ArrayList<EmpleadosTable>();
		res.addObserver(this);
	}
	public void addEmpl() {
		row.add(new EmpleadosTable());
		fireTableStructureChanged();
	}
	public void RemoveEmpl() {
		row.remove(row.size() - 1);
		fireTableStructureChanged();
	}
	@Override
	public int getRowCount() {
		return row.size();
	}
	@Override
	public int getColumnCount() {
		return col.length;
	}
	@Override
	public String getColumnName(int column) {
		return col[column];
	}
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
		}
    }

	
	public void setEdit(boolean x) {
		edit = x;
	}
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return edit;
	}
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
		}
		return s;
	}
	public void clear() {
		row.clear();
	}
	private void act(List<Empleado> emp) {
		if(emp != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					row.clear();
					for(int i = 0; i < emp.size(); i++) {
						row.add(new EmpleadosTable(null, null, null, null));
						row.get(i).setNombre(emp.get(i).getNombre());
						row.get(i).setId(String.valueOf(emp.get(i).getid()));
						row.get(i).setSalario(String.valueOf(emp.get(i).getSalario()));
						row.get(i).setFechaPago(emp.get(i).getFechaPago());
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

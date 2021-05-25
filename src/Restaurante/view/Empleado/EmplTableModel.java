package Restaurante.view.Empleado;

import java.util.ArrayList;
import java.util.List;

import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import Restaurante.model.Almacen;
import Restaurante.model.Cliente;
import Restaurante.model.Cocinero;
import Restaurante.model.ComidaYBebida;
import Restaurante.model.Empleado;
import Restaurante.model.Encargado;
import Restaurante.model.ResObserver;


public class EmplTableModel extends AbstractTableModel implements ResObserver{

	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Id","Salario", "FechaPago"};
	private List<EmpleadosTable> row;
	
	public EmplTableModel(){
		row = new ArrayList<EmpleadosTable>();
	}
	
	public void update(String n, String i, String s, String f) {
		row.clear();
		
		EmpleadosTable et = new EmpleadosTable(n,i,s,f);
		row.add(et);
		
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
	public void setNombreAt(Object o, int rowIndex, int colIndex) {
		EmpleadosTable et = row.get(rowIndex);
		et.setNombre(o.toString());
	}
	public void setIdAt(Object o, int rowIndex, int colIndex) {
		EmpleadosTable et = row.get(rowIndex);
		et.setId(o.toString());
	}
	public void setSalarioAt(Object o, int rowIndex, int colIndex) {
		EmpleadosTable et = row.get(rowIndex);
		et.setSalario(o.toString());
	}
	public void setFechaPagoAt(Object o, int rowIndex, int colIndex) {
		EmpleadosTable et = row.get(rowIndex);
		et.setFechaPago(o.toString());
	}
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1;
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
	@Override
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		act(emp);
		
	}

	@Override
	public void onRemove(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(emp);
	}

	@Override
	public void onModified(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(emp);
	}

	@Override
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(emp);
	}

}

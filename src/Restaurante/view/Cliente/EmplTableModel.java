package Restaurante.view.Cliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class EmplTableModel extends AbstractTableModel {

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

}

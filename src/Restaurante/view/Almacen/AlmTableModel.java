package Restaurante.view.Almacen;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;

public class AlmTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Tipo","Cantidad"};
	private List<AlmTable> row;
	
	public AlmTableModel(){
		row = new ArrayList<AlmTable>();
	}
	
	public void update(String n, String t, String c) {
		row.clear();
		
		AlmTable at = new AlmTable(n,t,c);
		row.add(at);
		
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
		AlmTable at = row.get(rowIndex);
		at.setNombre(o.toString());
	}
	public void setCantidadAt(Object o, int rowIndex, int colIndex) {
		AlmTable at = row.get(rowIndex);
		at.setCantidad(o.toString());
	}
	public void setTipoAt(Object o, int rowIndex, int colIndex) {
		AlmTable at = row.get(rowIndex);
		at.setTipo(o.toString());
	}
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1;
	}
	@Override
	public Object getValueAt(int rowIndex, int colIndex) {
		AlmTable at = row.get(rowIndex);
		String s = "";
		switch(colIndex) {
		case 0:
			s = at.getNombre();
			break;
		case 1:
			s = at.getCantidad();
			break;
		case 2:
			s = at.getTipo();
			break;
		}
		return s;
	}
	public void clear() {
		row.clear();
	}
}

package Restaurante.view.ComidaYBebida;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class CYBTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Cantidad","Comida", "Descripcion"};
	private List<CYBTable> row;
	
	public CYBTableModel(){
		row = new ArrayList<CYBTable>();
	}
	
	public void update(String n, String c, String _c, String d) {
		row.clear();
		
		CYBTable ct = new CYBTable(n,c,_c,d);
		row.add(ct);
		
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
		CYBTable ct = row.get(rowIndex);
		ct.setNombre(o.toString());
	}
	public void setCantidadAt(Object o, int rowIndex, int colIndex) {
		CYBTable ct = row.get(rowIndex);
		ct.setCantidad(o.toString());
	}
	public void setComidaAt(Object o, int rowIndex, int colIndex) {
		CYBTable ct = row.get(rowIndex);
		ct.setComida(o.toString());
	}
	public void setDescAt(Object o, int rowIndex, int colIndex) {
		CYBTable ct = row.get(rowIndex);
		ct.setDesc(o.toString());
	}
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1;
	}
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
	public void clear() {
		row.clear();
	}

}

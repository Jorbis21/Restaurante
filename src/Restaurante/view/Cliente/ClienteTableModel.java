package Restaurante.view.Cliente;

import java.util.ArrayList;
import java.util.List;

import javax.swing.table.AbstractTableModel;


public class ClienteTableModel extends AbstractTableModel{
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Cuenta","MetodoPago"};
	private List<ClienteTable> row;
	
	public ClienteTableModel(){
		row = new ArrayList<ClienteTable>();
	}
	
	public void update(String n, String c, String m) {
		row.clear();
		
		ClienteTable ct = new ClienteTable(n,c,m);
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
		ClienteTable ct = row.get(rowIndex);
		ct.setNombre(o.toString());
	}
	public void setCuentaAt(Object o, int rowIndex, int colIndex) {
		ClienteTable ct = row.get(rowIndex);
		ct.setCuenta(o.toString());
	}
	public void setFechaPagoAt(Object o, int rowIndex, int colIndex) {
		ClienteTable ct = row.get(rowIndex);
		ct.setMetodoPago(o.toString());
	}
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1;
	}
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
	public void clear() {
		row.clear();
	}

}

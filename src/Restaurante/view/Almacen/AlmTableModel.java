package Restaurante.view.Almacen;

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


public class AlmTableModel extends AbstractTableModel implements ResObserver{
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Tipo","Cantidad"};
	private List<AlmTable> row;
	
	public AlmTableModel(Restaurante res){
		row = new ArrayList<AlmTable>();
		res.addObserver(this);
	}
	
	public void update(String n, String t, String c) {
		row.clear();
		
		AlmTable at = new AlmTable(n,t,c);
		row.add(at);
		
		fireTableStructureChanged();
	}
	public void addAlm() {
		row.add(new AlmTable());
		fireTableStructureChanged();
	}
	public void RemoveAlm() {
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
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1 || colIndex== 0|| colIndex ==2;
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
			s = at.getTipo();
			break;
		case 2:
			s = at.getCantidad();
			break;
		}
		return s;
	}
	public void clear() {
		row.clear();
	}
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

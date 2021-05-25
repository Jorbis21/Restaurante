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


public class CocineroTableModel extends AbstractTableModel implements ResObserver{
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private String[] col = {"Nombre","Id","Salario", "FechaPago", "Tipo", "Especialidad"};
	private List<CocineroTable> row;
	
	public CocineroTableModel(Restaurante res){
		row = new ArrayList<CocineroTable>();
		res.addObserver(this);
	}
	
	public void addCoci() {
		row.add(new CocineroTable());
		fireTableStructureChanged();
	}
	public void RemoveCoci() {
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
		CocineroTable ct = row.get(rowIndex);
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
			case 4:
				ct.setTipo(o.toString());
			break;
			case 5:
				ct.setEspecialidad(o.toString());
			break;
		}
    }
	
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return edit;
	}
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
		}
		return s;
	}
	public void clear() {
		row.clear();
	}
	private void act(List<Cocinero> coc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < coc.size(); i++) {
					row.add(new CocineroTable(null, null, null, null, null, null));
					row.get(i).setNombre(coc.get(i).getNombre());
					row.get(i).setId(String.valueOf(coc.get(i).getid()));
					row.get(i).setSalario(String.valueOf(coc.get(i).getSalario()));
					row.get(i).setFechaPago(coc.get(i).getFechaPago());
					row.get(i).setTipo(coc.get(i).getTipo());
					row.get(i).setEspecialidad(coc.get(i).getEspecialidad());
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

	public void setEdit(boolean b) {
		edit = b;
		
	}
}

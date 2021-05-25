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


public class CocineroTableModel extends AbstractTableModel implements ResObserver{
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Id","Salario", "FechaPago", "Tipo", "Especialidad"};
	private List<CocineroTable> row;
	
	public CocineroTableModel(){
		row = new ArrayList<CocineroTable>();
	}
	
	public void update(String n, String i, String s, String f, String t, String e) {
		row.clear();
		
		CocineroTable ct = new CocineroTable(n,i,s,f,t,e);
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
		CocineroTable ct = row.get(rowIndex);
		ct.setNombre(o.toString());
	}
	public void setIdAt(Object o, int rowIndex, int colIndex) {
		CocineroTable ct = row.get(rowIndex);
		ct.setId(o.toString());
	}
	public void setSalarioAt(Object o, int rowIndex, int colIndex) {
		CocineroTable ct = row.get(rowIndex);
		ct.setSalario(o.toString());
	}
	public void setFechaPagoAt(Object o, int rowIndex, int colIndex) {
		CocineroTable ct = row.get(rowIndex);
		ct.setFechaPago(o.toString());
	}
	public void setTipoAt(Object o, int rowIndex, int colIndex) {
		CocineroTable ct = row.get(rowIndex);
		ct.setTipo(o.toString());
	}
	public void setEspecialidadAt(Object o, int rowIndex, int colIndex) {
		CocineroTable ct = row.get(rowIndex);
		ct.setEspecialidad(o.toString());
	}
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1;
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
	public void onRemove(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(coci);
	}

	@Override
	public void onModified(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
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

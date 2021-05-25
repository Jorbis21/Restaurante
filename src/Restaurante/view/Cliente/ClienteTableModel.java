package Restaurante.view.Cliente;

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


public class ClienteTableModel extends AbstractTableModel implements ResObserver{
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Cuenta","MetodoPago"};
	private List<ClienteTable> row;
	
	public ClienteTableModel(Restaurante res){
		row = new ArrayList<ClienteTable>();
		res.addObserver(this);
	}
	
	
	public void addCli() {
		row.add(new ClienteTable());
		fireTableStructureChanged();
	}
	public void RemoveCli() {
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
		ClienteTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				ct.setCuenta(o.toString());
			break;
			case 2:
				ct.setMetodoPago(o.toString());
			break;
		}
    }
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return colIndex == 1 || colIndex== 0|| colIndex ==2;
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
	
	private void act(List<Cliente> cli) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < cli.size(); i++) {
					row.add(new ClienteTable());
					row.get(i).setNombre(cli.get(i).getNombre());
					boolean x = cli.get(i).getMetodoPago();
					if(x)
						row.get(i).setMetodoPago("Tarjeta");
					else
						row.get(i).setMetodoPago("Metalico");
					row.get(i).setCuenta(String.valueOf(cli.get(i).getCuenta()));
				}
				fireTableStructureChanged();
			}
		});
		
	}
	@Override
	public void onAdd(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
 		act(cli);
 		
	}

	@Override
	public void onRemove(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(cli);
	}

	@Override
	public void onModified(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(cli);
	}

	@Override
	public void onRegister(List<Almacen> alm, List<Cliente> cli, List<Empleado> emp, List<Encargado> enc,
			List<ComidaYBebida> CYB, List<Cocinero> coci) {
		// TODO Auto-generated method stub
		act(cli);
	}

}

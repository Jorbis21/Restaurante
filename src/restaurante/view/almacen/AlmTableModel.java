/**
 * Modelo de la tabla almacen
 */
package restaurante.view.almacen;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.AbstractTableModel;

import restaurante.model.Almacen;
import restaurante.model.Cliente;
import restaurante.model.Cocinero;
import restaurante.model.ComidaYBebida;
import restaurante.model.Empleado;
import restaurante.model.ResObserver;


public class AlmTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Tipo","Cantidad"};
	private List<AlmTable> row;
	private GuiAlmCtrl alm;
	
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 * @throws FileNotFoundException 
	 */
	public AlmTableModel() throws FileNotFoundException{
		alm = new GuiAlmCtrl();
		row = new ArrayList<AlmTable>();
		alm.iniciarObs(this);
	}
	/**
	 * Aniade fila
	 * @throws FileNotFoundException 
	 */
	public void addAlm() throws FileNotFoundException {
		row.add(new AlmTable());
		alm.eventoAlm(row.get(getRowCount() - 1).convert(), 0, -1);
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 * @throws FileNotFoundException 
	 */
	public void RemoveAlm(int x) throws FileNotFoundException {
		Almacen a = row.get(x).convert();
		row.remove(x);
		alm.eventoAlm(a, 1, x);
		fireTableStructureChanged();
	}
	/**
	 * Devuelve Num filas
	 */
	@Override
	public int getRowCount() {
		return row.size();
	}
	/**
	 * Devuelve Num Columnas
	 */
	@Override
	public int getColumnCount() {
		return col.length;
	}
	@Override
	/**
	 * Devuelve nombre columna 
	 */
	public String getColumnName(int column) {
		return col[column];
	}
	/**
	 * Pone un valor en la tabla
	 */
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
				if(Integer.parseInt(o.toString()) < 0) {
					JOptionPane.showMessageDialog(null, "La cantidad no puede ser menor que 0", "Cantidad negativa", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					ct.setCantidad(o.toString());
				}
				
			break;
		}
		try {
			alm.eventoAlm(ct.convert(), 2, rowIndex);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	/**
	 * La tabla es editable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return true;
	}
	/**
	 * Devuelve el valor de una posicion indicada
	 */
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
	/**
	 * Limpia la tabla
	 */
	public void clear() {
		row.clear();
	}
	/**
	 * Actualiza los datos atraves de los observadores
	 * @param alm
	 */
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
	public void ObsAlm(List<Almacen> alm) {
		act(alm);
	}
	@Override
	public void ObsCli(List<Cliente> cli) {}
	@Override
	public void ObsEmp(List<Empleado> emp) {}
	@Override
	public void ObsCyb(List<ComidaYBebida> cyb) {}
	@Override
	public void ObsCoci(List<Cocinero> coci) {}
}

/**
 * Modelo de la tabla ComidaYBebida
 */
package restaurante.view.comidaybebida;

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

public class CYBTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private String[] col = {"Nombre","Cantidad","Comida", "Descripcion","Precio"};
	private List<CYBTable> row;
	private boolean edit;
	private GuiCybCtrl cyb;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 * @throws FileNotFoundException 
	 */
	public CYBTableModel() throws FileNotFoundException{
		cyb = new GuiCybCtrl();
		row = new ArrayList<CYBTable>();
		cyb.iniciarObs(this);
	}
	/**
	 * Aniade fila
	 * @throws FileNotFoundException 
	 */
	public void addCYB() throws FileNotFoundException {
		row.add(new CYBTable());
		cyb.eventoCyb(row.get(getRowCount() - 1).convert(), 0, -1);
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 * @throws FileNotFoundException 
	 */
	public void RemoveCYB(int x) throws FileNotFoundException {
		ComidaYBebida c = row.get(x).convert();
		row.remove(x);
		cyb.eventoCyb(c, 1, x);
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
	/**
	 * Devuelve nombre columna 
	 */
	@Override
	public String getColumnName(int column) {
		return col[column];
	}
	/**
	 * Pone un valor en la tabla
	 */
	public void setValueAt(Object o, int rowIndex, int columnIndex) {
		CYBTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
				if(Integer.parseInt(o.toString()) < 0) {
					JOptionPane.showMessageDialog(null, "La cantidad no puede ser menor que 0", "Cantidad negativa", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					ct.setCantidad(o.toString());
				}
				
			break;
			case 2:
				if(o.toString().equals("Comida") || o.toString().equals("Bebida"))
					ct.setComida(o.toString());
				else
					JOptionPane.showMessageDialog(null, "Comida solo puede ser Comida o Bebida", "ERROR", JOptionPane.ERROR_MESSAGE, null);
			break;
			case 3:
				ct.setDesc(o.toString());
			break;
			case 4:
				if(Integer.parseInt(o.toString()) < 0) {
					JOptionPane.showMessageDialog(null, "El precio no puede ser menor que 0", "Precio negativo", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					ct.setPrecio(o.toString());
				}
				
			break;
		}
		try {
			cyb.eventoCyb(ct.convert(), 2, rowIndex);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
    }
	/**
	 * La tabla es editable
	 */
	@Override
	public boolean isCellEditable(int rowIndex, int colIndex) {
		return edit;
	}
	/**
	 * Devuelve el valor de una posicion indicada
	 */
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
		case 4: 
			s = ct.getPrecio();
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
	 * @param cyb
	 */
	private void act(List<ComidaYBebida> cyb) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < cyb.size(); i++) {
					row.add(new CYBTable(null, null, null, null, null));
					row.get(i).setNombre(cyb.get(i).getNombre());
					boolean x = cyb.get(i).getComida();
					if(x)
						row.get(i).setComida("Bebida");
					else
						row.get(i).setComida("Comida");
					row.get(i).setCantidad(String.valueOf(cyb.get(i).getCantidad()));
					row.get(i).setDesc(cyb.get(i).getDesc());
					row.get(i).setPrecio(String.valueOf(cyb.get(i).getPrecio()));
				}
				fireTableStructureChanged();
			}
		});
		
	}
	
	//-------------------
	//Observers
	//-------------------
	
	public void setEdit(boolean b) {
		edit = b;
	}
	@Override
	public void ObsAlm(List<Almacen> alm) {}
	@Override
	public void ObsCli(List<Cliente> cli) {}
	@Override
	public void ObsEmp(List<Empleado> emp) {}
	@Override
	public void ObsCyb(List<ComidaYBebida> cyb) {
		act(cyb);
	}
	@Override
	public void ObsCoci(List<Cocinero> coci) {}

}

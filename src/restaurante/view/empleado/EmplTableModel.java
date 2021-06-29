/**
 * Modelo de la tabla Empleado
 */
package restaurante.view.empleado;

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
import restaurante.view.cocinero.GuiCociCtrl;

public class EmplTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private String[] col = {"Nombre","Id","Salario", "FechaPago","Dni"};
	private List<EmpleadosTable> row;
	private GuiEmplCtrl emp;
	private GuiCociCtrl coci;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 * @throws FileNotFoundException 
	 */
	public EmplTableModel(int status) throws FileNotFoundException{
		emp = new GuiEmplCtrl(status);
		coci = new GuiCociCtrl();
		row = new ArrayList<EmpleadosTable>();
		emp.iniciarObs(this);
	}
	/**
	 * Aniade fila
	 * @throws FileNotFoundException 
	 */
	public void addEmpl() throws FileNotFoundException {
		row.add(new EmpleadosTable());
		emp.eventoEnc(row.get(getRowCount() - 1).convert(), 0, -1);
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 * @throws FileNotFoundException 
	 */
	public void RemoveEmpl(int x) throws FileNotFoundException {
		Empleado e = row.get(x).convert();
		row.remove(x);
		emp.eventoEnc(e, 1, x);
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
		EmpleadosTable ct = row.get(rowIndex);
		switch(columnIndex) {
			case 0:
				ct.setNombre(o.toString());
			break;
			case 1:
			try {
				if(coci.buscId(Integer.parseInt(o.toString()))||emp.buscId(Integer.parseInt(o.toString()))) {
					JOptionPane.showMessageDialog(null, "Id ya en uso", "ERROR", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					ct.setId(o.toString());
				}
			} catch (NumberFormatException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
				
			break;
			case 2:
				if(Integer.parseInt(o.toString()) < 0) {
					JOptionPane.showMessageDialog(null, "El salario no puede ser menor que 0", "Salario negativa", JOptionPane.ERROR_MESSAGE, null);
				}
				else {
					ct.setSalario(o.toString());
				}
			break;
			case 3:
				ct.setFechaPago(o.toString());
			break;
			case 4:	
				try {
					if(coci.buscDni(o.toString())||emp.buscDni(o.toString())) {
						JOptionPane.showMessageDialog(null, "Dni ya en uso", "ERROR", JOptionPane.ERROR_MESSAGE, null);
					}
					else {
						ct.setDni(o.toString());
					}
				} catch (NumberFormatException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FileNotFoundException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
			break;
		}
		try {
			emp.eventoEnc(ct.convert(), 2, rowIndex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

	/**
	 * Indica si la tabla es editable
	 * @param x
	 */
	public void setEdit(boolean x) {
		edit = x;
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
		case 4:
			s = et.getDni();
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
	 * @param emp
	 */
	private void act(List<Empleado> emp) {
		if(emp != null) {
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					row.clear();
					for(int i = 0; i < emp.size(); i++) {
						row.add(new EmpleadosTable(null, null, null, null, null));
						row.get(i).setNombre(emp.get(i).getNombre());
						row.get(i).setId(String.valueOf(emp.get(i).getid()));
						row.get(i).setSalario(String.valueOf(emp.get(i).getSalario()));
						row.get(i).setFechaPago(emp.get(i).getFechaPago());
						row.get(i).setDni(emp.get(i).getDni());
					}
					fireTableStructureChanged();
				}
			});
		}
		
		
	}
	
	@Override
	public void ObsAlm(List<Almacen> alm) {}
	@Override
	public void ObsCli(List<Cliente> cli) {}
	@Override
	public void ObsEmp(List<Empleado> emp) {
		act(emp);
	}
	@Override
	public void ObsCyb(List<ComidaYBebida> cyb) {}
	@Override
	public void ObsCoci(List<Cocinero> coci) {}

}

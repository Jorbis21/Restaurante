/**
 * Modelo de la tabla Cocinero
 */
package restaurante.view.cocinero;

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
import restaurante.view.empleado.GuiEmplCtrl;

public class CocineroTableModel extends AbstractTableModel implements ResObserver{
	//-----------------
	//Atributos
	//-----------------
	private static final long serialVersionUID = 1L;
	private boolean edit = false;
	private String[] col = {"Nombre","Id","Salario", "FechaPago", "Tipo", "Especialidad", "Dni"};
	private List<CocineroTable> row;
	private GuiCociCtrl coci;
	private GuiEmplCtrl emp;
	//-----------------
	//Metodos
	//-----------------
	/**
	 * Constructor
	 * @param res
	 * @throws FileNotFoundException 
	 */
	public CocineroTableModel(int status) throws FileNotFoundException{
		emp = new GuiEmplCtrl(status);
		coci = new GuiCociCtrl();
		row = new ArrayList<CocineroTable>();
		coci.iniciarObs(this);
		
	}
	/**
	 * Aniade fila
	 * @throws FileNotFoundException 
	 */
	public void addCoci() throws FileNotFoundException {
		row.add(new CocineroTable());
		coci.eventoCoci(row.get(getRowCount() - 1).convert(), 0, -1);
		fireTableStructureChanged();
	}
	/**
	 * Quita fila
	 * @throws FileNotFoundException 
	 */
	public void RemoveCoci(int x) throws FileNotFoundException {
		Cocinero c = row.get(x).convert();
		row.remove(x);
		coci.eventoCoci(c, 1, x);
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
		CocineroTable ct = row.get(rowIndex);
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
				ct.setTipo(o.toString());
			break;
			case 5:
				ct.setEspecialidad(o.toString());
			break;
			case 6:
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
			coci.eventoCoci(ct.convert(), 2, rowIndex);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	/**
	 * Indica si la tabla es editable
	 * @param b
	 */
	public void setEdit(boolean b) {
		edit = b;
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
			break;
		case 6:
			s = ct.getDni();
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
	private void act(List<Cocinero> coc) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				row.clear();
				for(int i = 0; i < coc.size(); i++) {
					row.add(new CocineroTable(null, null, null, null, null, null,null));
					row.get(i).setNombre(coc.get(i).getNombre());
					row.get(i).setId(String.valueOf(coc.get(i).getid()));
					row.get(i).setSalario(String.valueOf(coc.get(i).getSalario()));
					row.get(i).setFechaPago(coc.get(i).getFechaPago());
					row.get(i).setTipo(coc.get(i).getTipo());
					row.get(i).setEspecialidad(coc.get(i).getEspecialidad());
					row.get(i).setDni(coc.get(i).getDni());
				}
				fireTableStructureChanged();
			}
		});
		
	}
	
	@Override
	public void ObsAlm(List<Almacen> alm) {}
	@Override
	public void ObsCli(List<Cliente> cli) {}
	@Override
	public void ObsEmp(List<Empleado> emp) {}
	@Override
	public void ObsCyb(List<ComidaYBebida> cyb) {}
	@Override
	public void ObsCoci(List<Cocinero> coci) {
		act(coci);
	}
}

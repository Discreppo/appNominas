/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package vista;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;
import modelo.Empleado;
import modelo.EmpleadoEventual;
import modelo.EmpleadoFijo;

/**
 *
 * @author daw1
 */
public class EmpleadoTableModel extends AbstractTableModel {

    private List<Empleado> listado;
    private String[] columnas;

    public EmpleadoTableModel() {
        this.listado = new ArrayList<>();
        this.columnas = new String[]{"DNI","NOMBRE","SALARIO","HORAS","INGRESOS","TIPO"};
    }

    public void setListado(List<Empleado> listado) {
        this.listado = listado;
        this.fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return listado.size();
    }

    @Override
    public int getColumnCount() {
        return columnas.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Empleado c = listado.get(rowIndex);
        Object o = null;
        switch (columnIndex) {
            case 0:
                o = c.getDni().toString();
                break;
            case 1:
                o = c.getNombre();
                break;
            case 2:
                if (c.getClass().getName().equals("EmpleadoFijo")) {
                    o = ((EmpleadoFijo) c).getSalario();
                } else {
                    o = ((EmpleadoEventual) c).getSalarioHoras();
                }
                break;
            case 3:
                if (c.getClass().getName().equals("EmpleadoFijo")) {
                    o = "0";
                } else {
                    o = ((EmpleadoEventual) c).getHoras();
                }
                break;
            case 4:
                o = c.ingresos();
                break;
            case 5:
                o = c.getClass();
                break;
        }
        return o;
    }

    @Override
    public boolean isCellEditable(int row, int col) {
        return false;
    }

    @Override
    public Class<?> getColumnClass(int col) {
        Class<?> clase = null;
        switch (col) {
            case 0:
                clase = String.class;
                break;
            case 1:
                clase = String.class;
                break;
            case 2:
                clase = Float.class;
                break;
            case 3:
                clase = Integer.class;
                break;
            case 4:
                clase = Integer.class;
                break;
            case 5:
                clase = Integer.class;
                break;
            
        }
        return clase;
    }

    @Override
    public String getColumnName(int col) {
        return columnas[col];
    }
    
    public void setEmpleados(List<Empleado> listado){
        this.listado = listado;
        
    }
}

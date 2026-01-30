/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labherecnia;

import java.util.ArrayList;
import java.util.Calendar;
public class Empresa {
    ArrayList<Empleados> empleados= new ArrayList<Empleados>();
    
    public Empleados buscarEmpleado(String codigo) {
        for (Empleados e : empleados) {
            if (e.getCodigo().equals(codigo)) 
                return e;
        }
        return null;
    }
    
    void registrarEmpleado(String tipoEmpleado, String codigoUnico,String nombre, double salarioBase ){
      if(tipoEmpleado.equalsIgnoreCase("Estandar")){
         Empleados e= new Empleados(codigoUnico,nombre,salarioBase);
         empleados.add(e);
      }  
      else if(tipoEmpleado.equalsIgnoreCase("Temporal")){
        EmpleadoTemporal e= new EmpleadoTemporal(codigoUnico,nombre,salarioBase);
        empleados.add(e);
    }
      else if(tipoEmpleado.equalsIgnoreCase("Ventas")){
          EmpleadoVentas e =new EmpleadoVentas(codigoUnico,nombre,salarioBase);
          empleados.add(e);
      }
    }
    
      void registrarHoras(String codigo, double horas){
          Empleados e = buscarEmpleado(codigo);
        if (e != null) {
            e.registrarHorasTrabajadas(horas);
        }
      }
      void registrarVenta(String codigo, double monto, int mes){
          Empleados emp= buscarEmpleado(codigo);
          if(emp instanceof EmpleadoVentas){
              ((EmpleadoVentas) emp).registroVentas(mes,monto);
          }
      }
      void actualizarFechaFinContrato(String codigo, Calendar nuevaFechaFinContrato){
          Empleados emp= buscarEmpleado(codigo);
          if(emp instanceof EmpleadoTemporal){
              ((EmpleadoTemporal) emp).setFinFechaContrato(nuevaFechaFinContrato);
          }
      }
      
}

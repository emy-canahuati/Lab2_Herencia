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
      void generarReporte(){
          int estandar=0;
          int temporal=0;
          int ventas=0;
          System.out.println("=====Reporte de Empleados=====");
          
          for(Empleados e: empleados){
              System.out.println("\nIdentidad: "+ e.codigoUnico+"\nNombre: "+e.nombre +"\nHoras Trabajadas: "+e.horasTrabajadas+"Pago: "+e.calcularPago());
          
          if (e instanceof EmpleadoVentas) {
            EmpleadoVentas ev = (EmpleadoVentas) e;
            
            System.out.println("\nTipo de Empleado: Empleado Estandar"+
                    "\nVentas del mes: " + ev.ventasMensuales.length);
            System.out.println("Comisión: " + (ev.ventasMensuales.length * ev.tasaComision));
            ventas++;}

          else if (e instanceof EmpleadoTemporal) {
            EmpleadoTemporal et = (EmpleadoTemporal) e;
            System.out.println("\nTipo de Empleado: Empleado Temporal"+
                    "\nFecha fin de contrato: " + et.finFechaContrato.getTime());
            temporal++;

        } else {
              System.out.println("\nTipo de Empleado: Empleado Estandar");
            estandar++;
        }
          
      }
      }
}

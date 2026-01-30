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

    public ArrayList<Empleados> obtenerEmpleados() {
        return empleados;
    }
    
    boolean registrarEmpleado(String tipoEmpleado, String codigoUnico,String nombre, double salarioBase ){
      if (buscarEmpleado(codigoUnico) != null) {
          return false;
      }
      if(tipoEmpleado.equalsIgnoreCase("Estandar")){
         Empleados e= new Empleados(codigoUnico,nombre,salarioBase);
         empleados.add(e);
         return true;
      }  
      else if(tipoEmpleado.equalsIgnoreCase("Temporal")){
        EmpleadoTemporal e= new EmpleadoTemporal(codigoUnico,nombre,salarioBase);
        empleados.add(e);
        return true;
    }
      else if(tipoEmpleado.equalsIgnoreCase("Ventas")){
          EmpleadoVentas e =new EmpleadoVentas(codigoUnico,nombre,salarioBase);
          empleados.add(e);
          return true;
      }
      return false;
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
      
      double pagoMensual(Empleados empleado, int mesActual){
          if (empleado instanceof EmpleadoVentas){
              return ((EmpleadoVentas) empleado).salarioBase+((EmpleadoVentas) empleado).calculoComision(mesActual);
          }else if (empleado instanceof EmpleadoTemporal){
              return ((EmpleadoTemporal)empleado).calcularPago();
          } else{
                return empleado.calcularPago()-(empleado.calcularPago()*0.035);
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
            
            System.out.println("\nTipo de Empleado: Empleado Ventas"+
                    "\nVentas anuales: " + ev.ventasAnuales());
            System.out.println("Comisión mes actual: " + ev.calculoComision(Calendar.getInstance().get(Calendar.MONTH) + 1));
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
      System.out.println("\nTotales -> Estándar: " + estandar + ", Temporal: " + temporal + ", Ventas: " + ventas);
      }
      
      
}

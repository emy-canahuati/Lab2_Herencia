/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labherecnia;



/**
 *
 * @author emyca
 */
import java.time.LocalDateTime;
public class EmpleadoVentas extends Empleados {
    private double[] ventasMensuales;
    private double tasaComision;
    
    public EmpleadoVentas(String codigoUnico, String nombre, double salarioBase){
        super(codigoUnico, nombre, salarioBase);
        this.ventasMensuales= new double[12];
    }
    
    public void registroVentas(int mes,double venta){
        int mesActual = LocalDateTime.now().getMonthValue()-1;
        ventasMensuales[mesActual]+=venta;
    }
    
    public void setTasaComision(double tasaComision){
        this.tasaComision=tasaComision;
    }
    
    public double calculoComision(int mes){
        return (ventasMensuales[mes]*tasaComision);
    }
    
    public double pagoMensual(int mesActual){
        double pago= (super.calcularPago()+calculoComision(mesActual));
        return pago;
    }
    
    public double ventasAnuales(){
        return ventasAnuales(0);
    }
    
    private double ventasAnuales(int index){
        if (index<12){
            return ventasAnuales(index+1)+ventasMensuales[index];
        }
        return 0;
    }
    
    String mostrarInformacion(){
        return super.mostrarInformacion()+"\nVentas Anuales: L."+ventasAnuales();
    }


}

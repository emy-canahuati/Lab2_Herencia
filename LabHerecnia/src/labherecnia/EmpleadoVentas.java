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
public class EmpleadoVentas {
    private double[] ventasMensuales;
    private double tasaComision;
    
    public EmpleadoVentas(){
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
    
    
    
    public double ventasAnuales(){
        
    }
    
}

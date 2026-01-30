/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labherecnia;



/**
 *
 * @author emyca
 */
public class EmpleadoVentas extends Empleados {
    protected double[] ventasMensuales;
    protected double tasaComision;
    
    public EmpleadoVentas(String codigoUnico, String nombre, double salarioBase){
        super(codigoUnico, nombre, salarioBase);
        this.ventasMensuales= new double[12];
    }
    
    public void registroVentas(int mes, double venta){
        if (mes >= 1 && mes <= 12) {
            ventasMensuales[mes - 1] += venta;
        }
    }
    
    public void setTasaComision(double tasaComision){
        this.tasaComision=tasaComision;
    }
    
    public double calculoComision(int mes){
        if (mes >= 1 && mes <= 12) {
            return (ventasMensuales[mes - 1] * tasaComision);
        }
        return 0;
    }
    
    public double pagoMensual(int mes){
        double pago= (super.calcularPago()+calculoComision(mes));
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

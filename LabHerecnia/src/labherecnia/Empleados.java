package labherecnia;

import java.util.Calendar;

public class Empleados {
    String codigoUnico;
    String nombre;
    Calendar fechaContratacion;
    double salarioBase;
    double horasTrabajadas;
    Empleados(String codigoUnico, String nombre, double salarioBase){
        this.codigoUnico=codigoUnico;
        this.nombre=nombre;
        this.salarioBase=salarioBase;
        fechaContratacion= Calendar.getInstance();
        horasTrabajadas=0;
    }
    String getCodigo(){
        return codigoUnico;
    }
    void registrarHorasTrabajadas(double horasSumadas){
        horasTrabajadas+= horasSumadas;
    }
    double calcularPago(){
        return salarioBase*(horasTrabajadas/160);
    }
    
    String mostrarInformacion(){
        return "\nEmpleado: "+ nombre.toUpperCase()+
                "\nIdentidad: "+ codigoUnico+ 
                "\nFecha de Contratacion: "
                + fechaContratacion.get(Calendar.DAY_OF_MONTH) +"/"+ (fechaContratacion.get(Calendar.MONTH)+1)+"/"+fechaContratacion.get(Calendar.YEAR) ;
    }

}

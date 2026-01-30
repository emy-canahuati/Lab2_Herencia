/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labherecnia;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 *
 * @author palma
 */
public class EmpleadoTemporal extends Empleados {
    private Calendar finFechaContrato;

    EmpleadoTemporal(String codigoUnico, String nombre, double salarioBase) {
        super(codigoUnico, nombre, salarioBase);
    }

    public Calendar getFinFechaContrato() {
        return finFechaContrato;
    }

    public void setFinFechaContrato(Calendar finFechaContrato) {
        this.finFechaContrato = finFechaContrato;
    }

    public double calcularPago() {
        double horasTrabajadas = 0;
        double salario = 0;
        Calendar fechaActual = Calendar.getInstance();
        if (!fechaActual.after(finFechaContrato)) {
            return super.calcularPago();
        }

        return 0.0;
    }

    @Override
    public  String mostrarInformacion(){

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaFormateada = sdf.format(this.finFechaContrato.getTime());
        return super.mostrarInformacion() + "\n"
                + "Fecha fin contrato: " + fechaFormateada;

    }
}

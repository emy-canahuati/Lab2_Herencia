package labherecnia;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class EmpleadoTemporal extends Empleados {
    protected Calendar finFechaContrato;

    EmpleadoTemporal(String codigoUnico, String nombre, double salarioBase) {
        super(codigoUnico, nombre, salarioBase);
        this.finFechaContrato = Calendar.getInstance();
    }

    public Calendar getFinFechaContrato() {
        return finFechaContrato;
    }

    public void setFinFechaContrato(Calendar finFechaContrato) {
        this.finFechaContrato = finFechaContrato;
    }

    public double calcularPago() {
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

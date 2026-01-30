package labherecnia;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class PanelRegistrarEmpleados extends JPanel {
    private final Empresa empresa;

    public PanelRegistrarEmpleados(Empresa empresa) {
        this.empresa = empresa;
        setLayout(new BorderLayout(10, 10));

        JPanel panelTipo = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JLabel lblTipo = new JLabel("Tipo de empleado:");
        JComboBox<String> comboTipo = new JComboBox<>(new String[]{"Estándar", "Temporal", "Ventas"});
        panelTipo.add(lblTipo);
        panelTipo.add(comboTipo);

        JPanel panelComun = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        JTextField txtCodigo = new JTextField(12);
        JTextField txtNombre = new JTextField(20);
        JCalendar calFechaContratacion = new JCalendar();
        JTextField txtSalarioBase = new JTextField(10);
        JTextField txtHoras = new JTextField(6);

        int row = 0;
        gbc.gridx = 0;
        gbc.gridy = row;
        panelComun.add(new JLabel("Código:"), gbc);
        gbc.gridx = 1;
        panelComun.add(txtCodigo, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panelComun.add(new JLabel("Nombre:"), gbc);
        gbc.gridx = 1;
        panelComun.add(txtNombre, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panelComun.add(new JLabel("Fecha de contratación:"), gbc);
        gbc.gridx = 1;
        panelComun.add(calFechaContratacion, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panelComun.add(new JLabel("Salario base:"), gbc);
        gbc.gridx = 1;
        panelComun.add(txtSalarioBase, gbc);

        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        panelComun.add(new JLabel("Horas trabajadas:"), gbc);
        gbc.gridx = 1;
        panelComun.add(txtHoras, gbc);


        JPanel panelExtraTemporal = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JCalendar calFinContrato = new JCalendar();
        panelExtraTemporal.add(new JLabel("Fecha fin contrato:"));
        panelExtraTemporal.add(calFinContrato);

        JPanel panelExtraVentas = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JTextField txtTasaComision = new JTextField(6);
        panelExtraVentas.add(new JLabel("Tasa comisión (ej. 0.05):"));
        panelExtraVentas.add(txtTasaComision);

        CardLayout extrasLayout = new CardLayout();
        JPanel panelExtras = new JPanel(extrasLayout);
        panelExtras.add(new JPanel(), "ESTANDAR");
        panelExtras.add(panelExtraTemporal, "TEMPORAL");
        panelExtras.add(panelExtraVentas, "VENTAS");

        comboTipo.addActionListener(e -> {
            String tipoSeleccionado = (String) comboTipo.getSelectedItem();
            String tipo = mapTipoEmpleado(tipoSeleccionado);
            if ("Temporal".equals(tipo)) {
                extrasLayout.show(panelExtras, "TEMPORAL");
            } else if ("Ventas".equals(tipo)) {
                extrasLayout.show(panelExtras, "VENTAS");
            } else {
                extrasLayout.show(panelExtras, "ESTANDAR");
            }
        });

        JButton btnRegistrar = new JButton("Registrar");
        JLabel lblEstado = new JLabel(" ");

        btnRegistrar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            String nombre = txtNombre.getText().trim();
            String salarioStr = txtSalarioBase.getText().trim();
            String horasStr = txtHoras.getText().trim();
            String tipoSeleccionado = (String) comboTipo.getSelectedItem();
            String tipo = mapTipoEmpleado(tipoSeleccionado);

            if (codigo.isEmpty() || nombre.isEmpty() || salarioStr.isEmpty() || horasStr.isEmpty()) {
                lblEstado.setText("Complete los campos obligatorios.");
                return;
            }
            if (this.empresa.buscarEmpleado(codigo) != null) {
                lblEstado.setText("Ya existe un empleado con ese código.");
                return;
            }
            double salario;
            double horas;
            try {
                salario = Double.parseDouble(salarioStr);
                horas = Double.parseDouble(horasStr);
            } catch (NumberFormatException ex) {
                lblEstado.setText("Salario u horas inválidos.");
                return;
            }

            Calendar fechaContratacion = Calendar.getInstance();
            fechaContratacion.setTime(calFechaContratacion.getDate());
            Calendar hoy = Calendar.getInstance();
            normalizarFecha(fechaContratacion);
            normalizarFecha(hoy);
            if (fechaContratacion.before(hoy)) {
                lblEstado.setText("La fecha de contratación no puede ser anterior a hoy.");
                return;
            }
            if (fechaContratacion.after(hoy)) {
                lblEstado.setText("La fecha de contratación no puede ser futura.");
                return;
            }

            if (!this.empresa.registrarEmpleado(tipo, codigo, nombre, salario)) {
                lblEstado.setText("No se pudo registrar el empleado.");
                return;
            }
            Empleados empleado = this.empresa.buscarEmpleado(codigo);
            if (empleado == null) {
                lblEstado.setText("No se pudo registrar el empleado.");
                return;
            }
            empleado.registrarHorasTrabajadas(horas);
            empleado.fechaContratacion = fechaContratacion;

            if (empleado instanceof EmpleadoTemporal) {
                Calendar finContrato = Calendar.getInstance();
                finContrato.setTime(calFinContrato.getDate());
                normalizarFecha(finContrato);
                if (finContrato.before(hoy)) {
                    lblEstado.setText("La fecha fin contrato no puede ser anterior a hoy.");
                    return;
                }
                if (finContrato.before(fechaContratacion)) {
                    lblEstado.setText("La fecha fin contrato no puede ser anterior a contratación.");
                    return;
                }
                ((EmpleadoTemporal) empleado).setFinFechaContrato(finContrato);
            }

            if (empleado instanceof EmpleadoVentas) {
                String tasaStr = txtTasaComision.getText().trim();
                if (!tasaStr.isEmpty()) {
                    try {
                        double tasa = Double.parseDouble(tasaStr);
                        ((EmpleadoVentas) empleado).setTasaComision(tasa);
                    } catch (NumberFormatException ex) {
                        lblEstado.setText("Tasa de comisión inválida.");
                        return;
                    }
                }
            }

            lblEstado.setText("Empleado registrado.");
            txtCodigo.setText("");
            txtNombre.setText("");
            txtSalarioBase.setText("");
            txtHoras.setText("");
            txtTasaComision.setText("");
        });

        JPanel panelCentro = new JPanel(new BorderLayout(10, 10));
        panelCentro.add(panelComun, BorderLayout.NORTH);
        panelCentro.add(panelExtras, BorderLayout.CENTER);

        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(btnRegistrar, BorderLayout.WEST);
        panelInferior.add(lblEstado, BorderLayout.CENTER);

        add(panelTipo, BorderLayout.NORTH);
        add(panelCentro, BorderLayout.CENTER);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private String mapTipoEmpleado(String tipoSeleccionado) {
        if ("Estándar".equals(tipoSeleccionado)) {
            return "Estandar";
        }
        return tipoSeleccionado;
    }

    private void normalizarFecha(Calendar fecha) {
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
}

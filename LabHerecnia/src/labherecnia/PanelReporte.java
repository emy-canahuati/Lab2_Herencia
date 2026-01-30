package labherecnia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.Objects;

public class PanelReporte extends JPanel {

    public Empresa empresa;
    public PanelReporte(Empresa empresa, Runnable onActualizarContrato){
        this.empresa = empresa;
        Runnable actualizarContratoHandler = onActualizarContrato;
        setLayout(new BorderLayout(10, 10));

        JPanel panelBuscar = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JLabel lblCodigo = new JLabel("Código:");
        JTextField txtCodigo = new JTextField(15);
        JButton btnBuscar = new JButton("Buscar");
        panelBuscar.add(new JLabel("Buscar Empleados por Código"));
        panelBuscar.add(lblCodigo);
        panelBuscar.add(txtCodigo);
        panelBuscar.add(btnBuscar);

        DefaultTableModel modelo = new DefaultTableModel(
                new Object[]{"Código", "Nombre", "Tipo empleado", "Salario base"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaResultados = new JTable(modelo);
        JScrollPane scrollResultado = new JScrollPane(tablaResultados);
        JLabel lblEstado = new JLabel(" ");
        final Empleados[] empleadoSeleccionado = new Empleados[]{null};

        btnBuscar.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            modelo.setRowCount(0);
            if (codigo.isEmpty()) {
                lblEstado.setText("Ingrese un código para buscar.");
                return;
            }
            Empleados empleado = empresa.buscarEmpleado(codigo);
            if (empleado == null) {
                lblEstado.setText("No se encontró un empleado con el código " + codigo + ".");
                empleadoSeleccionado[0] = null;
                return;
            }
            lblEstado.setText("Empleado encontrado.");
            modelo.addRow(new Object[]{
                    empleado.getCodigo(),
                    empleado.nombre,
                    tipoEmpleado(empleado),
                    String.format("%.2f", empleado.salarioBase)
            });
            empleadoSeleccionado[0] = empleado;
        });

        tablaResultados.getSelectionModel().addListSelectionListener(e -> {
            if (e.getValueIsAdjusting()) {
                return;
            }
            int row = tablaResultados.getSelectedRow();
            if (row >= 0) {
                String codigo = Objects.toString(modelo.getValueAt(row, 0), "");
                if (!codigo.isEmpty()) {
                    empleadoSeleccionado[0] = empresa.buscarEmpleado(codigo);
                }
            }
        });

        JPanel panelAcciones = new JPanel(new FlowLayout(FlowLayout.LEFT, 8, 8));
        JButton btnRegistrarVentas = new JButton("Registrar Ventas");
        JButton btnModificarInfo = new JButton("Modificar Información");
        JButton btnCalcularPagos = new JButton("Calcular Pagos");
        JButton btnActualizarContrato = new JButton("Actualizar Contrato");
        panelAcciones.add(btnRegistrarVentas);
        panelAcciones.add(btnModificarInfo);
        panelAcciones.add(btnCalcularPagos);
        panelAcciones.add(btnActualizarContrato);

        btnRegistrarVentas.addActionListener(e -> {
            Empleados empleado = empleadoSeleccionado[0];
            if (empleado == null) {
                lblEstado.setText("Seleccione un empleado para registrar ventas.");
                return;
            }
            if (!(empleado instanceof EmpleadoVentas)) {
                lblEstado.setText("El empleado seleccionado no es de ventas.");
                return;
            }
            String montoStr = JOptionPane.showInputDialog(this, "Monto de venta:", "Registrar Ventas", JOptionPane.PLAIN_MESSAGE);
            if (montoStr == null || montoStr.trim().isEmpty()) {
                return;
            }
            String mesStr = JOptionPane.showInputDialog(this, "Mes (1-12):", "Registrar Ventas", JOptionPane.PLAIN_MESSAGE);
            if (mesStr == null || mesStr.trim().isEmpty()) {
                return;
            }
            try {
                double monto = Double.parseDouble(montoStr.trim());
                int mes = Integer.parseInt(mesStr.trim());
                empresa.registrarVenta(empleado.getCodigo(), monto, mes);
                lblEstado.setText("Venta registrada para el empleado " + empleado.getCodigo() + ".");
            } catch (NumberFormatException ex) {
                lblEstado.setText("Monto o mes inválido.");
            }
        });

        btnModificarInfo.addActionListener(e -> {
            Empleados empleado = empleadoSeleccionado[0];
            if (empleado == null) {
                lblEstado.setText("Seleccione un empleado para modificar información.");
                return;
            }
            String nuevoNombre = JOptionPane.showInputDialog(this, "Nuevo nombre:", empleado.nombre);
            if (nuevoNombre != null && !nuevoNombre.trim().isEmpty()) {
                empleado.nombre = nuevoNombre.trim();
            }
            String nuevoSalario = JOptionPane.showInputDialog(this, "Nuevo salario base:", empleado.salarioBase);
            if (nuevoSalario != null && !nuevoSalario.trim().isEmpty()) {
                try {
                    empleado.salarioBase = Double.parseDouble(nuevoSalario.trim());
                } catch (NumberFormatException ex) {
                    lblEstado.setText("Salario inválido.");
                    return;
                }
            }
            // Refrescar la fila si existe
            int row = tablaResultados.getSelectedRow();
            if (row >= 0) {
                modelo.setValueAt(empleado.nombre, row, 1);
                modelo.setValueAt(tipoEmpleado(empleado), row, 2);
                modelo.setValueAt(String.format("%.2f", empleado.salarioBase), row, 3);
            }
            lblEstado.setText("Información actualizada.");
        });

        btnCalcularPagos.addActionListener(e -> {
            Empleados empleado = empleadoSeleccionado[0];
            if (empleado == null) {
                lblEstado.setText("Seleccione un empleado para calcular pagos.");
                return;
            }
            String mesStr = JOptionPane.showInputDialog(this, "Mes (1-12):", "Calcular Pagos", JOptionPane.PLAIN_MESSAGE);
            if (mesStr == null || mesStr.trim().isEmpty()) {
                return;
            }
            try {
                int mes = Integer.parseInt(mesStr.trim());
                double pago = empresa.pagoMensual(empleado, mes);
                lblEstado.setText("Pago mensual: " + String.format("%.2f", pago));
            } catch (NumberFormatException ex) {
                lblEstado.setText("Mes inválido.");
            }
        });

        btnActualizarContrato.addActionListener(e -> actualizarContratoHandler.run());

        add(panelBuscar, BorderLayout.NORTH);
        add(scrollResultado, BorderLayout.CENTER);
        JPanel panelInferior = new JPanel(new BorderLayout());
        panelInferior.add(panelAcciones, BorderLayout.CENTER);
        panelInferior.add(lblEstado, BorderLayout.SOUTH);
        add(panelInferior, BorderLayout.SOUTH);
    }

    private String tipoEmpleado(Empleados empleado) {
        if (empleado instanceof EmpleadoVentas) {
            return "Ventas";
        }
        if (empleado instanceof EmpleadoTemporal) {
            return "Temporal";
        }
        return "Estándar";
    }

}

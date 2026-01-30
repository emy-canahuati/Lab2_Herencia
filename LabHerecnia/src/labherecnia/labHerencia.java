package labherecnia;

import com.toedter.calendar.JCalendar;
import javax.swing.*;
import java.awt.*;
import java.util.Calendar;

public class labHerencia extends JFrame {
    public Empresa empresa;
    public JPanel panelBotones;
    public JPanel panelCentral;

    public CardLayout cardLayout;



    public labHerencia() {
        setTitle("Gestion Empleados");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        empresa = new Empresa();
        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Menú"));
        JButton btnAgregarEmpleados = new JButton("Agregar Empleados");
        JButton btnRegistrarHoras = new JButton("Registrar Horas");
        JButton btnRegistrarVentas = new JButton("Registrar Ventas");
        JButton btnActualizarContrato = new JButton("Actualizar Contrato");
        JButton btnReporteEmpleados = new JButton("Reporte de Empleados");


        panelBotones.add(btnAgregarEmpleados);
        panelBotones.add(btnRegistrarHoras);
        panelBotones.add(btnRegistrarVentas);
        panelBotones.add(btnActualizarContrato);
        panelBotones.add(btnReporteEmpleados);

        add(panelBotones, BorderLayout.WEST);

        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);

        JPanel panelAgregarEmpleados = new PanelRegistrarEmpleados(empresa);

        JPanel panelHoras = new registrarHorasPanel(empresa);

        JPanel panelRegistrarVentas = new registrarVentasPanel(empresa);

        JPanel panelActualizarContrato = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(6, 6, 6, 6);
        gbc.anchor = GridBagConstraints.WEST;

        JLabel lblCodigo = new JLabel("Código:");
        JTextField txtCodigo = new JTextField(12);
        JLabel lblFecha = new JLabel("Fecha fin de contrato:");
        JCalendar calFinContrato = new JCalendar();
        JButton btnActualizarContratoPanel = new JButton("Actualizar");
        JLabel lblEstadoContrato = new JLabel(" ");

        gbc.gridx = 0;
        gbc.gridy = 0;
        panelActualizarContrato.add(lblCodigo, gbc);
        gbc.gridx = 1;
        panelActualizarContrato.add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        panelActualizarContrato.add(lblFecha, gbc);
        gbc.gridx = 1;
        panelActualizarContrato.add(calFinContrato, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        panelActualizarContrato.add(btnActualizarContratoPanel, gbc);

        gbc.gridy = 3;
        panelActualizarContrato.add(lblEstadoContrato, gbc);

        btnActualizarContratoPanel.addActionListener(e -> {
            String codigo = txtCodigo.getText().trim();
            if (codigo.isEmpty()) {
                lblEstadoContrato.setText("Ingrese un código.");
                return;
            }
            Empleados empleado = empresa.buscarEmpleado(codigo);
            if (!(empleado instanceof EmpleadoTemporal)) {
                lblEstadoContrato.setText("El empleado no es temporal o no existe.");
                return;
            }
            Calendar nuevaFecha = Calendar.getInstance();
            nuevaFecha.setTime(calFinContrato.getDate());
            normalizarFecha(nuevaFecha);
            Calendar hoy = Calendar.getInstance();
            normalizarFecha(hoy);
            Calendar fechaContratacion = empleado.fechaContratacion;
            if (fechaContratacion != null) {
                normalizarFecha(fechaContratacion);
            }
            if (fechaContratacion != null && nuevaFecha.before(fechaContratacion)) {
                lblEstadoContrato.setText("La fecha fin no puede ser anterior a contratación.");
                return;
            }
            if (nuevaFecha.before(hoy)) {
                lblEstadoContrato.setText("La fecha fin no puede ser anterior a hoy.");
                return;
            }
            empresa.actualizarFechaFinContrato(codigo, nuevaFecha);
            lblEstadoContrato.setText("Fecha de fin de contrato actualizada.");
        });

        JPanel panelReporte = new PanelReporte(
                empresa,
                () -> cardLayout.show(panelCentral, "ACTUALIZAR_CONTRATO")
        );
        
        panelCentral.add(panelAgregarEmpleados, "AGREGAR_EMPLEADOS");
        panelCentral.add(panelRegistrarVentas, "REGISTRAR_VENTAS");
        panelCentral.add(panelActualizarContrato, "ACTUALIZAR_CONTRATO");
        panelCentral.add(panelReporte, "REPORTE");
        panelCentral.add(panelHoras, "HORAS");

        add(panelCentral, BorderLayout.CENTER);


        btnAgregarEmpleados.addActionListener(e -> cardLayout.show(panelCentral, "AGREGAR_EMPLEADOS"));
        btnRegistrarHoras.addActionListener(e -> cardLayout.show(panelCentral, "HORAS"));
        btnRegistrarVentas.addActionListener(e -> cardLayout.show(panelCentral, "REGISTRAR_VENTAS"));
        btnActualizarContrato.addActionListener(e -> cardLayout.show(panelCentral, "ACTUALIZAR_CONTRATO"));
        btnReporteEmpleados.addActionListener(e -> cardLayout.show(panelCentral, "REPORTE"));
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new labHerencia().setVisible(true);
        });
    }

    private void normalizarFecha(Calendar fecha) {
        fecha.set(Calendar.HOUR_OF_DAY, 0);
        fecha.set(Calendar.MINUTE, 0);
        fecha.set(Calendar.SECOND, 0);
        fecha.set(Calendar.MILLISECOND, 0);
    }
}

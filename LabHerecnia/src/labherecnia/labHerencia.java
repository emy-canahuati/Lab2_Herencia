/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package labherecnia;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author adria
 */
public class labHerencia extends JFrame {

    public JPanel panelBotones;
    public JPanel panelCentral;

    public CardLayout cardLayout;



    public labHerencia() {
        setTitle("Gestion Empleados");
        setSize(800, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        panelBotones = new JPanel();
        panelBotones.setLayout(new GridLayout(0, 1, 5, 5));
        panelBotones.setBorder(BorderFactory.createTitledBorder("Menú"));
        JButton btnAgregarEstandar = new JButton("Agregar Empleado Estándar");
        JButton btnAgregarTemporal = new JButton("Agregar Empleado Temporal");
        JButton btnAgregarVentas = new JButton("Agregar Empleado Ventas");
        JButton btnRegistrarHoras = new JButton("Registrar Horas");
        JButton btnRegistrarVentas = new JButton("Registrar Ventas");
        JButton btnActualizarContrato = new JButton("Actualizar Contrato");
        JButton btnReporteEmpleados = new JButton("Reporte de Empleados");


        panelBotones.add(btnAgregarEstandar);
        panelBotones.add(btnAgregarTemporal);
        panelBotones.add(btnAgregarVentas);
        panelBotones.add(btnRegistrarHoras);
        panelBotones.add(btnRegistrarVentas);
        panelBotones.add(btnActualizarContrato);
        panelBotones.add(btnReporteEmpleados);

        add(panelBotones, BorderLayout.WEST);

        cardLayout = new CardLayout();
        panelCentral = new JPanel(cardLayout);

        JPanel panelEstandar = new JPanel();
        panelEstandar.add(new JLabel("Formulario Empleado Estándar"));

        JPanel panelTemporal = new JPanel();
        panelTemporal.add(new JLabel("Formulario Empleado Temporal"));

        JPanel panelVentas = new JPanel();
        panelVentas.add(new JLabel("Formulario Empleado Ventas"));

        JPanel panelHoras = new JPanel();
        panelHoras.add(new JLabel("Registrar Horas Trabajadas"));

        JPanel panelRegistrarVentas = new JPanel();
        panelRegistrarVentas.add(new JLabel("Registrar Ventas"));

        JPanel panelActualizarContrato = new JPanel();
        panelActualizarContrato.add(new JLabel("Actualizar Fecha Fin Contrato"));

        JPanel panelReporte = new JPanel();
        panelReporte.add(new JLabel("Reporte de Empleados"));

        panelCentral.add(panelRegistrarVentas, "REGISTRAR_VENTAS");
        panelCentral.add(panelActualizarContrato, "ACTUALIZAR_CONTRATO");
        panelCentral.add(panelReporte, "REPORTE");
        panelCentral.add(panelEstandar, "ESTANDAR");
        panelCentral.add(panelTemporal,"TEMPORAL");
        panelCentral.add(panelVentas, "VENTAS");
        panelCentral.add(panelHoras, "HORAS");

        add(panelCentral, BorderLayout.CENTER);


        btnAgregarEstandar.addActionListener(e -> cardLayout.show(panelCentral, "ESTANDAR"));
        btnAgregarTemporal.addActionListener(e -> cardLayout.show(panelCentral, "TEMPORAL"));
        btnAgregarVentas.addActionListener(e -> cardLayout.show(panelCentral, "VENTAS"));
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
}

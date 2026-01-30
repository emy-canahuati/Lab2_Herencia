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

        JPanel panelAgregarEmpleados = new JPanel();
        panelAgregarEmpleados.add(new JLabel("Agregar Empleados"));

        JPanel panelHoras = new registrarHorasPanel(empresa);

        JPanel panelRegistrarVentas = new JPanel();
        panelRegistrarVentas.add(new JLabel("Registrar Ventas"));

        JPanel panelActualizarContrato = new JPanel();
        panelActualizarContrato.add(new JLabel("Actualizar Fecha Fin Contrato"));

        JPanel panelReporte = new PanelReporte(empresa);

        panelCentral.add(panelRegistrarVentas, "REGISTRAR_VENTAS");
        panelCentral.add(panelActualizarContrato, "ACTUALIZAR_CONTRATO");
        panelCentral.add(panelReporte, "REPORTE");
        panelCentral.add(panelAgregarEmpleados, "AGREGAR_EMPLEADOS");
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
}

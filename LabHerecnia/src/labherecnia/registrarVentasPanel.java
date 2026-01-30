/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labherecnia;

import javax.swing.*;
import java.awt.*;

public class registrarVentasPanel extends JPanel {
    private Empresa empresa;
    private JTextField txtCodigo;
    private JTextField txtMonto;
    private JSpinner spMes;

    registrarVentasPanel(Empresa empresa) {
        this.empresa = empresa;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JLabel lblTitulo = new JLabel("Registrar Ventas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblCodigo = new JLabel("Código del empleado:");
        JLabel lblMes = new JLabel("Mes (1-12):");
        JLabel lblMonto = new JLabel("Monto de venta:");

        txtCodigo = new JTextField(15);
        spMes = new JSpinner(new SpinnerNumberModel(1, 1, 12, 1));
        txtMonto = new JTextField(15);

        JButton btnRegistrar = new JButton("Registrar Venta");

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0;
        gbc.gridy = 1;
        add(lblCodigo, gbc);

        gbc.gridx = 1;
        add(txtCodigo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        add(lblMes, gbc);

        gbc.gridx = 1;
        add(spMes, gbc);

        gbc.gridx = 0;
        gbc.gridy = 3;
        add(lblMonto, gbc);

        gbc.gridx = 1;
        add(txtMonto, gbc);

        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2;
        add(btnRegistrar, gbc);

        btnRegistrar.addActionListener(e -> registrarVenta());
    }

    private void registrarVenta() {
        String codigo = txtCodigo.getText().trim();
        String montoTexto = txtMonto.getText().trim();
        int mes = (int) spMes.getValue();

        if (codigo.isEmpty() || montoTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!esDouble(montoTexto)) {
            JOptionPane.showMessageDialog(this,
                    "Ingrese un monto válido",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            txtMonto.setText("");
            return;
        }
        Empleados emp = empresa.buscarEmpleado(codigo);
        if (!(emp instanceof EmpleadoVentas)) {
            JOptionPane.showMessageDialog(this,
                    "Empleado no encontrado o no es de ventas",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        double monto = Double.parseDouble(montoTexto);
        empresa.registrarVenta(codigo, monto, mes);

        JOptionPane.showMessageDialog(this,
                "Venta registrada correctamente",
                "Éxito",
                JOptionPane.INFORMATION_MESSAGE);

        txtCodigo.setText("");
        txtMonto.setText("");
        spMes.setValue(1);
    }

    public static boolean esDouble(String texto) {
        if (texto == null || texto.isEmpty()) {
            return false;
        }
        return texto.matches("-?\\d+(\\.\\d+)?");
    }
}

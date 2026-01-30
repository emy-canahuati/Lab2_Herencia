package labherecnia;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class PanelReporte extends JPanel {

    public Empresa empresa;
    public PanelReporte(Empresa empresa){
        this.empresa = empresa;
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
                new Object[]{"Código", "Nombre", "Salario base"}, 0
        ) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        JTable tablaResultados = new JTable(modelo);
        JScrollPane scrollResultado = new JScrollPane(tablaResultados);
        JLabel lblEstado = new JLabel(" ");

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
                return;
            }
            lblEstado.setText("Empleado encontrado.");
            modelo.addRow(new Object[]{
                    empleado.getCodigo(),
                    empleado.nombre,
                    String.format("%.2f", empleado.salarioBase)
            });
        });

        add(panelBuscar, BorderLayout.NORTH);
        add(scrollResultado, BorderLayout.CENTER);
        add(lblEstado, BorderLayout.SOUTH);
    }

    
}

package labherecnia;
import javax.swing.*;
import java.awt.*;
public class registrarHorasPanel extends JPanel {
    private Empresa empresa;
    private JTextField txtCodigo;
    private JTextField txtHoras;
    
    registrarHorasPanel(Empresa empresa){
        this.empresa=empresa;
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        
        JLabel lblTitulo = new JLabel("Registrar Horas Trabajadas");
        lblTitulo.setFont(new Font("Arial", Font.BOLD, 18));

        JLabel lblCodigo = new JLabel("Código del empleado:");
        JLabel lblHoras = new JLabel("Horas trabajadas:");

        txtCodigo = new JTextField(15);
        txtHoras = new JTextField(15);

        JButton btnRegistrar = new JButton("Registrar Horas");
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 2;
        add(lblTitulo, gbc);

        gbc.gridwidth = 1;
        gbc.gridx = 0; gbc.gridy = 1;
        add(lblCodigo, gbc);

        gbc.gridx = 1;
        add(txtCodigo, gbc);

        gbc.gridx = 0; gbc.gridy = 2;
        add(lblHoras, gbc);

        gbc.gridx = 1;
        add(txtHoras, gbc);

        gbc.gridx = 0; gbc.gridy = 3; gbc.gridwidth = 2;
        add(btnRegistrar, gbc);
        
        btnRegistrar.addActionListener(e -> registrarHoras());
    }
    private void registrarHoras() {
        String codigo = txtCodigo.getText().trim();
        String horasTexto = txtHoras.getText().trim();
        
        if (!esDouble(horasTexto)) {
         JOptionPane.showMessageDialog(this,
        "Ingrese un número válido (ej: 8 o 7.5)",
        "Error",
        JOptionPane.ERROR_MESSAGE);
         txtHoras.setText("");
        return;
}

        if (codigo.isEmpty() || horasTexto.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "Todos los campos son obligatorios",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;}
            double horas = Double.parseDouble(horasTexto);

            Empleados emp = empresa.buscarEmpleado(codigo);
            if (emp == null) {
                JOptionPane.showMessageDialog(this,
                        "Empleado no encontrado",
                        "Error",
                        JOptionPane.ERROR_MESSAGE);
                return;
            }

            empresa.registrarHoras(codigo, horas);

            JOptionPane.showMessageDialog(this,
                    "Horas registradas correctamente",
                    "Éxito",
                    JOptionPane.INFORMATION_MESSAGE);

            txtCodigo.setText("");
            txtHoras.setText("");
        }
    public static boolean esDouble(String texto) {
    if (texto == null || texto.isEmpty()) return false;

    return texto.matches("-?\\d+(\\.\\d+)?");
}
}


/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package labherecnia;

import javax.swing.*;
import java.awt.*;
import javax.swing.JPanel;

/**
 *
 * @author emyca
 */
public class RegistrarEmpleadoVentas extends JPanel{
    JLabel eti_nombre, eti_codigo,eti_fechaContratacion, eti_salarioBase, eti_horas;
    JTextField nombre,codigo,fechaContratacion,salarioBase,horas;
    
    RegistrarEmpleadoVentas(){
        this.setLayout(new CardLayout());
        
        eti_nombre=new JLabel("Empleado de Ventas");
        add(eti_nombre);
        
        nombre=new JTextField();
        add(nombre);
        
        eti_codigo=new JLabel("Nombre Completo:");
        add(eti_codigo);
        
        codigo=new JTextField();
        add(codigo);
        
        eti_fechaContratacion=new JLabel("Fecha de Contratación:");
        add(eti_fechaContratacion);
        
        fechaContratacion=new JTextField();
        add(fechaContratacion);
        
        eti_salarioBase=new JLabel("Salario Base:");
        add(eti_salarioBase);
        
        salarioBase=new JTextField();
        add(salarioBase);
        
        eti_horas=new JLabel("Horas Trabajadas:");
        add(eti_horas);
        
        horas=new JTextField();
        add(horas);
        
       
        this.setVisible(true);
    }
    
    
}

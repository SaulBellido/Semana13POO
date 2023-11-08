/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gui;

/**
 *
 * @author SAUL
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class SistemaRegistroEstudiantes extends JFrame {
    private JTextField campoNombre, campoApellido, campoID, campoFechaNacimiento, campoDireccion;
    private DefaultListModel<Estudiante> modeloListaEstudiantes;
    private JList<Estudiante> listaEstudiantes;

    private ArrayList<Estudiante> estudiantes = new ArrayList<>();

    public SistemaRegistroEstudiantes() {
        setTitle("Sistema de Registro de Estudiantes");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2));
        getContentPane().add(panel);

        panel.add(new JLabel("Nombre:"));
        campoNombre = new JTextField();
        panel.add(campoNombre);

        panel.add(new JLabel("Apellido:"));
        campoApellido = new JTextField();
        panel.add(campoApellido);

        panel.add(new JLabel("ID:"));
        campoID = new JTextField();
        panel.add(campoID);

        panel.add(new JLabel("Fecha de Nacimiento:"));
        campoFechaNacimiento = new JTextField();
        panel.add(campoFechaNacimiento);

        panel.add(new JLabel("Dirección:"));
        campoDireccion = new JTextField();
        panel.add(campoDireccion);

        JButton botonAgregar = new JButton("Agregar Estudiante");
        panel.add(botonAgregar);

        modeloListaEstudiantes = new DefaultListModel<>();
        listaEstudiantes = new JList<>(modeloListaEstudiantes);

        panel.add(new JScrollPane(listaEstudiantes));

        botonAgregar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                agregarEstudiante();
            }
        });

        setVisible(true);
    }

    private void agregarEstudiante() {
        String nombre = campoNombre.getText();
        String apellido = campoApellido.getText();
        String id = campoID.getText();
        String fechaNacimiento = campoFechaNacimiento.getText();
        String direccion = campoDireccion.getText();

        if (nombre.isEmpty() || apellido.isEmpty() || id.isEmpty() || fechaNacimiento.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos obligatorios.");
            return;
        }

        for (Estudiante estudiante : estudiantes) {
            if (estudiante.getId().equals(id)) {
                JOptionPane.showMessageDialog(this, "Ya existe un estudiante con el mismo ID.");
                return;
            }
        }

        Estudiante estudiante = new Estudiante(nombre, apellido, id, fechaNacimiento, direccion);
        estudiantes.add(estudiante);
        modeloListaEstudiantes.addElement(estudiante);

        campoNombre.setText("");
        campoApellido.setText("");
        campoID.setText("");
        campoFechaNacimiento.setText("");
        campoDireccion.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new SistemaRegistroEstudiantes();
            }
        });
    }

    public static class Estudiante {
        private String nombre;
        private String apellido;
        private String id;
        private String fechaNacimiento;
        private String direccion;

        public Estudiante(String nombre, String apellido, String id, String fechaNacimiento, String direccion) {
            this.nombre = nombre;
            this.apellido = apellido;
            this.id = id;
            this.fechaNacimiento = fechaNacimiento;
            this.direccion = direccion;
        }

        public String getNombre() {
            return nombre;
        }

        public String getApellido() {
            return apellido;
        }

        public String getId() {
            return id;
        }

        public String getFechaNacimiento() {
            return fechaNacimiento;
        }

        public String getDireccion() {
            return direccion;
        }

        @Override
        public String toString() {
            return nombre + " " + apellido + " (ID: " + id + ")";
        }
    }
}

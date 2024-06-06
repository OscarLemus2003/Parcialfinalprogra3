package org.example.Vista;
import org.example.Modelo.Contacto;
import org.example.Servicio.Agenda;

import java.time.LocalDate;

import java.io.IOException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Agenda agenda = new Agenda();

        // Agregar contactos
        agenda.agregarContacto("oscar", 123456789, "oscar@example.com", LocalDate.of(1990, 1, 1));
        agenda.agregarContacto("martin", 987654321, "martin@example.com", LocalDate.of(1985, 5, 15));
        agenda.agregarContacto("gisselle", 234356789, "gisselle@example.com", LocalDate.of(1992, 3, 10));
        // Agregar más contactos...

        // Mostrar contactos
        System.out.println("Contactos en la agenda:");
        agenda.mostrarContactos();

        // Buscar un contacto
        System.out.println("\nBuscando el contacto de david:");
        Contacto contacto = agenda.buscarContacto("nombre", "david");
        if (contacto != null) {
            System.out.println("Nombre: " + contacto.getNombre() + ", Teléfono: " + contacto.getTelefono() + ", Correo: " + contacto.getCorreoElectronico());
        } else {
            System.out.println("Contacto no encontrado.");
        }

        // Serializar la agenda
        try {
            agenda.guardarAgenda("contacto.dat");
            System.out.println("\nAgenda guardada en 'contacto.dat'.");
        } catch (IOException e) {
            System.out.println("Error al guardar la agenda: " + e.getMessage());
        }

        // Deserializar la agenda
        try {
            agenda.cargarAgenda("contacto.dat");
            System.out.println("\nAgenda cargada desde 'contacto.dat'.");
            System.out.println("Contactos en la agenda después de cargar:");
            agenda.mostrarContactos();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar la agenda: " + e.getMessage());
        }
    }
}

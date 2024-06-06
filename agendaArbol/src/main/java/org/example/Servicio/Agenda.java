package org.example.Servicio;
import org.example.Modelo.Contacto;
import org.example.Modelo.NodoContacto;

import java.io.*;
import java.time.LocalDate;

public class Agenda {
    private NodoContacto raiz;

    public Agenda() {
        this.raiz = null;
    }

    public void agregarContacto(String nombre, long telefono, String correoElectronico, LocalDate fechaNacimiento) {
        Contacto nuevoContacto = new Contacto(nombre, telefono, correoElectronico, fechaNacimiento);
        if (this.raiz == null) {
            this.raiz = new NodoContacto(nuevoContacto);
        } else {
            this.insertar(this.raiz, nuevoContacto);
        }
    }

    private void insertar(NodoContacto padre, Contacto contacto) {
        if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) < 0) {
            if (padre.getIzdo() == null) {
                padre.setIzdo(new NodoContacto(contacto));
            } else {
                insertar(padre.getIzdo(), contacto);
            }
        } else if (contacto.getNombre().compareTo(padre.getContacto().getNombre()) > 0) {
            if (padre.getDcho() == null) {
                padre.setDcho(new NodoContacto(contacto));
            } else {
                insertar(padre.getDcho(), contacto);
            }
        }
    }

    public Contacto buscarContacto(String criterio, String valor) {
        return buscar(this.raiz, criterio, valor);
    }

    private Contacto buscar(NodoContacto nodo, String criterio, String valor) {
        if (nodo == null) {
            return null;
        }
        Contacto contacto = nodo.getContacto();
        if ((criterio.equals("nombre") && contacto.getNombre().equals(valor)) ||
                (criterio.equals("telefono") && String.valueOf(contacto.getTelefono()).equals(valor)) ||
                (criterio.equals("correoElectronico") && contacto.getCorreoElectronico().equals(valor))) {
            return contacto;
        }
        Contacto izquierdo = buscar(nodo.getIzdo(), criterio, valor);
        return (izquierdo != null) ? izquierdo : buscar(nodo.getDcho(), criterio, valor);
    }

    public Contacto buscar(Contacto contacto) {
        return buscar(this.raiz, contacto);
    }

    private Contacto buscar(NodoContacto nodo, Contacto contacto) {
        if (nodo == null) {
            return null;
        }
        Contacto nodoContacto = nodo.getContacto();
        if ((contacto.getNombre() != null && contacto.getNombre().equals(nodoContacto.getNombre())) ||
                (contacto.getTelefono() != 0 && contacto.getTelefono() == nodoContacto.getTelefono()) ||
                (contacto.getCorreoElectronico() != null && contacto.getCorreoElectronico().equals(nodoContacto.getCorreoElectronico()))) {
            return nodoContacto;
        }
        Contacto izquierdo = buscar(nodo.getIzdo(), contacto);
        return (izquierdo != null) ? izquierdo : buscar(nodo.getDcho(), contacto);
    }

    public void guardarAgenda(String archivo) throws IOException {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(archivo))) {
            oos.writeObject(raiz);
        }
    }

    public void cargarAgenda(String archivo) throws IOException, ClassNotFoundException {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(archivo))) {
            raiz = (NodoContacto) ois.readObject();
        }
    }

    public void mostrarContactos() {
    }
}


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Services;

/**
 *
 * @author Jairo
 */
import Model.Libro;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.Stateless;

import Model.Prestamo;
import javax.ejb.EJB;

//Esta clase la utilizo como una clase de servicios que utilizare en el Bean de PrestamoLibro
@Stateless
public class ServicioPrestamo {

    @EJB
    ServicioLibro servicioLibro;

    private List<Prestamo> prestamos = new ArrayList<Prestamo>();
    private List<Prestamo> historialPrestamos = new ArrayList<Prestamo>();

    private Prestamo prestmosD;

    public List<Prestamo> listarPrestamo() {
        return prestamos;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }

    public Prestamo getPrestmosD() {
        return prestmosD;
    }

    public void setPrestmosD(Prestamo prestmosD) {
        this.prestmosD = prestmosD;
    }

    //Este me permite retornar los nombres de los libros gracias a un servicio que llamo en el Bean ServicioLibro Metodo ObtenerLibro
    //Es util para mostrar los libros registrados en un combobox para utilizarlos cuando se va a prestar un libro
    public List<String> libros() {
        List<String> libros = new ArrayList<String>();
        List<Libro> librosList = new ArrayList<Libro>();

        librosList = servicioLibro.getLibros();
        for (Libro libro : librosList) {
            if(libro.getEstadoPrestamo().equals("Disponible")){
                libros.add(libro.getNombreLibro());
            }
        }
        return libros;
    }

    //Registro el prestamo de libro
    public void crearPrestamo(Prestamo prestamo) {
        prestamo.setCodPrestamo((prestamos.size() + 1) + "");
        Libro libro = servicioLibro.ObtenerLibroPorNombre(prestamo.getNomLibro());
        prestamo.setCodLibro(libro.getISBN());
        libro.setEstadoPrestamo("No disponible");
        prestamos.add(prestamo);
    }

    //Historial prestamos
    //registro el historial de prestamos
    public void crearHistorial(Prestamo prestamo) {
        prestamo.setCodPrestamo((historialPrestamos.size() + 1) + "");
        Libro libCod = servicioLibro.ObtenerLibroPorNombre(prestamo.getNomLibro());
        prestamo.setCodLibro(libCod.getISBN());
        historialPrestamos.add(prestamo);
    }

    //Este metodo me permite retornar el historial de todos los prestamos de libros
    public List<Prestamo> listarHistorialPrestamos() {
        return historialPrestamos;
    }

    public Prestamo ObtenerPrestamo(String codRegistro) {
        Prestamo objetoPrestamo = new Prestamo();
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodLibro().equals(codRegistro)) {
                objetoPrestamo = prestamos.get(i);

            }
        }
        return objetoPrestamo;
    }
    
    public Prestamo ObtenerPrestamoCoLibro(String codLibro) {
        Prestamo objetoPrestamo = new Prestamo();
        for (int i = 0; i < prestamos.size(); i++) {
            if (prestamos.get(i).getCodLibro().equals(codLibro)) {
                objetoPrestamo = prestamos.get(i);
            }
        }
        return objetoPrestamo;
    }
    public Prestamo ObtenerHisCoLibro(String codLibro) {
        Prestamo objetoHistorial = new Prestamo();
        for (int i = 0; i < historialPrestamos.size(); i++) {
            if (historialPrestamos.get(i).getCodLibro().equals(codLibro)) {
                objetoHistorial = historialPrestamos.get(i);
            }
        }
        return objetoHistorial;
    }

    public void DevolverLibro(Libro libro, Prestamo prestamo) {
        prestamos.remove(prestamo);
        prestmosD = prestamo;
        libro.setEstadoPrestamo("Disponible");
    }

}

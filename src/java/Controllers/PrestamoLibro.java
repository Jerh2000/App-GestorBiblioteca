/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Libro;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Model.Prestamo;
import Services.ServicioLibro;
import Services.ServicioPrestamo;

@Named
@ViewScoped

/**
 *
 * @author Jairo
 */
public class PrestamoLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    ServicioPrestamo servicioPrestamo;
    ServicioLibro servicioLibro;
    private List<String> libros;

    private Prestamo prestamo;
    

    @PostConstruct
    public void init() {
        prestamo = new Prestamo(); 
    }

    //Adquiero los libros registrado para mostrar en combobox
    public List<String> getLibros() {
        libros = servicioPrestamo.libros();
        return libros;
    }

    public void setLibros(List<String> libros) {
        this.libros = libros;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }
    
    

    public void AgregarPrestamo() throws IOException {
        servicioPrestamo.crearPrestamo(prestamo);
        servicioPrestamo.crearHistorial(prestamo);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("librosprestados.xhtml");
    }
    
    
}

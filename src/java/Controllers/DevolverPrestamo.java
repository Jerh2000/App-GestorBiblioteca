/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import Model.Libro;
import Model.Prestamo;
import Services.ServicioLibro;
import Services.ServicioPrestamo;
import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Jairo
 */
@Named
@ViewScoped
public class DevolverPrestamo implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicioLibro servicioLibro;
    @EJB
    private ServicioPrestamo servicioPrestamo;

    private Libro libroD;
    private Prestamo prestamoD;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String isbn = fc.getExternalContext().getRequestParameterMap().get("ISBN");
        libroD = servicioLibro.ObtenerLibroPorISBN(isbn);
        
        FacesContext fcp = FacesContext.getCurrentInstance();
        String isbnp = fcp.getExternalContext().getRequestParameterMap().get("ISBN");
        prestamoD = servicioPrestamo.ObtenerPrestamo(isbnp);
    }

    public ServicioLibro getServicioLibro() {
        return servicioLibro;
    }

    public void setServicioLibro(ServicioLibro servicioLibro) {
        this.servicioLibro = servicioLibro;
    }

    public Libro getLibroD() {
        return libroD;
    }

    public void setLibroD(Libro libroD) {
        this.libroD = libroD;
    }

    public Prestamo getPrestamoD() {
        return prestamoD;
    }

    public void setPrestamoD(Prestamo prestamoD) {
        this.prestamoD = prestamoD;
    }
    
    

    public void Devolver() throws IOException {
        servicioPrestamo.DevolverLibro(libroD,prestamoD);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("librosprestados.xhtml");
    }
}

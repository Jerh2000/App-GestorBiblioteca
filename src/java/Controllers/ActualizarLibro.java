package Controllers;

import java.io.IOException;
import java.io.Serializable;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Model.Libro;
import Model.Prestamo;
import Services.ServicioLibro;
import Services.ServicioPrestamo;


@Named
@ViewScoped
public class ActualizarLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicioLibro servicioLibro;
    @EJB
    private ServicioPrestamo servicioPrestamo;
    
    private Libro libroA;
    private Prestamo prestamo;
    private Prestamo historial;

    @PostConstruct
    public void init() {
        FacesContext fc = FacesContext.getCurrentInstance();
        String codRegistro = fc.getExternalContext().getRequestParameterMap().get("codRegistro");
        libroA = servicioLibro.ObtenerLibro(codRegistro);
        prestamo = servicioPrestamo.ObtenerPrestamoCoLibro(libroA.getISBN());
        historial = servicioPrestamo.ObtenerHisCoLibro(libroA.getISBN());
    }

    public void ActualizarLibro() throws IOException {
            servicioLibro.Actualizar(libroA,prestamo,historial);
    }

    public Libro getLibroA() {
        return libroA;
    }

    public void setLibroA(Libro libroA) {
        this.libroA = libroA;
    }

}

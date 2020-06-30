package Controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Model.Libro;
import Services.ServicioLibro;

@Named
@ViewScoped
public class ActualizarLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicioLibro servicioLibro;

    private Libro libroA;
    
    @PostConstruct
    public void init(){
        FacesContext fc = FacesContext.getCurrentInstance();
        String codRegistro = fc.getExternalContext().getRequestParameterMap().get("codRegistro");
        libroA = servicioLibro.ObtenerLibro(codRegistro); 
    }

    public void ActualizarLibro() throws IOException{
        servicioLibro.Actualizar(libroA);
    }

    public Libro getLibroA() {
        return libroA;
    }

    public void setLibroA(Libro libroA) {
        this.libroA = libroA;
    }
    
    
}

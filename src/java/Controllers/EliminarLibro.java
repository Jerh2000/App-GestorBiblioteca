/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Model.Libro;
import Services.ServicioLibro;
import javax.inject.Inject;

/**
 *
 * @author Jairo
 */
@Named
@ViewScoped
public class EliminarLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @Inject
    private ServicioLibro servicioLibro;
    
    private Libro libroE;
    
    @PostConstruct
    public void init(){//Este inicializador me obtiene el dato o parametro de la vista index del elemento que quiero eliminar o modificar
        FacesContext fc = FacesContext.getCurrentInstance();
        String codRegistro = fc.getExternalContext().getRequestParameterMap().get("codRegistro");
        libroE = servicioLibro.ObtenerLibro(codRegistro);
    }
    
    
    //metodo que me llama a un servicio de la clase ServicioLibro la cual me permite eliminar registros de libros
    public void EliminarLibro() throws IOException{
        servicioLibro.Eliminar(libroE);
        ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
        context.redirect("index.xhtml");
    }

    public Libro getLibroE() {
        return libroE;
    }

    public void setLibroE(Libro libroE) {
        this.libroE = libroE;
    }
    
   

    
    
}

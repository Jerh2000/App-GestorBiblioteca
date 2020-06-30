/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;

/**
 *
 * @author Jairo
 */
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
import javax.faces.application.FacesMessage;

@Named
@ViewScoped
public class RegistrarLibro implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicioLibro servicioLibro;

    private Libro libro;

    @PostConstruct
    public void init() {//Me inicializa un objeto del Bean Libro con los datos ingresado en el formulario
        libro = new Libro();
    }

    public Libro getLibro() {
        return libro;
    }

    public void setLibro(Libro libro) {
        this.libro = libro;
    }

    //Este metodo me llama al metodo create de la clase ServicioLibro con el fin de guardar un nuevo registro de Libro
    public void registro() throws IOException {
        servicioLibro.create(libro);
    }
}

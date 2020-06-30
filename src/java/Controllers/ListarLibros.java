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
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import Model.Libro;
import Services.ServicioLibro;
import java.util.ArrayList;

@Named
@ViewScoped
public class ListarLibros implements Serializable {

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicioLibro servicioLibro;

    private List<Libro> listaLibros;

    
    //Me incinializa una lista de libros registrados (listaLibros)
    //Para ello llama a un metodo de la clase ServicioLibro (listarLibros();)
    //Esto permite poder listar los registros de libros
    @PostConstruct
    public void init() {
        listaLibros = servicioLibro.listarLibros();
    }

    public ServicioLibro getServicioLibro() {
        return servicioLibro;
    }

    public void setServicioLibro(ServicioLibro servicioLibro) {
        this.servicioLibro = servicioLibro;
    }

    public List<Libro> getListaLibros() {
        return listaLibros;
    }

    public void setListaLibros(List<Libro> listaLibros) {
        this.listaLibros = listaLibros;
    }

}

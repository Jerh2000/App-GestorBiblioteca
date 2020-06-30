/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controllers;


import Model.Prestamo;
import Services.ServicioPrestamo;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Jairo
 */
@Named(value = "listarHistorial")
@ViewScoped
public class ListarHistorial implements Serializable{

    private static final long serialVersionUID = 1L;

    @EJB
    private ServicioPrestamo servicioPrestamo;

    private List<Prestamo> listaHistorialPrestamos;

    
    //Me incinializa una lista de hisotorial de prestamos hechos (listaHistorialPrestamos)
    //Para ello llama a un metodo de la clase ServicioPrestamo (listarHistorialPrestamos();)
    //Esto permite poder listar los registros total de libros prestados
    @PostConstruct
    public void init() {
        listaHistorialPrestamos = servicioPrestamo.listarHistorialPrestamos();
    }

    public ServicioPrestamo getServicioPrestamo() {
        return servicioPrestamo;
    }

    public void setServicioPrestamo(ServicioPrestamo servicioPrestamo) {
        this.servicioPrestamo = servicioPrestamo;
    }

    public List<Prestamo> getListaHistorialPrestamos() {
        return listaHistorialPrestamos;
    }

    public void setListaHistorialPrestamos(List<Prestamo> listaHistorialPrestamos) {
        this.listaHistorialPrestamos = listaHistorialPrestamos;
    }

    
   
    

    
    
}

package com.Inventario.ControldeInventario.Servicios;

import com.Inventario.ControldeInventario.Entidades.Productos;
import com.Inventario.ControldeInventario.Repositorios.ProductosRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;


@Service
public class ProductosService {
   @Autowired
    private ProductosRepository productoRepository;
   
   private static final Logger log = LoggerFactory.getLogger(ProveedoresService.class);
   
   //Ahora ejecutamos los metodos
    public List<Productos> obtenerTodosLosProductos(){
        return productoRepository.findAll();
    }
    
    //saber si se inserto de manera correcta en la base de datos
    public boolean insertarProducto (Productos a){
        boolean respuesta = false;
        try{
            productoRepository.save(a); //Insert o Update a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean actualizarProducto (Productos a){
        boolean respuesta = false;
        try{
            productoRepository.save(a); //Insert o Update a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean eliminarProducto (Productos a){
        boolean respuesta = false;
        try{
            productoRepository.delete(a); //Delete a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    // Método para obtener un producto por ID
    public Optional<Productos> obtenerProductoPorId(Long id) {
        return productoRepository.findById(id);
    }
    
    @Transactional
    public boolean eliminarProductos(Long id) {
    try {
        // Lógica para eliminar el producto por ID
        productoRepository.deleteById(id);
        return true; // La eliminación fue exitosa
    } catch (EmptyResultDataAccessException e) {
        // El proveedor con el ID proporcionado no existe
        return false; // La eliminación no fue exitosa
    } catch (Exception e) {
        // Manejo de otras excepciones (registra el error si es necesario)
        log.error("Error al eliminar proveedor con ID: {}", id, e);
        return false; // La eliminación no fue exitosa
    }
    }
    
}

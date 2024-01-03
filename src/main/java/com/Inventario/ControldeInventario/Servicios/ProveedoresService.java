package com.Inventario.ControldeInventario.Servicios;

import com.Inventario.ControldeInventario.Entidades.Proveedores;
import com.Inventario.ControldeInventario.Repositorios.ProveedoresRepository;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class ProveedoresService {
    @Autowired
    private ProveedoresRepository proveedorRepository;
    
    private static final Logger log = LoggerFactory.getLogger(ProveedoresService.class);
    
    //Ahora ejecutamos los metodos
    public List<Proveedores> obtenerTodosLosProveedores(){
        return proveedorRepository.findAll();
    }
    
    //saber si se inserto de manera correcta en la base de datos
    public boolean insertarProveedor (Proveedores a){
        boolean respuesta = false;
        try{
            proveedorRepository.save(a); //Insert o Update a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean actualizarProveedor (Proveedores a){
        boolean respuesta = false;
        try{
            proveedorRepository.save(a); //Insert o Update a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    public boolean eliminarProveedor (Proveedores a){
        boolean respuesta = false;
        try{
            proveedorRepository.delete(a); //Delete a nivel de BD
            respuesta = true;
        }catch (Exception ex){
            respuesta = false;
        }
        return respuesta;
    }
    
    // Método para obtener un proveedor por ID
    public Optional<Proveedores> obtenerProveedorPorId(Long id) {
        return proveedorRepository.findById(id);
    }
    
    @Transactional
    public boolean eliminarProveedores(Long id) {
    try {
        // Lógica para eliminar el proveedor por ID
        proveedorRepository.deleteById(id);
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

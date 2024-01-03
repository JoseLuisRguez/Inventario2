package com.Inventario.ControldeInventario.Controladores;

import com.Inventario.ControldeInventario.Entidades.Proveedores;
import com.Inventario.ControldeInventario.Servicios.ProveedoresService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/proveedores")
public class ProveedoresController {
    @Autowired
    private ProveedoresService proveedorService;
    
    //Esto es un edpoint el cual cacha los datos
    @GetMapping("") 
    public List<Proveedores> getTodosLosProveedores(){
        return proveedorService.obtenerTodosLosProveedores();
    }
    
    //
    @PostMapping("")
    public List<Proveedores> postInsertarProveedor(@RequestBody Proveedores a){ //recuerda que el nombre de la variable siempre debe ser igual
        if (proveedorService.insertarProveedor(a)){
            return proveedorService.obtenerTodosLosProveedores();
        }else{
            return null;
        }
    }
    
    @PutMapping("")
    //Recibimos valores por body
    public List<Proveedores> putActualizarProveedor(@RequestBody Proveedores a){ //recuerda que el nombre de la variable siempre debe ser igual
        if (proveedorService.actualizarProveedor(a)){
            return proveedorService.obtenerTodosLosProveedores();
        }else{
            return null;
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Proveedores> obtenerProveedorPorId(@PathVariable Long id) {
    try {
        // Lógica para obtener el proveedor por ID utilizando el servicio
        Optional<Proveedores> usuarioOptional = proveedorService.obtenerProveedorPorId(id);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            // Devuelve un 404 Not Found si el proveedor no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        // Manejo de excepciones y devolución de un 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedores(@PathVariable Long id) {
        boolean eliminado = proveedorService.eliminarProveedores(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
}

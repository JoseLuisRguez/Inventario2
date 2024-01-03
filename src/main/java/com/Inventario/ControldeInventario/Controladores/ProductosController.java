package com.Inventario.ControldeInventario.Controladores;

import com.Inventario.ControldeInventario.Entidades.Productos;
import com.Inventario.ControldeInventario.Servicios.ProductosService;
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
@RequestMapping("/api/productos")
public class ProductosController {
    @Autowired
    private ProductosService productoService;
    
    //Esto es un edpoint el cual cacha los datos
    @GetMapping("") 
    public List<Productos> getTodosLosProductos(){
        return productoService.obtenerTodosLosProductos();
    }
    
    //
    @PostMapping("")
    public List<Productos> postInsertarProductos(@RequestBody Productos a){ //recuerda que el nombre de la variable siempre debe ser igual
        if (productoService.insertarProducto(a)){
            return productoService.obtenerTodosLosProductos();
        }else{
            return null;
        }
    }
    
    @PutMapping("")
    //Recibimos valores por body
    public List<Productos> putActualizarProductos(@RequestBody Productos a){ //recuerda que el nombre de la variable siempre debe ser igual
        if (productoService.actualizarProducto(a)){
            return productoService.obtenerTodosLosProductos();
        }else{
            return null;
        }
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Productos> obtenerProductosPorId(@PathVariable Long id) {
    try {
        // Lógica para obtener el producto por ID utilizando el servicio
        Optional<Productos> usuarioOptional = productoService.obtenerProductoPorId(id);

        if (usuarioOptional.isPresent()) {
            return ResponseEntity.ok(usuarioOptional.get());
        } else {
            // Devuelve un 404 Not Found si el producto no existe
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    } catch (Exception e) {
        // Manejo de excepciones y devolución de un 500 Internal Server Error
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProductos(@PathVariable Long id) {
        boolean eliminado = productoService.eliminarProductos(id);
        if (eliminado) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}

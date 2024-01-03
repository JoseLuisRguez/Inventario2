package com.Inventario.ControldeInventario.Repositorios;


import com.Inventario.ControldeInventario.Entidades.Productos;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductosRepository extends JpaRepository<Productos, Long>{
    
}

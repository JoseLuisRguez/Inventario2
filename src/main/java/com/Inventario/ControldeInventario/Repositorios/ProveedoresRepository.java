package com.Inventario.ControldeInventario.Repositorios;

import com.Inventario.ControldeInventario.Entidades.Proveedores;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProveedoresRepository extends JpaRepository<Proveedores, Long>{
    
}

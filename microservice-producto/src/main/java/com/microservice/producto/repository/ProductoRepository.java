package com.microservice.producto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.microservice.productos.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<CProducto , Long>{
    
}

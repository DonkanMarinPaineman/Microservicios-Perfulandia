package com.microservice.producto.service;

import com.microservice.producto.dto.ProductoDTO;

import java.util.List;

public interface ProductoService {

    List<ProductoDTO> listarTodos();
    ProductoDTO obtenerPorId(Long id);
    ProductoDTO guardar(ProductoDTO producto);
    ProductoDTO actualizar(Long id , ProductoDTO producto);
    void eliminar(Long id);
}

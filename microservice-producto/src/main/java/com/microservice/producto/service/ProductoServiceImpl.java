package com.microservice.producto.service;

import com.microservice.producto.dto.ProductoDTO;
import com.microservice.producto.model.Producto;
import com.microservice.producto.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    private ProductoDTO convertirADTO(Producto producto) {
        ProductoDTO dto = new ProductoDTO();
        dto.setId(producto.getId());
        dto.setNombre(producto.getNombre());
        dto.setDescripcion(producto.getDescripcion());
        dto.setPrecio(producto.getPrecio());
        dto.setStock(producto.getStock());
        dto.setMarca(producto.getMarca());
        return dto;
    }

    private Producto convertirAEntidad(ProductoDTO dto) {
        Producto producto = new Producto();
        producto.setId(dto.getId());
        producto.setNombre(dto.getNombre());
        producto.setDescripcion(dto.getDescripcion());
        producto.setPrecio(dto.getPrecio());
        producto.setStock(dto.getStock());
        producto.setMarca(dto.getMarca());
        return producto;
    }

    @Override
    public List<ProductoDTO> buscarTodos() {
        return productoRepository.findAll()
                .stream()
                .map(this::convertirADTO)
                .collect(Collectors.toList());
    }

    @Override
    public ProductoDTO buscarPorId(Long id) {
        return productoRepository.findById(id)
                .map(this::convertirADTO)
                .orElse(null);
    }

    @Override
    public void guardar(ProductoDTO dto) {
        Producto producto = convertirAEntidad(dto);
        productoRepository.save(producto);
    }

    @Override
    public ProductoDTO actualizar(Long id, ProductoDTO dto) {
        Producto productoExistente = productoRepository.findById(id).orElse(null);
        if (productoExistente != null) {
            productoExistente.setNombre(dto.getNombre());
            productoExistente.setDescripcion(dto.getDescripcion());
            productoExistente.setPrecio(dto.getPrecio());
            productoExistente.setStock(dto.getStock());
            productoExistente.setMarca(dto.getMarca());
            return convertirADTO(productoRepository.save(productoExistente));
        }
        return null;
    }

    @Override
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}

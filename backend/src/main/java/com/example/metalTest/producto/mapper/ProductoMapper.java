package com.example.metalTest.producto.mapper;

import com.example.metalTest.producto.controller.request.ProductoRequest;
import com.example.metalTest.producto.domain.Producto;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface ProductoMapper {
    Producto productoRequestToProducto(ProductoRequest productoRequest);
}

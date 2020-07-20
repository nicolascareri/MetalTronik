package com.example.metalTest.producto.service;


import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.producto.controller.request.ProductoRequest;
import com.example.metalTest.producto.domain.Producto;

import java.util.List;

public interface ProductoService {
    List<Producto> getAll();

    Producto create(ProductoRequest productoRequest);

    Producto update(ProductoRequest productoRequest, Integer id) throws ValidateFieldException;
}

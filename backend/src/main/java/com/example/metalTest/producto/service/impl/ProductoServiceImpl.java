package com.example.metalTest.producto.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.producto.controller.request.ProductoRequest;
import com.example.metalTest.producto.domain.Producto;
import com.example.metalTest.producto.mapper.ProductoMapper;
import com.example.metalTest.producto.repository.ProductoRepository;
import com.example.metalTest.producto.service.ProductoService;
import com.example.metalTest.repuestomaquina.repository.RepuestoMaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoServiceImpl implements ProductoService {

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    RepuestoMaquinaRepository repuestoMaquinaRepository;

    @Autowired
    ProductoMapper productoMapper;

    @Override
    public List<Producto> getAll() {
        return productoRepository.findAll();
    }

    @Override
    public Producto create(ProductoRequest productoRequest) {
        Producto producto = productoMapper.productoRequestToProducto(productoRequest);
        producto.setRepuesto(repuestoMaquinaRepository.findById(productoRequest.getRepuesto_cod()).get());
        return productoRepository.save(producto);
    }

    @Override
    public Producto update(ProductoRequest productoRequest, Integer id) throws ValidateFieldException {

        Optional<Producto> update = productoRepository.findById(id);

        if(!update.isPresent()){
            throw new ValidateFieldException("El producto que desea acceder no existe", "id", String.valueOf(id));
        }

        Producto producto = productoMapper.productoRequestToProducto(productoRequest);

        producto.setRepuesto(repuestoMaquinaRepository.findById(productoRequest.getRepuesto_cod()).get());

        producto.setId(id);

        return productoRepository.save(producto);

    }
}

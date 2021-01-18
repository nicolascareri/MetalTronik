package com.example.metalTest.usuarios.rol.service;

import com.example.metalTest.usuarios.rol.controller.request.RolRequest;
import com.example.metalTest.usuarios.rol.domain.Rol;
import org.springframework.stereotype.Service;

public interface RolService {
    Rol create(RolRequest rolRequest);
}

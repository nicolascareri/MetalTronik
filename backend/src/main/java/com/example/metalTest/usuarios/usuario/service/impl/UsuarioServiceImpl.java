package com.example.metalTest.usuarios.usuario.service.impl;

import com.example.metalTest.apiError.exception.ValidateFieldException;
import com.example.metalTest.security.jwt.JwtDto;
import com.example.metalTest.security.jwt.JwtProvider;
import com.example.metalTest.tipo.service.TipoService;
import com.example.metalTest.usuarios.credenciales.credencial.domain.Credencial;
import com.example.metalTest.usuarios.credenciales.credencial.repository.CredencialRepository;
import com.example.metalTest.usuarios.credenciales.rol.domain.Rol;
import com.example.metalTest.usuarios.credenciales.rol.repository.RolRepository;
import com.example.metalTest.usuarios.personal.controller.request.LoginRequest;
import com.example.metalTest.usuarios.personal.domain.Personal;
import com.example.metalTest.usuarios.personal.mapper.PersonalMapper;
import com.example.metalTest.usuarios.personal.repository.PersonalRepository;
import com.example.metalTest.usuarios.usuario.controller.request.UsuarioRequest;
import com.example.metalTest.usuarios.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    @Autowired
    TipoService tipoService;
    @Autowired
    PersonalRepository personalRepository;
    @Autowired
    PersonalMapper personalMapper;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    RolRepository rolRepository;
    @Autowired
    CredencialRepository credencialRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    JwtProvider jwtProvider;

    @Override
    public Personal create(UsuarioRequest usuarioRequest, Integer id) throws ValidateFieldException {
        //Busco el personal por id en la tabla personal
        Personal personal = personalRepository.findById(id).get();
        //busco el rol en la db
        Rol rol = rolRepository.findById(usuarioRequest.getRol()).get();
        //creo una credencial(nombre usuario y pass encriptada)
        Credencial credencial = new Credencial(usuarioRequest.getNombre_usuario(), passwordEncoder.encode(usuarioRequest.getContrasenia()), rol);
        //cuando seteo la credencial se guarda sola en la tabla Credencial
        personal.setCredencial(credencialRepository.save(credencial));
        personalRepository.save(personal);
        return personal;
    }

    @Override
    public List<Personal> getAll() {
        return personalRepository.findAllByCredencialIsNotNull();
    }

    @Override
    public Personal getById(Integer id) {
        return personalRepository.findById(id).get();
    }

    @Override
    public JwtDto login(LoginRequest loginRequest) {
        Rol rol = personalRepository.getByNombreUsuario(loginRequest.getNombre_usuario()).getCredencial().getRol();
       JwtDto jwtDto = getJwtDto(loginRequest, rol);

        return jwtDto;
    }

    private JwtDto getJwtDto(LoginRequest loginRequest, Rol rol){
        //le digo al manager de autenticacion que cree una nueva sesion
        Authentication authentication=
                authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getNombre_usuario(), loginRequest.getPassword()));
        //Seteo la autentificacion con el authentication
        SecurityContextHolder.getContext().setAuthentication(authentication);
        //Genero el toquen
        String jwt = jwtProvider.generateToken(authentication);
        //creo un usuario/user details
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        //creo un jwt data transfer object(el cifrado)
        JwtDto jwtDto = new JwtDto(jwt, userDetails.getUsername(), rol, userDetails.getAuthorities());
        return jwtDto;
    }
}

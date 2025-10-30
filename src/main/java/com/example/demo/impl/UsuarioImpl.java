package com.example.demo.impl;


import com.example.demo.entity.enums.Rol;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.mapper.UsuarioMapper;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.Sha256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioImpl implements UsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioMapper usuarioMapper;


    @Override
    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(usuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UsuarioDTO obtenerPorId(Long id) {
        return usuarioRepository.findById(id)
                .map(usuarioMapper::toDto)
                .orElse(null);
    }

    @Override
    public UsuarioDTO crear(UsuarioDTO usuarioDTO) {
        Usuario usuario = usuarioMapper.toEntity(usuarioDTO);
        if (usuario.getPassword() != null) {
            usuario.setPassword(Sha256Util.hash(usuario.getPassword()));
        }
        if (usuario.getRol() == null) {
            usuario.setRol(Rol.USUARIO);
        }
        Usuario guardado = usuarioRepository.save(usuario);
        return usuarioMapper.toDto(guardado);
    }

    @Override
    public void eliminar(Long id) {
        usuarioRepository.deleteById(id);
    }

    public UsuarioDTO obtenerPorEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .map(usuarioMapper::toDto)
                .orElse(null);
    }
    public Usuario obtenerPorEmailUser(String email) {
        return usuarioRepository.findByEmail(email)
                .orElse(null);
    }
}

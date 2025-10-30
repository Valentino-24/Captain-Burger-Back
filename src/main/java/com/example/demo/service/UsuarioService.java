package com.example.demo.service;

import com.example.demo.entity.Usuario;
import com.example.demo.entity.dto.UsuarioDTO;

import java.util.List;

public interface UsuarioService {
    List<UsuarioDTO> listarTodos();
    UsuarioDTO obtenerPorId(Long id);
    UsuarioDTO obtenerPorEmail(String email);
    Usuario obtenerPorEmailUser(String email);
    UsuarioDTO crear(UsuarioDTO usuarioDTO);
    void eliminar(Long id);
}

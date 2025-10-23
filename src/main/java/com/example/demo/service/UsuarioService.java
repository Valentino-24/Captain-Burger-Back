package com.example.demo.service;

import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.mapper.UsuarioMapper;
import com.example.demo.repository.UsuarioRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepositorio usuarioRepository;

    public UsuarioService(UsuarioRepositorio usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDTO)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));
        return UsuarioMapper.toDTO(usuario);
    }

    public UsuarioDTO crear(Usuario usuario) {
        Usuario guardado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(guardado);
    }

    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));

        usuario.setNombre(dto.getNombre());
        usuario.setApellido(dto.getApellido());
        usuario.setMail(dto.getMail());
        usuario.setCelular(dto.getCelular());
        usuario.setRol(dto.getRol());

        Usuario actualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDTO(actualizado);
    }

    public void borrar(Long id) {
        if (!usuarioRepository.existsById(id)) {
            throw new NoSuchElementException("Usuario no encontrado con id: " + id);
        }
        usuarioRepository.deleteById(id);
    }

    public void cambiarContrasena(Long id, String nuevaContrasena) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));
        usuario.setContraseña(nuevaContrasena);
        usuarioRepository.save(usuario);
    }

    public UsuarioDTO buscarPorMail(String mail) {
        Usuario usuario = usuarioRepository.findByMail(mail)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con mail: " + mail));
        return UsuarioMapper.toDTO(usuario);
    }
}

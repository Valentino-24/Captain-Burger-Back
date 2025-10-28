package com.example.demo.service;

import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.mapper.UsuarioMapper;
import com.example.demo.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }


    public List<UsuarioDTO> listarTodos() {
        return usuarioRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDto)
                .collect(Collectors.toList());
    }

    public UsuarioDTO obtenerPorId(Long id) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));
        return UsuarioMapper.toDto(usuario);
    }

    public UsuarioDTO crear(UsuarioDTO usuarioDto) {
        System.out.println("DTO recibido: " + usuarioDto);
        System.out.println("DTO.password = " + usuarioDto.getPassword());

        // mapear manualmente y comprobar
        Usuario u = new Usuario();
        u.setNombre(usuarioDto.getNombre());
        u.setEmail(usuarioDto.getEmail());
        u.setPassword(usuarioDto.getPassword()); // <- fijate qué imprime esto
        System.out.println("Entidad antes de save, password = " + u.getPassword());

        Usuario guardado = usuarioRepository.save(u); // aquí falla si password == null
        return UsuarioMapper.toDto(guardado);
    }


    public UsuarioDTO actualizar(Long id, UsuarioDTO dto) {
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Usuario no encontrado con id: " + id));

        usuario.setNombre(dto.getNombre());
        usuario.setEmail(dto.getEmail());
        usuario.setRol(dto.getRol());

        Usuario actualizado = usuarioRepository.save(usuario);
        return UsuarioMapper.toDto(actualizado);
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
        usuario.setPassword(nuevaContrasena);
        usuarioRepository.save(usuario);
    }

    public Usuario obtenerPorEmail(String mail) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(mail);
        return usuarioOpt.orElse(null);
    }
}

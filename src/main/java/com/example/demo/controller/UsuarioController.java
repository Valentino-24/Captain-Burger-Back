package com.example.demo.controller;

import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.service.UsuarioService;
import com.example.demo.utils.Sha256Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.entity.mapper.UsuarioMapper;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    @Autowired
    private UsuarioMapper usuarioMapper;

    @GetMapping
    public List<UsuarioDTO> listar() {
        return usuarioService.listarTodos();
    }
    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario request){
        Usuario usuario = usuarioService.obtenerPorEmailUser(request.getEmail());
        if (usuario == null) {
            return ResponseEntity.status(401).body(null);
        }
        if (!usuario.getPassword().equals(request.getPassword())) {

            return ResponseEntity.status(401).body(null);
        }
        UsuarioDTO dto = usuarioMapper.toDto(usuario);
        dto.setPassword(null);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public UsuarioDTO obtenerPorId(@PathVariable Long id){
        return usuarioService.obtenerPorId(id);
    }

    //Busqueda: http://localhost:8080/usuarios/search?email="email"
    @GetMapping("/search")
    public UsuarioDTO buscarPorMail(@RequestParam String email) {
        return usuarioService.obtenerPorEmail(email);
    }

    @PostMapping
    public UsuarioDTO crear(@RequestBody UsuarioDTO usuarioDTO) {
        return usuarioService.crear(usuarioDTO);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        usuarioService.eliminar(id);
    }
}

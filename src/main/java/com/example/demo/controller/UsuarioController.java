package com.example.demo.controller;

import com.example.demo.entity.dto.UsuarioDTO;
import com.example.demo.entity.Usuario;
import com.example.demo.entity.mapper.UsuarioMapper;
import com.example.demo.service.UsuarioService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public ResponseEntity<List<UsuarioDTO>> listar() {
        List<UsuarioDTO> lista = usuarioService.listarTodos();
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/login")
    public ResponseEntity<UsuarioDTO> login(@RequestBody Usuario request) {
        Usuario usuario = usuarioService.obtenerPorEmail(request.getEmail());
        if (usuario == null) {
            return ResponseEntity.status(401).body(null);
        }

        UsuarioDTO dto = UsuarioMapper.toDto(usuario);
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> obtenerPorId(@PathVariable Long id) {
        try {
            UsuarioDTO dto = usuarioService.obtenerPorId(id);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
/*
    @GetMapping("/search")
    public ResponseEntity<UsuarioDTO> buscarPorMail(@RequestParam String mail) {
        try {
            UsuarioDTO dto = usuarioService.obtenerPorEmail(mail);
            return ResponseEntity.ok(dto);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
*/
    @PostMapping
    public ResponseEntity<UsuarioDTO> crear(@RequestBody UsuarioDTO usuarioDTO) {
        UsuarioDTO creado = usuarioService.crear(usuarioDTO);
        System.out.println("Usuario Creado");
        return ResponseEntity.status(HttpStatus.CREATED).body(creado);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UsuarioDTO> actualizar(@PathVariable Long id, @RequestBody UsuarioDTO dto) {
        try {
            UsuarioDTO actualizado = usuarioService.actualizar(id, dto);
            return ResponseEntity.ok(actualizado);
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> borrar(@PathVariable Long id) {
        try {
            usuarioService.borrar(id);
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/{id}/password")
    public ResponseEntity<Void> cambiarContrasena(@PathVariable Long id, @RequestBody CambioContrasenaRequest req) {
        try {
            usuarioService.cambiarContrasena(id, req.getNuevaContrasena());
            return ResponseEntity.noContent().build();
        } catch (NoSuchElementException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    public static class CambioContrasenaRequest {
        private String nuevaContrasena;
        public String getNuevaContrasena() { return nuevaContrasena; }
        public void setNuevaContrasena(String nuevaContrasena) { this.nuevaContrasena = nuevaContrasena; }
    }
}

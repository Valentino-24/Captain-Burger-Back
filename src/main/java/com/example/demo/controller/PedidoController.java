package com.example.demo.controller;

import com.example.demo.entity.dto.PedidoDTO;
import com.example.demo.entity.dto.ProductoDTO;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/pedidos")
@CrossOrigin(origins = "http://localhost:5173")
public class PedidoController {

    @Autowired
    private PedidoService pedidoService;

    @GetMapping
    public ResponseEntity<List<PedidoDTO>> listar() {
        return ResponseEntity.ok(pedidoService.listarTodos());
    }

    @PostMapping
    public ResponseEntity<PedidoDTO> crear(@RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO creado = pedidoService.crear(pedidoDTO);
        if (creado == null) return ResponseEntity.badRequest().build();
        return ResponseEntity.created(URI.create("/pedidos/" + creado.getId())).body(creado);
    }

    @GetMapping("/usuario/{usuarioId}")
    public ResponseEntity<List<PedidoDTO>> listarPorUsuario(@PathVariable Long usuarioId) {
        return ResponseEntity.ok(pedidoService.listarPorUsuario(usuarioId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PedidoDTO> obtener(@PathVariable Long id) {
        PedidoDTO dto = pedidoService.obtenerPorId(id);
        if (dto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PedidoDTO> actualizar(@PathVariable Long id, @RequestBody PedidoDTO pedidoDTO) {
        PedidoDTO actualizado = pedidoService.actualizar(id, pedidoDTO);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @PatchMapping("/{id}/estado")
    public ResponseEntity<PedidoDTO> actualizarEstado(@PathVariable Long id, @RequestBody Map<String, String> body) {
        String nuevoEstado = body.get("estado");
        if (nuevoEstado == null) return ResponseEntity.badRequest().build();
        PedidoDTO actualizado = pedidoService.actualizarEstado(id, nuevoEstado);
        if (actualizado == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(actualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        pedidoService.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}

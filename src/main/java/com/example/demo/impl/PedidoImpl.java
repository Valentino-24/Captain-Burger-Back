package com.example.demo.impl;

import com.example.demo.entity.*;
import com.example.demo.entity.dto.PedidoDTO;
import com.example.demo.entity.mapper.PedidoMapper;
import com.example.demo.repository.PedidoRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.repository.UsuarioRepository;
import com.example.demo.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PedidoImpl implements PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private PedidoMapper pedidoMapper;

    @Override
    public List<PedidoDTO> listarTodos() {
        return pedidoRepository.findAll()
                .stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoDTO actualizarEstado(Long id, String estado) {
        Optional<Pedido> opt = pedidoRepository.findById(id);
        if (!opt.isPresent()) return null;
        Pedido pedido = opt.get();
        pedido.setEstado(estado);
        Pedido saved = pedidoRepository.save(pedido);
        return pedidoMapper.toDto(saved);
    }


    @Override
    @Transactional
    public PedidoDTO crear(PedidoDTO pedidoDTO) {
        Pedido pedido = pedidoMapper.toEntity(pedidoDTO);
        pedido.setFecha(LocalDateTime.now());

        // set usuario si vino usuarioId
        if (pedidoDTO.getUsuarioId() != null) {
            Optional<Usuario> u = usuarioRepository.findById(pedidoDTO.getUsuarioId());
            u.ifPresent(pedido::setUsuario);
        }

        // Vincular productos en detalles por productoId
        if (pedidoDTO.getDetalles() != null) {
            List<DetallePedido> detalles = pedidoDTO.getDetalles().stream().map(dto -> {
                DetallePedido d = pedidoMapper.detalleToEntity(dto);
                if (dto.getProductoId() != null) {
                    productoRepository.findById(dto.getProductoId()).ifPresent(d::setProducto);
                }
                d.setPedido(pedido);
                return d;
            }).collect(Collectors.toList());
            pedido.setDetalles(detalles);
        }

        Pedido guardado = pedidoRepository.save(pedido);
        return pedidoMapper.toDto(guardado);
    }

    @Override
    public PedidoDTO obtenerPorId(Long id) {
        return pedidoRepository.findById(id)
                .map(pedidoMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<PedidoDTO> listarPorUsuario(Long usuarioId) {
        return pedidoRepository.findByUsuario_Id(usuarioId)
                .stream()
                .map(pedidoMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public PedidoDTO actualizar(Long id, PedidoDTO pedidoDTO) {
        Optional<Pedido> opt = pedidoRepository.findById(id);
        if (!opt.isPresent()) return null;
        Pedido pedido = opt.get();

        pedido.setTelefono(pedidoDTO.getTelefono());
        pedido.setDireccion(pedidoDTO.getDireccion());
        pedido.setMetodoPago(pedidoDTO.getMetodoPago());
        pedido.setNotas(pedidoDTO.getNotas());
        pedido.setFecha(pedidoDTO.getFecha());
        pedido.setTotal(pedidoDTO.getTotal());
        pedido.setEstado(pedidoDTO.getEstado());

        // actualizar usuario si viene
        if (pedidoDTO.getUsuarioId() != null) {
            usuarioRepository.findById(pedidoDTO.getUsuarioId()).ifPresent(pedido::setUsuario);
        }

        // Reemplazamos detalles: clear + agregar nuevos
        if (pedido.getDetalles() != null) {
            pedido.getDetalles().clear();
        }
        if (pedidoDTO.getDetalles() != null) {
            List<DetallePedido> nuevos = pedidoDTO.getDetalles().stream().map(dto -> {
                DetallePedido d = pedidoMapper.detalleToEntity(dto);
                if (dto.getProductoId() != null) {
                    productoRepository.findById(dto.getProductoId()).ifPresent(d::setProducto);
                }
                d.setPedido(pedido);
                return d;
            }).collect(Collectors.toList());
            pedido.setDetalles(nuevos);
        }

        Pedido actualizado = pedidoRepository.save(pedido);
        return pedidoMapper.toDto(actualizado);
    }

    @Override
    public void eliminar(Long id) {
        pedidoRepository.deleteById(id);
    }
}

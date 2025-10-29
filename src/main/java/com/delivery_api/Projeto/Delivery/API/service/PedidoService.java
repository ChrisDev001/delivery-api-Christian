package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Pedido;
import com.delivery_api.Projeto.Delivery.API.repository.PedidoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    public Pedido cadastrar(Pedido pedido) {
        if (pedido.getCliente() == null || pedido.getRestaurante() == null || pedido.getProdutos().isEmpty()) {
            throw new IllegalArgumentException("Cliente, restaurante e produtos são obrigatórios");
        }
        pedido.setDataHora(LocalDateTime.now());
        pedido.setStatus("PENDENTE");
        pedido.setValorTotal(calcularValorTotal(pedido));
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> listarTodos() {
        return pedidoRepository.findAll();
    }

    public Optional<Pedido> buscarPorId(Long id) {
        return pedidoRepository.findById(id);
    }

    public Pedido atualizarStatus(Long id, String status) {
        Pedido pedido = pedidoRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pedido não encontrado"));
        pedido.setStatus(status);
        return pedidoRepository.save(pedido);
    }

    public List<Pedido> buscarPorStatus(String status) {
        return pedidoRepository.findByStatus(status);
    }

    public List<Pedido> buscarPorCliente(Long clienteId) {
        return pedidoRepository.findByClienteId(clienteId);
    }

    public List<Pedido> buscarPorRestaurante(Long restauranteId) {
        return pedidoRepository.findByRestauranteId(restauranteId);
    }

    private Double calcularValorTotal(Pedido pedido) {
        return pedido.getProdutos().stream()
                .mapToDouble(p -> p.getPreco())
                .sum();
    }
}

package com.delivery_api.Projeto.Delivery.API.service;

import com.delivery_api.Projeto.Delivery.API.entity.Restaurante;
import com.delivery_api.Projeto.Delivery.API.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RestauranteService {

    @Autowired
    private RestauranteRepository restauranteRepository;

    public Restaurante cadastrar(Restaurante restaurante) {
        if (restaurante.getNome() == null || restaurante.getNome().isEmpty()) {
            throw new IllegalArgumentException("Nome do restaurante é obrigatório");
        }
        restaurante.setAtivo(true);
        return restauranteRepository.save(restaurante);
    }

    public List<Restaurante> listarAtivos() {
        return restauranteRepository.findByAtivoTrue();
    }

    public Optional<Restaurante> buscarPorId(Long id) {
        return restauranteRepository.findById(id);
    }

    public Restaurante atualizar(Long id, Restaurante restaurante) {
        Restaurante existente = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        existente.setNome(restaurante.getNome());
        existente.setEmail(restaurante.getEmail());
        existente.setCategoria(restaurante.getCategoria());
        return restauranteRepository.save(existente);
    }

    public void inativar(Long id) {
        Restaurante restaurante = restauranteRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Restaurante não encontrado"));
        restaurante.setAtivo(false);
        restauranteRepository.save(restaurante);
    }

    public List<Restaurante> buscarPorNome(String nome) {
        return restauranteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public Optional<Restaurante> buscarPorEmail(String email) {
        return restauranteRepository.findByEmail(email);
    }
}

package com.stackburgers.service;

import com.stackburgers.dto.ProdutoDTO;
import com.stackburgers.model.Bebida;
import com.stackburgers.repository.BebidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BebidaService {

    @Autowired
    private BebidaRepository bebidaRepository;

    public List<Bebida> listarTodas() {
        return bebidaRepository.findAll();
    }

    public List<Bebida> listarDisponiveis() {
        return bebidaRepository.findByDisponivelTrue();
    }

    public Optional<Bebida> buscarPorId(Long id) {
        return bebidaRepository.findById(id);
    }

    public Bebida cadastrar(ProdutoDTO.BebidaRequest request) {
        Bebida bebida = new Bebida();
        bebida.setNome(request.getNome());
        bebida.setDescricao(request.getDescricao());
        bebida.setPreco(request.getPreco());
        bebida.setTipo(request.getTipo());
        bebida.setVolumeMl(request.getVolumeMl());
        bebida.setAlcolica(request.getAlcolica());
        bebida.setGelada(request.getGelada());
        bebida.setDisponivel(request.getDisponivel());
        return bebidaRepository.save(bebida);
    }

    public Optional<Bebida> atualizar(Long id, ProdutoDTO.BebidaRequest request) {
        return bebidaRepository.findById(id).map(bebida -> {
            bebida.setNome(request.getNome());
            bebida.setDescricao(request.getDescricao());
            bebida.setPreco(request.getPreco());
            bebida.setTipo(request.getTipo());
            bebida.setVolumeMl(request.getVolumeMl());
            bebida.setAlcolica(request.getAlcolica());
            bebida.setGelada(request.getGelada());
            bebida.setDisponivel(request.getDisponivel());
            return bebidaRepository.save(bebida);
        });
    }

    public boolean remover(Long id) {
        return bebidaRepository.findById(id).map(b -> {
            bebidaRepository.delete(b);
            return true;
        }).orElse(false);
    }

    public Optional<Bebida> alternarDisponibilidade(Long id) {
        return bebidaRepository.findById(id).map(b -> {
            b.setDisponivel(!b.getDisponivel());
            return bebidaRepository.save(b);
        });
    }
}

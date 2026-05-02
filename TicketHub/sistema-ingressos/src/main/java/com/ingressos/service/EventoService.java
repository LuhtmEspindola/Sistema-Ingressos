package com.ingressos.service;

import com.ingressos.model.Evento;
import com.ingressos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventoService {

    @Autowired
    private EventoRepository eventoRepository;

    public List<Evento> listarTodos() {
        return eventoRepository.findAll();
    }

    public Optional<Evento> buscarPorId(String id) {
        return eventoRepository.findById(id);
    }

    public Evento inserir(Evento evento) {
        if (evento.getId() != null && evento.getId().isBlank()) evento.setId(null);
        return eventoRepository.save(evento);
    }

    public Evento atualizar(Evento evento) {
        return eventoRepository.save(evento);
    }

    public void remover(String id) {
        eventoRepository.deleteById(id);
    }

    public List<Evento> buscarPorNome(String nome) {
        return eventoRepository.findByNomeContainingIgnoreCase(nome);
    }

    public void registrarVenda(String eventoId) {
        eventoRepository.findById(eventoId).ifPresent(e -> {
            e.registrarVenda();
            eventoRepository.save(e);
        });
    }

    public void cancelarVenda(String eventoId) {
        eventoRepository.findById(eventoId).ifPresent(e -> {
            e.cancelarVenda();
            eventoRepository.save(e);
        });
    }
}

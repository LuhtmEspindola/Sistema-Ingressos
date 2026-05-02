package com.ingressos.service;

import com.ingressos.model.*;
import com.ingressos.repository.IngressoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class IngressoService {

    @Autowired
    private IngressoRepository ingressoRepository;

    @Autowired
    private EventoService eventoService;

    public List<Ingresso> listarTodos() {
        return ingressoRepository.findAll();
    }

    public Optional<Ingresso> buscarPorId(String id) {
        return ingressoRepository.findById(id);
    }

    public List<Ingresso> listarPorEvento(String eventoId) {
        return ingressoRepository.findByEventoId(eventoId);
    }

    public List<Ingresso> listarPorTipo(String tipo) {
        return ingressoRepository.findByTipo(tipo);
    }

    /**
     * Fábrica polimórfica: instancia o subtipo correto de Ingresso
     * conforme o tipo selecionado pelo usuário, delega calcularValor()
     * a cada subclasse e persiste no MongoDB.
     */
    public Ingresso emitir(String eventoId, String nomeComprador, String cpfComprador,
                           String tipo, String motivoMeia) {

        Evento evento = eventoService.buscarPorId(eventoId)
            .orElseThrow(() -> new IllegalArgumentException("Evento nao encontrado."));

        if (!evento.temVagasDisponiveis()) {
            throw new IllegalStateException("Evento esgotado. Nao ha vagas disponiveis.");
        }

        Ingresso ingresso = switch (tipo.toUpperCase()) {
            case "VIP"    -> new IngressoVIP(eventoId, evento.getNome(), nomeComprador,
                                             cpfComprador, evento.getLocal(),
                                             evento.getData(), evento.getPrecoBase());
            case "MEIA"   -> new IngressoMeia(eventoId, evento.getNome(), nomeComprador,
                                              cpfComprador, evento.getLocal(),
                                              evento.getData(), evento.getPrecoBase(),
                                              motivoMeia != null ? motivoMeia : "Nao informado");
            default       -> new IngressoNormal(eventoId, evento.getNome(), nomeComprador,
                                                cpfComprador, evento.getLocal(),
                                                evento.getData(), evento.getPrecoBase());
        };

        // Polimorfismo em ação: calcularValor() chama a implementação correta
        ingresso.calcularValor();

        eventoService.registrarVenda(eventoId);
        return ingressoRepository.save(ingresso);
    }

    public void cancelar(String ingressoId) {
        ingressoRepository.findById(ingressoId).ifPresent(ingresso -> {
            eventoService.cancelarVenda(ingresso.getEventoId());
            ingressoRepository.deleteById(ingressoId);
        });
    }
}

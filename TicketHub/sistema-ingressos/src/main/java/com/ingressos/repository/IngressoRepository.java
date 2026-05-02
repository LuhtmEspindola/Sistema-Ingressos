package com.ingressos.repository;

import com.ingressos.model.Ingresso;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface IngressoRepository extends MongoRepository<Ingresso, String> {
    List<Ingresso> findByEventoId(String eventoId);
    List<Ingresso> findByTipo(String tipo);
    List<Ingresso> findByCpfComprador(String cpf);
}

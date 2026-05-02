package com.ingressos.config;

import com.ingressos.model.Evento;
import com.ingressos.repository.EventoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private EventoRepository eventoRepository;

    @Override
    public void run(String... args) {
        if (eventoRepository.count() == 0) {
            eventoRepository.save(new Evento("Show Rock Nacional", "Arena Anhembi - SP",
                "15/08/2025", "Grande show com as maiores bandas do rock nacional.", 120.00, 5000));

            eventoRepository.save(new Evento("Festival de Jazz", "Teatro Municipal - RJ",
                "22/08/2025", "Uma noite especial com os melhores artistas de jazz do Brasil.", 80.00, 800));

            eventoRepository.save(new Evento("Peça Teatral: Hamlet", "Teatro Carlos Gomes - SP",
                "30/08/2025", "Classico de Shakespeare em producao nacional premiada.", 60.00, 400));

            eventoRepository.save(new Evento("Corrida 10K da Cidade", "Parque Ibirapuera - SP",
                "05/09/2025", "Corrida de rua aberta ao publico em circuito no parque.", 40.00, 2000));

            eventoRepository.save(new Evento("Stand-Up Comedy Night", "Centro de Convencoes - BH",
                "12/09/2025", "Os melhores comicos do pais em uma noite imperdivel.", 70.00, 1200));

            System.out.println("[DataInitializer] 5 eventos de exemplo inseridos.");
        }
    }
}

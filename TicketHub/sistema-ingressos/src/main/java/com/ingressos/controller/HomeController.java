package com.ingressos.controller;

import com.ingressos.service.EventoService;
import com.ingressos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    private IngressoService ingressoService;

    @GetMapping("/")
    public String index(Model model) {
        var ingressos = ingressoService.listarTodos();
        long totalVIP    = ingressos.stream().filter(i -> "VIP".equals(i.getTipo())).count();
        long totalNormal = ingressos.stream().filter(i -> "NORMAL".equals(i.getTipo())).count();
        long totalMeia   = ingressos.stream().filter(i -> "MEIA".equals(i.getTipo())).count();
        double totalArrecadado = ingressos.stream().mapToDouble(i -> i.calcularValor()).sum();

        model.addAttribute("totalEventos",    eventoService.listarTodos().size());
        model.addAttribute("totalIngressos",  ingressos.size());
        model.addAttribute("totalVIP",        totalVIP);
        model.addAttribute("totalNormal",     totalNormal);
        model.addAttribute("totalMeia",       totalMeia);
        model.addAttribute("totalArrecadado", String.format("%.2f", totalArrecadado));
        return "index";
    }
}

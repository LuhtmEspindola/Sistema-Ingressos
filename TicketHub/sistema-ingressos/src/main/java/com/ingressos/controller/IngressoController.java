package com.ingressos.controller;

import com.ingressos.model.Ingresso;
import com.ingressos.service.EventoService;
import com.ingressos.service.IngressoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ingressos")
public class IngressoController {

    @Autowired
    private IngressoService ingressoService;

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String tipo) {
        if (tipo != null && !tipo.isBlank()) {
            model.addAttribute("ingressos", ingressoService.listarPorTipo(tipo.toUpperCase()));
            model.addAttribute("tipoFiltro", tipo);
        } else {
            model.addAttribute("ingressos", ingressoService.listarTodos());
        }
        return "ingressos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("eventos", eventoService.listarTodos());
        return "ingressos/form";
    }

    @GetMapping("/detalhe/{id}")
    public String detalhe(@PathVariable String id, Model model) {
        ingressoService.buscarPorId(id).ifPresent(ingresso -> {
            model.addAttribute("ingresso", ingresso);
            // Polimorfismo: imprimirIngresso() chama implementação da subclasse correta
            model.addAttribute("impressao", ingresso.imprimirIngresso());
            model.addAttribute("descricaoTipo", ingresso.descricaoTipo());
            model.addAttribute("valorCalculado", ingresso.calcularValor());
        });
        return "ingressos/detalhe";
    }

    @PostMapping("/emitir")
    public String emitir(@RequestParam String eventoId,
                         @RequestParam String nomeComprador,
                         @RequestParam String cpfComprador,
                         @RequestParam String tipo,
                         @RequestParam(required = false) String motivoMeia,
                         RedirectAttributes ra) {
        Ingresso ingresso = ingressoService.emitir(eventoId, nomeComprador, cpfComprador, tipo, motivoMeia);
        ra.addFlashAttribute("mensagem", "Ingresso emitido com sucesso! Valor: R$ "
            + String.format("%.2f", ingresso.calcularValor()));
        return "redirect:/ingressos/detalhe/" + ingresso.getId();
    }

    @PostMapping("/cancelar/{id}")
    public String cancelar(@PathVariable String id, RedirectAttributes ra) {
        ingressoService.cancelar(id);
        ra.addFlashAttribute("mensagem", "Ingresso cancelado.");
        return "redirect:/ingressos";
    }
}

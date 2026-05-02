package com.ingressos.controller;

import com.ingressos.model.Evento;
import com.ingressos.service.EventoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @GetMapping
    public String listar(Model model, @RequestParam(required = false) String busca) {
        if (busca != null && !busca.isBlank()) {
            model.addAttribute("eventos", eventoService.buscarPorNome(busca));
            model.addAttribute("busca", busca);
        } else {
            model.addAttribute("eventos", eventoService.listarTodos());
        }
        return "eventos/lista";
    }

    @GetMapping("/novo")
    public String novoForm(Model model) {
        model.addAttribute("evento", new Evento());
        return "eventos/form";
    }

    @GetMapping("/editar/{id}")
    public String editarForm(@PathVariable String id, Model model) {
        eventoService.buscarPorId(id).ifPresent(e -> model.addAttribute("evento", e));
        return "eventos/form";
    }

    @PostMapping("/salvar")
    public String salvar(@ModelAttribute Evento evento, RedirectAttributes ra) {
        if (evento.getId() != null && !evento.getId().isBlank()) {
            eventoService.atualizar(evento);
        } else {
            evento.setId(null);
            eventoService.inserir(evento);
        }
        ra.addFlashAttribute("mensagem", "Evento salvo com sucesso!");
        return "redirect:/eventos";
    }

    @PostMapping("/remover/{id}")
    public String remover(@PathVariable String id, RedirectAttributes ra) {
        eventoService.remover(id);
        ra.addFlashAttribute("mensagem", "Evento removido com sucesso!");
        return "redirect:/eventos";
    }
}

package com.greatlearning.GP3.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.greatlearning.GP3.model.Ticket;
import com.greatlearning.GP3.service.TicketService;

@Controller
@RequestMapping("/tickets")
public class TicketController {
    private final TicketService ticketService;

    @Autowired
    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @GetMapping("/")
    public String getAllTickets(Model model) {
        List<Ticket> tickets = ticketService.getAllTickets();
        model.addAttribute("tickets", tickets);
        return "index";
    }

    @GetMapping("/new")
    public String showCreateForm() {
        return "create";
    }

    @PostMapping("/create")
    public String createTicket(@ModelAttribute Ticket ticket) {
        ticketService.createTicket(ticket);
        System.out.println(ticket.getId());
        return "redirect:/tickets/";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "edit";
        } else {
            return "redirect:/tickets/";
        }
    }

    @PostMapping("/update")
    public String updateTicket(@ModelAttribute Ticket ticket) {
        ticketService.updateTicket(ticket);
        return "redirect:/tickets/";
    }

    @GetMapping("/delete/{id}")
    public String deleteTicket(@PathVariable Long id) {
        ticketService.deleteTicket(id);
        return "redirect:/tickets/";
    }

    @GetMapping("/view/{id}")
    public String viewTicket(@PathVariable Long id, Model model) {
        Optional<Ticket> ticket = ticketService.getTicketById(id);
        if (ticket.isPresent()) {
            model.addAttribute("ticket", ticket.get());
            return "view";
        } else {
            return "redirect:/tickets/";
        }
    }
}
